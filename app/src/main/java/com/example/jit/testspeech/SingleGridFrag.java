package com.example.jit.testspeech;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jtung on 10/22/2015.
 *
 */
public class SingleGridFrag extends Fragment {
    static final String TAG = "taggy";
    TextView txtSpeechInput;
    int[] arr = {0,0,0,0,0,0,0,0,0};
    int[] spaceFilled = {0,0,0,0,0,0,0,0,0};
    final Controller controller = new Controller();
    int i;
    boolean gameEnd;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RightyMarks.ttf");
        txtSpeechInput = (TextView) getActivity().findViewById(R.id.singlePText);
        txtSpeechInput.setTypeface(custom_font);


        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gridfragment_layout, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext()));

        txtSpeechInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int pos = ttt(s);

                if (gameEnd||pos>8) {
                } else {
                    if (spaceFilled[pos] == 1) {
                    } else {
                        arr[pos] = 1;
                        spaceFilled[pos] = 1;
                        i = controller.makeMove(pos);
                        Log.v(TAG, Integer.toString(i));

                        if (i < 0 || i > 8) {
                            if (i > 8) {
                                i = i - 20;
                                arr[i] = 2;
                                spaceFilled[i] = 1;
                                gameEnd = true;

                                final Animation animFadein;
                                animFadein = AnimationUtils.loadAnimation(getActivity(),
                                        R.anim.fade_in);

                                final Animation animFadeOut;
                                animFadeOut = AnimationUtils.loadAnimation(getActivity(),
                                        R.anim.fade_out);

                                FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.singlep_frag_container);
                                grid.startAnimation(animFadeOut);

                                final ImageView winlose = (ImageView) getActivity().findViewById(R.id.winlosemsg);
                                winlose.startAnimation(animFadein);
                                winlose.setBackgroundResource(R.drawable.lose);
                                final ImageButton p1cast = (ImageButton) getActivity().findViewById(R.id.btnSpeak);
                                p1cast.setVisibility(View.INVISIBLE);
                                final ImageButton replay = (ImageButton) getActivity().findViewById(R.id.btnReplay);
                                replay.startAnimation(animFadein);
                                replay.setImageResource(R.drawable.playagain);

                                replay.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.singlep_frag_container);
                                        grid.setAlpha(1);
                                        gameEnd = false;
                                        winlose.setBackgroundResource(0);
                                        for (int j=0; j <arr.length; j++){
                                            arr[j] = 0;
                                        }

                                        for (int j=0; j <spaceFilled.length; j++){
                                            spaceFilled[j] = 0;
                                        }

                                        p1cast.setVisibility(View.VISIBLE);
                                        replay.setVisibility(View.GONE);

                                       getActivity().recreate();
                                    }
                                });


                            } else if (i == -2) {
                                Animation animFadein;
                                animFadein = AnimationUtils.loadAnimation(getActivity(),
                                        R.anim.fade_in);

                                Animation animFadeOut;
                                animFadeOut = AnimationUtils.loadAnimation(getActivity(),
                                        R.anim.fade_out);
                                FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.singlep_frag_container);
                                grid.startAnimation(animFadeOut);
                                final ImageView winlose = (ImageView) getActivity().findViewById(R.id.winlosemsg);
                                winlose.startAnimation(animFadein);
                                winlose.setBackgroundResource(R.drawable.draw);
                                final ImageButton p1cast = (ImageButton) getActivity().findViewById(R.id.btnSpeak);
                                p1cast.setVisibility(View.INVISIBLE);
                                final ImageButton replay = (ImageButton) getActivity().findViewById(R.id.btnReplay);
                                replay.startAnimation(animFadein);
                                replay.setImageResource(R.drawable.playagain);

                                replay.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.singlep_frag_container);
                                        grid.setAlpha(1);
                                        gameEnd = false;
                                        winlose.setBackgroundResource(0);
                                        for (int j = 0; j < arr.length; j++) {
                                            arr[j] = 0;
                                        }

                                        for (int j = 0; j < spaceFilled.length; j++) {
                                            spaceFilled[j] = 0;
                                        }

                                        p1cast.setVisibility(View.VISIBLE);
                                        replay.setVisibility(View.GONE);

                                        getActivity().recreate();
                                    }
                                });

                            }

                        } else {
                            arr[i] = 2;
                            spaceFilled[i] = 1;
                        }
                    }
                }
                Fragment currentFragment = getFragmentManager().findFragmentByTag("SINGLEPEE");
                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.detach(currentFragment);
                fragTransaction.commit();

                Fragment newcurrentFragment = getFragmentManager().findFragmentByTag("SINGLEPEE");
                FragmentTransaction newfragTransaction = getFragmentManager().beginTransaction();
                newfragTransaction.attach(newcurrentFragment);
                newfragTransaction.commit();

            }
        });


        return view;
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return 9;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            int width = 0;
            int height = 0;
            WindowManager w = getActivity().getWindowManager();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Point size = new Point();
                w.getDefaultDisplay().getSize(size);
                width = size.x;
                height = size.y;
            } else {
                Display d = w.getDefaultDisplay();
                width = d.getWidth();
                height = d.getHeight();
            }

            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(width/4, width/4));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //imageView.setBackgroundResource(R.drawable.imageview_border);
            } else {
                imageView = (ImageView) convertView;
            }

            if(arr[position]==0){
                imageView.setImageResource(R.drawable.yellow_btn);
            }
            else if (arr[position]==1){
                imageView.setImageResource(R.drawable.cross_btn);
            }
            else if(arr[position]==2){
                imageView.setImageResource(R.drawable.circle_btn);
            }


            return imageView;
        }
    }

    private int ttt(CharSequence s){
        String mymove = s.toString().toLowerCase();
        int crossOrCircle = 0;
        int pos = 9;

        if(mymove.contains("cross")){
            crossOrCircle = 1;
        }
        else if(mymove.contains("circle")){
            crossOrCircle = 2;
        }

        if(mymove.contains("top left")){
            //arr[0] = crossOrCircle;
            pos = 0;
        }
        else if(mymove.contains("top center")){
            //arr[1] = crossOrCircle;
            pos = 1;
        }
        else if(mymove.contains("top right")){
            //arr[2] = crossOrCircle;
            pos = 2;
        }
        else if(mymove.contains("middle left")){
            //arr[3] = crossOrCircle;
            pos = 3;
        }
        else if(mymove.contains("middle center")){
            //arr[4] = crossOrCircle;
            pos = 4;
        }
        else if(mymove.contains("middle right")){
            //arr[5] = crossOrCircle;
            pos = 5;
        }
        else if(mymove.contains("bottom left")){
            //arr[6] = crossOrCircle;
            pos = 6;
        }
        else if(mymove.contains("bottom center")){
            //arr[7] = crossOrCircle;
            pos = 7;
        }
        else if(mymove.contains("bottom right")){
            //arr[8] = crossOrCircle;
            pos = 8;
        }

        Log.v("taggy","return val: " + String.valueOf(pos));
        return pos;
    }

}

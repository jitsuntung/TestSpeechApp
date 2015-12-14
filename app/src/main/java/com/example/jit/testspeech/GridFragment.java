package com.example.jit.testspeech;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;

/**
 * Created by jtung on 10/22/2015.
 *
 */
public class GridFragment  extends Fragment {
    TextView txtSpeechInput;
    Integer[] arr = {0,0,0,0,0,0,0,0,0};
    int[] filledIn = {0,0,0,0,0,0,0,0,0};
    static final String TAG = "taggy";
    int pone,ptwo;
    int turn = 1;
    Boolean started = false;
    Boolean gameOver = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RightyMarks.ttf");
        txtSpeechInput = (TextView) getActivity().findViewById(R.id.txtSpeechInput);
        txtSpeechInput.setTypeface(custom_font);
        ImageButton whocast = (ImageButton) getActivity().findViewById(R.id.whocast);



        // get the photo size and spacing
        // mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
        //mPhotoSpacing = getResources().getDimensionPixelSize(R.dimen.photo_spacing);

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gridfragment_layout, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext()));


        final Animation animFadein;
        animFadein = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fade_in);

        final Animation animFadeOut;
        animFadeOut = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fade_out);

        final Animation animFadeAway;
        animFadeAway = AnimationUtils.loadAnimation(getActivity(),
                R.anim.fade_away);


        if( (arr[0] == 1 && arr[1] == 1 && arr[2] == 1) || (arr[3]== 1 && arr[4]== 1 && arr[5]== 1)||
                (arr[6]== 1 && arr[7]== 1 && arr[8]== 1) || (arr[0]== 1 && arr[3]== 1 && arr[6]== 1) ||
                (arr[1]== 1 && arr[4]== 1 && arr[7]== 1) || (arr[2]== 1 && arr[5]== 1 && arr[8]== 1)  ||
                (arr[0]== 1 && arr[4]== 1 && arr[8]== 1) || (arr[2]== 1 && arr[4]== 1 && arr[6]== 1)
                ){
            if (pone == 1){
                FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.fragment_container1);
                grid.startAnimation(animFadeOut);
                ImageView winlose = (ImageView) getActivity().findViewById(R.id.winlosemsg);
                winlose.startAnimation(animFadein);
                winlose.setBackgroundResource(R.drawable.ponewins);
            }
            else if(ptwo == 1){
                FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.fragment_container1);
                grid.startAnimation(animFadeOut);
                ImageView winlose = (ImageView) getActivity().findViewById(R.id.winlosemsg);
                winlose.startAnimation(animFadein);
                winlose.setBackgroundResource(R.drawable.ptwowins);
            }
            gameOver = true;
        }
        else if((arr[0] == 2 && arr[1] == 2 && arr[2] == 2) || (arr[3]== 2 && arr[4]== 2 && arr[5]== 2)||
                    (arr[6]== 2 && arr[7]== 2 && arr[8]== 2) || (arr[0]== 2 && arr[3]== 2 && arr[6]== 2) ||
                    (arr[1]== 2 && arr[4]== 2 && arr[7]== 2) || (arr[2]== 2 && arr[5]== 2 && arr[8]== 2)  ||
                    (arr[0]== 2 && arr[4]== 2 && arr[8]== 2) || (arr[2]== 2 && arr[4]== 2 && arr[6]== 2))
        {

            if (pone == 2){
                FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.fragment_container1);
                grid.startAnimation(animFadeOut);
                ImageView winlose = (ImageView) getActivity().findViewById(R.id.winlosemsg);
                winlose.startAnimation(animFadein);
                winlose.setBackgroundResource(R.drawable.ponewins);
            }
            else if(ptwo == 2){
                FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.fragment_container1);
                grid.startAnimation(animFadeOut);
                ImageView winlose = (ImageView) getActivity().findViewById(R.id.winlosemsg);
                winlose.startAnimation(animFadein);
                winlose.setBackgroundResource(R.drawable.ptwowins);
            }
            gameOver = true;
        }
        else if((filledIn[0] == 1 && filledIn[1] == 1 && filledIn[2] == 1 && filledIn[3] == 1 && filledIn[4] == 1
                && filledIn[5] == 1 && filledIn[6] == 1 && filledIn[7] == 1 && filledIn[8] == 1)){

            FrameLayout grid = (FrameLayout) getActivity().findViewById(R.id.fragment_container1);
            grid.startAnimation(animFadeOut);
            ImageView winlose = (ImageView) getActivity().findViewById(R.id.winlosemsg);
            winlose.startAnimation(animFadein);
            winlose.setBackgroundResource(R.drawable.draw);
            gameOver = true;
        }


        if (turn == 1){
            whocast.startAnimation(animFadein);
            whocast.setImageResource(R.drawable.ponecast);
        }
        else if (turn == 2) {
            whocast.startAnimation(animFadein);
            whocast.setImageResource(R.drawable.ptwocast);
        }

        if(gameOver){
            whocast.setVisibility(View.INVISIBLE);
            whocast.startAnimation(animFadeAway);
            ImageButton replayBtn = (ImageButton) getActivity().findViewById(R.id.replayBtn);
            replayBtn.setImageResource(R.drawable.playagain);
            replayBtn.setAnimation(animFadein);

            replayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameOver = false;
                    turn = 1;
                    started = false;
                    for(int i = 0; i<arr.length; i++){
                        arr[i] = 0;
                    }
                    for(int i = 0; i<filledIn.length; i++){
                        filledIn[i] = 0;
                    }

                    txtSpeechInput.setText("");


                    getActivity().recreate();

                }
            });
        }

        else if(!gameOver){
            txtSpeechInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ttt(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Fragment currentFragment = getFragmentManager().findFragmentByTag("FRAGGYTAGGY");
                    FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                    fragTransaction.detach(currentFragment);
                    fragTransaction.attach(currentFragment);
                    fragTransaction.commit();
                }
            });
        }

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

            if(arr[position]==1){
                imageView.setImageResource(R.drawable.cross_btn);
            }
            else if(arr[position]==2){
                imageView.setImageResource(R.drawable.circle_btn);
            }
            else{
                imageView.setImageResource(R.drawable.yellow_btn);
            }

            return imageView;
        }
    }

    private void ttt(CharSequence s){
        String mymove = s.toString().toLowerCase();
        int crossOrCircle = 0;
        int pos;

        if(!started){
            if(mymove.contains("cross")){
                crossOrCircle = 1;
                pone = 1;
                ptwo = 2;
                started = true;
            }
            else if(mymove.contains("circle")){
                crossOrCircle = 2;
                pone = 2;
                ptwo = 1;
                started = true;
            }
        }

        if(started){
            if(turn == 1){
               crossOrCircle = pone;
            }
            else if(turn ==2){
               crossOrCircle = ptwo;
            }
        }

        if (started){
            if(mymove.contains("top left")){
                if(filledIn[0]!=1){
                    arr[0] = crossOrCircle;
                    pos = 0;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("top center")){
                if(filledIn[1]!=1) {
                    arr[1] = crossOrCircle;
                    pos = 1;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("top right")){
                if(filledIn[2]!=1) {
                    arr[2] = crossOrCircle;
                    pos = 2;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("middle left")){
                if(filledIn[3]!=1) {
                    arr[3] = crossOrCircle;
                    pos = 3;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("middle center")){
                if(filledIn[4]!=1) {
                    arr[4] = crossOrCircle;
                    pos = 4;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("middle right")){
                if(filledIn[5]!=1) {
                    arr[5] = crossOrCircle;
                    pos = 5;
                    filledIn[pos] = 1;
                    if (turn == 1) {
                        turn = 2;
                    } else if (turn == 2) {
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("bottom left")){
                if(filledIn[6]!=1) {
                    arr[6] = crossOrCircle;
                    pos = 6;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("bottom center")){
                if(filledIn[7]!=1) {
                    arr[7] = crossOrCircle;
                    pos = 7;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
            else if(mymove.contains("bottom right")){
                if(filledIn[8]!=1) {
                    arr[8] = crossOrCircle;
                    pos = 8;
                    filledIn[pos] = 1;
                    if(turn == 1){
                        turn = 2;
                    }
                    else if(turn ==2){
                        turn = 1;
                    }
                }
            }
        }

    }

}

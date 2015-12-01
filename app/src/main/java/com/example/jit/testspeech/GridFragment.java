package com.example.jit.testspeech;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
    Integer[] arr = {2,2,2,2,2,2,2,2,2};
    static final String TAG = "taggy";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        txtSpeechInput = (TextView) getActivity().findViewById(R.id.txtSpeechInput);

        // get the photo size and spacing
        // mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
        //mPhotoSpacing = getResources().getDimensionPixelSize(R.dimen.photo_spacing);

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gridfragment_layout, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                /*This following code replaces the fragment, not what we want to do.
                But put code here that you want to happen on selection of an item in the grid

                FragmentTwo newFragment = new FragmentTwo();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.fragment_place1, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();*/

            }
        });

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
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(251, 251));
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

        if(mymove.contains("cross")){
            crossOrCircle = 1;
        }
        else if(mymove.contains("circle")){
            crossOrCircle = 2;
        }

        if(mymove.contains("top left")){
            arr[0] = crossOrCircle;
        }
        else if(mymove.contains("top center")){
            arr[1] = crossOrCircle;
        }
        else if(mymove.contains("top right")){
            arr[2] = crossOrCircle;
        }
        else if(mymove.contains("middle left")){
            arr[3] = crossOrCircle;
        }
        else if(mymove.contains("middle center")){
            arr[4] = crossOrCircle;
        }
        else if(mymove.contains("middle right")){
            arr[5] = crossOrCircle;
        }
        else if(mymove.contains("bottom left")){
            arr[6] = crossOrCircle;
        }
        else if(mymove.contains("bottom center")){
            arr[7] = crossOrCircle;
        }
        else if(mymove.contains("bottom right")){
            arr[8] = crossOrCircle;
        }
    }

}

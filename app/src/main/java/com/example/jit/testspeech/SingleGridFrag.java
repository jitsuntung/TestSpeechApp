package com.example.jit.testspeech;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
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

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jtung on 10/22/2015.
 *
 */
public class SingleGridFrag extends Fragment {
    static final String TAG = "taggy";
    int[] arr = {0,0,0,0,0,0,0,0,0};
    final Controller controller = new Controller();
    int i;
    boolean gameEnd;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


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
                if (gameEnd){
                    Toast.makeText(getActivity(), "You Can't Click There!", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (arr[position] > 0) {
                        Toast.makeText(getActivity(), "You Can't Click There!", Toast.LENGTH_SHORT).show();
                    } else {
                        arr[position] = 1;
                        i = controller.makeMove(position);
                        Log.v(TAG, Integer.toString(i));

                        if (i < 0 || i > 8) {
                            if (i > 8) {
                                i = i - 20;
                                arr[i] = 2;
                                Toast.makeText(getActivity(), "You Lose!", Toast.LENGTH_SHORT).show();
                                gameEnd = true;
                            } else if (i == -2) {
                                Toast.makeText(getActivity(), "It's a Draw!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            arr[i] = 2;
                        }

                        Fragment currentFragment = getFragmentManager().findFragmentByTag("SINGLEPEE");
                        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                        fragTransaction.detach(currentFragment);
                        fragTransaction.attach(currentFragment);
                        fragTransaction.commit();
                    }
                }


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
}

package com.example.eltobgy.memorycardgame.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.eltobgy.memorycardgame.R;

/**
 * Created by Eltobgy on 16-Jul-18.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.abra, R.drawable.bellsprout,
            R.drawable.bullbasaur,R.drawable.caterpie,
            R.drawable.charmander,R.drawable.eevee,
            R.drawable.jigglypuff,R.drawable.mankey,
            R.drawable.meowth,R.drawable.pidgey,
            R.drawable.pikachu,R.drawable.psyduck,
            R.drawable.rattata,R.drawable.snorlax,
            R.drawable.squirtle,R.drawable.venonat,
            R.drawable.weedle,R.drawable.zubat


};
}

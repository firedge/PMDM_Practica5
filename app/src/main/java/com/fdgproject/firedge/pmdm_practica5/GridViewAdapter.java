package com.fdgproject.firedge.pmdm_practica5;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class GridViewAdapter extends CursorAdapter {

    public GridViewAdapter(Context context,Cursor data) {
        super(context, data, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater i = LayoutInflater.from(viewGroup.getContext());
        View v = i.inflate(R.layout.row_grid, viewGroup, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = null;

        if (view != null) {
            holder = new ViewHolder();
            holder.imageTitle = (TextView) view.findViewById(R.id.text);
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int image_path_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        Uri filePathUri = Uri.parse(cursor.getString(image_path_index));
        String fileName = filePathUri.getLastPathSegment().toString();
        holder.imageTitle.setText(fileName);
        Picasso.with(context).load(new File(cursor.getString(image_path_index))).into(holder.image);
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

}

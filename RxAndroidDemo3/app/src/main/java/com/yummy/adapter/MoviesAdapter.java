package com.yummy.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.first.viphu.rxandroiddemo.R;
import com.yummy.modal.MovieModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-6-25.
 */
public class MoviesAdapter extends BaseAdapter {
   private List<MovieModal>movieModals=new ArrayList<MovieModal>();
   private Context context;
    private  TextView name;
    private TextView descripte;
    private SimpleDraweeView img;
  public  MoviesAdapter(List<MovieModal>movieModals,Context context){
        this.context=context;
      this.movieModals=movieModals;
    }
    @Override
    public int getCount() {
        return movieModals.size();
    }

    @Override
    public Object getItem(int position) {
        return movieModals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.adapter_layout, null);
        }
         name = (TextView) convertView.findViewById(R.id.name);
         img = (SimpleDraweeView) convertView.findViewById(R.id.my_image_view);
         descripte = (TextView) convertView.findViewById(R.id.descripte);
        name.setText(movieModals.get(position).getTitle());
        descripte.setText(movieModals.get(position).getYear());
        Uri uri = Uri.parse(movieModals.get(position).getPosters().getThumbnail());
        img.setImageURI(uri);
        return convertView;
    }
}

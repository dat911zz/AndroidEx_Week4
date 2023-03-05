package com.ltdd.bt_t4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomGridAdapter extends BaseAdapter {

    private List<Country> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomGridAdapter(Context context, List<Country> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = layoutInflater.inflate(R.layout.country_item_layout, null);

            holder = new ViewHolder();
            holder.flagView = view.findViewById(R.id.imgFlag);
            holder.countryNameView = view.findViewById(R.id.txtName);
            holder.populationView = view.findViewById(R.id.txtPop);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Country country = this.list.get(i);
        holder.countryNameView.setText(country.getName());
        holder.populationView.setText(""+country.getPopulation());

        int imgId = this.getMipmapResIdByName(country.getFlag());

        holder.flagView.setImageResource(imgId);

        return view;
    }
    public int getMipmapResIdByName(String resName){
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        Log.i("CustomGridView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
    static class ViewHolder {
        ImageView flagView;
        TextView countryNameView;
        TextView populationView;
    }
}


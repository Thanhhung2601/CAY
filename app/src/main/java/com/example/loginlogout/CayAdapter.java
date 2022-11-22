package com.example.loginlogout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CayAdapter extends BaseAdapter {
    private Context context ;
    private int layout ;
    private List<Cay> cocktailList ;
    public CayAdapter(Context context , int layout , List<Cay> cocktailList ){
        this.context = context ;
        this.layout = layout ;
        this.cocktailList = cocktailList ;
    }

    @Override
    public int getCount() {
        return cocktailList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout , null);

        TextView tenkhoahoc = view.findViewById(R.id.tenkhoahoclist);
        TextView tenthuongoi = view.findViewById(R.id.tenthuonggoilist);
        TextView maula = view.findViewById(R.id.maulalist);
        ImageView img_order = view.findViewById(R.id.img_order);

        Cay cay = cocktailList.get(i);

        tenkhoahoc.setText(cay.getTenkhoahoc());
        tenthuongoi.setText(cay.getTenthuonggoi());
        maula.setText(cay.getMaula());
        img_order.setImageResource(cay.getImage());

        return view;
    }
}

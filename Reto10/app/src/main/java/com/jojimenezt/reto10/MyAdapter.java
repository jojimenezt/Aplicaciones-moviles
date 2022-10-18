package com.jojimenezt.reto10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jojimenezt.reto10.Model.LenguaNativa;

import java.util.ArrayList;

public class MyAdapter  extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<LenguaNativa> list;

    public MyAdapter(Context context, int layout, ArrayList<LenguaNativa> list)
    {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list == null)
            return 0;
        return this.list.size();
    }

    @Override
    public LenguaNativa getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        LayoutInflater inflater = LayoutInflater.from(this.context);
        v = inflater.inflate(R.layout.row_item, null);

        String nobmre = list.get(i).nombre_de_lengua;
        String des = list.get(i).descripci_n_de_lengua;
        String dep = list.get(i).departamento;
        String fami = list.get(i).familia_ling_stica;
        String habitantes = "Habitantes: " + String.valueOf(list.get(i).n_mero_de_habitantes);
        String hablantes = "Hablantes: " + String.valueOf(list.get(i).n_mero_de_hablantes);
        String vitalidad = list.get(i).vitalidad;

        ((TextView)v.findViewById(R.id.nombre)).setText(nobmre);
        ((TextView)v.findViewById(R.id.descrip)).setText(des);
        ((TextView)v.findViewById(R.id.departamento)).setText(dep);
        ((TextView)v.findViewById(R.id.familia)).setText(fami);
        ((TextView)v.findViewById(R.id.habitantes)).setText(habitantes);
        ((TextView)v.findViewById(R.id.hablantes)).setText(hablantes);
        ((TextView)v.findViewById(R.id.vitalidad)).setText(vitalidad);

        return v;
    }
}

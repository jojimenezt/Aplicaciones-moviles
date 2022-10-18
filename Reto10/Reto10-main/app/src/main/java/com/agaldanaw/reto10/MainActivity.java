package com.jojimenezt.reto10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jojimenezt.reto10.Model.LenguaNativa;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private ArrayList<LenguaNativa> _list;
    public static String BASE_DOMAIN = "https://www.datos.gov.co/resource/734h-gxtn.json";

    private ListView _listView;
    Spinner spinner;
    MyAdapter adapterList;

    EditText nombre;
    EditText departamento;
    EditText familia;
    EditText habitantes;
    EditText hablantes;

    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.vitalidad);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.clas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        _listView = (ListView)findViewById(R.id.listLenguas);
        TextView empylist = (TextView)findViewById(R.id.emptyList);
        _listView.setEmptyView(empylist);
        adapterList = new MyAdapter(this, android.R.layout.simple_list_item_1, _list);
        _listView.setAdapter(adapterList);

        nombre = (EditText)findViewById(R.id.nombre);
        departamento = (EditText)findViewById(R.id.departamento);
        familia = (EditText)findViewById(R.id.familia);
        habitantes = (EditText)findViewById(R.id.habitantes);
        hablantes = (EditText)findViewById(R.id.hablantes);
        total = (TextView) findViewById(R.id.total);

        ((Button)findViewById(R.id.filtrar)).setOnClickListener(new ButtonFilter(this));
        ((Button)findViewById(R.id.limpiar)).setOnClickListener(new ButtonClearFilter(this));

        _list = new ArrayList<LenguaNativa>();

        GetLenguaes();
    }

    public void GetLenguaes()
    {
        String ulr = GetUrl();
        queue = Volley.newRequestQueue(this);
        Context context = this;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET
                , ulr
                , null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                UpdateList(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ShowToast(error.toString(), context);
                    }
                }
        );

        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void fillListview() {
        MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, _list);
        _listView.setAdapter(adapter);
        total.setText("Lenguas: " + _list.size());
    }

    private String GetUrl() {
        StringBuilder sb = new StringBuilder(BASE_DOMAIN);
        Boolean isFilter =  !nombre.getText().toString().isEmpty() || !departamento.getText().toString().isEmpty()
                || !familia.getText().toString().isEmpty() || !habitantes.getText().toString().isEmpty()
                || !hablantes.getText().toString().isEmpty() || !spinner.getSelectedItem().toString().equals("Seleccione vitalidad");

        if(isFilter)
        {
            sb.append("?");
            boolean first = true;

            String filterNombre = "nombre_de_lengua=";
            String filterDepar = "departamento=";
            String filterFammi = "familia_ling_stica=";
            String filterHab = "n_mero_de_habitantes=";
            String filterHAbl = "n_mero_de_hablantes=";
            String filterVitalidad = "vitalidad=";

            if(!nombre.getText().toString().isEmpty())
            {
                sb.append(filterNombre);
                sb.append(nombre.getText().toString());
                first = false;
            }

            if(!departamento.getText().toString().isEmpty())
            {
                if(first)
                {
                    sb.append(filterDepar);
                    sb.append(departamento.getText().toString());
                    first = false;
                }
                else
                {
                    sb.append("&");
                    sb.append(filterDepar);
                    sb.append(departamento.getText().toString());
                }
            }

            if(!familia.getText().toString().isEmpty())
            {
                if(first)
                {
                    sb.append(filterFammi);
                    sb.append(familia.getText().toString());
                    first = false;
                }
                else
                {
                    sb.append("&");
                    sb.append(filterFammi);
                    sb.append(familia.getText().toString());
                }
            }

            if(!habitantes.getText().toString().isEmpty())
            {
                if(first)
                {
                    sb.append(filterHab);
                    sb.append(habitantes.getText().toString());
                    first = false;
                }
                else
                {
                    sb.append("&");
                    sb.append(filterHab);
                    sb.append(habitantes.getText().toString());
                }
            }

            if(!hablantes.getText().toString().isEmpty())
            {
                if(first)
                {
                    sb.append(filterHAbl);
                    sb.append(hablantes.getText().toString());
                    first = false;
                }
                else
                {
                    sb.append("&");
                    sb.append(filterHAbl);
                    sb.append(hablantes.getText().toString());
                }
            }

            if(!spinner.getSelectedItem().toString().equals("Seleccione vitalidad"))
            {
                if(first)
                {
                    sb.append(filterVitalidad);
                    sb.append(spinner.getSelectedItem().toString());
                }
                else
                {
                    sb.append("&");
                    sb.append(filterVitalidad);
                    sb.append(spinner.getSelectedItem().toString());
                }
            }

        }

        return sb.toString();
    }

    private void ShowToast(String message, Context context) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void UpdateList(JSONArray response) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _list = GetList(response);
                fillListview();
            }
        });

    }

    private ArrayList<LenguaNativa> GetList(JSONArray json)
    {
        try {
            ArrayList<LenguaNativa> list = new ArrayList<LenguaNativa>();
            for (int i = 0; i < json.length(); ++i)
            {
                JSONObject lengua = null;
                lengua = json.getJSONObject(i);

                String nombreJson = "Nombre: ";
                try {
                    nombreJson = nombreJson + lengua.getString("nombre_de_lengua");
                }
                catch (Exception e)
                {
                    nombreJson = nombreJson + "Sin dato";
                }

                String deswcrip = "Descripción: ";
                try {
                    deswcrip = deswcrip + lengua.getString("descripci_n_de_lengua");
                }
                catch (Exception e)
                {
                    deswcrip = deswcrip + "Sin dato";
                }

                String dep = "Departamento: ";
                try {
                    dep = dep + lengua.getString("departamento");
                }
                catch (Exception e)
                {
                    dep = dep + "Sin dato";
                }

                String famil = "Familia Ling_stica: ";
                try {
                    famil = famil + lengua.getString("familia_ling_stica");
                }
                catch (Exception e)
                {
                    famil = famil + "Sin dato";
                }

                int habi = 0;
                try {
                    habi = lengua.getInt("n_mero_de_habitantes");
                }
                catch (Exception e)
                {
                    habi = 0;
                }

                int habl = 0;
                try {
                    habl = lengua.getInt("n_mero_de_hablantes");
                }
                catch (Exception e)
                {
                    habl = 0;
                }

                String vit = "Vitalidad: ";
                try {
                    vit = vit + lengua.getString("vitalidad");
                }
                catch (Exception e)
                {
                    vit = vit + "Sin dato";
                }

                list.add(new LenguaNativa(nombreJson,
                        deswcrip,
                        dep,
                       famil,
                        habi,
                        habl,
                        vit
                        )
                );
            }
            return list;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return new ArrayList<>();
    }

    private class ButtonFilter implements View.OnClickListener {
        private boolean _clicked = false;
        private Context _context;
        public ButtonFilter(Context context)
        {
            _context = context;
        }
        public void onClick(View view) {
            try {
                if(!_clicked)
                {
                    _clicked = true;
                    GetLenguaes();
                }
            }catch(Exception e) { // ignore
            }
            finally {
                _clicked = false;
            }
        }
    }

    private class ButtonClearFilter implements View.OnClickListener {
        private boolean _clicked = false;
        private Context _context;
        public ButtonClearFilter(Context context)
        {
            _context = context;
        }
        public void onClick(View view) {
            try {
                if(!_clicked)
                {
                    _clicked = true;
                    nombre.setText("");
                    departamento.setText("");
                    familia.setText("");
                    habitantes.setText("");
                    hablantes.setText("");
                    spinner.setSelection(0);
                    GetLenguaes();
                }
            }catch(Exception e) { // ignore
            }
            finally {
                _clicked = false;
            }
        }
    }
}
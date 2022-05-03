package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private Mountain[] mountains;
    private WebView webView;
    ArrayAdapter<Mountain> adapter;
    private ListView listView;

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonFile(this, this).execute(JSON_FILE);

        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        try {


            adapter = new ArrayAdapter<Mountain>(MainActivity.this,R.layout.item);
            ListView listView = findViewById(R.id.list_view);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });




        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity ==>", json);

        Gson gson = new Gson();
        mountains = gson.fromJson(json,Mountain[].class);
        adapter = new ArrayAdapter<Mountain>(MainActivity.this,R.layout.item,mountains);



        for (int i = 0; i < mountains.length; i++) {
            Log.d("MainActivity ==>","Hittade ett berg" +  mountains[i].getName() + " " + mountains[i].getAuxdata().getWiki());


        }
        




    }





}

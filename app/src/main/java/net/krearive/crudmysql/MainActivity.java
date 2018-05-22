package net.krearive.crudmysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.krearive.crudmysql.model.NoteModel;
import net.krearive.crudmysql.model.ResponseTampilModel;
import net.krearive.crudmysql.retrofit.ApiConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<NoteModel> listcatatan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahCatatanActivity.class));
            }
        });

        recycler = findViewById(R.id.recycler);

        //1 model data
        //data dummy

//        //fori
//        for (int i = 0; i < 10; i++) {
//            NoteModel note1 = new NoteModel("1", "10000", " makan bakso", "27-7-2017");
//            listcatatan.add(note1);
//        }
//
//        NoteModel note2 = new NoteModel();
//        note2.setId("2");
//        note2.setKeterangan("minum jus");
//        note2.setPengeluaran("4000");
//        note2.setTanggal("30-3-2030");
        ambilDataCatatan();

        //2 adapter
        NoteAdapter adapter = new NoteAdapter(listcatatan, MainActivity.this);
        recycler.setAdapter(adapter);

        //3 layout manager
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void ambilDataCatatan() {
        Call<ResponseTampilModel> call = ApiConfig.getApiServices().tampilDataCatatan();
        call.enqueue(new Callback<ResponseTampilModel>() {
            @Override
            public void onResponse(Call<ResponseTampilModel> call, Response<ResponseTampilModel> response) {
                if (response.isSuccessful()){
                    if (response.body().getResult().equals("true")){
                        listcatatan = response.body().getData();

                        NoteAdapter adapter = new NoteAdapter(listcatatan, MainActivity.this);
                        recycler.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, ""+ response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Get Data Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTampilModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Get data Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ambilDataCatatan();
    }
}

package net.krearive.crudmysql;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.krearive.crudmysql.model.ResponseModel;
import net.krearive.crudmysql.retrofit.ApiConfig;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCatatanActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText edtPengeluaran;
    private EditText edtCatatan;
    private TextView edtTanggal;
    private Button btnSimpan;
    private String dataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_catatan);

        dataId = getIntent().getStringExtra("DATA_ID");
        String dataPengeluaran = getIntent().getStringExtra("DATA_PENGELUARAN");
        String dataKeterangan = getIntent().getStringExtra("DATA_KETERANGAN");
        String dataTanggal = getIntent().getStringExtra("DATA_TANGGAL");

        initView();

        edtPengeluaran.setText(dataPengeluaran);
        edtCatatan.setText(dataKeterangan);
        edtTanggal.setText(dataTanggal);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCatatan();
            }
        });

        edtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

    }


    private void editCatatan() {
        Call<ResponseModel> call = ApiConfig.getApiServices().editDataCatatan(
                dataId,
                edtPengeluaran.getText().toString(),
                edtCatatan.getText().toString(),
                edtTanggal.getText().toString());
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()){
                    if (response.body().getResult().equals("true")){
                        Toast.makeText(EditCatatanActivity.this, ""+ response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditCatatanActivity.this, ""+ response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditCatatanActivity.this, "Update Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EditCatatanActivity.this, "Update Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        edtPengeluaran = (EditText) findViewById(R.id.edtPengeluaran);
        edtCatatan = (EditText) findViewById(R.id.edtCatatan);
        edtTanggal = (TextView) findViewById(R.id.edtTanggal);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
    }

    private void showDatePicker() {
        int tahun = Calendar.getInstance().get(Calendar.YEAR);
        int bulan = Calendar.getInstance().get(Calendar.MONTH);
        int tanggal = Calendar.getInstance().get(Calendar.DATE);
        DatePickerDialog datePicker = new DatePickerDialog(EditCatatanActivity.this, EditCatatanActivity.this, tahun, bulan, tanggal);
        datePicker.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int tahun, int bulan, int tanggal) {
        edtTanggal.setText(tahun+"-"+bulan+"-"+tanggal);
    }
}

package net.krearive.crudmysql;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import net.krearive.crudmysql.model.NoteModel;
import net.krearive.crudmysql.model.ResponseModel;
import net.krearive.crudmysql.retrofit.ApiConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by idn on 5/22/2018.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private ArrayList<NoteModel> list;
    private Context context;

    //constructor
    public NoteAdapter(ArrayList<NoteModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //1 sambungkan ke note_item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.note_item, parent, false);

        return new MyViewHolder(itemView);
    }

    //3 set data
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvPengeluaran.setText(list.get(position).getPengeluaran());
        holder.tvKeterangan.setText(list.get(position).getKeterangan());
        holder.tvTanggal.setText(list.get(position).getTanggal());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context, EditCatatanActivity.class);
                //kirim data
                pindah.putExtra("DATA_ID", list.get(position).getId());
                pindah.putExtra("DATA_PENGELUARAN", list.get(position).getPengeluaran());
                pindah.putExtra("DATA_KETERANGAN", list.get(position).getKeterangan());
                pindah.putExtra("DATA_TANGGAL", list.get(position).getTanggal());
                context.startActivity(pindah);
            }
        });
        
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseModel> call = ApiConfig.getApiServices().hapusDataCatatan(
                        list.get(position).getId());
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful()){
                            if (response.body().getResult().equals("true")){
                                context.startActivity(new Intent(context, MainActivity.class));
//                                notifyDataSetChanged();
                                Toast.makeText(context, ""+ response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, ""+ response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Delete Not Success", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(context, "Delete Failure", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void hapusCatatan() {

    }

    //4 jumlah data
    @Override
    public int getItemCount() {
        return list.size();
    }

    //2 inisialisasi widget di dalam item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPengeluaran, tvKeterangan, tvTanggal;
        ImageButton btnEdit, btnHapus;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvPengeluaran = itemView.findViewById(R.id.tv_pengeluaran);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnHapus = itemView.findViewById(R.id.btn_delete);
        }
    }

    //extend kelas ini ke RecyclerView.Adapter
}

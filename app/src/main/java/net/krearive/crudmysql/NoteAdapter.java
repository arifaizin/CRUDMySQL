package net.krearive.crudmysql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvPengeluaran.setText(list.get(position).getPengeluaran());
        holder.tvKeterangan.setText(list.get(position).getKeterangan());
        holder.tvTanggal.setText(list.get(position).getTanggal());
    }

    //4 jumlah data
    @Override
    public int getItemCount() {
        return list.size();
    }

    //2 inisialisasi widget di dalam item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPengeluaran, tvKeterangan, tvTanggal;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvPengeluaran = itemView.findViewById(R.id.tv_pengeluaran);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }

    //extend kelas ini ke RecyclerView.Adapter
}

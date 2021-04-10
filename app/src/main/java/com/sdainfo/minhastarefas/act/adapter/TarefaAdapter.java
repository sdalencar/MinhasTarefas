package com.sdainfo.minhastarefas.act.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdainfo.minhastarefas.R;
import com.sdainfo.minhastarefas.act.model.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHokder> {

    private List<Tarefa>  tarefaList;


    public TarefaAdapter(List<Tarefa> lista) {
        this.tarefaList = lista;
    }

    @NonNull
    @Override
    public MyViewHokder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tarefa_adapter, parent, false);

        return new MyViewHokder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHokder holder, int position) {
        Tarefa tarefa = tarefaList.get(position);
        holder.textView.setText(tarefa.getNome_tarefa());
    }

    @Override
    public int getItemCount() {
        return this.tarefaList.size();
    }

    public class MyViewHokder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHokder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_listar_tarefa);
        }
    }

}

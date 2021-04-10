package com.sdainfo.minhastarefas.act.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdainfo.minhastarefas.R;
import com.sdainfo.minhastarefas.act.adapter.TarefaAdapter;
import com.sdainfo.minhastarefas.act.helper.DbHelper;
import com.sdainfo.minhastarefas.act.helper.RecyclerItemClickListener;
import com.sdainfo.minhastarefas.act.helper.RecyclerItemClickListener.OnItemClickListener;
import com.sdainfo.minhastarefas.act.helper.TarefaDAO;
import com.sdainfo.minhastarefas.act.model.Tarefa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewLista);


        //adiciona clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onItemClick(View view, int position) {
                      //recupera o objeto para editar
                        Tarefa objetoTarefa = listaTarefas.get(position);

                        // abre a intente com o objeto para editar
                        Intent intent = new Intent(MainActivity.this, AdicionaTarefaActivity.class);
                        intent.putExtra("objeto", objetoTarefa );
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        tarefaSelecionada = listaTarefas.get(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Atenção:");
                        builder.setMessage("Deseja excluir a tarefa : " + tarefaSelecionada.getNome_tarefa() +" ?");

                        builder.setNegativeButton("Não",null);

                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TarefaDAO dao = new TarefaDAO(getApplicationContext());
                                if(dao.deletar(tarefaSelecionada)){
                                    carregarLista();
                                    Toast.makeText(getApplicationContext(), " " + tarefaSelecionada.getNome_tarefa() + " deletada...", Toast.LENGTH_LONG ).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), "Erro ao deletar  " + tarefaSelecionada.getNome_tarefa() + ".", Toast.LENGTH_LONG ).show();
                                }
                            }
                        });

                        builder.create();
                        builder.show();
                    }
                }

        ));





        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), AdicionaTarefaActivity.class));
            }
        });
    }


    public void carregarLista(){
        //listar as tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefas = tarefaDAO.listar();

        //exibir a lista


        //configurar um adapter
        tarefaAdapter = new TarefaAdapter(listaTarefas);



        //configurar o reclycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);


    }

    @Override
    protected void onStart() {
        carregarLista();
        super.onStart();
    }
}
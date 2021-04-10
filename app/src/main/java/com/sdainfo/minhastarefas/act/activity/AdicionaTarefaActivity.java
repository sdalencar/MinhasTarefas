package com.sdainfo.minhastarefas.act.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sdainfo.minhastarefas.R;
import com.sdainfo.minhastarefas.act.helper.TarefaDAO;
import com.sdainfo.minhastarefas.act.model.Tarefa;


public class AdicionaTarefaActivity extends AppCompatActivity {
    private EditText et_tarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_tarefa);
        et_tarefa = findViewById(R.id.et_digite_tarefa);

        // pegando o objeto para edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("objeto");
        //colocando o objeto pra edição
        if (tarefaAtual != null) {
            et_tarefa.setText(tarefaAtual.getNome_tarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_salvar:
                TarefaDAO dao = new TarefaDAO(getApplicationContext());
                if (tarefaAtual != null) {//editar
                    String nomeTarefa = et_tarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setId(tarefaAtual.getId());
                        tarefa.setNome_tarefa(nomeTarefa);
                        if (dao.atualizar(tarefa)) {
                            msg("Tarefa atualizada.");
                        } else {
                            msg("Erro ao atualizar a tarefa.");
                        }
                    }
                }else {
                    String nomeTarefa = et_tarefa.getText().toString();
                    if(!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNome_tarefa(nomeTarefa);
                        if(dao.salvar(tarefa)){
                            msg("Tarefa salva com sucesso.");
                        }else {
                            msg("Erro ao salva a tarefa");
                        }
                    }
                }
                finish();
                break;

            case R.id.action_voltar:
                msg("Vontando");
                this.finish();
                break;
            case R.id.action_sair:
                msg("Tchau");
                finishAffinity();
                break;

        }
        return super.

                onOptionsItemSelected(item);

    }

    private void msg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
package com.sdainfo.minhastarefas.act.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sdainfo.minhastarefas.act.model.Tarefa;

import java.util.ArrayList;
import java.util.List;



public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;
    private Context ctx;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper( context );
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
        this.ctx = context;
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNome_tarefa() );

        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv );

        }catch (Exception e){

            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNome_tarefa() );

        try {
            String[] Where = {String.valueOf(tarefa.getId())};
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", Where );
        }catch (Exception e){

            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = {String.valueOf(tarefa.getId())};
            escreve.delete(DbHelper.TABELA_TAREFAS, "id=?", args );

        }catch (Exception e){

            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ORDER by id desc ;";
        Cursor c = le.rawQuery(sql, null);

        while ( c.moveToNext() ){

            Tarefa tarefa = new Tarefa();

            long id = c.getLong( c.getColumnIndex("id") );
            String nomeTarefa = c.getString( c.getColumnIndex("nome") );

            tarefa.setId( id );
            tarefa.setNome_tarefa( nomeTarefa );

            tarefas.add( tarefa );

        }

        return tarefas;

    }


}

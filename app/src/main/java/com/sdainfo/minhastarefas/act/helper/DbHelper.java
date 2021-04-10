package com.sdainfo.minhastarefas.act.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";
    private final Context ctx;

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL ); ";

        try {
            db.execSQL(sql);
            msg(ctx, "INFO DB : Sucesso ao criar tabela");
        } catch (Exception e) {
            msg(ctx, "INFO DB : Erro ao criar tabela" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + " ;";

        try {
            db.execSQL(sql);
            onCreate(db);
            msg(ctx, "INFO DB : Sucesso ao atualizar App");
        } catch (Exception e) {
            msg(ctx, "INFO DB : Erro ao atualizar App" + e.getMessage());
        }

    }

    private void msg(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

}

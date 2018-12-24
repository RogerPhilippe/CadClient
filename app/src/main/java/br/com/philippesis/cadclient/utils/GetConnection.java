package br.com.philippesis.cadclient.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.philippesis.cadclient.database.DadosOpenHelper;

public class GetConnection {

    private SQLiteDatabase connection;

    private DadosOpenHelper dadosOpenHelper;

    public SQLiteDatabase createConnection(Context context) {

        try {

            dadosOpenHelper = new DadosOpenHelper(context);

            connection = dadosOpenHelper.getWritableDatabase();

            return connection;

        } catch (Exception e) {
            Log.v("ErrorConnection", e.toString());
            return null;
        }

    }

}

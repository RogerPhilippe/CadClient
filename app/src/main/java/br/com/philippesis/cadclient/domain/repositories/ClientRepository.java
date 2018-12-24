package br.com.philippesis.cadclient.domain.repositories;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.philippesis.cadclient.domain.entities.Client;

public class ClientRepository {

    private SQLiteDatabase mConnection;

    public ClientRepository(SQLiteDatabase connection) {
        this.mConnection = connection;
    }

    public void save(Client cliente) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", cliente.getmName());
        contentValues.put("ADDRESS", cliente.getmAddress());
        contentValues.put("EMAIL", cliente.getmEmail());
        contentValues.put("PHONE", cliente.getmPhone());

        mConnection.insertOrThrow("CLIENTE", null, contentValues);

    }

    public void delete(int id) {

        String[] whereArgs = {String.valueOf(id)};

        mConnection.delete("CLIENTE", "ID = ?", whereArgs);

    }

    public void update(Client cliente) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", cliente.getmName());
        contentValues.put("ADDRESS", cliente.getmAddress());
        contentValues.put("EMAIL", cliente.getmEmail());
        contentValues.put("PHONE", cliente.getmPhone());

        String[] whereArgs = {String.valueOf(cliente.getmId())};

        mConnection.update("CLIENTE", contentValues, "ID = ?", whereArgs);

    }

    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM CLIENTE ");

        Cursor resultSet = mConnection.rawQuery(sql.toString(), null);

        if(resultSet.getCount() > 0) {
            resultSet.moveToFirst();

            do {

                Client client = new Client();

                client.setmId(resultSet.getInt(resultSet.getColumnIndex("ID")));
                client.setmName(resultSet.getString(resultSet.getColumnIndex("NAME")));
                client.setmEmail(resultSet.getString(resultSet.getColumnIndex("EMAIL")));
                client.setmPhone(resultSet.getString(resultSet.getColumnIndex("PHONE")));

                clients.add(client);

            } while (resultSet.moveToNext());

        } else {
            clients = null;
        }

        return clients;
    }

    public Client findById(int id) {

        Client client = new Client();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM CLIENTE ");
        sql.append("WHERE ID = ?");

        String[] whereArgs = {String.valueOf(id)};

        Cursor resultSet = mConnection.rawQuery(sql.toString(), whereArgs);

        resultSet.moveToFirst();

        if(resultSet.getCount() > 0) {

            client.setmId(resultSet.getInt(resultSet.getColumnIndex("ID")));
            client.setmName(resultSet.getString(resultSet.getColumnIndex("NAME")));
            client.setmEmail(resultSet.getString(resultSet.getColumnIndex("EMAIL")));
            client.setmPhone(resultSet.getString(resultSet.getColumnIndex("PHONE")));

        } else {
            client = null;
        }

        return client;
    }

}

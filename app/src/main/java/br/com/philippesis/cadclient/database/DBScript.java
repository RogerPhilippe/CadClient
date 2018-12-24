package br.com.philippesis.cadclient.database;

public class DBScript {

    public static String getCreateTableCliente() {

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS CLIENTE ( ");
        sql.append("    ID      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    NAME    VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("    ADDRESS VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("    EMAIL   VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("    PHONE   VARCHAR (250) NOT NULL DEFAULT ('') ");
        sql.append(");");

        return sql.toString();

    }

}

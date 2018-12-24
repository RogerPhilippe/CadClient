package br.com.philippesis.cadclient;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import br.com.philippesis.cadclient.database.DadosOpenHelper;

public class MainActivity extends BaseActivity {

    private RecyclerView mainLstDados;

    private FloatingActionButton fab;

    private SQLiteDatabase connection;

    private DadosOpenHelper dadosOpenHelper;

    private ConstraintLayout layoutContentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.idMainToolbar);
        setSupportActionBar(toolbar);

        layoutContentMain = (ConstraintLayout) findViewById(R.id.idLayoutContantMain);

        createConnection();

        mainLstDados = (RecyclerView) findViewById(R.id.idLstDados);

        fab = (FloatingActionButton) findViewById(R.id.idBtnADD);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, CadClienteActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createConnection() {
        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            connection = dadosOpenHelper.getWritableDatabase();

            genericSnackbar(layoutContentMain, getString(R.string.connect_success), Snackbar.LENGTH_LONG, "OK");

        } catch (Exception e) {
            Log.v("ErrorConnection", e.toString());
            genericAlert(getString(R.string.title_error), getString(R.string.error_db_connection), "OK");
        }
    }

}

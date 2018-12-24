package br.com.philippesis.cadclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends BaseActivity {

    private RecyclerView mainLstDados;

    private FloatingActionButton fab;

    private ConstraintLayout layoutContentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.idMainToolbar);
        setSupportActionBar(toolbar);

        layoutContentMain = (ConstraintLayout) findViewById(R.id.idLayoutContantMain);

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



}

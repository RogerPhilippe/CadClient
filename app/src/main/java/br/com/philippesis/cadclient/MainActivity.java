package br.com.philippesis.cadclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import br.com.philippesis.cadclient.domain.entities.Client;
import br.com.philippesis.cadclient.domain.repositories.ClientRepository;
import br.com.philippesis.cadclient.utils.GetConnection;

public class MainActivity extends BaseActivity {

    private RecyclerView mainLstDados;

    private FloatingActionButton fab;

    private ConstraintLayout layoutContentMain;

    private ClientAdapter clientAdapter;

    private ClientRepository clientRepository;

    private List<Client> mDataClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.idMainToolbar);
        setSupportActionBar(toolbar);

        layoutContentMain = (ConstraintLayout) findViewById(R.id.idLayoutContantMain);

        // Recycleview
        mainLstDados = (RecyclerView) findViewById(R.id.idLstDados);
        mainLstDados.setHasFixedSize(true);

        loadRecycleview();

        // Botão flutuante (adicionar)
        fab = (FloatingActionButton) findViewById(R.id.idBtnADD);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CadClienteActivity.class);
                // Chama view
                //startActivity(intent);

                // Chama view, aguarda retorno
                startActivityForResult(intent, 1);

            }
        });
    }

    // Carrega os no Recycleview com dados vindos do banco de dados
    private void loadRecycleview() {

        // Objeto classe genérica para criar conexões
        GetConnection getConnection = new GetConnection();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mainLstDados.setLayoutManager(linearLayoutManager);

        // Cria objeto da classe repositório do cliente
        clientRepository = new ClientRepository(getConnection.createConnection(this));

        // Inserir todos os dados vindos do banco na lista mDataClients
        mDataClients = clientRepository.findAll();

        clientAdapter = new ClientAdapter(mDataClients);

        // Seta adapter (dados e layout) no Recycleview
        mainLstDados.setAdapter(clientAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            loadRecycleview();
        }

    }
}

package br.com.philippesis.cadclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.philippesis.cadclient.domain.entities.Client;
import br.com.philippesis.cadclient.domain.repositories.ClientRepository;
import br.com.philippesis.cadclient.utils.GetConnection;

public class CadClienteActivity extends BaseActivity {

    private EditText edtName;
    private EditText edtAddress;
    private EditText edtEmail;
    private EditText edtPhone;
    private String name = null;
    private String address = null;
    private String email = null;
    private String phone = null;
    private boolean isClient;

    private Client client;
    private ClientRepository clientRepository;

    private GetConnection getConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = (EditText) findViewById(R.id.idedtName);
        edtAddress = (EditText) findViewById(R.id.idedtAddress);
        edtEmail = (EditText) findViewById(R.id.idedtEmail);
        edtPhone = (EditText) findViewById(R.id.idedtPhone);

        isClient = getParams();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Verifica se trata-se de um cadastro novo ou atuaização de cadastro existente para exibir no menu "Cancelar" ou "Deletar".
        if(isClient) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_update_main, menu);
        } else {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.idActionSalvar:
                if(save()) {
                    genericAlert(this, "Aviso", "Salvo com Sucesso!", "OK");
                    limpaCampos();
                    edtName.requestFocus();
                } else {
                    genericAlert(this, "", "Erro ao tentar salvar.\nVerifique se preencheu corretamente todos os campos.", "OK");
                }
                break;
            case R.id.idActionCancelar:
                setMsg(this, "Nada alterado!");
                finish();
                break;
            case R.id.idActionDelete:
                deletar();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void limpaCampos() {
        edtName.setText(null);
        edtAddress.setText(null);
        edtEmail.setText(null);
        edtPhone.setText(null);
    }

    private boolean save() {

        boolean result = false;
        boolean camposNulos;

        name = edtName.getText().toString();
        address = edtAddress.getText().toString();
        email = edtEmail.getText().toString();
        phone = edtPhone.getText().toString();

        if(camposNulos = isEmpityValue(name)) {
            edtName.requestFocus();
        } else if(camposNulos = isEmpityValue(address)) {
            edtAddress.requestFocus();
        } else if(camposNulos = !isValidEmail(email)) {
            edtEmail.requestFocus();
        } else if(camposNulos = isEmpityValue(phone)) {
            edtPhone.requestFocus();
        }

        if(!camposNulos) {

            getConnection = new GetConnection();

            clientRepository = new ClientRepository(getConnection.createConnection(this));

            if(!isClient) {

                // Cliente novo
                client = new Client();
                client.setmName(name);
                client.setmAddress(address);
                client.setmEmail(email);
                client.setmPhone(phone);

                clientRepository.save(client);

            } else {

                // Atualizar cliente
                client.setmName(name);
                client.setmAddress(address);
                client.setmEmail(email);
                client.setmPhone(phone);
                clientRepository.update(client);

            }

            result = true;

        }

        return result;
    }

    // Pegar parâmetros vindos do Recycleview da MainActivity
    private boolean getParams() {

        boolean isParams = false;

        Bundle bundle = getIntent().getExtras();

        if(bundle != null && bundle.containsKey("client")) {

            client = (Client) bundle.getSerializable("client");

            edtName.setText(client.getmName());
            edtAddress.setText(client.getmAddress());
            edtEmail.setText(client.getmEmail());
            edtPhone.setText(client.getmPhone());

            isParams = true;

        }

        return isParams;
    }

    private void deletar() {
        AlertDialog.Builder alertDialogBulder = new AlertDialog.Builder(CadClienteActivity.this);
        alertDialogBulder.setMessage("Deseja realmente excluir o cliente " + client.getmName() + "?").setCancelable(false)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Excluir registro selecionado
                        getConnection = new GetConnection();
                        clientRepository = new ClientRepository(getConnection.createConnection(CadClienteActivity.this));
                        clientRepository.delete(client.getmId());
                        setMsg(CadClienteActivity.this, "Cliente "+client.getmName()+" excluído com sucesso!");
                        finish();
                    }
                })
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BaseActivity.setMsg(CadClienteActivity.this, "Nada alterado!");
                    }
                });

        AlertDialog alert = alertDialogBulder.create();
        alert.setTitle("Confirmação");
        alert.show();
    }

}

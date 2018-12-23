package br.com.philippesis.cadclient;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class CadClienteActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtAddress;
    private EditText edtEmail;
    private EditText edtPhone;
    private String name = null;
    private String address = null;
    private String email = null;
    private String phone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtName = (EditText) findViewById(R.id.idedtName);
        edtAddress = (EditText) findViewById(R.id.idedtAddress);
        edtEmail = (EditText) findViewById(R.id.idedtEmail);
        edtPhone = (EditText) findViewById(R.id.idedtPhone);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.idActionSalvar:
                if(isValidValues()) {
                    genericAlert(getString(R.string.warning), (getString(R.string.cliente) + getGap() + name + getGap() + getString(R.string.save_sucess) ), "OK");
                    limpaCampos();
                    edtName.requestFocus();
                } else {
                    genericAlert(getString(R.string.error), getString(R.string.error_empity_fields), "OK");
                }
                break;
            case R.id.idActionCancelar:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Validação dos campos
    private boolean isValidValues() {

        name = edtName.getText().toString();
        address = edtAddress.getText().toString();
        email = edtEmail.getText().toString();
        phone = edtPhone.getText().toString();

        boolean res = false;

        if(res = isEmpityValue(name)) {
            edtName.requestFocus();
        } else if(res = isEmpityValue(address)) {
            edtAddress.requestFocus();
        } else if(res = !isValidEmail(email)) {
            edtEmail.requestFocus();
        } else if(res = isEmpityValue(phone)) {
            edtPhone.requestFocus();
        }

        // Precisa ser negado, póis caso entre em um dos ifs, será true.
        return !res;
    }

    private void limpaCampos() {
        edtName.setText(null);
        edtAddress.setText(null);
        edtEmail.setText(null);
        edtPhone.setText(null);
    }

    // Verifica se valor está vazio
    private boolean isEmpityValue(String valor) {

        return TextUtils.isEmpty(valor) || valor.trim().isEmpty();

    }

    // Valida email, se está vazio ou se está digitado corretamente
    private boolean isValidEmail(String email) {

        return !isEmpityValue(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private void setMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private String getGap() {
        return " ";
    }

    private void genericAlert(String title, String msg, String btn) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton(btn, null);
        dlg.show();
    }

}

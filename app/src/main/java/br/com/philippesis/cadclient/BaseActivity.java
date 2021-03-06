package br.com.philippesis.cadclient;

import android.app.AlertDialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    // Dialog genérico de um botão
    protected static void genericAlert(Context context, String title, String msg, String btn) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton(btn, null);
        dlg.show();
    }

    // Toast genérico
    protected static void setMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    // Retorna um espaço em branco
    protected String getGap() {
        return " ";
    }

    // Verifica se valor está vazio
    protected boolean isEmpityValue(String valor) {

        return TextUtils.isEmpty(valor) || valor.trim().isEmpty();

    }

    // Valida email, se está vazio ou se está digitado corretamente
    protected boolean isValidEmail(String email) {

        return !isEmpityValue(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    // Snackbar genérico
    protected static void genericSnackbar(ConstraintLayout layoutContent, String msg, int length_long, String btn) {

        Snackbar.make(layoutContent, msg, length_long)
                .setAction(btn, null).show();

    }


}

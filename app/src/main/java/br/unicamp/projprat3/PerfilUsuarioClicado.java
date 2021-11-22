package br.unicamp.projprat3;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilUsuarioClicado extends AppCompatActivity {

    TextView tvNome;
    EditText edtDescricao, edtArea, edtCidade, edtCPF, edtEmail;
    Button btnChamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario_clicado);

        tvNome = (TextView) findViewById(R.id.tvNome);

        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        edtArea = (EditText) findViewById(R.id.edtArea);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        btnChamar = (Button) findViewById(R.id.btnChamar);

        Intent in = getIntent();
        Usuario usClicado = (Usuario) in.getSerializableExtra("usuarioSerializable");

        tvNome.setText  (usClicado.getNome());

        edtCidade.setText(usClicado.getCidade());
        edtArea.setText(usClicado.getArea());
        edtCPF.setText(usClicado.getCpf());
        edtEmail.setText(usClicado.getEmail());
        edtDescricao.setText(usClicado.getDescricao());

        btnChamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comporEmail(new String[] {usClicado.getEmail()});
            }
        });


    }

    public void comporEmail(String[] endereco) {
        String[] recipients = endereco;

        //String subject = mEditTextSubject.getText().toString();
        //String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Chamada para entrevista");
        //intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

}
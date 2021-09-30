package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class PerfilUsuario extends AppCompatActivity {

    EditText edtNome, edtCpf, edtCidade, edtArea, edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        edtNome   = (EditText) findViewById(R.id.edtNome);
        edtCpf    = (EditText) findViewById(R.id.edtCpf);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtArea   = (EditText) findViewById(R.id.edtArea);
        edtEmail  = (EditText) findViewById(R.id.edtEmail);
        edtSenha  = (EditText) findViewById(R.id.edtSenha);

        Intent intent = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("usuarioSerializable");

        edtNome.setText(usuario.getNome());
        edtCpf.setText(usuario.getCpf());
        edtCidade.setText(usuario.getCidade());
        edtArea.setText(usuario.getArea());
        edtEmail.setText(usuario.getEmail());
        edtSenha.setText(usuario.getSenha());
    }
}
package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class PerfilEmpresa extends AppCompatActivity {

    EditText edtNome, edtCnpj, edtEndereco,edtTelefone, edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empresa);

        edtNome     = (EditText) findViewById(R.id.edtNome);
        edtCnpj     = (EditText) findViewById(R.id.edtCnpj);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail    = (EditText) findViewById(R.id.edtEmail);
        edtSenha    = (EditText) findViewById(R.id.edtSenha);

        Intent intent = getIntent();
        Empresa empresa = (Empresa) intent.getSerializableExtra("empresaSerializable");

        edtNome.setText(empresa.getNome());
        edtCnpj.setText(empresa.getCnpj());
        edtTelefone.setText(empresa.getTelefone());
        edtEndereco.setText(empresa.getEndereco());
        edtEmail.setText(empresa.getEmail());
        edtSenha.setText(empresa.getSenha());
    }
}
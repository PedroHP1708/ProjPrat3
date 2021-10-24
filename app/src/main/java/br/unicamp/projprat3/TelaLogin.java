package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaLogin extends AppCompatActivity {

    Button btnEmp, btnPes, btnEntrar;
    RadioButton rbPes, rbEmp;
    EditText edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPes = (Button) findViewById(R.id.btnCadPes);
        btnEmp = (Button) findViewById(R.id.btnCadEmp);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        rbEmp = (RadioButton) findViewById(R.id.rbEmpresa);
        rbPes = (RadioButton) findViewById(R.id.rbPessoa);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        //Abrir tela de cadastro de empresa
        btnEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Voltar p tela de caadastro dps
                Intent intent = new Intent(TelaLogin.this, PerfilEmpresa.class);
                startActivity(intent);
            }
        });

        //Abrir tela de cadastro de usuario
        btnPes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Voltar p tela de caadastro dps
                Intent intent = new Intent(TelaLogin.this, PerfilUsuario.class);

                startActivity(intent);
            }
        });

        //Verificar a existencia do cadastro para abrir o respectivo perfil
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {
                    if (rbEmp.isChecked()) {
                        Intent intent = new Intent(TelaLogin.this, PerfilEmpresa.class);
                        finish();
                        startActivity(intent);
                    }

                    else if (rbPes.isChecked()) {
                        Intent intent = new Intent(TelaLogin.this, PerfilUsuario.class);
                        finish();
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(TelaLogin.this, "Selecione um tipo de conta", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(TelaLogin.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
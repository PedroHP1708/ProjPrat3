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
                Intent intent = new Intent(TelaLogin.this, TelaPrincipalEmpresa.class);
                startActivity(intent);
            }
        });

        //Abrir tela de cadastro de usuario
        btnPes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Voltar p tela de caadastro dps
                Intent intent = new Intent(TelaLogin.this, CriarVaga.class);

                startActivity(intent);
            }
        });

        //Verificar a existencia do cadastro para abrir o respectivo perfil
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {
                    if (rbEmp.isChecked()) {
                        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
                        //Pegar a rota do Json
                        Call<Empresa> call = service.getEmpresa(edtEmail.getText().toString());
                        if(call != null) {
                            call.enqueue(new Callback<Empresa>() {
                                @Override
                                public void onResponse(Call<Empresa> call, Response<Empresa> response) {

                                    if (response.isSuccessful()) {
                                        if (response.body().getSenha().equals(edtSenha.getText().toString()))
                                        {
                                            Empresa emp = (Empresa) response.body();
                                            Intent intent = new Intent(TelaLogin.this, PerfilEmpresa.class);
                                            intent.putExtra("empresaSerializable", emp);
                                            //finish();
                                            startActivity(intent);

                                        }
                                        else
                                        {
                                            Toast.makeText(TelaLogin.this, "Senha incorreta", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        String errorMessage = response.errorBody().toString();
                                        Toast.makeText(TelaLogin.this, "Essa conta nao existe", Toast.LENGTH_LONG).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<Empresa> call, Throwable t) {
                                    Toast.makeText(TelaLogin.this, "Erro", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    else if (rbPes.isChecked()) {
                        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
                        //Pegar a rota do Json
                        Call<Usuario> call = service.getUsuario(edtEmail.getText().toString());
                        if(call != null) {
                            call.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                                    if (response.isSuccessful()) {
                                        if (response.body().getSenha().equals(edtSenha.getText().toString()))
                                        {
                                            Usuario user = (Usuario) response.body();
                                            Intent intent = new Intent(TelaLogin.this, PerfilUsuario.class);
                                            intent.putExtra("usuarioSerializable", user);
                                            //finish();
                                            startActivity(intent);

                                        }
                                        else
                                        {
                                            Toast.makeText(TelaLogin.this, "Senha incorreta", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else {
                                        String errorMessage = response.errorBody().toString();
                                        Toast.makeText(TelaLogin.this, "Essa conta nao existe", Toast.LENGTH_LONG).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<Usuario> call, Throwable t) {
                                    Toast.makeText(TelaLogin.this, "Erro", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
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
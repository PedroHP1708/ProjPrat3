package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilEmpresa extends AppCompatActivity {

    EditText edtNome, edtCnpj, edtEndereco, edtTelefone, edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empresa);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCnpj = (EditText) findViewById(R.id.edtCnpj);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        //Intent intent = getIntent();
        //Empresa empresa = (Empresa) intent.getSerializableExtra("empresaSerializable");

        //MUDAR ID PARA DEIXAR DE SER IDENTITY

        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json
        Call<Empresa> call = service.getEmpresa("1234@gmail");
        if (call != null) {
            call.enqueue(new Callback<Empresa>() {
                @Override
                public void onResponse(Call<Empresa> call, Response<Empresa> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(PerfilEmpresa.this, "deu certo", Toast.LENGTH_LONG).show();
                        //myProgessBar.setVisibility(View.GONE);
                        Empresa user = (Empresa) response.body();
                        edtNome.setText(user.getNome());
                        edtCnpj.setText(user.getCnpj());
                        edtEndereco.setText(user.getEndereco());
                        edtTelefone.setText(user.getTelefone());
                        edtEmail.setText(user.getEmail());
                        edtSenha.setText(user.getSenha());
                    } else {
                        String errorMessage = response.errorBody().toString();
                        Toast.makeText(PerfilEmpresa.this, "" + response.body(), Toast.LENGTH_LONG).show();
                        Toast.makeText(PerfilEmpresa.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Empresa> call, Throwable t) {
                    //myProgessBar.setVisibility(View.GONE);
                    String messageProblem = t.getMessage().toString();
                    Toast.makeText(PerfilEmpresa.this, messageProblem, Toast.LENGTH_LONG).show();
                    Toast.makeText(PerfilEmpresa.this, "failure", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
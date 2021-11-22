package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroEmpresa extends AppCompatActivity {

    EditText edtNome, edtCnpj, edtEndereco, edtTelefone, edtEmail, edtSenha;
    Button btnContinuar;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_empresa);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCnpj = (EditText) findViewById(R.id.edtCnpj);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnContinuar = (Button) findViewById(R.id.btnContinuar);

        session = new Session(CadastroEmpresa.this);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtNome.getText().toString().equals("") && !edtCnpj.getText().toString().equals("") && !edtEndereco.getText().toString().equals("") && !edtTelefone.getText().toString().equals("") && !edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals(""))
                {
                    Service service = RetrofitConfig.getRetrofitInstance().create((Service.class));
                    Empresa emp = new Empresa(edtNome.getText().toString(), edtCnpj.getText().toString(), edtEndereco.getText().toString(), edtTelefone.getText().toString(), edtEmail.getText().toString(), ".", edtSenha.getText().toString(), ".");
                    Call<Empresa> call = service.incluirEmpresa(emp);
                    call.enqueue(new Callback<Empresa>() {
                        @Override
                        public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(CadastroEmpresa.this, "Ocorreu um erro na requisicao", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(CadastroEmpresa.this, emp.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Empresa> call, Throwable t) {

                            Intent intent = new Intent(CadastroEmpresa.this, TelaPrincipalEmpresa.class);
                            //intent.putExtra("empresaSerializable", emp);
                            session.setusename(emp.getEmail());
                            startActivity(intent);
                        }
                    });
                }
                else
                {
                    Toast.makeText(CadastroEmpresa.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
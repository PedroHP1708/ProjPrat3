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

public class CadastroPessoa extends AppCompatActivity {

    EditText edtNome, edtCpf, edtCidade, edtArea, edtEmail, edtSenha;
    Button btnContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        edtNome   = (EditText) findViewById(R.id.edtNome);
        edtCpf    = (EditText) findViewById(R.id.edtCpf);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtArea   = (EditText) findViewById(R.id.edtArea);
        edtEmail  = (EditText) findViewById(R.id.edtEmail);
        edtSenha  = (EditText) findViewById(R.id.edtSenha);

        btnContinuar = (Button) findViewById(R.id.btnContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtNome.getText().toString().equals("") && !edtCpf.getText().toString().equals("") && !edtArea.getText().toString().equals("") && !edtCidade.getText().toString().equals("") && !edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals(""))
                {
                    Service service = RetrofitConfig.getRetrofitInstance().create((Service.class));
                    Usuario pes = new Usuario(edtNome.getText().toString(), edtCpf.getText().toString(), edtArea.getText().toString(), edtCidade.getText().toString(), edtEmail.getText().toString(), edtSenha.getText().toString());
                    Call<Usuario> call = service.incluirUsuario(pes);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(CadastroPessoa.this, "Ocorreu um erro na requisicao", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                            Intent intent = new Intent(CadastroPessoa.this, PerfilUsuario.class);
                            intent.putExtra("pessoaSerializable", pes);
                            finish();
                            startActivity(intent);
                        }
                    });
                }
                else
                {
                    Toast.makeText(CadastroPessoa.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
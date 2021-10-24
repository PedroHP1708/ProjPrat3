package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        //MUDAR ID PARA DEIXAR DE SER IDENTITY

        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json
        Call<Usuario> call = service.getUsuario(usuario.getEmail());
        if(call != null) {
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(PerfilUsuario.this, "deu certo", Toast.LENGTH_LONG).show();
                        //myProgessBar.setVisibility(View.GONE);
                        Usuario user = (Usuario) response.body();
                        edtNome.setText(user.getNome());
                        edtCpf.setText(user.getCpf());
                        edtCidade.setText(user.getCidade());
                        edtArea.setText(user.getArea());
                        edtEmail.setText(user.getEmail());
                        edtSenha.setText(user.getSenha());
                    } else {
                        String errorMessage = response.errorBody().toString();
                        Toast.makeText(PerfilUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(PerfilUsuario.this, "failure", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
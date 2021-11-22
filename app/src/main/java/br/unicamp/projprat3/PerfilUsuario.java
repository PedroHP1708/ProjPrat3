package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilUsuario extends AppCompatActivity {

    EditText edtNome, edtCpf, edtCidade, edtArea, edtEmail, edtSenha, edtDescricao;

    GridView vagaGridView;
    private GridViewVagaUsuarioAdapter adapter;

    List<Vaga> listaVaga;
    List<VagaAplicada> listaVagaAplicada;

    Button btnHome;

    Usuario usu;
    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        session = new Session(PerfilUsuario.this);

        edtNome   = (EditText) findViewById(R.id.edtNome);
        edtCpf    = (EditText) findViewById(R.id.edtCpf);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtArea   = (EditText) findViewById(R.id.edtArea);
        edtEmail  = (EditText) findViewById(R.id.edtEmail);
        edtSenha  = (EditText) findViewById(R.id.edtSenha);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(PerfilUsuario.this, TelaPrincipalUsuario.class);
                startActivity(in);
                finish();
            }
        });

        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        String email = session.getusename();
        //Pegar a rota do Json
        Call<Usuario> call = service.getUsuario(email);
        if(call != null) {
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                    if (response.isSuccessful()) {
                        //myProgessBar.setVisibility(View.GONE);
                        Usuario user = (Usuario) response.body();
                        edtNome.setText(user.getNome());
                        edtCpf.setText(user.getCpf());
                        edtCidade.setText(user.getCidade());
                        edtArea.setText(user.getArea());
                        edtEmail.setText(user.getEmail());
                        edtSenha.setText(user.getSenha());
                        edtDescricao.setText(user.getDescricao());
                        usu = user;
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

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch(Exception er)
        {}
        atualizarPesquisa();
    }


    private void atualizarPesquisa()
    {

        //Download JSON via Retrofit
        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json
        Call<List<VagaAplicada>> call = service.getVagasAplicadas();
        call.enqueue(new Callback<List<VagaAplicada>>() {
            @Override
            public void onResponse(Call<List<VagaAplicada>> call, Response<List<VagaAplicada>> response) {
                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                if(response.isSuccessful()){
                    listaVagaAplicada = response.body();
                }
                else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(PerfilUsuario.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(PerfilUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<VagaAplicada>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(PerfilUsuario.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(PerfilUsuario.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch(Exception er)
        {}

        Call<List<Vaga>> call2 = service.getVagas();
        call2.enqueue(new Callback<List<Vaga>>() {
            @Override
            public void onResponse(Call<List<Vaga>> call, Response<List<Vaga>> response) {
                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                if(response.isSuccessful()){
                    populateGridView(listaVagaAplicada, response.body(), usu);
                }
                else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(PerfilUsuario.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(PerfilUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(PerfilUsuario.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(PerfilUsuario.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void populateGridView(List<VagaAplicada> listaVagasAplicadas, List<Vaga> listaVaga, Usuario user){
        vagaGridView = (GridView) findViewById(R.id.vagaGridView);
        vagaGridView.setAdapter(null);
        List<VagaAplicada> listaFinal = new ArrayList<VagaAplicada>();
        if(listaVaga != null && listaVagasAplicadas != null)
        {
            for(int pos = 0; pos < listaVaga.size(); pos++)
            {
                for(int p = 0; p < listaVagasAplicadas.size(); p++)
                {
                    if(listaVaga.get(pos).getId() == listaVagasAplicadas.get(p).getIdVaga() && listaVagasAplicadas.get(p).getEmailUsuario().equals(user.getEmail()))
                        listaFinal.add(listaVagasAplicadas.get(p));
                }
            }
            adapter = new GridViewVagaUsuarioAdapter(this,listaFinal, listaVaga, user);
            vagaGridView.setAdapter(adapter);
        }


    }
}
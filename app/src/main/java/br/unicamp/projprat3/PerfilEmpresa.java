package br.unicamp.projprat3;

import androidx.appcompat.app.AlertDialog;
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

public class PerfilEmpresa extends AppCompatActivity {

    EditText edtNome, edtCnpj, edtEndereco, edtTelefone, edtEmail, edtSenha;
    GridView vagaAplicadaGridView;
    private GridViewVagaAplicadaAdapter adapter;

    List<Usuario> listaUsuario;
    List<Vaga> listaVaga;
    List<VagaAplicada> listaVagaAplicada;

    Button btnHome2;

    private Session session;//global variable

    Empresa emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empresa);

        session = new Session(PerfilEmpresa.this);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtCnpj = (EditText) findViewById(R.id.edtCnpj);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnHome2 = (Button) findViewById(R.id.btnHome2);
        btnHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(PerfilEmpresa.this, TelaPrincipalEmpresa.class);
                startActivity(in);
                finish();
            }
        });

        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json
        Call<Empresa> call = service.getEmpresa(session.getusename());
        if (call != null) {
            call.enqueue(new Callback<Empresa>() {
                @Override
                public void onResponse(Call<Empresa> call, Response<Empresa> response) {

                    if (response.isSuccessful()) {
                        //myProgessBar.setVisibility(View.GONE);
                        Empresa user = (Empresa) response.body();
                        edtNome.setText(user.getNome());
                        edtCnpj.setText(user.getCnpj());
                        edtEndereco.setText(user.getEndereco());
                        edtTelefone.setText(user.getTelefone());
                        edtEmail.setText(user.getEmail());
                        edtSenha.setText(user.getSenha());

                        emp = user;
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
                    Toast.makeText(PerfilEmpresa.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(PerfilEmpresa.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<VagaAplicada>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(PerfilEmpresa.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(PerfilEmpresa.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
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
                    populateGridView(listaVagaAplicada, response.body(), emp);
                }
                else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(PerfilEmpresa.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(PerfilEmpresa.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(PerfilEmpresa.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(PerfilEmpresa.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateGridView(List<VagaAplicada> listaVagasAplicadas, List<Vaga> listaVaga, Empresa emp){
        vagaAplicadaGridView = (GridView) findViewById(R.id.vagaAplicadaGridView);
        vagaAplicadaGridView.setAdapter(null);
        List<VagaAplicada> listaFinal = new ArrayList<VagaAplicada>();
        if(listaVaga != null && listaVagasAplicadas != null)
        {
            for(int pos = 0; pos < listaVaga.size(); pos++)
            {
                for(int p = 0; p < listaVagasAplicadas.size(); p++)
                {
                    if(listaVaga.get(pos).getId() == listaVagasAplicadas.get(p).getIdVaga() && listaVaga.get(pos).getEmailEmpresa().equals(emp.getEmail()) &&
                            listaVagasAplicadas.get(p).getSituacao().equals("Em analise"))
                        listaFinal.add(listaVagasAplicadas.get(p));
                }
            }
            adapter = new GridViewVagaAplicadaAdapter(this,listaFinal, listaVaga, emp);
            vagaAplicadaGridView.setAdapter(adapter);
        }


    }
}
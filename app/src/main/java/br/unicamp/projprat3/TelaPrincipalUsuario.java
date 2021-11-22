package br.unicamp.projprat3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPrincipalUsuario extends AppCompatActivity {

    GridView vagaGridView;
    private GridViewVagaAdapter adapter;

    List<Vaga> listaResultado;
    List<Empresa> listaEmpresas;
    List<Empresa> listaRetornada;

    Button btnArea, btnLocalizacao, btnSalario, btnEmpresa;
    ImageButton btnPesquisaUsuario;

    AlertDialog.Builder builder;
    AlertDialog dialog;
    EditText edtTextoDialog, edtBarraPesquisa;
    Button btnConfirmar, btnCancelar;
    View viFoto;

    String areaProc, localizacaoProc, empresaProc;
    Integer salarioProc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_usuario);

        btnArea        = (Button) findViewById(R.id.btnArea);
        btnLocalizacao = (Button) findViewById(R.id.btnLocalizacao);
        btnSalario     = (Button) findViewById(R.id.btnSalario);
        btnEmpresa     = (Button) findViewById(R.id.btnEmpresa);
        btnPesquisaUsuario     = (ImageButton) findViewById(R.id.btnPesquisaUsuario);

        edtBarraPesquisa = (EditText) findViewById(R.id.edtBarraPesquisa);
        viFoto = (View) findViewById(R.id.viFoto);

        viFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(TelaPrincipalUsuario.this, PerfilUsuario.class);
                startActivity(in);
            }
        });

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(TelaPrincipalUsuario.this).inflate(R.layout.dialog_pesquisa_area,null, false);
                mostrarDialog(0, v);
            }
        });

        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(TelaPrincipalUsuario.this).inflate(R.layout.dialog_pesquisa_area,null, false);
                mostrarDialog(1, v);
            }
        });

        btnSalario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(TelaPrincipalUsuario.this).inflate(R.layout.dialog_pesquisa_area,null, false);
                mostrarDialog(2, v);
            }
        });

        btnEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(TelaPrincipalUsuario.this).inflate(R.layout.dialog_pesquisa_area,null, false);
                mostrarDialog(3, v);
            }
        });


        listaRetornada = pegarEmpresas();

        //FUNCAO SALVADORA
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }
        catch(Exception er)
        {}

        btnPesquisaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtBarraPesquisa.getText().toString().equals(""))
                {
                    atualizarPesquisa(edtBarraPesquisa.getText().toString(), 4);
                }
                else
                {
                    Toast.makeText(TelaPrincipalUsuario.this, "Digite uma vaga que deseja procurar", Toast.LENGTH_LONG).show();
                }
            }
        });

        atualizarPesquisa(listaResultado);
    }

    private void mostrarDialog(int tipo, View v)
    {
        builder = new AlertDialog.Builder(TelaPrincipalUsuario.this);
        AlertDialog alertDialog = builder.create();

        alertDialog.setCancelable(false);

        btnCancelar = v.findViewById(R.id.btnCancelar);
        btnConfirmar = v.findViewById(R.id.btnConfirmar);
        edtTextoDialog = (EditText) v.findViewById(R.id.edtTexto);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        if(tipo == 0)
        {
            alertDialog.setTitle("Digite a localização procurada");
            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    localizacaoProc = edtTextoDialog.getText().toString();
                    atualizarPesquisa(localizacaoProc, 0);
                    alertDialog.dismiss();
                }
            });
        }

        else if(tipo == 1)
        {
            alertDialog.setTitle("Digite a área de serviço procurada");

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    areaProc = edtTextoDialog.getText().toString();
                    atualizarPesquisa(areaProc, 1);
                    alertDialog.dismiss();
                }
            });
        }

        else if(tipo == 2)
        {
            alertDialog.setTitle("Digite o salário que você busca");

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try
                    {
                        salarioProc = Integer.parseInt(edtTextoDialog.getText().toString());
                        atualizarPesquisa(salarioProc.toString(), 2);
                        alertDialog.dismiss();
                    }
                    catch(Exception erro)
                    {
                        Toast.makeText(TelaPrincipalUsuario.this, "Digite um número de salário", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

        else
        {
            alertDialog.setTitle("Digite a empresa procurada");

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    empresaProc = edtTextoDialog.getText().toString();

                    Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
                    Call<List<Empresa>> call = service.getEmpresas();
                    if (call != null) {
                        call.enqueue(new Callback<List<Empresa>>() {
                            @Override
                            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {

                                if (response.isSuccessful()) {
                                    for(int pos = 0; pos < response.body().size(); pos++)
                                    {
                                        if(response.body().get(pos).getNome().equals(empresaProc))
                                        {
                                            empresaProc = response.body().get(pos).getEmail();
                                        }
                                    }
                                    atualizarPesquisa(empresaProc, 3);
                                    alertDialog.dismiss();
                                }
                                else {
                                    String errorMessage = response.errorBody().toString();
                                    Toast.makeText(TelaPrincipalUsuario.this, "" + response.body(), Toast.LENGTH_LONG).show();
                                    Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                                //myProgessBar.setVisibility(View.GONE);
                                String messageProblem = t.getMessage().toString();
                                Toast.makeText(TelaPrincipalUsuario.this, messageProblem, Toast.LENGTH_LONG).show();
                                Toast.makeText(TelaPrincipalUsuario.this, "failure", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            });
        }

        alertDialog.setView(v);
        alertDialog.show();

    }


    private void atualizarPesquisa(List<Vaga> listaEmpresa)
    {

        //Download JSON via Retrofit
        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json
        Call<List<Vaga>> call = service.getVagas();
        call.enqueue(new Callback<List<Vaga>>() {
            @Override
            public void onResponse(Call<List<Vaga>> call, Response<List<Vaga>> response) {
                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                if(response.isSuccessful()){
                    populateGridView(response.body(), listaRetornada);
                }
                else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(TelaPrincipalUsuario.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(TelaPrincipalUsuario.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void atualizarPesquisa(String campoProc, int tipo)
    {
        //Download JSON via Retrofit
        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json

        List<Vaga> listaAtualizada = new ArrayList<Vaga>();

        Call<List<Vaga>> call = service.getVagas();
        call.enqueue(new Callback<List<Vaga>>() {
            @Override
            public void onResponse(Call<List<Vaga>> call, Response<List<Vaga>> response) {
                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                if(response.isSuccessful()){

                    if(tipo == 0)
                    {
                        for(int pos = 0; pos < response.body().size(); pos++)
                        {
                            if(response.body().get(pos).getEndereco().equals(campoProc) && !campoProc.equals(""))
                            {
                                listaAtualizada.add(response.body().get(pos));
                            }
                        }
                    }

                    else if(tipo == 1)
                    {
                        for(int pos = 0; pos < response.body().size(); pos++)
                        {
                            if(response.body().get(pos).getArea().equals(campoProc) && !campoProc.equals(""))
                            {
                                listaAtualizada.add(response.body().get(pos));
                            }
                        }
                    }

                    else if(tipo == 2)
                    {
                        for(int pos = 0; pos < response.body().size(); pos++)
                        {
                            if(response.body().get(pos).getSalarioBase() == Integer.parseInt(campoProc) && !campoProc.equals(""))
                            {
                                listaAtualizada.add(response.body().get(pos));
                            }
                        }
                    }

                    else if(tipo == 3)
                    {
                        for(int pos = 0; pos < response.body().size(); pos++)
                        {
                            if(response.body().get(pos).getEmailEmpresa().equals(campoProc) && !campoProc.equals(""))
                            {
                                listaAtualizada.add(response.body().get(pos));
                            }
                        }
                    }

                    else
                    {
                        for(int pos = 0; pos < response.body().size(); pos++)
                        {
                            if(response.body().get(pos).getTitulo().equals(campoProc) && !campoProc.equals(""))
                            {
                                listaAtualizada.add(response.body().get(pos));
                            }
                        }
                    }


                    populateGridView(listaAtualizada, listaRetornada);
                }else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(TelaPrincipalUsuario.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(TelaPrincipalUsuario.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateGridView(List<Vaga> listaVagas, List<Empresa> listaEmpresas){
        vagaGridView = (GridView) findViewById(R.id.vagaGridView);
        vagaGridView.setAdapter(null);
        if(listaEmpresas != null)
        {
            adapter = new GridViewVagaAdapter(this,listaVagas, listaEmpresas);
            vagaGridView.setAdapter(adapter);
        }

    }

    private List<Empresa> pegarEmpresas()
    {

        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json
        Call<List<Empresa>> call = service.getEmpresas();
        //List<Empresa> lista = ArrayList<Empresa>();
       /* try
        {
            Response<List<Empresa>> response = call.execute();
            //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
            if(response.isSuccessful()){
                listaRetornada = response.body();
            }else{
                String errorMessage = response.errorBody().toString();
                Toast.makeText(TelaPrincipalUsuario.this, errorMessage, Toast.LENGTH_LONG).show();
                Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception er)
        {
            er.printStackTrace();
            Toast.makeText(TelaPrincipalUsuario.this, er.getMessage(), Toast.LENGTH_SHORT).show();
        }
*/
        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                if(response.isSuccessful()){
                    listaRetornada = response.body();
                }else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(TelaPrincipalUsuario.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(TelaPrincipalUsuario.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(TelaPrincipalUsuario.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });

        return listaRetornada;
    }
}
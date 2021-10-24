package br.unicamp.projprat3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPrincipalEmpresa extends AppCompatActivity {

    GridView usuarioGridView;
    private GridViewUsuarioAdapter adapter;
    List<Usuario> listaResultado;
    Button btnArea, btnLocalizacao;

    AlertDialog.Builder builder;
    AlertDialog dialog;
    EditText edtTextoDialog, edtBarraPesquisaEmp;
    Button btnConfirmar, btnCancelar;

    ImageButton btnPesquisaEmpresa;

    String areaProc, localizacaoProc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_empresa);

        btnArea = (Button) findViewById(R.id.btnArea);
        btnLocalizacao = (Button) findViewById(R.id.btnLocalizacao);

        btnPesquisaEmpresa   = (ImageButton) findViewById(R.id.btnPesquisaEmpresa);
        edtBarraPesquisaEmp  = (EditText) findViewById(R.id.edtBarraPesquisaEmp);

        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(TelaPrincipalEmpresa.this).inflate(R.layout.dialog_pesquisa_area,null, false);
                mostrarDialog(1, v);
            }
        });

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(TelaPrincipalEmpresa.this).inflate(R.layout.dialog_pesquisa_area,null, false);
                mostrarDialog(0, v);
            }
        });

        btnPesquisaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtBarraPesquisaEmp.getText().toString().equals(""))
                {
                    atualizarPesquisa(edtBarraPesquisaEmp.getText().toString(), 2);
                }
                else
                {
                    Toast.makeText(TelaPrincipalEmpresa.this, "Digite um usuário que deseja procurar", Toast.LENGTH_LONG).show();
                }
            }
        });

        atualizarPesquisa(listaResultado);
    }

    private void mostrarDialog(int tipo, View v)
    {
        builder = new AlertDialog.Builder(TelaPrincipalEmpresa.this);
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

        if(tipo == 1)
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
        else
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

        alertDialog.setView(v);
        alertDialog.show();

    }


    private void atualizarPesquisa(List<Usuario> listaUsuario)
    {

        //Download JSON via Retrofit
        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json
        Call<List<Usuario>> call = service.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                if(response.isSuccessful()){
                    populateGridView(response.body());
                }else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(TelaPrincipalEmpresa.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(TelaPrincipalEmpresa.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(TelaPrincipalEmpresa.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(TelaPrincipalEmpresa.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void atualizarPesquisa(String campoProc, int tipo)
    {
        //Download JSON via Retrofit
        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
        //Pegar a rota do Json

        List<Usuario> listaAtualizada = new ArrayList<Usuario>();

        Call<List<Usuario>> call = service.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                if(response.isSuccessful()){

                    if(tipo == 0)
                    {
                        for(int pos = 0; pos < response.body().size(); pos++)
                        {
                            if(response.body().get(pos).getCidade().equals(campoProc) && !campoProc.equals(""))
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


                    else
                    {
                        for(int pos = 0; pos < response.body().size(); pos++)
                        {
                            if(response.body().get(pos).getNome().equals(campoProc) && !campoProc.equals(""))
                            {
                                listaAtualizada.add(response.body().get(pos));
                            }
                        }
                    }

                    populateGridView(listaAtualizada);
                }else{
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(TelaPrincipalEmpresa.this, errorMessage, Toast.LENGTH_LONG).show();
                    Toast.makeText(TelaPrincipalEmpresa.this, "entrou no else do response", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                String messageProblem = t.getMessage().toString();
                Toast.makeText(TelaPrincipalEmpresa.this, messageProblem, Toast.LENGTH_SHORT).show();
                Toast.makeText(TelaPrincipalEmpresa.this, "entrou no else do Failure", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateGridView(List<Usuario> listaUsuario){
        usuarioGridView = (GridView) findViewById(R.id.usuarioGridView);
        usuarioGridView.setAdapter(null);
        adapter = new GridViewUsuarioAdapter(this,listaUsuario);
        usuarioGridView.setAdapter(adapter);
    }

}
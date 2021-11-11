package br.unicamp.projprat3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GridViewVagaAplicadaAdapter extends BaseAdapter {
    private List<VagaAplicada> listaVagasAplicadas;
    private List<Usuario> listaUsuarios;
    private List<Vaga> listaVagas;
    private Context context;
    private Empresa emp;
    AlertDialog.Builder builder;


    public GridViewVagaAplicadaAdapter(Context context, List<VagaAplicada> paramListaVagaAplicada, List<Vaga> paramListaVaga, Empresa emp){
        this.listaVagasAplicadas = paramListaVagaAplicada;
        //this.listaUsuarios = paramListaUsuario;
        this.listaVagas = paramListaVaga;
        this.emp = emp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaVagasAplicadas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVagasAplicadas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_aplicacao_pendente, parent,false);
        }

        TextView tvItemNomeUsuario = view.findViewById(R.id.tvItemNomeUsuario);
        TextView tvItemTituloVaga = view.findViewById(R.id.tvItemTituloVaga);
        Button btnAprovar = view.findViewById(R.id.btnAprovar);
        Button btnRejeitar = view.findViewById(R.id.btnRejeitar);

        VagaAplicada vagaAp = listaVagasAplicadas.get(position);
        Usuario usuario = null;
        Vaga vaga = null;


        for(int pos = 0; pos < listaVagas.size(); pos++)
        {
            if(vagaAp.getIdVaga() == listaVagas.get(pos).getId())
            {
                if(listaVagas.get(pos).getEmailEmpresa().equals(emp.getEmail()))
                {
                    vaga = listaVagas.get(pos);
                }
            }
        }

/*
        tvItemNomeUsuario.setText(usuario.getNome());
        tvItemTituloVaga.setText(vaga.getTitulo());*/


        tvItemNomeUsuario.setText(vagaAp.getEmailUsuario());
        tvItemTituloVaga.setText(vaga.getTitulo());

        btnAprovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(context);


                builder.setTitle("Deseja mesmo aprovar essa aplicação?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
                        VagaAplicada vagaNova = new VagaAplicada(vagaAp.getId(), vagaAp.getIdVaga(), vagaAp.getEmailUsuario(), "Chamada");
                        Call<VagaAplicada> call = service.alterarVagaAplicada(vagaNova.getId(), vagaNova);
                        call.enqueue(new Callback<VagaAplicada>() {
                            @Override
                            public void onResponse(Call<VagaAplicada> call, Response<VagaAplicada> response) {
                                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                                if(response.isSuccessful()){
                                    //listaVagaAplicada = response.body();
                                    comporEmail(new String[] {vagaAp.getEmailUsuario()}, "Aprovação");
                                }
                                else{
                                    String errorMessage = response.errorBody().toString();
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                                    Toast.makeText(context, "entrou no else do response", Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<VagaAplicada> call, Throwable t) {
                                String messageProblem = t.getMessage().toString();
                                Toast.makeText(context, messageProblem, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, "entrou no else do Failure", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        btnRejeitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(context);

                builder.setTitle("Deseja mesmo rejeitar essa aplicação?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Service service  = RetrofitConfig.getRetrofitInstance().create(Service.class);
                        VagaAplicada vagaNova = new VagaAplicada(vagaAp.getId(), vagaAp.getIdVaga(), vagaAp.getEmailUsuario(), "Recusado");
                        Call<VagaAplicada> call = service.alterarVagaAplicada(vagaNova.getId(), vagaNova);
                        call.enqueue(new Callback<VagaAplicada>() {
                            @Override
                            public void onResponse(Call<VagaAplicada> call, Response<VagaAplicada> response) {
                                //Para atualizar, percorrer o response e comparar com os itens selecionadas nos botoes
                                if(response.isSuccessful()){
                                    //listaVagaAplicada = response.body();
                                    comporEmail(new String[] {vagaAp.getEmailUsuario()}, "Rejeição");
                                }
                                else{
                                    String errorMessage = response.errorBody().toString();
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                                    Toast.makeText(context, "entrou no else do response", Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<VagaAplicada> call, Throwable t) {
                                String messageProblem = t.getMessage().toString();
                                Toast.makeText(context, messageProblem, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, "entrou no else do Failure", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }

    public void comporEmail(String[] endereco, String situacao) {
        String[] recipients = endereco;

        //String subject = mEditTextSubject.getText().toString();
        //String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,  situacao + " de aplicação de vaga");
        //intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        context.startActivity(Intent.createChooser(intent, "Choose an email client"));
        Toast.makeText(context, situacao + " realizada com sucesso", Toast.LENGTH_LONG).show();
    }
}

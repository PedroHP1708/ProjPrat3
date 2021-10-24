package br.unicamp.projprat3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class GridViewVagaAplicadaAdapter extends BaseAdapter {
    private List<VagaAplicada> listaVagasAplicadas;
    private List<Usuario> listaUsuarios;
    private List<Vaga> listaVagas;
    private Context context;
    private Empresa emp;

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

            }
        });
        btnRejeitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}

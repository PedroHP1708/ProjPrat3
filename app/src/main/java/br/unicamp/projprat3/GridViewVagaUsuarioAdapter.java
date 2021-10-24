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

public class GridViewVagaUsuarioAdapter extends BaseAdapter {
    private List<Vaga> listaVaga;
    private List<VagaAplicada> listaVagaAplicada;
    private Context context;
    Usuario user;

    public GridViewVagaUsuarioAdapter(Context context, List<VagaAplicada> paramListaVagaAplicada, List<Vaga> paramListaVaga, Usuario user){
        this.listaVaga    = paramListaVaga;
        this.listaVagaAplicada = paramListaVagaAplicada;
        this.context = context;
        this.user = user;
    }

    @Override
    public int getCount() {
        return listaVagaAplicada.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVagaAplicada.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_vaga_usuario, parent,false);
        }


        TextView tvItemNomeEmpresa = view.findViewById(R.id.tvItemNomeEmpresa);
        TextView tvItemSituacaoVaga = view.findViewById(R.id.tvItemSituacaoVaga);

        VagaAplicada vagaAp = listaVagaAplicada.get(position);
        Empresa emp = null;
        Vaga vaga = null;


        for(int pos = 0; pos < listaVaga.size(); pos++)
        {
            if(vagaAp.getIdVaga() == listaVaga.get(pos).getId())
            {
                if(vagaAp.getEmailUsuario().equals(user.getEmail()))
                {
                    vaga = listaVaga.get(pos);
                }
            }
        }

/*
        tvItemNomeUsuario.setText(usuario.getNome());
        tvItemTituloVaga.setText(vaga.getTitulo());*/


        tvItemNomeEmpresa.setText(vaga.getEmailEmpresa());
        tvItemSituacaoVaga.setText(vagaAp.getSituacao());


        return view;
    }
}

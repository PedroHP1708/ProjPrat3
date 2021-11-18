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

public class GridViewVagaAdapter extends BaseAdapter {

    private List<Vaga> listaVaga;
    private List<Empresa> listaEmpresa;
    private Context context;

    public GridViewVagaAdapter(Context context, List<Vaga> recebeParametroListaVaga, List<Empresa> recebeParametroListaEmpresa){
        this.listaVaga    = recebeParametroListaVaga;
        this.listaEmpresa = recebeParametroListaEmpresa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaVaga.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVaga.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_vaga_pesquisa, parent,false);
        }

        TextView tvNomeVaga = view.findViewById(R.id.tvNomeVaga);
        TextView tvNomeEmpresa = view.findViewById(R.id.tvNomeEmpresa);
        TextView tvAreaEmpresa = view.findViewById(R.id.tvAreaEmpresa);
        Button btnDetalhes = view.findViewById(R.id.btnDetalhes);

        Vaga vaga = listaVaga.get(position);
        Empresa empresa = null;

        for(int pos = 0; pos < listaEmpresa.size(); pos++)
        {
            if(vaga.getEmailEmpresa().equals(listaEmpresa.get(pos).getEmail()))
            {
                empresa = listaEmpresa.get(pos);
            }
        }

        tvNomeVaga.setText(vaga.getTitulo());
        tvNomeEmpresa.setText(empresa.getNome());
        tvAreaEmpresa.setText(vaga.getArea());
/*
IMAGEM DE PERFIL DPS
        if((dog.getImagem() != null) && (dog.getImagem().length()>0)){
            Picasso.get().load(dog.getImagem()).into(dogImageView);
        }else{
            Toast.makeText(context, "Não carregou a imagem", Toast.LENGTH_LONG).show();
        }
*/
        btnDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vaga vagaPassada = new Vaga(vaga.getId(), vaga.getEmailEmpresa(), vaga.getTitulo(), vaga.getEndereco(), vaga.getArea(), vaga.getSalarioBase());
                Intent in = new Intent(context, TelaVaga.class);
                in.putExtra("vagaSerializable", (Serializable) vagaPassada);
                context.startActivity(in);
            }
        });
        return view;
    }
}
package br.unicamp.projprat3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class GridViewUsuarioAdapter extends BaseAdapter {

    private List<Usuario> listaUsuario;
    private Context context;

    public GridViewUsuarioAdapter(Context context, List<Usuario> recebeParametroListaUsuario){
        this.listaUsuario = recebeParametroListaUsuario;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaUsuario.size();
    }

    @Override
    public Object getItem(int position) {
        return listaUsuario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_usuario_pesquisa, parent,false);
        }

        TextView tvNomeUsuario = view.findViewById(R.id.tvNomeUsuario);
        TextView tvAreaUsuario = view.findViewById(R.id.tvAreaUsuario);
        Button btnDetalhes = view.findViewById(R.id.btnDetalhes);

        Usuario user = listaUsuario.get(position);

        tvNomeUsuario.setText(user.getNome());
        tvAreaUsuario.setText(user.getArea());
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
                Usuario usuario = new Usuario(user.getEmail(), user.getNome(), user.getCpf(), user.getArea(), user.getCidade(), user.getFotoDePerfil(), user.getSenha(), user.getDescricao());
                Intent in = new Intent(context, PerfilUsuarioClicado.class);
                in.putExtra("usuarioSerializable", (Serializable) usuario);
                context.startActivity(in);
            }
        });
        return view;
    }

}

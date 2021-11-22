package br.unicamp.projprat3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btnContato = view.findViewById(R.id.btnContato);

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

        Vaga v = vaga;
        tvItemNomeEmpresa.setText(vaga.getEmailEmpresa());
        tvItemSituacaoVaga.setText(vagaAp.getSituacao());

        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comporEmail(new String[] {v.getEmailEmpresa()});
            }
        });

        return view;
    }

    public void comporEmail(String[] endereco) {
        String[] recipients = endereco;

        //String subject = mEditTextSubject.getText().toString();
        //String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        //intent.putExtra(Intent.EXTRA_SUBJECT,  situacao + " de aplicação de vaga");
        //intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        context.startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}

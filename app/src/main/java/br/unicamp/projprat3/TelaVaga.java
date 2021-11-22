package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaVaga extends AppCompatActivity {

    EditText edtEmpresa, edtAreaAtuacao, edtDescricaoVaga, edtEndereco, edtSalario;
    TextView tvTitulo;

    Button btnCandidatar;

    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_vaga);

        edtEmpresa = (EditText) findViewById(R.id.edtEmpresa);
        edtAreaAtuacao = (EditText) findViewById(R.id.edtAreaAtuacao);
        edtDescricaoVaga = (EditText) findViewById(R.id.edtDescricaoVaga);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtSalario = (EditText) findViewById(R.id.edtSalario);

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);

        btnCandidatar = (Button) findViewById(R.id.btnCandidatar);

        Intent in = getIntent();
        Vaga vaga = (Vaga) in.getSerializableExtra("vagaClicada");

        tvTitulo.setText(vaga.getTitulo());
        edtEmpresa.setText(vaga.getEmailEmpresa());
        edtAreaAtuacao.setText(vaga.getArea());
        edtDescricaoVaga.setText(vaga.getDescricao());
        edtEndereco.setText(vaga.getEndereco());
        edtSalario.setText(""+vaga.getSalarioBase());

        session = new Session(TelaVaga.this);

        String emailUsuario = session.getusename();

        btnCandidatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Service service = RetrofitConfig.getRetrofitInstance().create((Service.class));
                VagaAplicada vagaAInserir = new VagaAplicada(vaga.getId(), emailUsuario, "Em analise");


                Call<VagaAplicada> call = service.incluirVagaAplicada(vagaAInserir);

                call.enqueue(new Callback<VagaAplicada>() {
                    @Override
                    public void onResponse(Call<VagaAplicada> call, Response<VagaAplicada> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(TelaVaga.this, "Ocorreu um erro na requisicao", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(TelaVaga.this, "Vaga Criada com Sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TelaVaga.this, PerfilUsuario.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<VagaAplicada> call, Throwable t) {

                        Intent intent = new Intent(TelaVaga.this, TelaPrincipalUsuario.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
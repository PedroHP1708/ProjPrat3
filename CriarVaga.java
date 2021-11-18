package br.unicamp.projprat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarVaga extends AppCompatActivity {

    Button btnCriar;
    EditText edtTitulo, edtEndereco, edtArea, edtDescricao, edtSalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_vaga);

        btnCriar = (Button) findViewById(R.id.btnCriar);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtArea = (EditText) findViewById(R.id.edtArea);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        edtSalario = (EditText) findViewById(R.id.edtSalario);

        // vai ser apagado
        String emailEmpresa = "netflix@gmail.com";

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edtTitulo.getText().toString().equals("") && !edtEndereco.getText().toString().equals("") && !edtArea.getText().toString().equals("") && !edtDescricao.getText().toString().equals("") && !edtSalario.getText().toString().equals("")) {

                    Service service = RetrofitConfig.getRetrofitInstance().create((Service.class));
                    VagaAInserir vaga = new VagaAInserir(emailEmpresa, edtTitulo.getText().toString(), edtEndereco.getText().toString(), edtArea.getText().toString(), Double.parseDouble(edtSalario.getText().toString()), edtDescricao.getText().toString());


                    Call<VagaAInserir> call = service.incluirVaga(vaga);

                    call.enqueue(new Callback<VagaAInserir>() {
                        @Override
                        public void onResponse(Call<VagaAInserir> call, Response<VagaAInserir> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(CriarVaga.this, "Ocorreu um erro na requisicao", Toast.LENGTH_SHORT).show();
                            }

                            Toast.makeText(CriarVaga.this, "Vaga Criada com Sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CriarVaga.this, PerfilEmpresa.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<VagaAInserir> call, Throwable t) {

                            Intent intent = new Intent(CriarVaga.this, TelaPrincipalEmpresa.class);
                            startActivity(intent);
                        }
                    });



                }
                else {
                    Toast.makeText(CriarVaga.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

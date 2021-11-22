// Generated by view binder compiler. Do not edit!
package br.unicamp.projprat3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import br.unicamp.projprat3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPerfilEmpresaBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView cnpj;

  @NonNull
  public final EditText edtCnpj;

  @NonNull
  public final EditText edtEmail;

  @NonNull
  public final EditText edtEndereco;

  @NonNull
  public final EditText edtNome;

  @NonNull
  public final EditText edtSenha;

  @NonNull
  public final EditText edtTelefone;

  @NonNull
  public final TextView email;

  @NonNull
  public final TextView endereco;

  @NonNull
  public final TextView nome;

  @NonNull
  public final TextView senha;

  @NonNull
  public final TextView telefone;

  @NonNull
  public final GridView vagaAplicadaGridView;

  @NonNull
  public final View viFoto;

  private ActivityPerfilEmpresaBinding(@NonNull RelativeLayout rootView, @NonNull TextView cnpj,
      @NonNull EditText edtCnpj, @NonNull EditText edtEmail, @NonNull EditText edtEndereco,
      @NonNull EditText edtNome, @NonNull EditText edtSenha, @NonNull EditText edtTelefone,
      @NonNull TextView email, @NonNull TextView endereco, @NonNull TextView nome,
      @NonNull TextView senha, @NonNull TextView telefone, @NonNull GridView vagaAplicadaGridView,
      @NonNull View viFoto) {
    this.rootView = rootView;
    this.cnpj = cnpj;
    this.edtCnpj = edtCnpj;
    this.edtEmail = edtEmail;
    this.edtEndereco = edtEndereco;
    this.edtNome = edtNome;
    this.edtSenha = edtSenha;
    this.edtTelefone = edtTelefone;
    this.email = email;
    this.endereco = endereco;
    this.nome = nome;
    this.senha = senha;
    this.telefone = telefone;
    this.vagaAplicadaGridView = vagaAplicadaGridView;
    this.viFoto = viFoto;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPerfilEmpresaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPerfilEmpresaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_perfil_empresa, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPerfilEmpresaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cnpj;
      TextView cnpj = ViewBindings.findChildViewById(rootView, id);
      if (cnpj == null) {
        break missingId;
      }

      id = R.id.edtCnpj;
      EditText edtCnpj = ViewBindings.findChildViewById(rootView, id);
      if (edtCnpj == null) {
        break missingId;
      }

      id = R.id.edtEmail;
      EditText edtEmail = ViewBindings.findChildViewById(rootView, id);
      if (edtEmail == null) {
        break missingId;
      }

      id = R.id.edtEndereco;
      EditText edtEndereco = ViewBindings.findChildViewById(rootView, id);
      if (edtEndereco == null) {
        break missingId;
      }

      id = R.id.edtNome;
      EditText edtNome = ViewBindings.findChildViewById(rootView, id);
      if (edtNome == null) {
        break missingId;
      }

      id = R.id.edtSenha;
      EditText edtSenha = ViewBindings.findChildViewById(rootView, id);
      if (edtSenha == null) {
        break missingId;
      }

      id = R.id.edtTelefone;
      EditText edtTelefone = ViewBindings.findChildViewById(rootView, id);
      if (edtTelefone == null) {
        break missingId;
      }

      id = R.id.email;
      TextView email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.endereco;
      TextView endereco = ViewBindings.findChildViewById(rootView, id);
      if (endereco == null) {
        break missingId;
      }

      id = R.id.nome;
      TextView nome = ViewBindings.findChildViewById(rootView, id);
      if (nome == null) {
        break missingId;
      }

      id = R.id.senha;
      TextView senha = ViewBindings.findChildViewById(rootView, id);
      if (senha == null) {
        break missingId;
      }

      id = R.id.telefone;
      TextView telefone = ViewBindings.findChildViewById(rootView, id);
      if (telefone == null) {
        break missingId;
      }

      id = R.id.vagaAplicadaGridView;
      GridView vagaAplicadaGridView = ViewBindings.findChildViewById(rootView, id);
      if (vagaAplicadaGridView == null) {
        break missingId;
      }

      id = R.id.viFoto;
      View viFoto = ViewBindings.findChildViewById(rootView, id);
      if (viFoto == null) {
        break missingId;
      }

      return new ActivityPerfilEmpresaBinding((RelativeLayout) rootView, cnpj, edtCnpj, edtEmail,
          edtEndereco, edtNome, edtSenha, edtTelefone, email, endereco, nome, senha, telefone,
          vagaAplicadaGridView, viFoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

// Generated by view binder compiler. Do not edit!
package br.unicamp.projprat3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
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

public final class ActivityTelaPrincipalUsuarioBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnArea;

  @NonNull
  public final Button btnEmpresa;

  @NonNull
  public final Button btnLocalizacao;

  @NonNull
  public final ImageButton btnPesquisaUsuario;

  @NonNull
  public final Button btnSalario;

  @NonNull
  public final EditText edtBarraPesquisa;

  @NonNull
  public final TextView filtrarPor;

  @NonNull
  public final GridView vagaGridView;

  @NonNull
  public final View viFoto;

  private ActivityTelaPrincipalUsuarioBinding(@NonNull RelativeLayout rootView,
      @NonNull Button btnArea, @NonNull Button btnEmpresa, @NonNull Button btnLocalizacao,
      @NonNull ImageButton btnPesquisaUsuario, @NonNull Button btnSalario,
      @NonNull EditText edtBarraPesquisa, @NonNull TextView filtrarPor,
      @NonNull GridView vagaGridView, @NonNull View viFoto) {
    this.rootView = rootView;
    this.btnArea = btnArea;
    this.btnEmpresa = btnEmpresa;
    this.btnLocalizacao = btnLocalizacao;
    this.btnPesquisaUsuario = btnPesquisaUsuario;
    this.btnSalario = btnSalario;
    this.edtBarraPesquisa = edtBarraPesquisa;
    this.filtrarPor = filtrarPor;
    this.vagaGridView = vagaGridView;
    this.viFoto = viFoto;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTelaPrincipalUsuarioBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTelaPrincipalUsuarioBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_tela_principal_usuario, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTelaPrincipalUsuarioBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnArea;
      Button btnArea = ViewBindings.findChildViewById(rootView, id);
      if (btnArea == null) {
        break missingId;
      }

      id = R.id.btnEmpresa;
      Button btnEmpresa = ViewBindings.findChildViewById(rootView, id);
      if (btnEmpresa == null) {
        break missingId;
      }

      id = R.id.btnLocalizacao;
      Button btnLocalizacao = ViewBindings.findChildViewById(rootView, id);
      if (btnLocalizacao == null) {
        break missingId;
      }

      id = R.id.btnPesquisaUsuario;
      ImageButton btnPesquisaUsuario = ViewBindings.findChildViewById(rootView, id);
      if (btnPesquisaUsuario == null) {
        break missingId;
      }

      id = R.id.btnSalario;
      Button btnSalario = ViewBindings.findChildViewById(rootView, id);
      if (btnSalario == null) {
        break missingId;
      }

      id = R.id.edtBarraPesquisa;
      EditText edtBarraPesquisa = ViewBindings.findChildViewById(rootView, id);
      if (edtBarraPesquisa == null) {
        break missingId;
      }

      id = R.id.filtrar_por;
      TextView filtrarPor = ViewBindings.findChildViewById(rootView, id);
      if (filtrarPor == null) {
        break missingId;
      }

      id = R.id.vagaGridView;
      GridView vagaGridView = ViewBindings.findChildViewById(rootView, id);
      if (vagaGridView == null) {
        break missingId;
      }

      id = R.id.viFoto;
      View viFoto = ViewBindings.findChildViewById(rootView, id);
      if (viFoto == null) {
        break missingId;
      }

      return new ActivityTelaPrincipalUsuarioBinding((RelativeLayout) rootView, btnArea, btnEmpresa,
          btnLocalizacao, btnPesquisaUsuario, btnSalario, edtBarraPesquisa, filtrarPor,
          vagaGridView, viFoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
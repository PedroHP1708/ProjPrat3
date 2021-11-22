// Generated by view binder compiler. Do not edit!
package br.unicamp.projprat3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import br.unicamp.projprat3.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemAplicacaoPendenteBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button btnAprovar;

  @NonNull
  public final Button btnRejeitar;

  @NonNull
  public final TextView tvItemNomeUsuario;

  @NonNull
  public final TextView tvItemTituloVaga;

  @NonNull
  public final View viFoto;

  private ItemAplicacaoPendenteBinding(@NonNull CardView rootView, @NonNull Button btnAprovar,
      @NonNull Button btnRejeitar, @NonNull TextView tvItemNomeUsuario,
      @NonNull TextView tvItemTituloVaga, @NonNull View viFoto) {
    this.rootView = rootView;
    this.btnAprovar = btnAprovar;
    this.btnRejeitar = btnRejeitar;
    this.tvItemNomeUsuario = tvItemNomeUsuario;
    this.tvItemTituloVaga = tvItemTituloVaga;
    this.viFoto = viFoto;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemAplicacaoPendenteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemAplicacaoPendenteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_aplicacao_pendente, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemAplicacaoPendenteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAprovar;
      Button btnAprovar = ViewBindings.findChildViewById(rootView, id);
      if (btnAprovar == null) {
        break missingId;
      }

      id = R.id.btnRejeitar;
      Button btnRejeitar = ViewBindings.findChildViewById(rootView, id);
      if (btnRejeitar == null) {
        break missingId;
      }

      id = R.id.tvItemNomeUsuario;
      TextView tvItemNomeUsuario = ViewBindings.findChildViewById(rootView, id);
      if (tvItemNomeUsuario == null) {
        break missingId;
      }

      id = R.id.tvItemTituloVaga;
      TextView tvItemTituloVaga = ViewBindings.findChildViewById(rootView, id);
      if (tvItemTituloVaga == null) {
        break missingId;
      }

      id = R.id.viFoto;
      View viFoto = ViewBindings.findChildViewById(rootView, id);
      if (viFoto == null) {
        break missingId;
      }

      return new ItemAplicacaoPendenteBinding((CardView) rootView, btnAprovar, btnRejeitar,
          tvItemNomeUsuario, tvItemTituloVaga, viFoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
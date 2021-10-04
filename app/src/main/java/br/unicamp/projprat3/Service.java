package br.unicamp.projprat3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

interface Service {

    @GET("/api/usuario/{id}")
    //@GET("/api/dog/get/{id}")
    Call<Usuario> getUsuario(@Path("id") int id);

    @DELETE("/api/usuario/{id}")
    Call<Usuario> excluirUsuario(@Path("id") String id);

    @POST("/api/usuario")
    Call<Usuario> incluirUsuario(@Body Usuario usuario);

    @PUT("/api/dog/{id}")
    Call<Usuario> alterarUsuario(@Path("id") String id, @Body Usuario usuario);

    @GET("/api/empresa/{id}")
    Call<Empresa> getEmpresa(@Path("id") int id);

    @DELETE("/api/empresa/{id}")
    Call<Empresa> excluirEmpresa(@Path("id") String id);

    @POST("/api/empresa")
    Call<Empresa> incluirEmpresa(@Body Empresa empresa);

    @PUT("/api/dog/{id}")
    Call<Empresa> alterarEmpresa(@Path("id") String id, @Body Empresa empresa);

    /*CRIAR VAGA E VAGA APLICADA*/
}

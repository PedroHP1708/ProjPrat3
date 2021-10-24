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

    //Operacoes com empresa
    @GET("/api/usuario")
    Call<List<Usuario>> getUsuarios();

    @GET("/api/usuario/{id}")
    Call<Usuario> getUsuario(@Path("id") String id);

    @DELETE("/api/usuario/{id}")
    Call<Usuario> excluirUsuario(@Path("id") String id);

    @POST("/api/usuario")
    Call<Usuario> incluirUsuario(@Body Usuario usuario);

    @PUT("/api/dog/{id}")
    Call<Usuario> alterarUsuario(@Path("id") String id, @Body Usuario usuario);

    //Operacoes com empresa
    @GET("/api/empresa")
    Call<List<Empresa>> getEmpresas();

    @GET("/api/empresa/{id}")
    Call<Empresa> getEmpresa(@Path("id") String id);

    @DELETE("/api/empresa/{id}")
    Call<Empresa> excluirEmpresa(@Path("id") String id);

    @POST("/api/empresa")
    Call<Empresa> incluirEmpresa(@Body Empresa empresa);

    @PUT("/api/empresa/{id}")
    Call<Empresa> alterarEmpresa(@Path("id") String id, @Body Empresa empresa);

    //Operacoes com vagas
    @GET("/api/vaga")
    Call<List<Vaga>> getVagas();

    @GET("/api/vaga/{id}")
    Call<Vaga> getVaga(@Path("id") String id);

    @DELETE("/api/vaga/{id}")
    Call<Vaga> excluirVaga(@Path("id") String id);

    @POST("/api/vaga")
    Call<Vaga> incluirVaga(@Body Vaga vaga);

    @PUT("/api/dog/{id}")
    Call<Vaga> alterarVaga(@Path("id") String id, @Body Vaga vaga);
}

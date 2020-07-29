package com.example.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.petagram.model.Animal;
import com.example.petagram.model.ConstructorAnimales;
import com.example.petagram.restApi.EndpointsApi;
import com.example.petagram.restApi.adapter.RestApiAdapter;
import com.example.petagram.restApi.model.AnimalResponse;
import com.example.petagram.vista.fragment.IRecyclerViewUser;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecyclerViewUserPresenter implements IRecylerViewUserPresenter {

    private IRecyclerViewUser iRecyclerViewFragmentView;
    private Context context;
    private ConstructorAnimales constructorAnimales;
    private ArrayList<Animal> animales;

    public RecyclerViewUserPresenter(IRecyclerViewUser iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerAnimalesBaseDatos() {
        constructorAnimales = new ConstructorAnimales(context);
        animales = constructorAnimales.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<AnimalResponse> animalResponseCall = endpointsApi.getRecentMedia();

        animalResponseCall.enqueue(new Callback<AnimalResponse>() {
            @Override
            public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                AnimalResponse animalResponse = response.body();
                animales = animalResponse.getAnimales();
                mostrarContactosRV();
            }

            @Override
            public void onFailure(Call<AnimalResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });



    }

    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(animales));
        iRecyclerViewFragmentView.generarGridLayout();
    }
}

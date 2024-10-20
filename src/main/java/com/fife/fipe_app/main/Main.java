package com.fife.fipe_app.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fife.fipe_app.model.*;

import com.fife.fipe_app.service.ConsumeAPI;
import com.fife.fipe_app.service.ConvertData;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ConsumeAPI consume = new ConsumeAPI();
    private ConvertData convert = new ConvertData();
    
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1";
    String json;
 
    public void consult() {

        var searchAuto = showMenu();
        consultAutos(searchAuto);

        System.out.println("Digite o código da marca que deseja consultar: ");
        var codOfMarca = scanner.nextLine();
        consultMarca(searchAuto, codOfMarca);


    }

    public String showMenu() {
        System.out.println("O que deseja persquisar? Carro, Moto, Caminhão");
        String value = scanner.nextLine();
        String searchValue;

        if(value.toLowerCase().contains("carr")) {
            searchValue = "/carros";
        } else if (value.toLowerCase().contains("mot")) {
            searchValue = "/motos";
        } else if (value.toLowerCase().contains("cam")) {
            searchValue = "/caminhoes";
        } else {
            throw new Error("Valor invalido");
        }

        System.out.println(searchValue);
        return searchValue;
    }

    public void consultAutos(String searchValue) {
        List<DataMarca> marcas = new ArrayList<>();
        json = consume.creatData(BASE_URL + searchValue + "/marcas");
        DataMarca[] dataMarcas = convert.getData(json, DataMarca[].class);
        for(DataMarca data : dataMarcas) {
            marcas.add(data);
            System.out.println(data);
        }
    }

    public void consultMarca(String searchValue, String codOfMarca) {
        System.out.println(codOfMarca);
        System.out.println(searchValue);
        System.out.println(BASE_URL + searchValue + "/marcas/" + codOfMarca + "/modelos");
    
        json = consume.creatData(BASE_URL + searchValue + "/marcas/" + codOfMarca + "/modelos");
    
        ResponseModelos responseModelos = convert.getData(json, ResponseModelos.class);
    
        List<DataModelos> modelos = responseModelos.modelos();
        for (DataModelos modelo : modelos) {
            System.out.println(modelo);
        }
    }
}

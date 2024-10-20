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
    
    private String url = "https://parallelum.com.br/fipe/api/v1";
    String json;
 
    public void consult() {

        var searchAuto = showMenu();
        consultAutos(searchAuto);

        System.out.println("Digite o código da marca que deseja consultar: ");
        var codOfMarca = scanner.nextLine();
        consultMarca(codOfMarca);

        System.out.println("Qual modelo deseja consultar? ");
        var codOfModelo = scanner.nextLine();
        consultModel(codOfModelo);

        System.out.println("Qual ano deseja consultar? ");
        var year = scanner.nextLine();
        consultCar(year);

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
        return searchValue;
    }

    public void consultAutos(String searchValue) {
        url = url + searchValue + "/marcas";
        List<DataMarca> marcas = new ArrayList<>();
        json = consume.creatData(url);
        DataMarca[] dataMarcas = convert.getData(json, DataMarca[].class);
        for(DataMarca data : dataMarcas) {
            marcas.add(data);
            System.out.println(data);
        }
    }

    public void consultMarca(String codOfMarca) {
        System.out.println(url + codOfMarca + "/modelos");
        url = url + "/" + codOfMarca + "/modelos";
        json = consume.creatData(url);
    
        ResponseModelos responseModelos = convert.getData(json, ResponseModelos.class);
    
        List<DataModelos> modelos = responseModelos.modelos();
        for (DataModelos modelo : modelos) {
            System.out.println(modelo);
        }
    }

    public void consultModel(String codOfModel) {
        url = url + "/" + codOfModel+ "/" + "anos/";
        
        json = consume.creatData(url);

        DataModelos[] responseModelos = convert.getData(json, DataModelos[].class);
        System.out.println("Anos: ");
        for(DataModelos modelo : responseModelos) {
            System.out.println(modelo);
        }
    }

    public void consultCar(String year) {
        url = url + year;
        System.out.println(url);
        json =  consume.creatData(url);

        DataCar car =  convert.getData(json, DataCar.class);
        System.out.println(car);
    }
}

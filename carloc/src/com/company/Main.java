package com.company;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Pedido pedido = new Pedido();
        List<Calendar> dates;
        String carType;
        int numPass;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test01.txt")));
        String aux = br.readLine();

        while (aux != null){
            System.out.println(aux);
            String[] data = aux.split(":");
            String[] datas = data[2].split(",");
            carType = data[0];
            numPass = Integer.valueOf(data[1]);
            dates = new ArrayList<>();

            for (int i = 0; i < datas.length; i++) {
                Calendar dateAux = Calendar.getInstance();
                Date date = new SimpleDateFormat("ddMMMyyyy").parse(datas[i].substring(0, 9));
                dateAux.setTime(date);
                dates.add(dateAux);
            }

            String resp = pedido.findCar(carType, numPass, dates);
            System.out.println(resp);

            aux = br.readLine();
        }

        br.close();
    }
}

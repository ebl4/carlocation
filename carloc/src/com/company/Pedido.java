package com.company;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dell on 03/12/2016.
 */
public class Pedido {

    public String findCar(String tipoCarro, int numPass, List<Calendar> dates) {
        String locador = "";
        int numWeekend = 0, numDayOfWeek = 0, numDays = dates.size(), numIntevalDays = 0, numIntervalWDays = 0;
        int numUtilDays = 0, southCarCost = 0, northCarCost = 0;

        if (numPass > 4) {
            locador = "NorthCar";
        }
        //Independente do tipo de carro, o numero de passageiros deve indicar o carro mais barato
        else if (numPass <= 4 && numPass > 2) {
            locador = "SouthCar";
        } else {
            numWeekend = qtdWeekend(dates);
            numDayOfWeek = numDays - numDayOfWeek;

             /*
        Fidelity customers, reward dates
         */
            if (numDays > 2) {
                locador = "WestCar";
            }
                         /*
        Regular customers, interval dates
         */
            else if (numDays == 2){
                numIntevalDays = numOfDays(dates.get(0), dates.get(1));
                numIntervalWDays = numWeekendDays(dates.get(0), dates.get(1));
                numUtilDays = numIntevalDays - numIntervalWDays;
                southCarCost = cost(numUtilDays, numIntervalWDays, 210, 200);
                northCarCost = cost(numUtilDays, numIntervalWDays, 530, 200);

                if (southCarCost < northCarCost) locador = "SouthCar";
                else locador = "NorthCar";

            }
        }

        return locador;
    }

    public int qtdWeekend(List<Calendar> dates){
        int qtdeW = 0;
        for (int i = 0; i < dates.size(); ++i){
            if(isWeekend(dates.get(i))){
                qtdeW++;
            }
        }
        return qtdeW;
    }

    public boolean isWeekend(Calendar date){
        return (date.get(Calendar.DAY_OF_WEEK) ==  Calendar.SUNDAY ||
                date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) ? true : false;
    }

    public int numOfDays(Calendar date1, Calendar date2){
        return date1.get(Calendar.DAY_OF_YEAR) - date2.get(Calendar.DAY_OF_YEAR);
    }

    public int numWeekendDays(Calendar date1, Calendar date2){
        int numWDays = 0;
        int numDate1 = date1.get(Calendar.DAY_OF_YEAR), numDate2 = Calendar.DAY_OF_YEAR;
        for (int i = numDate1; i < numDate2; ++i){
            Calendar dataAux = Calendar.getInstance();
            dataAux.set(Calendar.DAY_OF_MONTH, i);
            if(dataAux.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || dataAux.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                numWDays++;
            }
        }
        return numWDays;
    }

    public int cost(int numUtilDays, int numWDays, int priceUtilDay, int priceWDay){
        int total = numUtilDays*priceUtilDay + numWDays*priceWDay;
        return total;
    }
}

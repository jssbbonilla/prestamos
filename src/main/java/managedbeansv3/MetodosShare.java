/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeansv3;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class MetodosShare {

    public String limpiarFormatoUtilDate(String fechaIngresada) throws ParseException {
        String fecha = fechaIngresada;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date date;
        date = (Date) formatter.parse(fecha);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formatedDate = cal.get(Calendar.YEAR) + "/"
                + (cal.get(Calendar.MONTH) + 1)
                + "/" + cal.get(Calendar.DATE);

        return formatedDate;
    }

    public Date utilDatetoSqlDate(String fechaIngresada) throws ParseException {
        String fecha = fechaIngresada;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        java.util.Date utilDate = (java.util.Date) formatter.parse(fecha);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public Date stringToSqlDate(String fechaIngresada) throws ParseException {
        String fecha = fechaIngresada;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date utilDate = (java.util.Date) formatter.parse(fecha);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public double mascaraDosDigitos(double valor) {
        double resultado = 0;
        double filtroDigitos = 0;
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat decimales = new DecimalFormat("#.##", simbolo);

        resultado = valor;
        String covertidor = decimales.format(resultado);
        filtroDigitos = Double.valueOf(covertidor);

        return filtroDigitos;
    }

    public int obtenerMesesFecha(String fechaIngresada) {
        int mes = 0;

        try {
            String fecha = fechaIngresada;
            char[] fechas = fecha.toCharArray();
            String mesesfecha = "" + fechas[5] + fechas[6];
            mes = Integer.parseInt(mesesfecha);

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
        return mes;
    }

    public int diferenciaDiasFechas(String fechaMenor, String fechaMayor) {

        String fechaAnterior = fechaMenor;
        String[] fechaAnt = fechaAnterior.split("-");
        Integer anioAnt = Integer.parseInt(fechaAnt[0]);
        Integer mesAnt = Integer.parseInt(fechaAnt[1]);
        Integer diaAnt = Integer.parseInt(fechaAnt[2]);

        String fechaActual = fechaMayor;
        String[] fechaHoy = fechaActual.split("-");
        Integer anioHoy = Integer.parseInt(fechaHoy[0]);
        Integer mesHoy = Integer.parseInt(fechaHoy[1]);
        Integer diaHoy = Integer.parseInt(fechaHoy[2]);

        int anio;
        int mes;
        int dia;

        //años
        int d1 = anioHoy - anioAnt;
        anio = 360 * (d1);
        //meses
        int d2 = mesHoy - mesAnt;
        mes = 30 * (d2);
        //dias
        dia = diaHoy - diaAnt;
        int totaldias = dia + mes + anio;
        System.out.println("numero de dias: " + totaldias);

        return totaldias;
    }

    public int diferenciaDiasDeMesTranscurrido(String fechaMenor, String fechaMayor) {

        String fechaAnterior = fechaMenor;
        String[] fechaAnt = fechaAnterior.split("-");
        Integer anioAnt = Integer.parseInt(fechaAnt[0]);
        Integer mesAnt = Integer.parseInt(fechaAnt[1]);
        Integer diaAnt = Integer.parseInt(fechaAnt[2]);

        String fechaActual = fechaMayor;
        String[] fechaHoy = fechaActual.split("-");
        Integer anioHoy = Integer.parseInt(fechaHoy[0]);
        Integer mesHoy = Integer.parseInt(fechaHoy[1]);
        Integer diaHoy = Integer.parseInt(fechaHoy[2]);

        int anio;
        int mes;
        //años
        int d1 = anioHoy - anioAnt;
        anio = 12 * (d1);
        //meses
        int d2 = mesHoy - mesAnt + anio;
        mes = 30 * (d2);
        int totalmeses = mes;

        System.out.println("numero de dias de meses: " + totalmeses);

        return totalmeses;
    }

    public int encontrarMeses(String fechaIncial, String fechaFinal) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fInicial = dateFormat.parse(fechaIncial);
            Date fFinal = dateFormat.parse(fechaFinal);

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(fInicial);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(fFinal);

            //Cálculo de meses para las fechas de inicio y finalización
            int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            //Diferencia en meses entre las dos fechas
            int diffMonth = endMes - startMes;
            System.out.println("Hay " + diffMonth + " meses de diferencia");
            return diffMonth;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public int encontrarDias(String fechaIncial, String fechaFinal) {
        int totalDias = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fInicial = dateFormat.parse(fechaIncial);
            Date fFinal = dateFormat.parse(fechaFinal);

            totalDias = (int) ((fFinal.getTime() - fInicial.getTime()) / 86400000);

            System.out.println("Hay " + totalDias + " dias de diferencia");
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
        return totalDias;
    }
}

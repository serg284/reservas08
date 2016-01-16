package reservas21;

import java.io.*;
import java.util.Scanner;


public class reservas21 {
    public static void main(String[] args) {

        //arrays de salas.txt
        int[]       salaIdSala = new int[100];                 //coluna1
        String[]    salaNomeSala = new String[100];            //coluna2
        int[]       salaFilas = new int[100];                  //coluna3
        int[]       salaLugares = new int[100];                //coluna4
        String[]    salaNomeEspetaculo = new String[100];      //coluna5
        int[]       salaPrecoBilhete = new int[100];           //coluna6
        //novos arrays
        int[]       salaTotalLugares = new int[100];        
        
        //arrays de reservas.txt
        int[]       reservaIdSala = new int[100];              //coluna1
        String[]    reservaNomeEspetaculo = new String[100];   //coluna2
        char[]      reservaCondicao = new char[100];           //coluna3
        int[]       reservaFila = new int[100];                //coluna4
        int[]       reservaLugar = new int[100];               //coluna5
        int[]       reservaDia = new int[100];                 //coluna6
        int[]       reservaMes = new int[100];                 //coluna7
        char[]      reservaSessao = new char[100];             //coluna8

        //arrays de clientes
        String[]    clientes = new String[100];   //clientes. precisamos para mostrar DE QUEM sao as reservas.
        String[]    clientesLugares = new String[100]; // contem os lugares reservados dos clientes acima.
        
        //declara variaveis
        int num_salas = 0;
        int num_reservas = 0;
        int num_clientes = 0;
        int i, j;
        String output= "";

        
        /**
         *********************************************
         ************ salas.txt **********************
         *********************************************
         */

        // salas.txt : le dados do ficheiro
        Scanner scanner;
        try	{
            scanner = new Scanner( new File("salas.txt") ).useDelimiter( "\\s*:\\s*|\\s*\n\\s*" );
        }
        catch( FileNotFoundException ex )
        {
            System.out.println( ex );
            return;
        }
        
        scanner.skip( "\\s*" );
        
        
        // salas.txt: separa o conteudo da variavel scanner em arrays diferentes.
        for( i = 0;  scanner.hasNextLine();  i++ ){
            salaIdSala[i]         = scanner.nextInt();      //coluna1
            salaNomeSala[i]       = scanner.next();         //coluna2
            salaFilas[i]          = scanner.nextInt();      //coluna3
            salaLugares[i]        = scanner.nextInt();      //coluna4
            salaNomeEspetaculo[i] = scanner.next();         //coluna5
            salaPrecoBilhete[i]   = scanner.nextInt();      //coluna6

            scanner.skip( "\\s*" );
            num_salas++;
        }
        
        
        // salas.txt : output para ecra
        System.out.println("*** salas ***");
        
        for( i = 0;  i < num_salas;  i++ ){
            System.out.print  ( "L" + i + ":\t") ;
            System.out.print  ( "C1:" + salaIdSala[i]  +        "\t");
            System.out.print  ( "C2:" + salaNomeSala[i] +      "\t");
            System.out.print  ( "C3:" + salaFilas[i] +         "\t");
            System.out.print  ( "C4:" + salaLugares[i] +       "\t");
            System.out.print  ( "C5:" + salaNomeEspetaculo[i] + "\t");
            System.out.println( "C6:" + salaPrecoBilhete[i]  +  "\t");
        }

         /**
         *********************************************
         ************ reservas.txt *******************
         *********************************************
         */

        // reservas.txt : le dados do ficheiro
        Scanner scannerReservas;
        try {
            scannerReservas = new Scanner( new File("reservas.txt") ).useDelimiter( "\\s*:\\s*|\\s*\n\\s*" );
        }
        catch( FileNotFoundException ex )
        {
            System.out.println( ex );
            return;
        }
        scannerReservas.skip( "\\s*" );

        // reservas.txt: separa o conteudo da variavel scanner em arrays diferentes.
        System.out.println("*** reservas ***");
        for( j = 0;  scannerReservas.hasNextLine();  j++ ){
            reservaIdSala[j] = scannerReservas.nextInt();           //coluna1
            reservaNomeEspetaculo[j] = scannerReservas.next();      //coluna2
            reservaCondicao[j] = scannerReservas.next().charAt(0);  //coluna3
            reservaFila[j]  = scannerReservas.nextInt();            //coluna4
            reservaLugar[j]  = scannerReservas.nextInt();           //coluna5
            reservaDia[j]    = scannerReservas.nextInt();           //coluna6
            reservaMes[j]   = scannerReservas.nextInt();            //coluna7
            reservaSessao[j] = scannerReservas.next().charAt(0);    //coluna8
 
            scannerReservas.skip( "\\s*" );
            num_reservas++;
        }


        // reservas.txt : output para ecra
        for( i = 0;  i < num_reservas ;  i++ ){
            System.out.print  ( "L" + i + ":\t") ;
            System.out.print  ( "C1:" + reservaIdSala[i]          + "\t");
            System.out.print  ( "C2:" + reservaNomeEspetaculo[i]  + "\t");
            System.out.print  ( "C3:" + reservaCondicao[i]        + "\t");
            System.out.print  ( "C4:" + reservaFila[i]            + "\t");
            System.out.print  ( "C5:" + reservaLugar[i]           + "\t");
            System.out.print  ( "C6:" + reservaDia[i]             + "\t");
            System.out.print  ( "C7:" + reservaMes[i]             + "\t");
            System.out.println( "C8:" + reservaSessao[i]          + "\t");
        }

        //output
        System.out.println("** inicio **");
        System.out.println("reservaCondicao.lenght:" + reservaCondicao.length);
        System.out.println("num_reservas:" + num_reservas);


        // construir lista de clientes
     
        
        
        
        

        // total de lugares livres  
        // oO!!! opsss! isto é para TODAS as salas....! 
        // nao está a mostrar para cada sala individual.
        int totalReservas = 0;
        int totalCompras = 0;        
        int totalAnulacoes = 0;    
        int totalReservasN = 0; 
        for (int k = 0; k < num_reservas; k++) {
            if (reservaCondicao[k] == 'R') {
                totalReservas++;
            }
            if (reservaCondicao[k] == 'C') {
                totalCompras++;
            }
            if (reservaCondicao[k] == 'A') {
                totalAnulacoes++;
            }    
            //somar o valor das condicoes N = reservas multiplas.
            if (reservaCondicao[k] >= '0' && reservaCondicao[k] <= '9') {
                System.out.println(reservaCondicao[k]);

            int reservaCondicaoInt = Integer.parseInt(String.valueOf(reservaCondicao[k]));   
            //source: http://stackoverflow.com/questions/2683324/java-char-array-to-int
            totalReservasN+=reservaCondicaoInt;
            }
      }
        System.out.println("total Reservas:" + totalReservas);            
        System.out.println("Total Compras:" + totalCompras);   
        System.out.println("Total Anulacoes:" + totalAnulacoes);   
        System.out.println("Total ReservasN:" + totalReservasN);   

    //total lugares livres = (salaTotalLugares - salaReservas - salaCompras) + salaAnulacoes     
    //total de lugares nas salas
    for (int k = 0; k < num_salas; k++) {
        salaTotalLugares[k] = salaFilas[k] * salaLugares[k] ;
        System.out.println(salaNomeSala[k] +  "\tfilas:" + salaFilas[k] + "\t lug:" + salaLugares[k] + " \t total lug:" + salaTotalLugares[k] ) ;
    }



        
        
    }
}
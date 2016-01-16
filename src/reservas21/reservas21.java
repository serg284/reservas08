package reservas21;

import java.io.*;
import java.util.Scanner;


public class reservas21 {
    public static void main(String[] args) {

        //arrays de salas.txt
        int[]     salaIdSala = new int[100];                 //coluna1
        String[]  salaNomeSala = new String[100];            //coluna2
        int[]     salaFilas = new int[100];                  //coluna3
        int[]     salaLugares = new int[100];                //coluna4
        String[]  salaNomeEspetaculo = new String[100];      //coluna5
        int[]     salaPrecoBilhete = new int[100];           //coluna6
        //novos arrays
        int[]     salaTotalLugares = new int[100];                 //coluna1
        
        //arrays de reservas.txt
        int[]     reservaIdSala = new int[100];              //coluna1
        String[]  reservaNomeEspetaculo = new String[100];   //coluna2
        char[]    reservaCondicao = new char[100];           //coluna3
        int[]     reservaFila = new int[100];                //coluna4
        int[]     reservaLugar = new int[100];               //coluna5
        int[]     reservaDia = new int[100];                 //coluna6
        int[]     reservaMes = new int[100];                 //coluna7
        char[]    reservaSessao = new char[100];             //coluna8

        //declara variaveis
        int num_salas = 0;
        int num_reservas = 0;
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
        System.out.println("\tC1:idSala \tC2:nomeSala \tC3:fila \tC4:lugares \tC5:nomeEspetaculo \tC6:precoBilhete");
        
        for( i = 0;  i < num_salas;  i++ ){
            System.out.print  ( "L" + i + ":\t") ;
            System.out.print  ( "C1:" + salaIdSala[i]  +        "\t\t");
            System.out.print  ( "C2:" + salaNomeSala[i] +      "\t");
            System.out.print  ( "C3:" + salaFilas[i] +         "\t\t");
            System.out.print  ( "C4:" + salaLugares[i] +       "\t\t");
            System.out.print  ( "C5:" + salaNomeEspetaculo[i] + "\t\t");
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
        //for( j = 0;  j < num_reservas;  j++ ){
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
        
        //output.txt

System.out.println("** inicio **");
System.out.println("reservaCondicao.lenght:" + reservaCondicao.length);
System.out.println("num_reservas:" + num_reservas);




// total de lugares livres
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
            //aqui, construir uma maneira de somar o valor dos Ns = reservas multiplas.
            if (reservaCondicao[k] == 'A') {
                totalReservasN++;
            }    
    }
        System.out.println("total Reservas:" + totalReservas);            
        System.out.println("Total Compras:" + totalCompras);   
        System.out.println("Total Anulacoes:" + totalAnulacoes);   
        System.out.println("Total ReservasN:" + totalReservasN);   






//lugares livres
//total lugares livres = (salaTotalLugares - salaReservas - salaCompras) + salaAnulacoes     


//total de lugares nas salas
        for (int k = 0; k < num_salas; k++) {
            //System.out.println("nome da sala:" + salaNomeSala[k]);
            salaTotalLugares[k] = salaFilas[k] * salaLugares[k] ;
            System.out.println(salaNomeSala[k] +  " filas:" + salaFilas[k] + " lug:" + salaLugares[k] + " total lug:" + salaTotalLugares[k] ) ;
        }



        
        
    }
}
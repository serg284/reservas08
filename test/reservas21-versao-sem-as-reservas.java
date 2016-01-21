package reservas21;


import java.io.*;
import java.util.Scanner;


public class reservas21 {
	
	/***********************
	 * Variaveis
	 ***********************/
	
	//arrays de salas.txt
	private static int[]     salaIdSala = new int[100];                 //coluna1
	private static String[]  salaNomeSala = new String[100];            //coluna2
	private static int[]     salaFilas = new int[100];                  //coluna3
	private static int[]     salaLugares = new int[100];                //coluna4
	private static String[]  salaNomeEspetaculo = new String[100];      //coluna5
	private static int[]     salaPrecoBilhete = new int[100];           //coluna6
	//novos arrays
	private static int[]     salaTotalLugares = new int[100];            //coluna1

	//arrays de reservas.txt
	private static int[]     reservaIdSala = new int[100];              //coluna1
	private static String[]  reservaNomeCliente = new String[100];   //coluna2
	private static char[]    reservaCondicao = new char[100];           //coluna3
	private static int[]     reservaFila = new int[100];                //coluna4
	private static int[]     reservaLugar = new int[100];               //coluna5
	private static int[]     reservaDia = new int[100];                 //coluna6
	private static int[]     reservaMes = new int[100];                 //coluna7
	private static char[]    reservaSessao = new char[100];             //coluna8

	//array clientes
	private static String[]	clientesNome = new String[100];
	private static String[]	clientesLugares = new String[100];

	//declara variaveis
	private static int num_salas = 0;
	private static int num_reservas = 0;
	private static int num_clientes =0;
	
        //remove o ultimo char de uma string. vamos precisar no mapa de lugares
        //source: http://stackoverflow.com/questions/7438612/how-to-remove-the-last-character-from-a-string
        private static String removeLastChar(String str) {
            return str.substring(0,str.length()-1);
        }

	//Converte um char array em int, vamos usar nas reservas multiplas, em digito
        //source: http://stackoverflow.com/questions/2683324/java-char-array-to-int
	private static int charParaInt(char numeroChar) {
		return (int) numeroChar-'0';
	}
	
	//Dado um codigo de sessao (em char) devolve o equivalente numerico
	private static int sessaoNumero(char sessaoChar) {
		switch (sessaoChar) {
		case 'M': return 0;
		case 'T': return 1;
		case 'N': return 2;
		}
		return -1; //Erro
	}
	
	//Dado um nome de cliente devolve o seu id
	//o id E o indice no array de clientes + 1
	private static int procuraCliente(String nomeCliente) {
		for (int c=0; c<num_clientes; c++) {
			if (clientesNome[c].equals(nomeCliente))    //tem de ser equals, porque é string. = só funciona em int.
				return c+1;
		}
		return -1; //Erro. Cliente nao encontrado
	}
	
	//Devolve uma String com a representacao do mapa de uma sala
	private static String imprimirMapa(int[][] mapa, int f_max, int l_max) {
		String mapaString="";
		for (int f=0; f<f_max; f++) {
			for (int l=0; l<l_max; l++) {
				if (mapa[f][l]==0)
					mapaString+="L|";
				else if (mapa[f][l]>0)
					mapaString+=("C|");
				else
					mapaString+=("R|");
			}
			mapaString+="\n";
		}
		
		//TODO
		//Eliminar | ao final
                
		return mapaString;
	}
	
	public static void main(String[] args) {

		int i, j;
		String output= "";

/*********************************************
 ************ salas.txt **********************
 *********************************************/

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


// *************** mostra salas.txt no ecra **************************
//System.out.println("*** salas ***");
//System.out.println("\tC1:idSala \tC2:nomeSala \tC3:fila \tC4:lugares \tC5:nomeEspetaculo \tC6:precoBilhete");
//for( i = 0;  i < num_salas;  i++ ){
//    System.out.print  ( "L" + i + ":\t") ;
//    System.out.print  ( "C1:" + salaIdSala[i]  +        "\t\t");
//    System.out.print  ( "C2:" + salaNomeSala[i] +      "\t");
//    System.out.print  ( "C3:" + salaFilas[i] +         "\t\t");
//    System.out.print  ( "C4:" + salaLugares[i] +       "\t\t");
//    System.out.print  ( "C5:" + salaNomeEspetaculo[i] + "\t\t");
//    System.out.println( "C6:" + salaPrecoBilhete[i]  +  "\t");
//}


 /**********************************
  * reservas.txt
  **********************************/

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
//System.out.println("*** reservas ***");
for( j = 0;  scannerReservas.hasNextLine();  j++ ){
    reservaIdSala[j] = scannerReservas.nextInt();           //coluna1
    reservaNomeCliente[j] = scannerReservas.next();      //coluna2
    reservaCondicao[j] = scannerReservas.next().charAt(0);  //coluna3
    reservaFila[j]  = scannerReservas.nextInt();            //coluna4
    reservaLugar[j]  = scannerReservas.nextInt();           //coluna5
    reservaDia[j]    = scannerReservas.nextInt();           //coluna6
    reservaMes[j]   = scannerReservas.nextInt();            //coluna7
    reservaSessao[j] = scannerReservas.next().charAt(0);    //coluna8

    scannerReservas.skip( "\\s*" );
    num_reservas++;
}


// ******* mostra o conteudo de reservas.txt no ecra ************
//for( i = 0;  i < num_reservas ;  i++ ){
//    System.out.print  ( "L" + i + ":\t") ;
//    System.out.print  ( "C1:" + reservaIdSala[i]          + "\t");
//    System.out.print  ( "C2:" + reservaNomeCliente[i]		+ "\t");
//    System.out.print  ( "C3:" + reservaCondicao[i]        + "\t");
//    System.out.print  ( "C4:" + reservaFila[i]            + "\t");
//    System.out.print  ( "C5:" + reservaLugar[i]           + "\t");
//    System.out.print  ( "C6:" + reservaDia[i]             + "\t");
//    System.out.print  ( "C7:" + reservaMes[i]             + "\t");
//    System.out.println( "C8:" + reservaSessao[i]          + "\t");
//}



// ****** mostra total de clientes no ecra ***********
//for (i=0; i<num_reservas; i++) {
//	
//	for (j=0; j<num_clientes; j++) {
//		if (reservaNomeCliente[i].equals(clientesNome[j]))
//			break;
//	}
//	
//	if (j==num_clientes) {
//		clientesNome[num_clientes]=reservaNomeCliente[i];
//		num_clientes++;
//	}
//}
//System.out.println("Encontrados " + num_clientes + " clientes.");
// for (i=0; i<num_clientes; i++)
//	System.out.println(clientesNome[i]);


//****** criação de mapa de reservas **********************
int[][] mapa = new int[100][100];
int fila, max_filas;
int lugar, max_lugares;

int idCliente;

boolean reservaEncontrada=false;
int reservasProcessadas=0;

for (int sala=1; sala<=num_salas; sala++) {
	
	max_filas=salaFilas[sala];
	max_lugares=salaLugares[sala];
	
	for (int mes=1; mes<=12; mes++) {
		for (int dia=1; dia<=31; dia++) {
			for (int sessao=0; sessao<3; sessao++) {
				//inicializar mapa a zeros (lugares vazios)
				for (i=0; i<100; i++)
					for (j=0; j<100; j++)
						mapa[i][j]=0;
				for (int r=0; r<num_reservas; r++) {
					if ( 	reservaIdSala[r]==sala &&
							reservaMes[r]==mes &&
							reservaDia[r]==dia &&
							sessaoNumero(reservaSessao[r])==sessao ) {
						
						reservasProcessadas++;
						reservaEncontrada=true;
						
						idCliente=procuraCliente(reservaNomeCliente[r]);
						fila=reservaFila[r]-1;
						lugar=reservaLugar[r]-1;
						
						
						switch (reservaCondicao[r]) {
						case 'A':
							mapa[fila][lugar]=0;
							break;
						case 'C':
							mapa[fila][lugar]=idCliente;
							break;
						case 'R':
							mapa[fila][lugar]=-idCliente;
							break;
						default:
							int numLugares=charParaInt(reservaCondicao[r]);
							for (int l=0; l<numLugares; l++)
								mapa[fila][lugar+l]=-idCliente;
						}
					}
				}
				if (reservaEncontrada) {
                                        System.out.println("nome da sala");
                                        System.out.println(imprimirMapa(mapa, max_filas, max_lugares));
                                        String temp = imprimirMapa(mapa, max_filas, max_lugares);
                                       // System.out.println(temp + "after removing" + removeLastChar(temp)  );
				}
				reservaEncontrada=false;
				if (reservasProcessadas==num_reservas) {
					//System.exit(0);
				}				
			}
		}
	}
}


//demo removeLastChar.   demo works!!!
String s1 = "teste";
System.out.println(s1);
System.out.println("after removing: " + removeLastChar(s1)  );


//********************* codigo antigo *******************************
////objetivo: lugares livres
//int totalReservas = 0, totalCompras = 0, totalAnulacoes = 0, totalReservasN = 0;
//
////total de reservas
//for (int k = 0; k < num_reservas; k++) {
//    if (reservaCondicao[k] == 'R') {
//        totalReservas++;   }
//    if (reservaCondicao[k] == 'C') {
//        totalCompras++;    }
//    if (reservaCondicao[k] == 'A') {
//        totalAnulacoes++;  }
//    if (reservaCondicao[k] >= '0' && reservaCondicao[k] <= '9') {
//        totalReservasN+= (int) reservaCondicao[k]-'0';  }
//}
//System.out.println("Reservas:" + totalReservas + ",Compras:" + totalCompras + ",Anulacoes:" + totalAnulacoes + ",ReservasN:" + totalReservasN);  
//
////lugares livres
////total lugares livres = salaTotalLugares - salaReservas - salaCompras + salaAnulacoes     
//
//        //total de lugares nas salas
//        for (int k = 0; k < num_salas; k++) {
//            //System.out.println("nome da sala:" + salaNomeSala[k]);
//            salaTotalLugares[k] = salaFilas[k] * salaLugares[k] ;
//            System.out.print(salaNomeSala[k] +  " filas:" + salaFilas[k] + " lug:" + salaLugares[k] + " total lug:" + salaTotalLugares[k] + " *** " ) ;
//        }
//            System.out.println(""); //enter final, para que o BUILD SUCCESSFUL fique fixe.
    }
}

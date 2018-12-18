/*
 * Program: Aplikacja do tworzenia i modyfikowania prostych grafów przy pomocy GUI (Swing)
 *    Plik: ConsoleUserDialog.java
 *
 *   Autor: Adrian Mrówka
 *  Indeks: 234939
 *    Data: grudzień 2018 r.
 */

import java.util.Scanner;

public class ConsoleUserDialog {

	private final String ERROR_MESSAGE = "Nieprawidlowe dane!\nSprobuj jeszcze raz.";

	private Scanner scanner = new Scanner(System.in);

	public void printMessage(String message) {
			System.out.println(message);
	}

	public void printInfoMessage(String message) {
			System.out.println(message);
			enterString("Nacisnij ENTER");
	}

	public void printErrorMessage(String message) {
			System.err.println(message);
			System.err.println("Nacisnij ENTER");
			enterString("");
	}

	public void clearConsole() {
	    System.out.println("\n\n");
	}

	public String enterString(String prompt) {
	    System.out.print(prompt);
	    return scanner.nextLine();
	}

	public char enterChar(String prompt) {
	    boolean isError;
	    char c = ' ';
	    do {
	        isError = false;
	        try {
	            c = enterString(prompt).charAt(0);
	        } catch (IndexOutOfBoundsException e) {
	            System.err.println(ERROR_MESSAGE);
	            isError = true;
	        }
	    } while (isError);
	    return c;
	}

	public int enterInt(String prompt) {
	    boolean isError;
	    int i = 0;

	    do {
	        isError = false;
	        try{
	            i = Integer.parseInt(enterString(prompt));
	        } catch(NumberFormatException e){
	            System.err.println(ERROR_MESSAGE);
	            isError = true;
	        }
	    } while(isError);

	    return i;
	}

	public float enterFloat(String prompt) {
	    boolean isError;
	    float d = 0;

	    do {
	        isError = false;

	        try {
	            d = Float.parseFloat(enterString(prompt));
	        } catch(NumberFormatException e){
	            System.err.println(ERROR_MESSAGE);
	            isError = true;
	        }
	    } while(isError);
	    return d;
	}

	public double enterDouble(String prompt) {
	    boolean isError;
	    double d = 0;

	    do {
	        isError = false;
	        try{
	            d = Double.parseDouble(enterString(prompt));
	        } catch(NumberFormatException e){
	            System.err.println(ERROR_MESSAGE);
	            isError = true;
	        }
	    } while(isError);
	    return d;
	}
		
}




import java.util.Scanner;
import java.util.Stack;
/**
 * Aufgabe01 - Verteilte Systeme - Beuth Hochschule für Technik
 * Diese Klasse lässt einen Mainthread, 2 weitere Threads starten um ein
 * Telefonbuch nach vorher abgefragten Angaben zu durchsuchen und die Ergebnisse in einem Speicher
 * unterzubringen welche der Mainthread dann zum schluss ausgibt.
 * @author Justin Sibilak
 * created: 20.10.2015
 */
public class Aufgabe01 {

    public static void main(String[] args) {
    	
    	//"lokal-globaler Stack welcher nur Strings akzeptiert.
    	Stack<String> stack = new Stack<String>();
    
    	/**
    	 * Hier wird das Telefonbuch mittels eines 2D-Arrays aufgbaut.
    	 */
        String[][] liste = new String[6][2];
        
        liste[0][0] = "Meier";	liste[0][1] = "4711";
        liste[1][0] = "Schmitt";liste[1][1] = "0815";
        liste[2][0] = "Müller";	liste[2][1] = "4711";
        liste[3][0] = "Meier";	liste[3][1] = "0816";
        liste[4][0] = "von Gruben";	liste[4][1] = "6311";
        liste[5][0] = "von Gruben";	liste[5][1] = "1145";
        System.out.println("=========================");
        System.out.println("===Name=========Nummer===");
        System.out.println("===Meier========4711=====");
        System.out.println("===Schmitt======0815=====");
        System.out.println("===Müller=======4711=====");
        System.out.println("===Meier========0816=====");
        System.out.println("===von Gruben===6311=====");
        System.out.println("===von Gruben===1145=====");
        System.out.println("exit beendet das Programm");
        
        /**
         * NameSucher ist ein Thread welcher ein Array auf der linken Seite
         * auf einen übergebenen String prüft.
         * @author Justin Sibilak
         *
         */
        class NameSucher extends Thread{
        	String s;
        	String[][] s2;
        	boolean flag = true;
        	
        	/**
        	 * Constructor für den NamenSucher
        	 * @param s		= String auf den überprüft wird
        	 * @param s2	= Ein 2D-Array (Telefonbuch)
        	 */
        	public NameSucher(String s, String[][] s2){
        		this.s = s;
        		this.s2 = s2;
        	}
        	public void run(){
        		for(int i = 0; i<s2.length; i++){
        				if(s.equals(s2[i][0])){
        					String tempString = s2[i][0] + "   " + s2[i][1];
        					stack.push(tempString);
        					flag = false;
        				}
        		}
        		if(flag){ 
        		String tempString = "Keine Person mit dem Namen: " + s + " konnte gefunden werden.";
        		stack.push(tempString);
        		}
        	}
        }
        
        /**
    	 * Constructor für den NumberSucher
    	 * @param s		= String auf den überprüft wird
    	 * @param s2	= Ein 2D-Array (Telefonbuch)
    	 */
        class NumberSucher extends Thread{
        	String s;
        	String[][] s2;
        	boolean flag = true;
        	public NumberSucher(String s, String[][] s2){
        		this.s = s;
        		this.s2 = s2;
        	}
        	public void run(){
        		for(int i = 0; i<s2.length; i++){
        				if(s.equals(s2[i][1])){
        					String tempString2 = s2[i][0] + "   " + s2[i][1];
        					stack.push(tempString2);
        					flag = false;
        				}
        		}
        		if(flag){
        			String tempString2 ="Keine Person mit der Nummer: " + s + " konnte gefunden werden.";
        			stack.push(tempString2);
        		}
        	}
        }
        
        
        //Scanner initalisiert
    	Scanner scanner = new Scanner(System.in);
        
    	//Schleife welche namen und nummer abfragt und die zwei Threads startet.
        while (true){
        	
        	System.out.println("Namen eingeben : ");
        	String name = scanner.nextLine();
        	if(name.equals("exit")) break;
        	System.out.println("Nummer eingeben : ");
        	String number = scanner.nextLine();
        	if(number.equals("exit")) break;

            NameSucher thread1 = new NameSucher(name, liste);
            NumberSucher thread2 = new NumberSucher(number, liste);
            
            thread1.start();
            thread2.start();
            try {
				thread1.join();
				thread2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            // Schreibe werte aus dem Stack auf den Bilschirm
            
            while(!stack.isEmpty()){
            	System.out.println(stack.pop());
            }
            
        }
        
        System.out.println("Cya later alligator...");
        scanner.close(); 
    }

}
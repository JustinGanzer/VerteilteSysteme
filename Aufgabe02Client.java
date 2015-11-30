
public class Aufgabe02Client {

	public static void main(String[] args) {
		System.out.println("C: Start");

    // Socket einrichten und verbinden
    // ---------------------------------------------------------
    System.out.println("C: Socket einrichten und verbinden");
    Socket s = new Socket("sun65.beuth-hochschule.de", 9021);

    // Leser und Schreiber fuer den Socket einrichten
    // ---------------------------------------------------------
    BufferedReader sbr = new BufferedReader(
                           new InputStreamReader(
                             s.getInputStream() ));
    PrintWriter spw = new PrintWriter(s.getOutputStream());

    // Echostring vom Benutzer anfordern, schreiben und lesen
    // ---------------------------------------------------------
    String echo = null;
    String back = null;
    BufferedReader br = new BufferedReader(
                            new InputStreamReader(System.in));
    while(true) {
      System.out.println("\nGib Echostring (Ende mit quit)");
      System.out.print("->");
      echo = br.readLine();
      echo = echo.trim();
      System.out.println("C: Vor dem println() mit:"+echo);
      spw.println(echo);            // Schreiben und
      spw.flush();
      System.out.println("C: Vor dem readLine()");
      back = sbr.readLine();        // Lesen
      System.out.println("Zurueck kommt:" + back);
      if(back.equals("quit")) break;
    } // end while

    spw.close();
    sbr.close();
    br.close();
    s.close();

	}

}

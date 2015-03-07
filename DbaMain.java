
import java.net.*;
import java.io.*;
import java.awt.Toolkit;
import java.util.*;
import java.text.SimpleDateFormat;

public class DbaMain implements Runnable {
    String urlString;
    DbaMain(String urlString){
        this.urlString = urlString;
    }

    public void run() {

        ArrayList<String> linkStart = Dba.fetchData(urlString);
        while(true){

            ArrayList<String> linkCmp = Dba.fetchData(urlString);
            System.out.println("Thread sleep 15 sec");
            System.out.println(urlString);
            System.out.println("");
            try{
                Thread.sleep(15000);
            } catch (Exception e) {
            }
            System.out.println("Done");
            boolean foundNew = false;
            for(int i = 0; i < linkStart.size(); i++){
                String one = linkStart.get(i);
                String two = linkCmp.get(i);
/*                  System.out.println();
                    System.out.println(one);
                    System.out.println(two);
*/
                if(!(one.equals(two))){
                    foundNew = true;
                }

            }
            if(foundNew){
                System.out.println("Theres a new record");
            }
                System.out.println("Theres no new record");

        }
    }

    public static void main(String[] args) throws Exception {
        String urlString1 = "http://www.dba.dk/billetter/billetter-til-sport-musik-og-kultur/koncerter-og-festivaler/?soeg=smukfest";
        String urlString2 = "http://www.dba.dk/billetter/billetter-til-sport-musik-og-kultur/koncerter-og-festivaler/?soeg=skanderborg";
        (new Thread(new DbaMain(urlString1))).start();
        (new Thread(new DbaMain(urlString2))).start();
    }

}



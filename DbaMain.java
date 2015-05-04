

import java.net.*;
import java.io.*;
import java.awt.Toolkit;
import java.util.*;
import java.text.SimpleDateFormat;

public class DbaMain implements Runnable {
    String urlString;
    String thread;
    DbaMain(String urlString, String thread){
        this.urlString = urlString;
        this.thread = thread;

    }

public void run() {
    ArrayList<String> linkStart = Dba.fetchData(urlString);
    while(true){

        ArrayList<String> linkCmp = Dba.fetchData(urlString);
        ArrayList<String> linkNew = new ArrayList<String>();
//            System.out.println("Thread sleep 15 sec");
//            System.out.println(urlString);
//            System.out.println("");
        boolean foundNew = false;

        for(int i = 0; i < linkCmp.size(); i++){
            if(!(linkStart.contains(linkCmp.get(i)))){
                if(linkCmp.get(i).contains("soeger") || linkCmp.get(i).contains("koebes")){
                    linkStart.add(linkCmp.get(i));
                }
                else {
                    foundNew = true;
                    linkNew.add(linkCmp.get(i));
                }
            }
        }

        try{
            if(foundNew){
                for(int i = 0; i < linkNew.size(); i++){
                    System.out.println(linkNew.get(i));
                    linkStart.add(linkNew.get(i));
                    System.out.println("Found a new post by thread  " + thread);
                }
                foundNew = false;
                Process p = Runtime.getRuntime().exec("vlc dad.mp3");
            }
            else {
                System.out.println("No new post in  " + thread);
            }
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        try{
            Thread.sleep(15000);
        }
        catch (Exception e) {}
    }
}
    public static void main(String[] args) throws Exception {
        String urlString1 = "http://www.dba.dk/billetter/billetter-til-sport-musik-og-kultur/koncerter-og-festivaler/?soeg=smukfest";
        String urlString2 = "http://www.dba.dk/billetter/billetter-til-sport-musik-og-kultur/koncerter-og-festivaler/?soeg=skanderborg";
        (new Thread(new DbaMain(urlString1,  "smukfest"))).start();
        (new Thread(new DbaMain(urlString2, "skanderborg"))).start();
    }

}



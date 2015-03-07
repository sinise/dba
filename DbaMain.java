
import java.net.*;
import java.io.*;
import java.awt.Toolkit;
import java.util.*;
import java.text.SimpleDateFormat;

public class DbaMain {
    public static void main(String[] args) throws Exception {
        ArrayList<String> linkStart = Dba.fetchData();
        while(true){
            ArrayList<String> linkCmp = Dba.fetchData();
            System.out.println("Thread sleep 600");
            Thread.sleep(6000);
            System.out.println("Done");
            for(int i = 0; i < linkStart.size(); i++){
                String one = linkStart.get(i);
                String two = linkCmp.get(i);
                if(!(one.equals(two))){
                    System.out.println();
                    System.out.println("Theres a new record");
                    System.out.println(one);
                    System.out.println(two);
                }
                else {
                    System.out.println("Theres not new record");
                }

            }
        }
    }
}

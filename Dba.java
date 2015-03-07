import java.net.*;
import java.io.*;
import java.awt.Toolkit;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.String;
/**
 * fetch content from DBA. either from a saved html file or from url
 * @param choice 0 to fecch online 1 to fetch from file
 */

public class Dba
{

    /** fetch data from DBA
     * @return an arraylist containg all links to items
     */
    public static ArrayList<String> fetchData(String urlString) {
        ArrayList<String> linkList = new ArrayList<String>();
        URL url;
        URLConnection uc;
        BufferedReader in;
        String[] userAgent = {"Mozilla/5.0 (Windows NT 6.0; rv:33.0) Gecko/20100101 Firefox/33.0",
                              "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0",
                              "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0)",
                              "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
                              "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.65 Safari/537.36",
                              "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 YaBrowser/14.10.2062.12061 Safari/537.36",
                              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/600.1.25 (KHTML, like Gecko) Version/8.0 Safari/600.1.25",
                              "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)"};
        try {
            url = new URL(urlString);
            uc = url.openConnection();
            uc.addRequestProperty("User-Agent", userAgent[0]);
            uc.connect();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

            String ch;
            String regExStart = "<tbody>";
            String regExStartEntry = "dbaListing listing";
            String link = "href=";
            String regExEndEntry = "</tr>";

            //find the start of entries
            while ((ch = in.readLine()) != null) {
              if (ch.contains(regExStart)) {
                break;
              }
            }


            while((ch = in.readLine()) != null){

                //find entry start
                while ((ch = in.readLine()) !=  null){
                    if (ch.contains(regExStartEntry)){
                        break;
                    }
                }
                while((ch = in.readLine()) != null){
                    if (ch.contains(link)) {
                        linkList.add(ch);
                        break;
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return linkList;

    }
}


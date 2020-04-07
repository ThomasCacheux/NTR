import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args){
        long accountNumber = 1;
        double value = 100;

        String xml = "<Information><idCompte>" + accountNumber + "</idCompte><montant>" + value + "</montant></Information>";
        System.out.println("**************************************************");
        testGetSolde(accountNumber);
        System.out.println("**************************************************");
        testGetOp(accountNumber);
        System.out.println("**************************************************");
        testCredit(xml);
        System.out.println("**************************************************");
        testDebit(xml);
        System.out.println("**************************************************");


    }



    public static void testGetSolde(long id){
        try {

            URL url = new URL("http://localhost:8082/getSolde/" + id);
            requestGet(url);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void testGetOp(long accountNumber){
        try {

            URL url = new URL("http://localhost:8082/getOp/" + accountNumber);
            requestGet(url);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public  static  void testDebit(String xml){
        try {

            URL url = new URL("http://localhost:8082/debiter");
            requestPost(xml, url);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public  static  void testCredit(String xml){
        try {

            URL url = new URL("http://localhost:8082/crediter");
            requestPost(xml, url);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private static void requestPost(String xml, URL url) throws IOException {
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/xml");

        OutputStream os = conn.getOutputStream();
        os.write(xml.getBytes());
        os.flush();


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }


    private static  void requestGet(URL url) throws IOException {
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }
}

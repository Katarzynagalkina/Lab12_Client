import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable{
    private Socket clientSocket;


    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override

    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));)

        {
            System.out.println("User attended delivery server.");
            Connector.readXML("fromServer.xml");

          //  System.out.println("Write your dishes numbers ")
            // String clientNumbers = br.readLine();

           //System.out.println("Write your  and delivery address");
           // String clientAddress = br.readLine();

           String clientAddress="rokossovskgo 20";
           String clientNumbers="5";

            Connector.createXML("toServer.xml", clientAddress,clientNumbers);
            Connector.readXML("fromServer2.xml");

        } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
                new Thread(new Client(new Socket("localhost",3345))).start();
    }

}


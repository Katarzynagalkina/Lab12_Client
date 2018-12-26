import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    private Socket clientSocket;


    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override

    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream oos = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream ois = new DataInputStream(clientSocket.getInputStream()))
        {
            System.out.println("User attended delivery server.");

            Boolean isWritten= ois.readBoolean();
            if(isWritten) {
                Connector.readXML("C:\\Users\\Katty\\Desktop\\message.xml");
            }


           System.out.println("Write your dishes numbers ") ;
            String clientNumbers = br.readLine();
            System.out.println("Write your  and delivery address");
            String clientAddress = br.readLine();

//           String clientAddress="rokossovskgo 20";
//           String clientNumbers="5";

            Connector.createXML("C:\\Users\\Katty\\Desktop\\message.xml", clientAddress,clientNumbers);
            oos.writeBoolean(true);

            isWritten= ois.readBoolean();
            if(isWritten) {
                Connector.readXML("C:\\Users\\Katty\\Desktop\\message.xml");
            }

        } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args) throws InterruptedException, IOException {
                new Thread(new Client(new Socket("localhost",3345))).start();
    }

}


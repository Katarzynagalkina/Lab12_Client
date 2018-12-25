import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.bind.*;
import javax.xml.parsers.ParserConfigurationException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static javax.xml.transform.OutputKeys.*;



public class CreateXML {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    DocumentBuilder builder= factory.newDocumentBuilder();
    Document document= builder.newDocument();

    Element message=document.createElement("message");
    Element addressData=document.createElement("addressData");
    Element dishData=document.createElement("dishData");


//    Text text1=document.createTextNode("number");
//    Text text2=document.createTextNode("address");
        //dishData.appendChild(text1);
        //addressData.appendChild(text2);

    document.appendChild(message);
    message.appendChild(dishData);
    message.appendChild(addressData);

    addressData.setAttribute("address","rokossovskgo 20");
    dishData.setAttribute("number","5");

    Transformer t= TransformerFactory.newInstance().newTransformer();
    t.setOutputProperty(OutputKeys.INDENT, "yes");
    t.transform(new DOMSource(document),new StreamResult(new FileOutputStream("new.xml")));
    }
}



//    <?xml version="1.0" encoding="UTF-8"?>
//<!DOCTYPE message SYSTEM "client.dtd">
//<message>
//<dishNumber> 3</dishNumber>
//<address> Marksa 14,5</address>
//</message>


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.parsers.ParserConfigurationException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import static javax.xml.transform.OutputKeys.*;



public class Connector {


    static void createXML(String XMLFileName, String clientAddress,String clientNumbers) throws ParserConfigurationException, IOException, SAXException, TransformerException {

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

        addressData.setAttribute("address",clientAddress);
        dishData.setAttribute("number",clientNumbers);

        Transformer t= TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document),new StreamResult(new FileOutputStream(XMLFileName)));
    }


    static void readXML(String XMLFileName) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(XMLFileName));


        SchemaFactory schemaFactoryfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(new File("fromServer1.xsd"));
        Schema schema=schemaFactoryfactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();

        try {
            validator.validate(new DOMSource(document));
            System.out.println("Successful XML validating");
        } catch (SAXException e) {
            e.printStackTrace();
        }


        Element element = document.getDocumentElement();
        System.out.println(element.getTagName());
        NodeList nodelist = element.getChildNodes();

        System.out.println("Your order : ");
        for (int i = 0; i < nodelist.getLength(); i++) {
            if (nodelist.item(i) instanceof Element) {
                //  System.out.println(((Element) nodelist.item(i)).getTagName()); //выводятся тупо имена тегов
                //  if(((Element) nodelist.item(i)).hasAttribute("menu"))
                System.out.println(((Element) nodelist.item(i)).getAttribute("menu"));
            }
        }
    }

}


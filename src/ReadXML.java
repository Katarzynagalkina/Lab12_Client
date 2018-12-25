import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ReadXML {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= factory.newDocumentBuilder();
        Document document=builder.parse(new File("new.xml"));

       // XMLConstants.XML_DTD_NS_URI //для dtd 

        SchemaFactory schemaFactoryfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(new File("client_new.xsd"));
        Schema schema=schemaFactoryfactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();

        // validate the DOM tree
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException e) {
           e.printStackTrace();
        }



        Element element=document.getDocumentElement();
        System.out.println(element.getTagName());
        NodeList nodelist=element.getChildNodes();

        for(int i=0;i<nodelist.getLength();i++) {
            if (nodelist.item(i) instanceof Element) {
                System.out.println(((Element) nodelist.item(i)).getTagName()); //выводятся тупо имена тегов
                if(((Element) nodelist.item(i)).hasAttribute("address"))
                    System.out.println(((Element) nodelist.item(i)).getAttribute("address") + "!");

            }
        }
        System.out.println("=============");


    }
}

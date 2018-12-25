import com.sun.source.doctree.SerialDataTree;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "message")
@XmlRootElement

public class Message { // implements Serializable
    public String address;
    public String number;
    Message(){};
}


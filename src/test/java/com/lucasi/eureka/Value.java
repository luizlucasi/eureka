import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;

@XmlAccessorType(XmlAccessType.FIELD)
public class Value {

    @XmlElements({
        @XmlElement(name = "int", type = Integer.class),
        @XmlElement(name = "boolean", type = Boolean.class),
        @XmlElement(name = "string", type = String.class),
        @XmlElement(name = "double", type = Double.class),
        @XmlElement(name = "dateTime.iso8601", type = Date.class),
        @XmlElement(name = "base64", type = byte[].class),
        @XmlElement(name = "array", type = Array.class),
        @XmlElement(name = "struct", type = Struct.class)
    })
    private Object content;

    // getters and setters
}

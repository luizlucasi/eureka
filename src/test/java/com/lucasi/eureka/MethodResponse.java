import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ValueType {

    @XmlElement(name = "int")
    private Integer intValue;

    @XmlElement(name = "boolean")
    private Boolean booleanValue;

    @XmlElement(name = "string")
    private String stringValue;

    @XmlElement(name = "double")
    private Double doubleValue;

    @XmlElement(name = "dateTime.iso8601")
    @XmlJavaTypeAdapter(value = DateTimeAdapter.class)
    private Date dateValue;

    @XmlElement(name = "base64")
    private byte[] base64Value;

    @XmlElement(name = "array")
    private Array arrayValue;

    @XmlElement(name = "struct")
    private Struct structValue;

}

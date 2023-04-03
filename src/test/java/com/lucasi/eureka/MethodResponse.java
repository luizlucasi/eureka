import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "methodResponse")
public class MethodResponse {

    @XmlElement(name = "params")
    private Params params;

    @XmlElement(name = "fault")
    private Fault fault;

    // getters and setters
}

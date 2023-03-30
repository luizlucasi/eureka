
@XmlRootElement(name = "methodResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodResponse {

    @XmlElement(name = "fault")
    private Fault fault;

    @XmlElementWrapper(name = "params")
    @XmlElement(name = "param")
    private List<ParamType> params;

    public Fault getFault() {
        return fault;
    }

    public void setFault(Fault fault) {
        this.fault = fault;
    }

    public List<ParamType> getParams() {
        return params;
    }

    public void setParams(List<ParamType> params) {
        this.params = params;
    }

}


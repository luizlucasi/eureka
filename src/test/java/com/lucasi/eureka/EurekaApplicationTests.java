
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


@XmlRootElement(name = "fault")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fault {
    
    @XmlElement(name = "value")
    private Value value;
    
    public Value getValue() {
        return value;
    }
    
    public void setValue(Value value) {
        this.value = value;
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Value {
        
        @XmlElement(name = "struct")
        private Struct struct;
        
        public Struct getStruct() {
            return struct;
        }
        
        public void setStruct(Struct struct) {
            this.struct = struct;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Struct {
        
        @XmlElement(name = "member")
        private List<Member> members;
        
        public List<Member> getMembers() {
            return members;
        }
        
        public void setMembers(List<Member> members) {
            this.members = members;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Member {
        
        @XmlElement(name = "name")
        private String name;
        
        @XmlElement(name = "value")
        private Value value;
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public Value getValue() {
            return value;
        }
        
        public void setValue(Value value) {
            this.value = value;
        }
    }
}



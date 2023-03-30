
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueType", propOrder = {
    "content"
})
public class ValueType {

    @XmlMixed
    protected List<Object> content;

    @XmlAttribute(name = "type")
    protected String type;

    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return this.content;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getString() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : getContent()) {
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(getString());
    }

    public int getInt() {
        return Integer.parseInt(getString());
    }

    public double getDouble() {
        return Double.parseDouble(getString());
    }

    public byte[] getBase64() {
        return Base64.getDecoder().decode(getString());
    }

    public Date getDateTimeIso8601() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.parse(getString());
    }
}





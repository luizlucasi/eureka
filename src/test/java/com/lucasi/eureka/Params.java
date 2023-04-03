@XmlAccessorType(XmlAccessType.FIELD)
public class StructType implements ValueType {

    @XmlElementWrapper(name = "struct")
    @XmlElement(name = "member")
    private List<MemberType> members;

    public List<MemberType> getMembers() {
        return members;
    }

    // getters and setters
}

@XmlAccessorType(XmlAccessType.FIELD)
public class MemberType {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "value")
    private ValueType value;

    public String getName() {
        return name;
    }

    public ValueType getValue() {
        return value;
    }

    // getters and setters
}

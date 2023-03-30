
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataType", propOrder = {
    "value"
})
public class DataType {

    @XmlElement(required = true)
    protected List<ValueType> value;

    public List<ValueType> getValue() {
        if (value == null) {
            value = new ArrayList<>();
        }
        return this.value;
    }
}


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayType", propOrder = {
    "data"
})
public class ArrayType {

    @XmlElement(required = true)
    protected DataType data;

    public DataType getData() {
        return data;
    }

    public void setData(DataType value) {
        this.data = value;
    }
}


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MemberType", propOrder = {
    "name",
    "value"
})
public class MemberType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected ValueType value;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public ValueType getValue() {
        return value;
    }

    public void setValue(ValueType value) {
        this.value = value;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StructType", propOrder = {
    "member"
})
public class StructType {

    @XmlElement(required = true)
    protected List<MemberType> member;

    public List<MemberType> getMember() {
        if (member == null) {
            member = new ArrayList<>();
        }
        return this.member;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueType", propOrder = {
    "content"
})
public class ValueType {

    @XmlMixed
    protected List<Serializable> content;
    @XmlAnyElement(lax = true)
    protected Object value;

    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return this.content;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}




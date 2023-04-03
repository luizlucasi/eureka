import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Array {

    @XmlElement(name = "data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        @XmlElement(name = "value")
        private List<Value> values;

        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }
    }
}


import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.ws.encoding.soap.DeserializationException;

public class XmlRpcResponse {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static XmlRpcResponse unmarshallXmlRpc(String xml) throws DeserializationException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MethodResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            MethodResponse methodResponse = (MethodResponse) unmarshaller.unmarshal(new StringReader(xml));

            if (methodResponse.getParams() != null) {
                ParamType paramType = methodResponse.getParams().getParam();
                if (paramType != null) {
                    ValueType valueType = paramType.getValue();
                    if (valueType != null) {
                        Object value = valueType.getContent();
                        if (value instanceof Integer) {
                            // valor é do tipo Integer
                            Integer i = (Integer) value;
                            return new XmlRpcResponse(i);
                        } else if (value instanceof Boolean) {
                            // valor é do tipo Boolean
                            Boolean b = (Boolean) value;
                            return new XmlRpcResponse(b);
                        } else if (value instanceof String) {
                            // valor é do tipo String
                            String s = (String) value;
                            return new XmlRpcResponse(s);
                        } else if (value instanceof Double) {
                            // valor é do tipo Double
                            Double d = (Double) value;
                            return new XmlRpcResponse(d);
                        } else if (value instanceof byte[]) {
                            // valor é do tipo byte[]
                            byte[] bytes = (byte[]) value;
                            return new XmlRpcResponse(bytes);
                        } else if (value instanceof Date) {
                            // valor é do tipo Date
                            Date date = (Date) value;
                            return new XmlRpcResponse(date);
                        } else if (value instanceof List) {
                            // valor é do tipo List
                            List<?> list = (List<?>) value;
                            return new XmlRpcResponse(list);
                        } else if (value instanceof Map) {
                            // valor é do tipo Map
                            Map<?, ?> map = (Map<?, ?>) value;
                            return new XmlRpcResponse(map);
                        } else {
                            // valor é desconhecido ou não suportado
                            throw new RuntimeException("Valor desconhecido ou não suportado.");
                        }
                    }
                }
            } else if (methodResponse.getFault() != null) {
                // lógica para lidar com erros
                throw new RuntimeException("Erro na resposta XML-RPC.");
            }

            return null;

        } catch (JAXBException e) {
            throw new DeserializationException("Erro ao desserializar a resposta XML-RPC.", e);
        }
    }
}

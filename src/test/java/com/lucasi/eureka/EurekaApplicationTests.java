<?xml version="1.0"?>
 <xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema">
  
  <xsd:element name="methodCall">
     <xsd:complexType>
       <xsd:all>
         <xsd:element name="methodName">
           <xsd:simpleType>
             <xsd:restriction base="ASCIIString">
               <xsd:pattern value="([A-Za-z0-9]|/|\.|:|_)*" />
             </xsd:restriction>
           </xsd:simpleType>
         </xsd:element>
         <xsd:element name="params" minOccurs="0" maxOccurs="1">
           <xsd:complexType>
             <xsd:sequence>
               <xsd:element name="param"  type="ParamType" 
                            minOccurs="0" maxOccurs="unbounded" />
             </xsd:sequence>
           </xsd:complexType>
          </xsd:element>
       </xsd:all>
     </xsd:complexType>  
   </xsd:element>
  
   <xsd:element name="methodResponse">
     <xsd:complexType>
       <xsd:choice>
         <xsd:element name="params">
           <xsd:complexType>
             <xsd:sequence>
               <xsd:element name="param" type="ParamType" />
             </xsd:sequence>
           </xsd:complexType>
         </xsd:element>
         <xsd:element name="fault">
           <xsd:complexType>
             <xsd:sequence>
               <xsd:element name="value">
                 <xsd:complexType>
                   <xsd:sequence>
                     <xsd:element name="struct"> 
                       <xsd:complexType> 
                         <xsd:sequence> 
                           <xsd:element name="member" 
                                        type="MemberType" />
                           <xsd:element name="member" 
                                        type="MemberType" />
                         </xsd:sequence>
                       </xsd:complexType>
                     </xsd:element>
                   </xsd:sequence>
                 </xsd:complexType>
               </xsd:element>
             </xsd:sequence>
           </xsd:complexType>
          </xsd:element>
       </xsd:choice>
     </xsd:complexType>  
   </xsd:element>
  
   <xsd:complexType name="ParamType">
     <xsd:sequence>
       <xsd:element name="value" type="ValueType" />
     </xsd:sequence>
   </xsd:complexType>
  
   <xsd:complexType name="ValueType" mixed="true">
     <xsd:choice>
       <xsd:element name="i4"            type="xsd:int" />
       <xsd:element name="int"           type="xsd:int" />
       <xsd:element name="string"        type="ASCIIString" />
       <xsd:element name="double"        type="xsd:decimal" />
       <xsd:element name="Base64"        type="xsd:base64Binary" />
       <xsd:element name="boolean"       type="NumericBoolean" />
       <xsd:element name="dateTime.iso8601" type="xsd:dateTime" />
       <xsd:element name="array"         type="ArrayType" />
       <xsd:element name="struct"        type="StructType" />
     </xsd:choice>
   </xsd:complexType>
  
   <xsd:complexType name="StructType">
     <xsd:sequence>
       <xsd:element name="member" type="MemberType" 
                    maxOccurs="unbounded" />
     </xsd:sequence>
   </xsd:complexType>
  
   <xsd:complexType name="MemberType">
     <xsd:sequence>
       <xsd:element name="name"  type="xsd:string" />
       <xsd:element name="value" type="ValueType" />
     </xsd:sequence>
   </xsd:complexType>
  
   <xsd:complexType name="ArrayType">
     <xsd:sequence>
       <xsd:element name="data">
         <xsd:complexType>
           <xsd:sequence>
             <xsd:element name="value"  type="ValueType" 
                          minOccurs="0" maxOccurs="unbounded" />
           </xsd:sequence>
         </xsd:complexType>
       </xsd:element>
     </xsd:sequence>
   </xsd:complexType>
  
   <xsd:simpleType name="ASCIIString">
     <xsd:restriction base="xsd:string">
       <xsd:pattern value="([ -~]|\n|\r|\t)*" />
     </xsd:restriction>
   </xsd:simpleType>
  
   <xsd:simpleType name="NumericBoolean">
     <xsd:restriction base="xsd:boolean">
       <xsd:pattern value="0|1" />
     </xsd:restriction>
   </xsd:simpleType>
  
 </xsd:schema>
}

public static XmlRpcResponse unmarshallXmlRpc(String xml) throws JAXBException, XMLStreamException {
    JAXBContext context = JAXBContext.newInstance(MethodResponse.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    try (StringReader stringReader = new StringReader(xml);
         XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(stringReader)) {
        MethodResponse methodResponse = unmarshaller.unmarshal(reader, MethodResponse.class).getValue();
        if (methodResponse.getParams() != null) {
            List<Object> params = new ArrayList<>();
            for (ParamType paramType : methodResponse.getParams().getParam()) {
                ValueType valueType = paramType.getValue();
                Object value = parseValue(valueType);
                params.add(value);
            }
            return new XmlRpcResponse(params);
        } else if (methodResponse.getFault() != null) {
            Map<String, Object> fault = new HashMap<>();
            for (MemberType memberType : methodResponse.getFault().getValue().getStruct().getMember()) {
                String key = memberType.getName();
                ValueType valueType = memberType.getValue();
                Object value = parseValue(valueType);
                fault.put(key, value);
            }
            return new XmlRpcResponse(fault);
        } else {
            throw new IllegalArgumentException("Unknown response");
        }
    }
}

private static Object parseValue(ValueType valueType) {
    if (valueType == null) {
        return null;
    }
    if (valueType.getInt() != null) {
        return valueType.getInt();
    } else if (valueType.getString() != null) {
        return valueType.getString();
    } else if (valueType.getDouble() != null) {
        return valueType.getDouble();
    } else if (valueType.getBase64() != null) {
        return valueType.getBase64();
    } else if (valueType.getArray() != null) {
        List<Object> list = new ArrayList<>();
        for (ValueType value : valueType.getArray().getData().getValue()) {
            list.add(parseValue(value));
        }
        return list;
    } else if (valueType.getStruct() != null) {
        Map<String, Object> map = new HashMap<>();
        for (MemberType memberType : valueType.getStruct().getMember()) {
            String key = memberType.getName();
            ValueType value = memberType.getValue();
            Object parsedValue = parseValue(value);
            map.put(key, parsedValue);
        }
        return map;
    } else if (valueType.getBoolean() != null) {
        return valueType.getBoolean();
    } else if (valueType.getDateTimeIso8601() != null) {
        return valueType.getDateTimeIso8601();
    } else {
        throw new IllegalArgumentException("Unknown value type");
    }
}

@XmlRootElement(name = "methodResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodResponse {
    
    @XmlElement(name = "params")
    private Params params;
    
    @XmlElement(name = "fault")
    private Fault fault;
    
    public Params getParams() {
        return params;
    }
    
    public void setParams(Params params) {
        this.params = params;
    }
    
    public Fault getFault() {
        return fault;
    }
    
    public void setFault(Fault fault) {
        this.fault = fault;
    }
    
    public static class Params {
        @XmlElement(name = "param")
        private Param param;
        
        public Param getParam() {
            return param;
        }
        
        public void setParam(Param param) {
            this.param = param;
        }
    }
    
    public static class Fault {
        @XmlElement(name = "value")
        private Value value;
        
        public Value getValue() {
            return value;
        }
        
        public void setValue(Value value) {
            this.value = value;
        }
    }
    
    public static class Param {
        @XmlElement(name = "value")
        private Value value;
        
        public Value getValue() {
            return value;
        }
        
        public void setValue(Value value) {
            this.value = value;
        }
    }
}





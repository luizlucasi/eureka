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



import java.io.File;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;




import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import org.w3c.dom.NodeList;

public class MainClass {

	static Map<String, Integer> attrMap = new HashMap<>();

	public static void main(String[] args) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(new FileInputStream(new File("input.xml")));

		NodeList entries = doc.getElementsByTagName("item");

		int num = entries.getLength();

		for (int i=0; i<num; i++) {
			Element node = (Element) entries.item(i);
			listAllAttributes(node);
		}
		
		 System.out.println(attrMap);
		 
		 int total = 0;
		 	for (Integer value : attrMap.values()) {
			    total = total + value; 
		
			 }
			
			System.out.println("krainata cena : " + total + " leva" ); 
			
			Document document = db.newDocument();
			
			String Cost = Integer.toString(total);
			Element derp = document.createElement("item"); 
		    derp.setAttribute("Cost",   Cost );           
		    document.appendChild(derp);                    
		  

		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer transformer = tf.newTransformer();

		    DOMSource source = new DOMSource(document);          
		    StreamResult result = new StreamResult("output.xml");  

		    transformer.transform(source, result);
			
			

	}
			 
	
	

	public static void listAllAttributes(Element element) {		

		NamedNodeMap attributes = element.getAttributes();



		int numAttrs = attributes.getLength();

		for (int i = 0; i < numAttrs; i++) {
			Attr attr = (Attr) attributes.item(i);

			String attributeName = attr.getNodeName();
			String attributeValue = attr.getNodeValue();

			int newAttrValue = Integer.parseInt(attributeValue);

				System.out.println("Found attribute: " + attributeName + " = " + newAttrValue);

			int oldAttrValue = 0;
			
			
			if( attrMap.containsValue(attributeValue)){

				
			}
		
			
			int sum = oldAttrValue + newAttrValue ;

			attrMap.put(attributeName, sum);
			
			
			
		}
		

	}
	
}

package smokepackage;   
import java.awt.List;
import java.io.BufferedInputStream;
import java.io.File;   
import java.io.FileInputStream;
import java.io.FileWriter;   
import java.io.IOException;   
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;   
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;   
//import java.util.List;  
import org.dom4j.Document;   
import org.dom4j.DocumentException;   
import org.dom4j.DocumentHelper;   
import org.dom4j.Element;   
import org.dom4j.io.SAXReader;   
import org.dom4j.io.XMLWriter;   
import org.dom4j.*;   
import org.xml.*;
import org.xml.sax.SAXException;
//import 
/**  
*   
* @author hongliang.dinghl  
* Dom4j 生成XML文档与解析XML文档  
*/  
public class ReadXML  {   

public void createXml(String fileName) {   
Document document = DocumentHelper.createDocument();   
Element employees=document.addElement("employees");   
Element employee=employees.addElement("employee");   
Element name= employee.addElement("name");   
name.setText("ddvip");   
Element sex=employee.addElement("sex");   
sex.setText("m");   
Element age=employee.addElement("age");   
age.setText("29");   
try {   
Writer fileWriter=new FileWriter(fileName);   
XMLWriter xmlWriter=new XMLWriter(fileWriter);   
xmlWriter.write(document);   
xmlWriter.close();   
} catch (IOException e) {   

System.out.println(e.getMessage());   
}   


}   


public ArrayList parserXml(String fileName) throws Exception {   
	File inputXml=new File(fileName);   
	SAXReader saxReader = new SAXReader(); 
	//saxReader.set
	//saxReader.se
	//java.util.List list = new ArrayList();  
	ArrayList<String[]> list = new ArrayList<String[]>();
	String[] testinfo = new String[3];
	String[] testinfolist = new String[3];
	int count;
	try {   
	//	Document document = saxReader.read(inputXml);   
		//Document document = saxReader.read(new BufferedInputStream(new FileInputStream(inputXml) ));  
		//document.setEncoding("UTF-8");
		//document
			FileInputStream in = new FileInputStream(inputXml);
			Reader reader = new InputStreamReader(in,"UTF-8");
			//SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(reader);

		
		Element testlist=document.getRootElement();   
		for(Iterator i = testlist.elementIterator(); i.hasNext();)
		{   
			Element testunit = (Element) i.next();   
			count=0;
			for(Iterator j = testunit.elementIterator(); j.hasNext();)
			{   
				Element node=(Element) j.next();   
				System.out.println(node.getName()+":"+node.getText());   
				testinfo[count]= node.getText();
				count++;
			}   
			
			testinfolist=testinfo.clone();
			list.add(testinfolist);
		}   
	} catch (DocumentException e) {   
		System.out.println(e.getMessage());   
	}   
		
		
return  list;
}   
}   
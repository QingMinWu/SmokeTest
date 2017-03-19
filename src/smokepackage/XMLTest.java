package smokepackage;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;  

public class XMLTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		ReadXML xmlconfig = new ReadXML();
		String filepath = new String();
		int testflag=0;
		if( testflag ==1)
			filepath = 
			"D:\\workspace\\SmokeTestMail\\src\\config.xml";
		else
			filepath = "/app/qatest/testxml/config.xml";
		
		
		filepath = "/app/qatest/testxml/bbossconfig.xml";
		ArrayList<String[]> list =
			new  ArrayList<String[]>(xmlconfig.parserXml(filepath));
		
		String[] testinfo = new String[3];
		for(int i=0;i<list.size();i++)
		{
			testinfo=list.get(i);
			//System.out.printf("tttttttttttttttt\n");
			for(int j=0;j<3;j++)
			{
				System.out.printf(testinfo[j]);
			}
			//System.out.printf("\ngggggggggggggg\n");
			
		}

	}

}

package smokepackage;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallJmeter {
	public String filetime=new String();
	public String moudlename = new String();
	public String jmxfile  = new String();
	 
		//String
public void jmeter_run(String moudlename,String jmxfile,String  filetime)
{
		
		this.filetime = filetime;
		//systime.
		this.moudlename = moudlename;
		this.jmxfile = jmxfile;
		
        String runcmd = new String();
        runcmd = "/app/qatest/apache-jmeter-2.13/bin/jmeter -n -t /app/qatest/TestScript/jmxfile/" + jmxfile 
        +" -l /app/qatest/TestScript/csvlog/" + moudlename +  filetime +".csv";
        //	"/app/qatest/apache-jmeter-2.13/bin/jmeter -n -t /app/qatest/TestScript/gxsoa-test.jmx -l /app/qatest/TestScript/csvlog/201509091947.csv";
		try {
        // String runcmd = "/app/qatest/apache-jmeter-2.13/bin/jmeter -n -t /app/qatest/TestScript/gxsoa-test.jmx -l /app/qatest/TestScript/csvlog/201509091947.csv";
		 System.out.println("开始执行jmx文件"+runcmd);
		 String rs=runShell(runcmd);
		 System.out.println("执行结果:"+rs);

		

//			  Thread.sleep(1000 * 60 * 30);
//		Thread.sleep(1000 * 60 );
		}
		catch (Exception e) {
		e.printStackTrace();
		
		}
}


public  String runShell(String shStr) throws Exception {
	StringBuffer strList = new StringBuffer();
	Process process;
	process = Runtime.getRuntime().exec(
	new String[] { "/bin/sh", "-c", shStr }, null, null);
	InputStreamReader ir = new InputStreamReader(process.getInputStream());
	LineNumberReader input = new LineNumberReader(ir);
	String line;
	process.waitFor();
	while ((line = input.readLine()) != null) {
	strList.append(line);
	strList.append("\r\n");
	}
	return strList.toString();
	}
}
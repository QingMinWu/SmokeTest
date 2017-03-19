package smokepackage;




import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
 

public class SmokeTestMail  {
	
	public static Collection getRecipientList(String str) {
		Collection list = new ArrayList();
		if (str == null)
			return list;

		if (str.indexOf(";") < 1) {
			list.add(str);
		} else {
			String[] ary = str.split(";");
			for (int i = 0; i < ary.length; i++) {
				list.add(ary[i]);
			}
		}
		return list;
	}
	
	public static void main(String[] args ) throws Exception
	{
		
		System.out.printf("version1.0.1  2015.10.15\n");
		int debug_flag = 0;   //debug= 1为本机调试，=0为服务器上运行；
		int taskSize = 5;    //定义线程数
		//从xml中获取文件测试脚本定义
		ReadXML xmlconfig = new ReadXML();
		String filepath =  new String();
		CallJmeter jmeter  = new CallJmeter();
		CSVFileUtil cvsfile = new CSVFileUtil();
		String mailtable = new String("");
		double allTrueNum = 0;
		double allFalseNum = 0;
		int cyclenum = 0 ;
		int lisesize = 0 ;
		String runpara[]= new String[4];
		String tabledef =  new CsvToHtml().htmlmail;
		String nullstring = new String();
		
		String per_true =""; 
		String per_false =""; 
		DecimalFormat per = new DecimalFormat("##.00%");
		
		//创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);  
		
		// 创建多个有返回值的任务  
		List<Future> futurelist = new ArrayList<Future>();  
		
		//获取测试时间戳
		Date systime = new Date();
		String filetime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(systime);

		//String x
		
		if(debug_flag==1)
		{
			filepath = "D:\\workspace\\SmokeTestMail\\src\\bbossconfig.xml";
		}
		else
		{
			//filepath = "/app/qatest/TestScript/config.xml";
			//filepath = "/app/qatest/TestScript/config.xml";
			filepath = "/app/qatest/TestScript/bbossconfig.xml";
		}
		ArrayList<String[]> list =
			new  ArrayList<String[]>(xmlconfig.parserXml(filepath));	
		
		ArrayList<String[]> logfile = new  ArrayList<String[]>();
		logfile = (ArrayList<String[]>) list.clone();
		String[] testinfo = new String[3];
		
		//判断循环次数,数组从0计数所以+1保持自然数
		lisesize = list.size()+1;
		if(lisesize%taskSize != 0)
		{
			cyclenum = lisesize/taskSize + 1;
		}
		else
		{
			cyclenum = lisesize/taskSize;
		}
		
		for(int i =0;i<cyclenum;i++)
		{
			for(int j = i*5;j<(i+1)*5;j++)
			{
				if(j>=list.size())  //判断是否超出测试清单长度，如果超出，则跳出
				{
					break;
				}
				testinfo=list.get(j);	
				System.out.printf("testinfo.length:"+testinfo.length);
				runpara[0] =  testinfo[0].toString();
				runpara[1] =  testinfo[1].toString();
				runpara[2] =  testinfo[2].toString();
				runpara[3] =  filetime;
				System.out.printf("调用接口测试,开启多线程"+testinfo[0]+"\n");
				Callable callable = new MytCallable(runpara);
				//jmeter.jmeter_run(testinfo[0],testinfo[1],filetime);
				System.out.printf("调用接口测试完成"+testinfo[1]+"\n");
					
				Future f = pool.submit(callable);  
				futurelist.add(f);  
			}
			testinfo=list.get(i);
			 
		}
		 pool.shutdown();  
		 for (Future f : futurelist) {  
			    // 从Future对象上获取任务的返回值，并输出到控制台  
			    System.out.println(">>>" + f.get().toString());  
			 	//nullstring = f.get().toString();
			 	
			   }  
		 
		// System.out.println(">>>" +mailtable+"<<<<");  
		 //mailtable =    tabledef  + tablenead  + mailtable + "</table></body></html>";
			
			
			
		for(int i=0;i<logfile.size();i++)
		{
			testinfo=list.get(i);			
			//读取xml配置，调用jmeter进程运行进程
			//jmeter_run(String moudlename,String jmxfile,String  filetime)		
			Map rstMap = cvsfile.readfile("/app/qatest/TestScript/csvlog/" + testinfo[0] +  filetime +".csv",testinfo[2]);
//			rstMap.put("MAIL_TABLE", mailtable);
//			rstMap.put("COUNT_TRUE", count_true);
//			rstMap.put("COUNT_FALSE", count_false);
			//生产邮件表格体
			mailtable = mailtable + (String)rstMap.get("MAIL_TABLE");	
			allTrueNum += Double.parseDouble((String)rstMap.get("COUNT_TRUE"));
			allFalseNum += Double.parseDouble((String)rstMap.get("COUNT_FALSE"));
			//mailtable = mailtable + cvsfile.readfile("/app/qatest/TestScript/csvlog/" + testinfo[0] +  filetime +".csv",testinfo[2]);	
		}
		
		per_true = per.format(allTrueNum/(allTrueNum+allFalseNum));
		per_false = per.format(allFalseNum/(allTrueNum+allFalseNum));
		
		String tablenead_per =new String("<p>+++冒烟接口成功数:"+ (int)allTrueNum
						+" ; 冒烟接口成功率: "+per_true+" ;</p>"
						+"<p>+++冒烟接口失败数:"+(int)allFalseNum
						+" ; 冒烟接口失败率:"+per_false+" ;</p>") ;
//		+ tablenead_per
//		+ </table>
//		+"<table width=\"820\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"query\">"
		
		String tablenead=new String("<thead><th>模块名称</th><th>接口名称</th><th>运行状态</th></thead>") ;
		
		mailtable =    new CsvToHtml().htmlmail  
				+ tablenead_per 
				+"<table width=\"820\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class=\"query\">" 
				+ tablenead  + mailtable + "</table></body></html>";
		System.out.println(mailtable);
		//发送结果文件
		String reulstmail = new String();
		SendMailToAll sendmm = new SendMailToAll();
		sendmm.sendmail(mailtable);

//		buildMail bm =  new buildMail();
//		bm.setFrom(UtilFunc.getProp2("from_mail"));
//		bm.setPassWord(EncodeUtil.deEncryption(UtilFunc
//				.getProp2("password")));
//		bm.setUserName(UtilFunc.getProp2("user_name"));
//		bm.setHost(UtilFunc.getProp2("smtp_server"));
//		
//		String productName = "";
//		productName = new String(UtilFunc.getProp2("productName").getBytes(
//				"ISO8859-1"), "GBK");
//		bm.setSubject(productName);
//		bm.setContent(mailtable,"text/html;charset=gb2312");
//		bm.sendMail(getRecipientList(UtilFunc.getProp2("to_mail")),
//				getRecipientList(UtilFunc.getProp2("cc")));	
	}
}






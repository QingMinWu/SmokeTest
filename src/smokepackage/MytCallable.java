package smokepackage;

import java.util.concurrent.Callable;


class MytCallable implements Callable<Object> 
{  
	private String[] taskpara;  
	public String mailtable;
	CSVFileUtil cvsfile = new CSVFileUtil();
	MytCallable(String[] taskpara) 
	{  
		this.taskpara = taskpara.clone();  
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		CallJmeter jmeter  = new CallJmeter();
		jmeter.jmeter_run(taskpara[0],taskpara[1],taskpara[3]);
		mailtable = mailtable + cvsfile.readfile("/app/qatest/TestScript/csvlog/" + taskpara[0] +  taskpara[3] +".csv",taskpara[2]);
		return mailtable;
	}  
}

/*
public Object call() throws Exception 
{  
   System.out.println(">>>" + taskNum + "任务启动");  
   Date dateTmp1 = new Date();  
   Thread.sleep(1000);  
   Date dateTmp2 = new Date();  
   long time = dateTmp2.getTime() - dateTmp1.getTime();  
   System.out.println(">>>" + taskNum + "任务终止");  
   return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";  
   */
	 
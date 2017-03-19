package smokepackage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.nio.*;
import java.nio.channels.*;
public  class UtilFunc
{

 

  public static String getEnvByName(String strName) {
    try {
      String ret = "";
      Map gp = System.getenv();
      for (Iterator ith = gp.entrySet().iterator(); ith.hasNext(); )
      {
        Map.Entry mp = (Map.Entry)ith.next();
        if ((mp.getKey().toString().equals(strName))) 
        	return mp.getValue().toString();
      }
      return "";
    }
    catch (Exception e)
    {
      System.out.println("execption:" + e.getMessage()); }
    return "";
  }



  public static String getNowYear()
  {
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy");   
	  return sdf.format(new java.util.Date());  

  }
  public static String getNowMonth()
  {
	  SimpleDateFormat sdf=new SimpleDateFormat("MM");   
	  return sdf.format(new java.util.Date());  
  }
  public static String getNowDay()
  {
	  SimpleDateFormat sdf=new SimpleDateFormat("dd");   
	  return sdf.format(new java.util.Date());  
  }
  
  public static String getNowHour()
  {
	  SimpleDateFormat sdf=new SimpleDateFormat("HH");   
	  return sdf.format(new java.util.Date());  
  }
  
  public static String getNowMin()
  {
	  SimpleDateFormat sdf=new SimpleDateFormat("mm");   
	  return sdf.format(new java.util.Date());  
  }
  
  public static String getTime()
  {
		Calendar now = Calendar.getInstance();
		int hh = now.get(Calendar.HOUR);
		int mm = now.get(Calendar.MINUTE);
		int ss = now.get(Calendar.SECOND);
		return String.valueOf(hh)+":"+String.valueOf(mm)+":"+String.valueOf(ss);
  }
  public static String vPath(String v) {
    return v.replace("\\", "/");
  }

 

  public static String getProp2(String keyname)
  {
    try
    {
      File f = new File(System.getProperty("user.dir") + "/build.properties");
      if (!(f.exists()))
      {
        System.out.println("\t配置文件没有找到!"+f.getAbsolutePath());
        System.exit(1);
      }
      InputStream is = new BufferedInputStream(new FileInputStream(f));
      Properties prop = new Properties();
      prop.load(is);
      if (is != null)
        is.close();
      return prop.getProperty(keyname).trim();
    }
    catch (Exception e)
    {
    	System.out.println("查找key:" + keyname);
    	e.printStackTrace();
    	return "";
    }
  }
  public static String getFileTime(File f)
  {        
    Calendar cal = Calendar.getInstance();    
    long time = f.lastModified();    
    cal.setTimeInMillis(time); 
    SimpleDateFormat  fs=new  SimpleDateFormat("yyyyMMddHH");  
    return fs.format(cal.getTime());
    
  }
  public static boolean FileCpy(String src,String dest)
  {
	  try
	  {
		    System.out.println("Coping "+src +" to "+dest);
		  //File dst = new File(dest);
		  //if(!dst.exists()) dst.createNewFile();
	        // 获取源文件和目标文件的输入输出流   
	        FileInputStream fin = new FileInputStream(src);   
	        FileOutputStream fout = new FileOutputStream(dest);   
	        // 获取输入输出通道   
	        FileChannel fcin = fin.getChannel();   
	        FileChannel fcout = fout.getChannel();   
	        // 创建缓冲区   
	        ByteBuffer buffer = ByteBuffer.allocate(1024);   
	        while (true) {   
	            // clear方法重设缓冲区，使它可以接受读入的数据   
	            buffer.clear();   
	            // 从输入通道中将数据读到缓冲区   
	            int r = fcin.read(buffer);   
	            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1   
	            if (r == -1) {   
	                break;   
	            }   
	            // flip方法让缓冲区可以将新读入的数据写入另一个通道   
	            buffer.flip();   
	            // 从输出通道中将数据写入缓冲区   
	            fcout.write(buffer);   
	        }  
		  return true;
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
		  return false;
	  }
  }
}
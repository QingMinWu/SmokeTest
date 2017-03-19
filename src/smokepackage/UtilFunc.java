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
        System.out.println("\t�����ļ�û���ҵ�!"+f.getAbsolutePath());
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
    	System.out.println("����key:" + keyname);
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
	        // ��ȡԴ�ļ���Ŀ���ļ������������   
	        FileInputStream fin = new FileInputStream(src);   
	        FileOutputStream fout = new FileOutputStream(dest);   
	        // ��ȡ�������ͨ��   
	        FileChannel fcin = fin.getChannel();   
	        FileChannel fcout = fout.getChannel();   
	        // ����������   
	        ByteBuffer buffer = ByteBuffer.allocate(1024);   
	        while (true) {   
	            // clear�������軺������ʹ�����Խ��ܶ��������   
	            buffer.clear();   
	            // ������ͨ���н����ݶ���������   
	            int r = fcin.read(buffer);   
	            // read�������ض�ȡ���ֽ���������Ϊ�㣬�����ͨ���ѵ�������ĩβ���򷵻�-1   
	            if (r == -1) {   
	                break;   
	            }   
	            // flip�����û��������Խ��¶��������д����һ��ͨ��   
	            buffer.flip();   
	            // �����ͨ���н�����д�뻺����   
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
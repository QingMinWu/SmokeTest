package smokepackage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMultipart;

/**
* @ClassName: Sendmail
* @Description: ����Email
* @author: wangyy
* @date: 2015-9-8 ����9:42:56
*
*/ 
public class SendMailToAll{

        String Mailinfo = new String();        
        Properties prop = new Properties();
        Session session= null; // =  Session.getInstance(prop);
        //Transport ts = null;   //= session.getTransport();
        Message message = null;
        
        Date systime = new Date();
		String filetime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(systime);
		
        public SendMailToAll()
        {

        	prop.setProperty("mail.host", "mail.asiainfo.com");
        	prop.setProperty("mail.transport.protocol", "smtp");
        	prop.setProperty("mail.smtp.auth", "fale");

 //       	session.getInstance(prop);
        	session =Session.getInstance(prop);
        	//ts= session.getTransport();
        	Mailinfo = "fast send mail";
        	
        }
        
      
      
        public void sendmail(String mailinfo) 
        {
        	
        	 //this.ts = session.getTransport()
        	
        	try{
  //      		this.session.setDebug(true);
        	Transport ts = session.getTransport();
        	ts.connect("mail.asiainfo.com", UtilFunc.getProp2("user_name"), EncodeUtil.deEncryption(UtilFunc.getProp2("password")));
        	 message = this.createSimpleMail1(session,mailinfo);
        	ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        	}catch(Exception e){       
                //e.printStackTrace();
               String errm =  e.toString();
               //e.toString()
               System.out.printf("---------exception begin-----------");
               System.out.printf(errm);
               System.out.printf("---------exception end-----------");

            }	         
        }     	

       public  MimeMessage createSimpleMail1(Session session,String mailall) throws Exception 
        {
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress(UtilFunc.getProp2("from_mail")));
        //�����ʼ��ռ���
        //String to[] = {"wangyy@asiainfo.com","xugui@asiainfo.com","AICMC-GZ-SRD-QA-PROJECT@asiainfo.com","liaocj@asiainfo.com"};
        String to[] = UtilFunc.getProp2("to_mail").split(";");//{"AICMC-GZ-SRD-QA-BCRM@asiainfo.com","AICMC-GZ-SRD-QA-PROJECT@asiainfo.com"};
//        String to[] = {"xugui@asiainfo.com"};
       // String cc[] = {"xiaoyj@asiainfo.com","yangwj@asiainfo.com","luokui3@asiainfo.com","xuerui@asiainfo.com"};
//        String cc[] = {"xugui@asiainfo.com"};
        String cc[] = UtilFunc.getProp2("cc").split(";"); //{"chengwc@asiainfo.com","chenyj7@asiainfo.com","heyq@asiainfo.com","hujp@asiainfo.com","liaf@asiainfo.com","liaocj@asiainfo.com","linzm3@asiainfo.com","luokui3@asiainfo.com","wangdj@asiainfo.com","wanggang7@asiainfo.com","wangyy@asiainfo.com","yinxj@asiainfo.com","yiys@asiainfo.com","zouaj@asiainfo.com","huww@asiainfo.com"};
        InternetAddress[] ToAddress = new InternetAddress[to.length];
        
        for(int i=0;i<to.length;i++)
        {
        	ToAddress[i]= new InternetAddress(to[i]);
        }
        //msg.setRecipients(RecipientType.TO, addressTo);
        
        InternetAddress[] CcAddress = new InternetAddress[cc.length];
        for(int j=0;j<cc.length;j++)
        {
        	CcAddress[j]= new InternetAddress(cc[j]);
        }
              
       //message.setRecipient(Message.RecipientType.TO, new InternetAddress("wangyy@asiainfo.com"));
        //message.setRecipient(Message.RecipientType.TO, new InternetAddress("wangyy@asiainfo.com"));
       message.setRecipients(Message.RecipientType.TO, ToAddress);
       message.setRecipients(Message.RecipientType.CC,CcAddress);
        //�ʼ��ı���
       System.setProperty("mail.mime.charset","UTF-8");
        message.setSubject("������������Ŀ��̨ð�̲��Խ��(����ҵ��)");
        //���÷���ʱ��Ϊ��ǰʱ��
        message.setSentDate(new Date());
        //�ʼ����ı�����
        
    //  ����Ϣ������������
        MimeBodyPart mdp=new MimeBodyPart();//�½�һ������ż����ݵ�BodyPart����
        
        //System.out.printf("--------------begin----------");
        //System.out.printf(mailall);
        //System.out.printf("--------------end----------");

        
        mdp.setContent(mailall,"text/html;charset=gb2312");//��BodyPart�����������ݺ͸�ʽ/���뷽ʽ
        MimeMultipart mm=new MimeMultipart();//�½�һ��MimeMultipart�����������BodyPart��
      //  ��(��ʵ�Ͽ��Դ�Ŷ��)
        mm.addBodyPart(mdp);//��BodyPart���뵽MimeMultipart������(���Լ�����BodyPart)
        message.setContent(mm);//��mm��Ϊ��Ϣ���������

     //   message.saveChanges();
        
        
     //   message.setContent("��ð���", "text/html;charset=UTF-8");
        //���ش����õ��ʼ�����
        return message;
    }	
        
}
      
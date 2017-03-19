package smokepackage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class CSVFileUtil {
	public static final String ENCODE = "UTF-8";
	public String filename = new String();
	public String filepath = new String();
	public String filecsv = new String();
	
	public String tablenead = new String(
			"<thead><th>ģ������</th><th>�ӿ�����</th><th>����״̬</th></thead>");

	CSVFileUtil(String filename, String filepath) {
		filecsv = filepath + filename;
	}

	CSVFileUtil(String filecsv) {
		this.filecsv = filecsv;
	}

	CSVFileUtil() {

	}

	public void readfile() {
		// this.readfile(filecsv);
	}

	public Map readfile(String filecsv, String name) {
		
		String mailtable = new String();
		int rowspan = 0;
		//����ð�̳ɹ�/ʧ��ͳ����
		double count_true = 0;
		double count_false = 0;
		
		try {
			// BufferedReader reader = new BufferedReader(new
			// FileReader(filecsv));//��������ļ���
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					filecsv), "UTF-8");
			BufferedReader reader = new BufferedReader(isr);

			// reader.readLine();//��һ����Ϣ��Ϊ������Ϣ������,�����Ҫ��ע�͵�
			String line = null;
			String iostate = new String();
			// String mailtable = new String();
			String pjie = new String();
			int pjieflag = 0;



			while ((line = reader.readLine()) != null) {
				
				if (pjieflag == 1) {
					line = pjie + line;
					pjieflag = 0;
				}
				String item[] = line.split(",");// CSV��ʽ�ļ�Ϊ���ŷָ����ļ���������ݶ����з�

				// String last = item[item.length-1];
				// int value = Integer.parseInt(last);//�������ֵ������ת��Ϊ��ֵ
				int itemlength = item.length;
				if (itemlength < 11) {
					pjie = line;
					pjieflag = 1;
					continue;
				}

				if (item[7].equals("true")) {
					iostate = "ð�̳ɹ�";
					mailtable = mailtable + "<tr><td>" + item[2] + "</td><td>"
							+ iostate + "</td></tr>";
					count_true++;
				} else {
					iostate = "ð��ʧ��";
					mailtable = mailtable + "<tr><td><span>" + item[2]
							+ "</span></td><td><span>" + iostate
							+ "</span></td></tr>";
					count_false++;
					
				}
				//ͳ��ÿ��ģ��Ľӿ���(����ʧ�����ͳɹ������ܺ�������)
				rowspan =( (int)count_false + (int)count_true);
				// System.out.println(ioname);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String tablemiddle = new String(mailtable.substring(4));
		mailtable = "<tr><td rowspan=\"" + rowspan + "\">" + name + "</td>"
				+ tablemiddle;
		// mailtable= "<td rowspan=\"" +rowspan + "\">" + name + "</td>" +
		// mailtable;

		Map rstMap = new HashMap();
		rstMap.put("MAIL_TABLE", mailtable);
		rstMap.put("COUNT_TRUE", String.valueOf(count_true));
		rstMap.put("COUNT_FALSE", String.valueOf(count_false));
		return rstMap;

	}
}

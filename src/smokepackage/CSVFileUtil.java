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
			"<thead><th>模块名称</th><th>接口名称</th><th>运行状态</th></thead>");

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
		//定义冒烟成功/失败统计数
		double count_true = 0;
		double count_false = 0;
		
		try {
			// BufferedReader reader = new BufferedReader(new
			// FileReader(filecsv));//换成你的文件名
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					filecsv), "UTF-8");
			BufferedReader reader = new BufferedReader(isr);

			// reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
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
				String item[] = line.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分

				// String last = item[item.length-1];
				// int value = Integer.parseInt(last);//如果是数值，可以转化为数值
				int itemlength = item.length;
				if (itemlength < 11) {
					pjie = line;
					pjieflag = 1;
					continue;
				}

				if (item[7].equals("true")) {
					iostate = "冒烟成功";
					mailtable = mailtable + "<tr><td>" + item[2] + "</td><td>"
							+ iostate + "</td></tr>";
					count_true++;
				} else {
					iostate = "冒烟失败";
					mailtable = mailtable + "<tr><td><span>" + item[2]
							+ "</span></td><td><span>" + iostate
							+ "</span></td></tr>";
					count_false++;
					
				}
				//统计每个模块的接口数(根据失败数和成功数的总和来计算)
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

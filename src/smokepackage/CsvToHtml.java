package smokepackage;

import java.util.Date;

public class CsvToHtml {
	String htmlmail = new String();
	int i;
	Date systime = new Date();
	CsvToHtml()
	{
	htmlmail = 
		"<html>"       
		+"<head>"
		+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
		+"<title>Untitled Document</title>"
		+"<style type=\"text/css\">"
		+".query {"
		+"	width:820px;"
		+"	border:1px solid #ccc;"
		+"	border-collapse:collapse;"
		+"	font-size:13px;"
		+"	font-weight:normal;"
		+"	margin-bottom:10px;"
		+"	margin-left:10px;"
		+"}"
		+""
		+".query th {"
		+"	border-collapse:collapse;"
		+"	border: 1px solid #CCC;"
		+"	height:30px;"
		+"	font-weight:normal;"
		+"	line-height:30px;"
		+"	background:#f1f1f1;"
		+"}"
		+".query th img{"
		+"	margin-top:3px;"
		+"}"
		+".query th span {"
		+"	color:#F00;"
		+"}"
		+".query th strong {"
		+"	color:#09F;"
		+"}"
		+".query td {"
		+"	text-align:center;"
		+"	border-collapse:collapse;"
		+"	border: 1px solid #CCC;"
		+"	height:30px;"
		+"	line-height:30px;"
		+"}"
		+".query td span {"
		+"	color:#F00;"
		+"}"
		+".query td strong {"
		+"	color:#09F;"
		+"}"
		+".query td img{"
		+"	margin-top:3px;"
		+"}"
		+"</style>"
		+"</head>"		
		+"<body>"
        +"<p>√∞—Ã≤‚ ‘√∞—Ã≤‚ ‘Ω·π˚,√∞—Ã ±º‰:"+systime.toString()+"</p> " 
        +"<h2>«Î◊¢“‚√∞—Ã ß∞‹µƒΩ”ø⁄</h2> " ;
	}
	
	
}

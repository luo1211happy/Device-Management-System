<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>设备管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #FFFFFF;
	font-size: 12px;
}
.STYLE2 {
	color: #fffc;
	font-size: 14px;
}
.STYLE3 {
	color:#FFFFFF;
	font-size: 20px;
	weight:900;
	
}

A:hover {
	BORDER-RIGHT: white 1px solid; PADDING-RIGHT: 6px; BORDER-TOP: white 1px solid; PADDING-LEFT: 6px; BACKGROUND: #005E5E; PADDING-BOTTOM: 0px; BORDER-LEFT: white 1px solid; COLOR: #43860c; PADDING-TOP: 3px; BORDER-BOTTOM: white 1px solid; TEXT-DECORATION: none; FONT-WEIGHT: bold;
}
-->
</style>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
  <tr>
    <td height="9" style="line-height:9px; background-image:url(images/main_04.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="97" height="10" background="">&nbsp;</td>
        <td width="509">&nbsp;</td>
        <td width="507">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="47" background="images/main_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="38" height="47" background="images/main_06.gif">&nbsp;</td>
        <td width="59"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="29" background="images/main_07.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="18" background="images/main_14.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <tr>
                <td  style="width:1px;">&nbsp;</td>
                <td >
                    <span class="STYLE1">
                       
                    </span>
                </td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="155" background="images/main_08.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" valign="bottom" align="left"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE3"><B><marquee></marquee></B></span></td>
            
          </tr>
        </table></td>
        <td width="200" background="images/main_11.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="11%" height="23">&nbsp;</td>
            <td width="89%" valign="bottom">
            <span class="STYLE1">
                                   
                <SCRIPT language=JavaScript>
                        var day="";
                        var month="";
                        var ampm="";
                        var ampmhour="";
                        var myweekday="";
                        var year="";
                        mydate=new Date();
                        myweekday=mydate.getDay();
                        mymonth=mydate.getMonth()+1;
                        myday= mydate.getDate();
                        myyear= mydate.getYear();
                        year=(myyear > 200) ? myyear : 1900 + myyear;
                        if(myweekday == 0)
                        weekday=" 星期日 ";
                        else if(myweekday == 1)
                        weekday=" 星期一 ";
                        else if(myweekday == 2)
                        weekday=" 星期二 ";
                        else if(myweekday == 3)
                        weekday=" 星期三 ";
                        else if(myweekday == 4)
                        weekday=" 星期四 ";
                        else if(myweekday == 5)
                        weekday=" 星期五 ";
                        else if(myweekday == 6)
                        weekday=" 星期六 ";
                        document.write("北京时间 "+year+"年"+mymonth+"月"+myday+"日 "+weekday+"");
                     </SCRIPT>               
              
            </span>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" style="line-height:5px; background-image:url(images/main_18.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="180" background="images/main_16.gif"  style="line-height:5px;">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  
  <TR bgcolor="#43860c">
          <TD height=21 background="images/main_48.gif" valign="bottom">
            <TABLE cellSpacing=0 cellPadding=0 width="100%" align=left border=0>
            <!-- table5 -->
           
              <TR>
                <TD width=5></TD>
                <TD >
                  <DIV align=left>
                  <SPAN style="FONT-SIZE: 9pt">
                      <FONT color="#FFFFFF">&nbsp;|</FONT>
                  </SPAN> 
                  <SPAN style="FONT-SIZE: 9pt">
                      <A href="/em/front/split_page.do?forward=m_basic_query" style="TEXT-DECORATION: none" target="mainFrame"><FONT color="#FFFFFF">设备信息查询</FONT></A>
                      <FONT color="#FFFFFF">&nbsp;| </FONT>
                      <A href="/em/front/split_page.do?forward=m_scrap_query" style="TEXT-DECORATION: none" target="mainFrame"><FONT  color="#FFFFFF">设备年限查询</FONT></A>
                      <FONT color="#FFFFFF">&nbsp;|</FONT>
                  </SPAN>
                  <SPAN  style="FONT-SIZE: 9pt">
                     <A href="/em/front/split_page.do?forward=m_alarm_query" style="TEXT-DECORATION: none" target="mainFrame"><FONT  color="#FFFFFF">寿命报警查询</FONT></A>
                     <FONT  color="#FFFFFF">&nbsp;|</FONT> 
                     <A href="/em/front/tab/m_fault_history.jsp" style="TEXT-DECORATION: none" target="mainFrame"><FONT color="#FFFFFF">历史故障记录</FONT></A>
                     <FONT color="#FFFFFF">&nbsp;|</FONT>
                     <a href="/em/front/split_page.do?forward=m_fault_query" style="TEXT-DECORATION: none" target="mainFrame"><FONT color="#FFFFFF">设备故障查询</FONT></a>
                     <FONT color="#FFFFFF">&nbsp;|</FONT>
                     <a href="/em/front/m_cipher_update.jsp" style="TEXT-DECORATION: none" target="mainFrame"><FONT color="#FFFFFF">用户信息更新</FONT></a>
                     <FONT color="#FFFFFF">&nbsp;|</FONT><FONT color="#FFFFFF" style="font-weight:bold">&nbsp;&gt;&gt;</FONT>
                     <a href="/em/logout.do" style="TEXT-DECORATION: none" target="_parent"><FONT color="#FFFFFF" style="font-weight:bold">退出系统</FONT></a>
                   
                  </SPAN>
                 </DIV>
                 </TD>
                 </TR>
            
            </TABLE>
            <!-- table5 end-->
</TD></TR>
<tr>
    <td height="5" style="line-height:5px; ">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="180" background=""  style="line-height:5px;">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>

<map name="Map" id="Map">
  <area shape="rect" coords="9,1,55,22" href="/em/front/blank.jsp" target="mainFrame"/>
  <area shape="rect" coords="66,2,160,20" href="" target="I1"/>
  <area shape="rect" coords="169,1,216,23" href="#" onClick="window.close();" target="_parent"/>
</map></body>
</html>
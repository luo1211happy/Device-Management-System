<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
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
	color: #FFFFFF;
	font-size: 12px;
}
-->
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
  <tr>
    <td height="9" style="line-height:9px; background-image:url(images/main_04.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="97" height="9" background="images/main_01.gif">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="47" background="images/main_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="47" background="images/top_06.gif">&nbsp;</td>
        <td width="59"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="30" background="images/top_07.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="17" background="images/top_14.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <tr>
                <td  style="width:1px;"></td>
                <td valign="middle" align="center">
                    <span class="STYLE2">
                        <c:if test="${sessionScope.keeper ne null}">
                             ${sessionScope.keeper.name}
                        </c:if>
                        <c:if test="${sessionScope.admin ne null}">
                             ${sessionScope.admin.name}
                        </c:if>
                    </span>
                </td>
              </tr>
            </table>
            </td>
          </tr>
        </table></td>
        <td width="181" background="images/top_08.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%" height="23" valign="bottom"><img src="images/main_12.gif" width="222" height="23" border="0" usemap="#Map" /></td>
            <td width="60%" align="left" valign="middle"> &nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE1"></span>	</td>
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
    <td height="5" style="line-height:5px; background-image:url(images/main_18.gif)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="180" background="images/main_16.gif"  style="line-height:5px;">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      </table>
    </td>
  </tr>
</table>

<map name="Map" id="Map">
  <area shape="rect" coords="9,1,55,22" href="/em/front/blank.jsp" target="I1"/>
  <c:if test="${sessionScope.keeper ne null}">
      <area shape="rect" coords="66,2,160,20" href="/em/front/cipher_update.jsp" target="I1"/>
  </c:if>
  <c:if test="${sessionScope.admin ne null}">
      <area shape="rect" coords="66,2,160,20" href="/em/front/m_cipher_update.jsp" target="I1"/>
  </c:if>
  <area shape="rect" coords="169,1,216,23" href="/em/logout.do" target="_parent"/>
</map></body>
</html>
    
    


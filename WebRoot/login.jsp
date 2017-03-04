<%@ page language="java" contentType="text/html; charset=gb2312" isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>管理平台</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
}
.STYLE3 {
	color: #005e5e;
	font-size: 12px;
}
.STYLE4 {
	color: #005e5e;
	font-size: 12px;
}
A:hover {
	BORDER-RIGHT: white 1px solid; PADDING-RIGHT: 6px; BORDER-TOP: black 1px solid; PADDING-LEFT: 6px; BACKGROUND: gray; PADDING-BOTTOM: 0px; BORDER-LEFT: black 1px solid; COLOR: #43860c; PADDING-TOP: 3px; BORDER-BOTTOM: white 1px solid; TEXT-DECORATION: none; FONT-WEIGHT: bold;
}
-->
</style></head>

<body>
<form name="form" action="/em/login.do" method="post">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#FFFFFF" valign="bottom">&nbsp;
       <FONT color="#005e5e" style="font-weight:bold; font-size:12px;"></FONT>
       <A href="/em/front/index.do"><FONT color="#005e5e" style="font-weight:bold; font-size:12px;"></FONT></A>
    </td>
  </tr>
  <tr>
    <td height="608" background="/em/front/images/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="/em/front/images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="/em/front/images/login_06.gif">&nbsp;</td>
            <td width="183" background="/em/front/images/login_07.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              
              <tr>
                <td width="21%" height="20"><div align="center"><span class="STYLE3">帐户:</span></div></td>
                <td width="79%" height="20"><input type="text" name="sn"  style="height:18px; width:130px; border:solid 1px #005e5e; font-size:18px; "></td>
              </tr>
              <tr>
                <td height="20"><div align="center"><span class="STYLE3">密码:</span></div></td>
                <td height="20"><input type="password" name="password"  style="height:18px; width:130px; border:solid 1px #005e5e; font-size:16px; "></td>
              </tr>
              <tr>
                <td height="25"><div align="center"><span class="STYLE3">权限:</span></div></td>
                <td height="25">
                  <select name='authority' style="width:100px; " >
          						<option value='N'>保管员</option>
          						<option value='M'>部门管理员</option>
          						<option value='B'>监察员</option>
          						<option value='S'>管理员</option>
                  </select>

                </td>
              </tr>
              
              <tr>
                <td height="30">&nbsp;</td>
                <td height="30" align="left">
                <img onClick="javascript:submit('form');" src="/em/front/images/1.gif" width="45" height="25" border="0"/>
                <img onClick="self.close();" src="/em/front/images/2.gif" width="45" height="25" border="0"/>
                </td>
              </tr>
              
         </table>
            </td>
            <td width="255" background="/em/front/images/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="247" valign="top" background="/em/front/images/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%">&nbsp;</td>
            <td width="22%">&nbsp;</td>
          </tr>
          
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;<span class="STYLE3">${requestScope.error}</span></td>
                <td width="56%" class="STYLE4">Copyright &copy; 2014 V1.0 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="005e5e">&nbsp;</td>
  </tr>
</table>


</form>
</body>

</html>


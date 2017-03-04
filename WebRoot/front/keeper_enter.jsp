<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="images/common.css" type="text/css" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="/em/front/skin/css/base.css" />
<link rel="stylesheet" type="text/css" href="/em/front/skin/css/main.css" />
<SCRIPT language="javascript">

<!--
function fsubmit(theform)
{
if (theform.sn.value.length<4 || theform.sn.value.length>18)
  {
    alert("错误提示：账户最少要4位，最多18位！");
    theform.sn.focus();
    return (false);
  }
if (theform.name.value.length==0)
  {
    alert("错误提示：姓名不能为空！");
    theform.name.focus();
    return (false);
  }
if (theform.password.value.length<6 || theform.password.value.length>16)
  {
    alert("错误提示：密码最少要6位，最多16位！ ！");
    theform.password.focus();
    return (false);
  }
if (theform.certain.value=="")
  {
    alert("错误提示：“确认密码”没有填写完整！");
    theform.certain.focus();
    return (false);
  }

if (theform.certain.value!=theform.password.value)
  {
    alert("错误提示：两次密码输入不一致！");
    theform.certain.focus();
    return (false);
  }
if (theform.authority.value=="-1")
  {
    alert("错误提示：请选择权限！");
    theform.authority.focus();
    return(false);
  }
if (theform.dep_sn.value=="-1")
  {
    alert("错误提示：请选择所属部门！");
    theform.dep_sn.focus();
    return(false);
  }
  
document.myform.submit();

}
function fclose(){
  window.location.href="/ce/front/blank.jsp";
}

function freset(){
  document.myform.reset();
}
-->

</SCRIPT> 
</head>

<body>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><div style='float:left'> <img height="14" src="/em/front/skin/images/frame/book1.gif" width="20" />&nbsp;欢迎注册设备管理系统:${requestScope.prompt}<a style="TEXT-DECORATION: underline" href="/em/front/blank.jsp"><FONT color=#cc0000>关    闭</FONT></a> </div>
      <div style='float:right;padding-right:8px;'>
        <!--  //保留接口  -->
      </div></td>
  </tr>
  <tr>
    <td height="80" background="/em/front/skin/images/frame/sp_bg.gif" style='padding:0px'></td>
  </tr>
</table>

<table width="60%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
  <tr>
    <td colspan="2" background="/em/front/skin/images/frame/wbg.gif" bgcolor="#EEF4EA" class='title'>
    	<div style='float:left'><span>欢迎注册设备管理系统</span></div>
    	<div style='float:right;padding-right:10px;'></div>
   </td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td height="30" colspan="2" align="center" valign="bottom"><table width="100%" border="0" cellspacing="1" cellpadding="1">
        <tr>
          <td width="15%" height="31" align="center">
              <img src="/em/front/skin/images/frame/qc.gif" width="90" height="30" />
          </td>
          <td width="75%" align="left" height="100">
             <div id="man_zone">
  <form name="myform" action="/em/keeper_enter.do" method="post">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style" height="100">
    <tr height="20">
      <td width="18%" class="left_title_2">账户：</td>
      <td width="82%"><input type="text" name="sn" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <font color="#FF0000" >* &nbsp; 必填</font></td>
    </tr>
    
    <tr height="20">
      <td class="left_title_1">输入密码：</td>
      <td><input type="password" name="password" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <font color="#FF0000" >* &nbsp; 必填</font></td>
    </tr>
    <tr height="20">
      <td class="left_title_2">确认密码：</td>
      <td><input type="password" name="certain" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <font color="#FF0000" >* &nbsp; 必填</font></td>
    </tr>
    <tr height="20">
      <td width="50%" class="left_title_2">姓名：</td>
      <td width="50%"><input type="text" name="name" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <font color="#FF0000" >* &nbsp; 必填</font></td>
    </tr>
    <tr height="20">
      <td class="left_title_2">权限：</td>
      <td>
        <select name='authority' style="height:22px; width:135px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
            <c:if test="${sessionScope.admin.authority eq 'M'}">
                <option value = "N">保管员</option>               
            </c:if>
            <c:if test="${sessionScope.admin.authority eq 'S'}">
                <option value='-1' >请选择</option>
                <option value = "M">部门管理员</option>
                <option value = "B">监察员</option>
                <option value = "S">管理员</option>
            </c:if>
            
            
        </select>
        <font color="#FF0000" >* &nbsp; 必填</font>
      </td>
    </tr>
    <tr height="20">
      <td class="left_title_2">所属部门：</td>
      <td>
        <select name='dep_sn' style="height:22px; width:135px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
            <c:if test="${sessionScope.admin.authority eq 'M'}">
                <c:forEach var = "dep" items = "${sessionScope.dep_list}">
                    <c:if test="${sessionScope.admin.dep_sn eq dep.sn}">
                        <option value = "${dep.sn}">${dep.name}</option>
                    </c:if>                    
                </c:forEach>
            </c:if>
            <c:if test="${sessionScope.admin.authority eq 'S'}">
                <option value='-1' >请选择</option>
                <c:forEach var = "dep" items = "${sessionScope.dep_list}">
                    <option value = "${dep.sn}">${dep.name}</option>
                </c:forEach>
            </c:if>
            
        </select>
        <font color="#FF0000" >* &nbsp; 必填</font>
      </td>
    </tr>

    <tr bgcolor="#FFFFFF" height="20">
      <td><a href="#"><img onclick="fsubmit(document.myform)" src="/em/front/images/tijiao.gif" /></a></td>
      <td><a href="#"><img onclick="freset()" src="/em/front/images/chongzhi.gif"/></a></td>
    </tr>
  </table>
  </form>
</div> 
           </td>
        </tr>
      </table></td>
  </tr>
</table>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="images/common.css" type="text/css" />
<title>����Ա��Ϣ����</title>
<link rel="stylesheet" type="text/css" href="/em/front/skin/css/base.css" />
<link rel="stylesheet" type="text/css" href="/em/front/skin/css/main.css" />
<SCRIPT language="javascript">

<!--
function fsubmit(theform)
{
if (theform.sn.value.length<4 || theform.sn.value.length>18)
  {
    alert("������ʾ���˻�������Ҫ4λ�����18λ��");
    theform.sn.focus();
    return (false);
  }
if (theform.old_password.value.length<6 || theform.old_password.value.length>16)
  {
    alert("������ʾ������������룬����������Ҫ6λ�����16λ��");
    theform.old_password.focus();
    return (false);
  }
if (theform.password.value.length<6 || theform.password.value.length>16)
  {
    alert("������ʾ����������Ҫ6λ�����16λ��");
    theform.password.focus();
    return (false);
  }
if (theform.certain.value=="")
  {
    alert("������ʾ����ȷ�����롱û����д������");
    theform.certain.focus();
    return (false);
  }

if (theform.certain.value!=theform.password.value)
  {
    alert("������ʾ�������������벻һ�£�");
    theform.certain.focus();
    return (false);
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
    <td><div style='float:left'> <img height="14" src="/em/front/skin/images/frame/book1.gif" width="20" />&nbsp;����Ա��Ϣ����:${requestScope.prompt} </div>
      <div style='float:right;padding-right:8px;'>
        <!--  //�����ӿ�  -->
      </div></td>
  </tr>
  <tr>
    <td height="80" background="/em/front/skin/images/frame/sp_bg.gif" style='padding:0px'></td>
  </tr>
</table>

<table width="60%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
  <tr>
    <td colspan="2" background="/em/front/skin/images/frame/wbg.gif" bgcolor="#EEF4EA" class='title'>
    	<div style='float:left'><span>����Ա��Ϣ����</span></div>
    	<div style='float:right;padding-right:10px;'></div>
   </td>
  </tr>
  <tr bgcolor="#FFFFFF" valign="top">
    <td height="30" colspan="1" align="center" valign="top">
      <table width="100%" border="0" cellspacing="1" cellpadding="1" >
        <tr>
          <td width="15%" height="31" align="center" valign="middle">
              <img src="/em/front/skin/images/frame/qc.gif" width="90" height="30" />
          </td>
          <td width="75%" align="left" height="100">
             <div id="man_zone">
  <form name="myform" action="/em/cipher_update.do" method="post">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style" height="100">
    <tr height="20">
      <td width="18%" class="left_title_2">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
      <td width="82%"><input readonly="readonly" type="text" name="sn" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:18px; color:#81b432;" 
      value="${sessionScope.admin.sn}" />
      </td>
    </tr>
    <tr height="20">
      <td class="left_title_1">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
      <td><input type="text" name="name" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" 
      value="${sessionScope.admin.name}"/>
        <font color="#FF0000" >* &nbsp; ����</font></td>
    </tr>
    <tr height="20">
      <td class="left_title_1">Ȩ&nbsp;&nbsp;&nbsp;&nbsp;�ޣ�</td>
      <td>
         <select name='authority' style="height:22px; width:135px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" >
            <c:if test="${sessionScope.admin.authority eq 'N'}"><option value = "N" SELECTED>����Ա</option></c:if>
            <c:if test="${sessionScope.admin.authority eq 'M'}"><option value = "M" SELECTED>���Ź���Ա</option></c:if>
            <c:if test="${sessionScope.admin.authority eq 'B'}"><option value = "B" SELECTED>���Ա</option></c:if>
            <c:if test="${sessionScope.admin.authority eq 'S'}"><option value = "S" SELECTED>����Ա</option></c:if>
         </select> 
      </td>
    </tr>
    <tr height="20">
      <td class="left_title_1">��&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</td>
      <td>
        <select name='dep_sn' style="height:22px; width:135px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
            <c:if test="${(sessionScope.admin.authority eq 'M') or (sessionScope.admin.authority eq 'B')}">
                <c:forEach var = "dep" items = "${sessionScope.dep_list}">
                    <c:if test="${sessionScope.admin.dep_sn eq dep.sn}">
                        <option value = "${dep.sn}" >${dep.name}</option>
                    </c:if>                   
                </c:forEach>               
            </c:if>
            <c:if test="${sessionScope.admin.authority eq 'S'}">
                <option value='-1' >��ѡ��</option>
                <c:forEach var = "dep" items = "${sessionScope.dep_list}">
                    <option value = "${dep.sn}" <c:if test="${sessionScope.admin.dep_sn eq dep.sn}">SELECTED</c:if>>${dep.name}</option>
                </c:forEach>
            </c:if>
            
        </select>
        <font color="#FF0000" >* &nbsp; ����</font></td>
    </tr>
    <tr height="20">
      <td class="left_title_1">ԭ �� �룺</td>
      <td><input type="password" name="old_password" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <font color="#FF0000" >* &nbsp; ����</font></td>
    </tr>
    
    <tr height="20">
      <td class="left_title_1">�� �� �룺</td>
      <td><input type="password" name="password" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <font color="#FF0000" >* &nbsp; ����</font></td>
    </tr>
    <tr height="20">
      <td class="left_title_2">ȷ�����룺</td>
      <td><input type="password" name="certain" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
        <font color="#FF0000" >* &nbsp; ����</font></td>
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

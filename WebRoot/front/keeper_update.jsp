<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="images/common.css" type="text/css" />
<title>�û���Ϣ����</title>
<link rel="stylesheet" type="text/css" href="/em/front/skin/css/base.css" />
<link rel="stylesheet" type="text/css" href="/em/front/skin/css/main.css" />
<SCRIPT language="javascript">

<!--
function fsubmit(theform)
{
if (theform.name.value.length==0)
  {
    alert("������ʾ����������Ϊ�գ������룡");
    theform.name.focus();
    return (false);
  }
if (theform.password.value.length<6 || theform.password.value.length>16)
  {
    alert("������ʾ����������Ҫ6λ�����16λ��");
    theform.password.focus();
    return (false);
  }
if (theform.authority.value=="-1")
  {
    alert("������ʾ����ѡ��Ȩ�ޣ�");
    theform.authority.focus();
    return(false);
  }
if (theform.dep_sn.value=="-1")
  {
    alert("������ʾ����ѡ���������ţ�");
    theform.dep_sn.focus();
    return(false);
  }
document.myform.submit();
window.opener.location.replace('/em/front/tab/keeper_update.jsp');
self.close();
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

<table width="60%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
  <tr>
    <td colspan="2" background="/em/front/skin/images/frame/wbg.gif" bgcolor="#EEF4EA" class='title'>
    	<div style='float:left'><span>�û���Ϣ����</span></div>
    	<div style='float:right;padding-right:10px;'></div>
   </td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td height="30" colspan="2" align="center" valign="bottom"><table width="100%" border="0" cellspacing="1" cellpadding="1">
        <tr>
          <td width="15%" height="31" align="center" valign="top">
              <img src="/em/front/skin/images/frame/qc.gif" width="90" height="30" />
          </td>
          <td width="75%" align="left" height="100">
             <div id="man_zone">
  <form name="myform" action="/em/admin/update_keeper.do" method="post">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style" height="100">
    <tr height="20">
      <td width="18%" class="left_title_2">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
      <td width="82%">
          <input readonly="readonly" type="text" name="sn" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:18px; color:#81b432;"  value="${param.sn}"/>
      </td>
    </tr>
    
    <tr height="20">
      <td class="left_title_1">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
      <td><input type="text" name="name" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" 
      value="<%=new String(request.getParameter("name").getBytes("ISO-8859-1"), "GB2312") %>"/>
        <font color="#FF0000" >* &nbsp; ����</font></td>
    </tr>
    <tr height="20">
      <td class="left_title_1">Ȩ&nbsp;&nbsp;&nbsp;&nbsp;�ޣ�</td>
      <td>
         <select name='authority' style="height:22px; width:135px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
            <c:if test="${sessionScope.admin.authority eq 'M'}">
                <option value = "N">����Ա</option>               
            </c:if>
            <c:if test="${sessionScope.admin.authority eq 'S'}">
                <option value='-1' >��ѡ��</option>
                <option value = "M" <c:if test="${param.authority eq 'M'}">SELECTED</c:if>>���Ź���Ա</option>
                <option value = "B" <c:if test="${param.authority eq 'B'}">SELECTED</c:if>>���Ա</option>
                <option value = "S" <c:if test="${param.authority eq 'S'}">SELECTED</c:if>>����Ա</option>
            </c:if>
            
         </select>
         <font color="#FF0000" >* &nbsp; ����</font>
      </td>
    </tr>
    <tr height="20">
      <td class="left_title_1">��&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</td>
      <td>
        <select name='dep_sn' style="height:22px; width:135px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
            <c:if test="${sessionScope.admin.authority eq 'M'}">
                <c:forEach var = "dep" items = "${sessionScope.dep_list}">
                    <c:if test="${param.dep_sn eq dep.sn}">
                        <option value = "${dep.sn}" >${dep.name}</option>
                    </c:if>                   
                </c:forEach>               
            </c:if>
            <c:if test="${sessionScope.admin.authority eq 'S'}">
                <option value='-1' >��ѡ��</option>
                <c:forEach var = "dep" items = "${sessionScope.dep_list}">
                    <option value = "${dep.sn}" <c:if test="${param.dep_sn eq dep.sn}">SELECTED</c:if>>${dep.name}</option>
                </c:forEach>
            </c:if>
            
        </select>
        <font color="#FF0000" >* &nbsp; ����</font></td>
    </tr>
    <tr height="20">
      <td class="left_title_1">�� �� �룺</td>
      <td><input type="password" name="password" value="${param.password}" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" />
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

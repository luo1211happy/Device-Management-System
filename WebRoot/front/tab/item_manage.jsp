<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�ޱ����ĵ�</title>
<link rel="stylesheet" type="text/css" href="/em/front/tab/css/base.css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}

-->
</style>

<script language=javascript>
var  highlightcolor='#eafcd5';
//�˴�clickcolorֻ����winϵͳ��ɫ������ܳɹ�,�����#xxxxxx�Ĵ���Ͳ���,��û�����Ϊʲô:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}


</script>
<script language=javascript>

function ToLink(location) 
	{
		window.open( location,'' ,'top='+(window.screen.availHeight-200)/2+',left='+(window.screen.availWidth-390)/2+',height=154,width=390,status=no,toolbar=no,menubar=no,location=no,resizable=no,titlebar=no,scrollbars=no');
	}
function deleteItem(uri){
    if (confirm("ȷ��Ҫɾ����С����")){
        window.location.href=uri;
    }
}
</script>
</head>

<body>
<!--  ������  -->
<form name='form2' action='/em/admin/add_item.do' method='post'>

<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
        <td width='90' align='center' height='40'><span class="STYLE4">С��¼��</span></td>
        <td width='150' align='right'>�������ƣ�&nbsp;</td>
		<td width='90' align="left">
            <select name='class_sn' style='width:300'>
               <option value='0' >��ѡ��</option>
               <c:forEach var = "class" items = "${sessionScope.class_list}">
                       <option value = "${class.sn}" <c:if test="${class.sn eq requestScope.sn}">SELECTED</c:if>>
                       ${class.name}</option>
               </c:forEach>
            </select>
        </td>   
        <td width='90' align="right">С�����ƣ� </td>
        <td width='69' align="left">
          <input name="item" type="text"  style='width:45' height="20" />
        </td>
		
        <td width="10"></td>
        <td width="45">
          <input name="imageField" type="image" onclick="fsubmit(document.form2);" src="/em/front/tab/images/insert.gif" width="45" height="20" border="0" class="np" />
        </td>
        <td width="400"></td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<table><tr><td height="5"></td></tr></table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="/em/front/tab/images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">С����Ϣ����</span></td>
        <td width="281" background="/em/front/tab/images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              

            </tr>
        </table></td>
        <td width="14"><img src="/em/front/tab/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="/em/front/tab/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">���</span></td>
            <td  height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">����</span></td>
            <td  height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">С��</span></td>
            <td  height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">����</span></td>
            <td  height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">ɾ��</span></td>
          </tr>
          <c:forEach var = "class"  items ="${sessionScope.class_list}"> 
          <tr>
            <td height="18" bgcolor="#FFFFFF">
              <div align="center" class="STYLE1">
                 ${class.sn}
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 ${class.name}
              </div>
            </td>
            <td colSpan="3" bgcolor="#FFFFFF" class="STYLE2">
                <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
                   <c:forEach var = "item"  items ="${sessionScope.item_list}"> 
                   <c:if test="${item.class_sn eq class.sn}">
                   <tr>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2" width="34%">
                       <div align="center" class="STYLE2 STYLE1">
                          ${item.name}
                       </div>
                   </td>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2"  width="33%">
                       <div align="center" class="STYLE2 STYLE1">
                          <a href="#" class="coolbg" onclick='ToLink("/em/front/tab/item_update.jsp?sn=${item.sn}&name=${item.name}")'>&nbsp;����&nbsp;</a>
                       </div>
                   </td>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2"  width="33%">
                       <div align="center" class="STYLE2 STYLE1">
                          <a href="#" class="coolbg" onclick='deleteItem("/em/admin/delete_item.do?sn=${item.sn}")'>&nbsp;ɾ��&nbsp;</a>
                       </div>
                   </td>
                   </tr>
                   </c:if>
                   </c:forEach>
                </table>              
            </td>
            
           
          </tr>
          </c:forEach>  
        </table></td>
        <td width="9" background="/em/front/tab/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="/em/front/tab/images/tab_20.gif" width="15" height="29" /></td>
        <td background="/em/front/tab/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE4"></span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">
              &nbsp;
            </div></td>
          </tr>
        </table></td>
        <td width="14"><img src="/em/front/tab/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>

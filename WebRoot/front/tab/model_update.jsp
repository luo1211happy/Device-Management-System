<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�ͺŸ���</title>
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

<script>
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

<script language="javascript">
function fsubmit(){
    document.form2.submit();
    window.opener.location.replace('/em/front/tab/model_manage.jsp');
    self.close();
}

function fillItem(group){
      var group=group;
      document.location.href="/em/admin/cascade.do?forward=model_update&sn=${param.sn}&name=${param.name}&life=${param.life}&hasAppendix=${param.hasAppendix}&supplier_sn=${param.supplier_sn}&group="+group; 
}
</script>


</head>

<body>

<table><tr><td height="5"></td></tr></table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="/em/front/tab/images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">�ͺŸ���</span></td>
        <td width="281" background="/em/front/tab/images/tab_05.gif">&nbsp;</td>
        <td width="14"><img src="/em/front/tab/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="/em/front/tab/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3">
        <form name='form2' action='/em/admin/modify_model.do?sn=${param.sn}' method='post'>
        <table align="center" cellpadding="0" cellspacing="0" width="100%">
        	
        	<tr>
                <td width='100' align='right'>�������ƣ�&nbsp;</td>
		        <td width='100' align="left">
                    <select name='class_sn' style='width:100px' onchange="fillItem(this.options[this.options.selectedIndex].value)">
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "class" items = "${sessionScope.class_list}">
                        <option value = "${class.sn}" <c:if test="${class.sn eq requestScope.hierarchy.group}">SELECTED</c:if>>${class.name}</option>
                        </c:forEach>
                    </select>
                </td> 
                <td width='100' align='right'>С�����ƣ�&nbsp;</td>
		        <td width='100' align="left">
                    <select name='item_sn' style='width:100px'>
                        <option value='0' >��ѡ��</option>                      
                        <c:forEach var = "item" items = "${sessionScope.item_list}">
                          <c:if test="${item.class_sn eq requestScope.hierarchy.group}">
                            <option value = "${item.sn}" <c:if test="${item.sn eq requestScope.sn}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>  
                <td width='100' align="right">�ͺ����ƣ� </td>
                <td width='100' align="left">
                    <input name="name" type="text"  style='width:100px'  height="20" value="${param.name}"/>
                </td>
              </tr>
              <tr><td colSpan="6"></td></tr>  
              <tr>
                <td width='100' align='right'>�������ƣ�&nbsp;</td>
		        <td width='100' align="left">
                    <select name='supplier_sn' style='width:100px' >
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "supplier" items = "${sessionScope.supplier_list}">
                        <option value = "${supplier.sn}" <c:if test="${supplier.sn eq param.supplier_sn}">SELECTED</c:if>>${supplier.name}</option>
                        </c:forEach>
                    </select>
                </td> 
                <td width='100' align='right'>ʹ�����ޣ�&nbsp;</td>
		        <td width='100' align="left">
                    <input name="life" type="text"  style='width:97px' height="20" value="${param.life}"/>
                </td>  
                <td width='100' align="right">�б����� </td>
                <td width='100' align="left">
                   <table>
                      <tr>
                         <c:if test="${param.hasAppendix eq 'Y'}">
                             <td><input name="hasAppendix" type="checkbox" style='width:20px'  checked align="left"/></td>
                         </c:if>
                         <c:if test="${param.hasAppendix eq 'N'}">
                             <td><input name="hasAppendix" type="checkbox" style='width:20px' align="left"/></td>
                         </c:if>
                         <td>&nbsp;&nbsp;<image name="imageField" align="right" type="image" onclick="fsubmit();" src="/em/front/tab/images/update.gif" class="np" /></td>
                     </tr>
                   </table>                                     
                </td>		
              </tr>
        </table>
        </form>
        </td>
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
            <td width="25%" height="29" nowrap="nowrap">&nbsp;</td>
            <td width="75%" valign="top" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="/em/front/tab/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>

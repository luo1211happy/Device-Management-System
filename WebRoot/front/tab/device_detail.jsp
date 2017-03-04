<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>设备详细信息</title>
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

<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></SCRIPT>
<script>
var  highlightcolor='#eafcd5';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
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
    if(document.form1.buy_date.value == ""){
        alert("购买日期不能为空！");
        return false;
    }
        
    document.form1.submit();
    window.opener.location.replace('/em/front/tab/manage_device.jsp');
    self.close();
}

function fillItem(group){
      var group=group;
      document.location.href="/em/admin/cascade.do?forward=model_update&sn=${param.sn}&name=${param.name}&life=${param.life}&hasAppendix=${param.hasAppendix}&supplier_sn=${param.supplier_sn}&group="+group; 
}

function print(dev_sn){
      var dev_sn = dev_sn;
      document.location.href="/em/front/export_single_device.do?dev_sn=" + dev_sn; 
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
        <td width="1101" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">设备详细信息</span></td>
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
        <form name='form1' action='/em/front/modify_device.do' method='post'>
        <table align="center" cellpadding="0" cellspacing="0" width="100%">
        	<tr><td height="10" colSpan="6"></td></tr>
        	<tr>
                <td width='100' align='right'>大项名称：&nbsp;</td>
		        <td width='100' align="left">
		           <c:forEach var="class" items="${sessionScope.class_list}">
                    <c:forEach var = "item"  items ="${sessionScope.item_list}">
                       <c:forEach var = "model"  items ="${sessionScope.model_list}">
                         <c:if test="${(class.sn eq item.class_sn) and (item.sn eq model.item_sn) and (model.sn eq param.model_sn)}">${class.name}</c:if>
                       </c:forEach>
                    </c:forEach>
                   </c:forEach>
                </td> 
                <td width='100' align='right'>小项名称：&nbsp;</td>
		        <td width='100' align="left">
                    <c:forEach var = "item"  items ="${sessionScope.item_list}">
                       <c:forEach var = "model"  items ="${sessionScope.model_list}">
                         <c:if test="${(item.sn eq model.item_sn) and (model.sn eq param.model_sn)}">${item.name}</c:if>
                       </c:forEach>
                    </c:forEach>
                </td>  
                <td width='100' align="right">型号名称： </td>
                <td width='100' align="left">
                    <c:forEach var = "model"  items ="${sessionScope.model_list}">
                       <c:if test="${model.sn eq param.model_sn}">${model.name}</c:if>
                    </c:forEach>
                </td>
              </tr>
              <tr><td height="10" colSpan="6"></td></tr>  
              <tr>
                <td width='100' align='right'>厂家名称：&nbsp;</td>
		        <td width='100' align="left">
                    <c:forEach var = "model"  items ="${sessionScope.model_list}">
                       <c:forEach var = "supplier" items = "${sessionScope.supplier_list}">
                       <c:if test="${(model.sn eq param.model_sn) and (supplier.sn eq model.supplier_sn)}">${supplier.name}</c:if>
                       </c:forEach>
                    </c:forEach>
                </td> 
                <td width='100' align='right'>使用年限：&nbsp;</td>
		        <td width='100' align="left">
                    <c:forEach var = "model"  items ="${sessionScope.model_list}">
                       <c:if test="${model.sn eq param.model_sn}">${model.life}</c:if>
                    </c:forEach>
                </td>  
                <td width='100' align="right">是否有备件： </td>
                <td width='100' align="left">
                    <c:forEach var = "model"  items ="${sessionScope.model_list}">
                       <c:if test="${model.sn eq param.model_sn}">
                         <c:if test="${model.hasAppendix eq 'Y'}">有</c:if>
                         <c:if test="${model.hasAppendix eq 'N'}">无</c:if>
                       </c:if>
                    </c:forEach>                               
                </td>		
              </tr>
              <tr><td height="30" colSpan="6"></td></tr> 
              <tr>
                <td width='100' align='right'>设备编号：&nbsp;</td>
		        <td width='100' align="left">
                    ${param.sn}
                </td> 
                <td width='100' align='right'>购买日期：&nbsp;</td>
		        <td width='100' align="left">
                    ${param.buy_date}
                </td>  
                <td width='100' align="right">设备状况： </td>
                <td width='100' align="left">
                    <c:if test="${'G' eq param.status}">良好</c:if>
                    <c:if test="${'F' eq param.status}">故障</c:if>                          
                    <c:if test="${param.status eq 'S'}">报废</c:if>                         
                </td>		
              </tr>
              <tr><td height="10" colSpan="6"></td></tr> 
              <tr>
                <td width='100' align='right'>长期带电：&nbsp;</td>
		        <td width='100' align="left">
                    <c:if test="${'Y' eq param.isElectric}">是</c:if>
                    <c:if test="${'N' eq param.isElectric}">否</c:if>                            
                   
                </td> 
                <td width='100' align='right'>存放位置：&nbsp;</td>
		        <td width='100' align="left">
                    <c:forEach var="loc" items = "${sessionScope.loc_list}">
                       <c:if test="${loc.sn eq param.location_sn}">${loc.address}</c:if>
                    </c:forEach>                            
                    
                </td>  
                <td width='100' align="right">保管人：</td>
                <td width='100' align="left">${sessionScope.keeper.name}</td>		
              </tr>
              <tr>
                <td colSpan="5"></td>
                <td>
                    <img name="imageField" onclick="print('${param.sn}');" src="/em/front/tab/images/print.gif" width="45" height="20" border="0" class="np" />
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

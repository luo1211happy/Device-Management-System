<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
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
<script language=javascript>
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
<script language=javascript>

function ToLink(location) 
	{
		window.open( location,'' ,'top='+(window.screen.availHeight-384)/2+',left='+(window.screen.availWidth-560)/2+',height=384,width=560,status=no,toolbar=no,menubar=no,location=no,resizable=no,titlebar=no,scrollbars=no');
	}
function deleteDevice(uri){
    if (confirm("确定要删除该设备信息吗？")){
        window.location.href=uri;
    }
}

function fillItem(group){
      var group=group;
      document.location.href="/em/admin/cascade.do?forward=fault_record&group="+group; 
}
function fillModel(group, item){
      var group=group;
      var item=item;
      document.location.href="/em/admin/cascade.do?forward=fault_record&group="+group+"&item="+item; 
}
function print(){
      document.location.href="/em/admin/get_finished_record.do?from_date=${param.from_date}&to_date=${param.to_date}&dev_sn=${param.dev_sn}&forward=print"; 
}
</script>
</head>

<body>
<!--  搜索表单  -->
<table width='99%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0' width="100%">
        <tr>
        <td height='40' width='10%' style="border-right:1px solid #CBD8AC;">
            <span class="STYLE4">历史故障</span>
        </td>
        <td><form name='form1' action='/em/admin/get_finished_record.do?forward=self' method='post'>
            <table> 
              <tr>
                <TD width='100' align="right">故障日期：&nbsp;</TD>
                <TD width='100' align="left">
                  <input name="from_date" type="text" value="${param.from_date}" onClick="calendar.show(this);" style="width:96px; "/>
                  
                </TD>
                <td width="100"><span class="STYLE4">&#8212;&#8212;&#8212;&rarr;</span></td>
                <TD width='100' align="left">
                  <input name="to_date" type="text" value="${param.to_date}" onClick="calendar.show(this);" style="width:96px; "/>                
                </TD>
                <td width="100" align="right">设备编号：</td>
                <td width="100" align="left">
                  <input name="dev_sn" type="text" value="${param.dev_sn}" style="width:96px; "/>   
                </td>
                <td width="50" >
                    <img name="imageField" onclick="document.form1.submit();" src="/em/front/tab/images/search.gif" width="45" height="20" border="0" class="np" />
                </td>
                <td width="50" >
                    <img name="imageField" onclick="print();" src="/em/front/tab/images/print.gif" width="45" height="20" border="0" class="np" />
                </td>
                <td colSpan="4"></td>		
              </tr>
            </table>
            </form>
        </td>
        
       </tr>
      </table>
    </td>
  </tr>
</table>


<table><tr><td height="5"></td></tr></table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="/em/front/tab/images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">设备历史故障</span></td>
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
        <td width="8" background="/em/front/tab/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="18" width="10%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">设备编号</span></td>
            <td  height="18" width="8%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">小项</span></td>           
            <td  height="18" width="8%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">型号</span></td>
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">故障日期</span></td>
            <td  height="18" width="24%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">故障信息</span></td>
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">维修日期</span></td>
            <td  height="18" width="24%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">维修记录</span></td>
            <td  height="18" width="8%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">保管人</span></td>
          </tr>
          <c:forEach var = "rec" items ="${requestScope.rec_list}"> 
          <tr>
            <td height="18" width="10%" bgcolor="#FFFFFF">
              <div align="left" class="STYLE1">
                 ${rec.device_sn}
              </div>
            </td>
            <td height="18" width="8%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                
                 <c:forEach var = "item"  items ="${sessionScope.item_list}">
                     <c:forEach var = "model"  items ="${sessionScope.model_list}">
                           <c:forEach var = "dev"  items ="${sessionScope.device_list}">
                               <c:if test="${(dev.sn eq rec.device_sn) and (model.sn eq dev.model_sn) and (item.sn eq model.item_sn)}">${item.name}</c:if>
                           </c:forEach>
                         
                     </c:forEach>
                 </c:forEach>
              </div>
            </td>
            <td height="18" width="8%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 <c:forEach var = "model"  items ="${sessionScope.model_list}">
                     <c:forEach var = "dev"  items ="${sessionScope.device_list}">
                        <c:if test="${(dev.sn eq rec.device_sn) and (model.sn eq dev.model_sn)}">${model.name}</c:if>
                     </c:forEach>       
                 </c:forEach>
              </div>
            </td>
            <td height="18" width="9%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 ${rec.fault_date}
              </div>
            </td>
            <td height="18" valign="top" width="24%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 ${rec.fault}
              </div>
            </td>
            <td height="18" width="9%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 ${rec.repair_date}
              </div>
            </td>
            <td height="18" valign="top" width="24%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 ${rec.repair}
              </div>
            </td> 
            <td height="18" width="8%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:forEach var = "keeper"  items ="${sessionScope.keeper_list}">
                     <c:forEach var = "dev"  items ="${sessionScope.device_list}">
                        <c:if test="${(dev.sn eq rec.device_sn) and (keeper.sn eq dev.keeper)}">${keeper.name}</c:if>
                     </c:forEach>       
                 </c:forEach>
              </div>
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
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE4"></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right"></td>
          </tr>
        </table></td>
        <td width="14"><img src="/em/front/tab/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>

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
		window.open( location,'' ,'top='+(window.screen.availHeight-380)/2+',left='+(window.screen.availWidth-830)/2+',height=380,width=830,status=no,toolbar=no,menubar=no,location=no,resizable=yes,titlebar=no,scrollbars=yes');
	}
function deleteDevice(uri){
    if (confirm("确定要删除该设备信息吗？")){
        window.location.href=uri;
    }
}

function fillItem(group){
      var group=group;
      document.location.href="/em/admin/cascade.do?forward=fault_query&group="+group; 
}
function fillModel(group, item){
      var group=group;
      var item=item;
      document.location.href="/em/admin/cascade.do?forward=fault_query&group="+group+"&item="+item; 
}
</script>
</head>

<body>
<!--  搜索表单  -->


<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
        <td height='60' width='10%' style="border-right:1px solid #CBD8AC;">
            <span class="STYLE4">故障查询</span>
        </td>
        <td><form name='form1' action='/em/front/query_for_manage.do?forward=fault_query&page=first' method='post'>
            <table>
              <tr>
                <td width='100' align='right'>大项名称：&nbsp;</td>
		        <td width='100' align="left">
                    <select name='class_sn' style='width:100px' id='class_sn' onchange="fillItem(this.options[this.options.selectedIndex].value)">
                        <option value='0' >请选择</option>
                        <c:forEach var = "class" items = "${sessionScope.class_list}">
                        <option value = "${class.sn}" <c:if test="${(class.sn eq requestScope.hierarchy.group) or (class.sn eq param.class_sn)}">SELECTED</c:if>>${class.name}</option>
                        </c:forEach>
                    </select>
                </td> 
                <td width='100' align='right'>小项名称：&nbsp;</td>
		        <td width='100' align="left">
                    <select name='item_sn' style='width:100px' onchange="fillModel(document.getElementById('class_sn').options[document.getElementById('class_sn').options.selectedIndex].value, this.options[this.options.selectedIndex].value)">
                        <option value='0' >请选择</option>
                        <c:forEach var = "item" items = "${sessionScope.item_list}">
                          <c:if test="${(item.class_sn eq requestScope.hierarchy.group) or (item.class_sn eq param.class_sn)}">
                            <option value = "${item.sn}" <c:if test="${(item.sn eq requestScope.hierarchy.item) or (item.sn eq param.item_sn)}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>  
                <td width='100' align="right">型号： </td>
                <td width='100' align="left">
                    <select name='model_sn' style='width:100px'>
                        <option value='0' >请选择</option>
                        <c:forEach var = "item" items = "${sessionScope.model_list}">
                          <c:if test="${(item.item_sn eq requestScope.hierarchy.item) or (item.item_sn eq param.item_sn)}">
                            <option value = "${item.sn}" <c:if test="${item.sn eq param.model_sn}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>
                <td width="100">
                </td>
                <td colSpan="5"></td>
                
                
              </tr>
                
              <tr>
                <TD width='100' align="right">购买日期：&nbsp;</TD>
                <TD width='100' align="left">
                  <input name="from_date" type="text" value="${param.from_date}" onClick="calendar.show(this);" style="width:96px; "/>
                  
                </TD>
                <td width="100"><span class="STYLE4">&#8212;&#8212;&#8212;&rarr;</span></td>
                <TD width='100' align="left">
                  <input name="to_date" type="text" value="${param.to_date}" onClick="calendar.show(this);" style="width:96px; "/>                
                </TD>
                <td width="100">
                    
                </td>
                <td width="100" align="right">
                    <img name="imageField" type="image" onclick="document.form1.submit();" src="/em/front/tab/images/search.gif" width="45" height="20" border="0" class="np" />
                </td>
                <td width="100" >
                    
                </td>
                <td colSpan="5"></td>		
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
        <td width="1101" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">设备故障查询</span></td>
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
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">设备编号</span></td>
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">小项</span></td>           
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">型号</span></td>
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">购买日期</span></td>
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">状况</span></td>
            <td  height="18" width="9%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">长期带电</span></td>
            <td  height="18" width="22%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">存放位置</span></td>
            <td  height="18" width="7%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">保管人</span></td>  
            <td  height="18" width="7%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">历史故障</span></td>  
            
          </tr>
          <c:forEach var = "dev" items ="${sessionScope.dev_list}" begin="${(sessionScope.split_page.currentPage-1)*sessionScope.split_page.pageRows}" end="${sessionScope.split_page.currentPage*sessionScope.split_page.pageRows-1}"> 
          <tr>
            <td height="18" width="9%" bgcolor="#FFFFFF">
              <div align="left" class="STYLE1">
                 ${dev.sn}
              </div>
            </td>
            <td height="18" width="9%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                
                 <c:forEach var = "item"  items ="${sessionScope.item_list}">
                     <c:forEach var = "model"  items ="${sessionScope.model_list}">
                         <c:if test="${(item.sn eq model.item_sn) and (model.sn eq dev.model_sn)}">${item.name}</c:if>
                     </c:forEach>
                 </c:forEach>
              </div>
            </td>
            <td height="18" width="9%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 <c:forEach var = "model"  items ="${sessionScope.model_list}">
                     <c:if test="${model.sn eq dev.model_sn}">${model.name}</c:if>
                 </c:forEach>
              </div>
            </td>
            <td height="18" width="9%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 ${dev.buy_date}
              </div>
            </td>
            <td height="18" width="9%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:if test = "${dev.status eq 'G'}">良好</c:if>
                 <c:if test = "${dev.status eq 'F'}">故障</c:if>
                 <c:if test = "${dev.status eq 'S'}">报废</c:if>
              </div>
            </td>
            <td height="18" width="9%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:if test = "${dev.isElectric eq 'Y'}">是</c:if>
                 <c:if test = "${dev.isElectric eq 'N'}">否</c:if>
              </div>
            </td>
            <td height="18" width="22%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 <c:forEach var = "loc"  items ="${sessionScope.loc_list}">
                     <c:if test="${loc.sn eq dev.position}">${loc.address}</c:if>
                 </c:forEach>
              </div>
            </td>
            
            <td align="center" bgcolor="#FFFFFF" class="STYLE2"  width="7%">
               <div align="center" class="STYLE2 STYLE1">
                 ${sessionScope.keeper.name}         
               </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" class="STYLE2"  width="7%">
                       <div align="center" class="STYLE2 STYLE1">
                          <a href="#" class="coolbg" onclick='ToLink("/em/front/single_device_fault.do?forward=self&sn=${dev.sn}&buy_date=${dev.buy_date}&status=${dev.status}&isElectric=${dev.isElectric}&location_sn=${dev.position}&model_sn=${dev.model_sn}")'>&nbsp;查询&nbsp;</a>
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
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE4">共${fn:length(sessionScope.dev_list) }条纪录</span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">
            <form name='form3' action='/em/front/split_page.do?forward=fault_query&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}' method='post'>  
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="62" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=fault_query&page=first&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}'" src="/em/front/tab/images/first.gif" width="37" height="15" /></div></td>
                  <td width="50" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=fault_query&page=previous&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}'" src="/em/front/tab/images/back.gif" width="43" height="15" /></div></td>
                  <td width="54" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=fault_query&page=next&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}'" src="/em/front/tab/images/next.gif" width="43" height="15" /></div></td>
                  <td width="49" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=fault_query&page=last&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}'" src="/em/front/tab/images/last.gif" width="37" height="15" /></div></td>
                  
                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
                  <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input name="page" type="text" class="STYLE1" style="height:15px; width:25px;" size="5" />
                  </span></td>
                  <td width="23" height="22" valign="middle">页</td>
                  <td width="30" height="22" valign="middle"><img onclick="document.form3.submit();" src="/em/front/tab/images/go.gif" width="37" height="15" /></td>
                  
                </tr>
              </table>
            </form>
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

<%@ page language="java" contentType="text/html; charset=gb2312"
    isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></SCRIPT>
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
		window.open( location,'' ,'top='+(window.screen.availHeight-210)/2+',left='+(window.screen.availWidth-560)/2+',height=210,width=560,status=no,toolbar=no,menubar=no,location=no,resizable=no,titlebar=no,scrollbars=no');
	}
function deleteDevice(uri){
    if (confirm("ȷ��Ҫɾ�����豸��Ϣ��")){
        window.location.href=uri;
    }
}

<c:if test="${sessionScope.admin.authority eq 'M'}">
function fillItem(group){
      var group=group;
      document.location.href="/em/admin/cascade.do?forward=m_alarm_query&group="+group; 
}
function fillModel(group, item){
      var group=group;
      var item=item;
      document.location.href="/em/admin/cascade.do?forward=m_alarm_query&group="+group+"&item="+item; 
}    
</c:if>
<c:if test="${(sessionScope.admin.authority eq 'S') or (sessionScope.admin.authority eq 'B')}">
function fillItem(group, dep){
      var group=group;
      var dep=dep;
      document.location.href="/em/admin/cascade.do?forward=m_alarm_query&group="+group+"&dep_sn="+dep; 
}
function fillModel(group, item, dep){
      var group=group;
      var item=item;
      var dep=dep;
      document.location.href="/em/admin/cascade.do?forward=m_alarm_query&group="+group+"&item="+item+"&dep_sn="+dep; 
}
function fillKeeper(group, item, dep){
      var group=group;
      var item=item;
      var dep=dep;
      document.location.href="/em/admin/cascade.do?forward=m_alarm_query&group="+group+"&item="+item+"&dep_sn="+dep; 
}
</c:if>
function print(){
      document.location.href="/em/front/export_alarm.do"; 
}
</script>
</head>

<body>
<!--  ��������  -->


<table width='99%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0' width='100%'>
        <c:if test="${(sessionScope.admin.authority eq 'S') or (sessionScope.admin.authority eq 'B')}">
        <tr>
        <td height='60' width='8%' style="border-right:1px solid #CBD8AC;">
            <span class="STYLE4">������ѯ</span>
        </td>
        <td><form name='form1' action='/em/admin/query_for_manage.do?forward=m_alarm_query&page=first' method='post'>
            <table>
              <tr>
                <td width='80' align='right'>���</td>
		        <td width='100' align="left">
                    <select name='class_sn' style='width:100px' id='class_sn' onchange="fillItem(this.options[this.options.selectedIndex].value, document.getElementById('dep_sn').options[document.getElementById('dep_sn').options.selectedIndex].value)">
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "class" items = "${sessionScope.class_list}">
                        <option value = "${class.sn}" <c:if test="${(class.sn eq requestScope.hierarchy.group) or (class.sn eq param.class_sn)}">SELECTED</c:if>>${class.name}</option>
                        </c:forEach>
                    </select>
                </td> 
                <td width='90' align='right'>С�</td>
		        <td width='100' align="left">
                    <select name='item_sn' id='item_sn' style='width:100px' onchange="fillModel(document.getElementById('class_sn').options[document.getElementById('class_sn').options.selectedIndex].value, this.options[this.options.selectedIndex].value, document.getElementById('dep_sn').options[document.getElementById('dep_sn').options.selectedIndex].value)">
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "item" items = "${sessionScope.item_list}">
                          <c:if test="${(item.class_sn eq requestScope.hierarchy.group) or (item.class_sn eq param.class_sn)}">
                            <option value = "${item.sn}" <c:if test="${(item.sn eq requestScope.hierarchy.item) or (item.sn eq param.item_sn)}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>  
                <TD width='90' align="right">�������ڣ�</TD>
                <TD width='74' align="left">
                  <input name="from_date" type="text" value="${param.from_date}" onClick="calendar.show(this);" style="width:70px; "/>
                  
                </TD>
                <td width="120"><span class="STYLE4">&#8212;&#8212;&#8212;&rarr;</span></td>
                <TD width='100' align="left">
                  <input name="to_date" type="text" value="${param.to_date}" onClick="calendar.show(this);" style="width:70px; "/>                
                </TD>
                
                <td colSpan="5"></td>
                
                
              </tr>
                
              <tr>
                <td width='80' align="right">�ͺţ� </td>
                <td width='100' align="left">
                    <select name='model_sn' style='width:100px'>
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "item" items = "${sessionScope.model_list}">
                          <c:if test="${(item.item_sn eq requestScope.hierarchy.item) or (item.item_sn eq param.item_sn)}">
                            <option value = "${item.sn}" <c:if test="${item.sn eq param.model_sn}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>
                <td width="90" align="right">���ţ�</td>
                <td width="100">
                   <select name='dep_sn' id='dep_sn' style='width:100px' onchange="fillKeeper(document.getElementById('class_sn').options[document.getElementById('class_sn').options.selectedIndex].value, document.getElementById('item_sn').options[document.getElementById('item_sn').options.selectedIndex].value, this.options[this.options.selectedIndex].value)">
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "dep" items = "${sessionScope.dep_list}">
                            <option value = "${dep.sn}" <c:if test="${dep.sn eq param.dep_sn}">SELECTED</c:if>>${dep.name}</option>   
                        </c:forEach>
                   </select>
                </td>
                <td width="90" align="right">��&nbsp;��&nbsp;�ˣ�</td>
                <td width="70">
                   <select name='keeper_sn' style='width:74px'>
                        <option value='0' >��ѡ��</option> 
                        <c:forEach var = "keeper" items = "${sessionScope.keeper_list}">
                            <c:if test="${keeper.dep_sn eq param.dep_sn}">
                                <option value = "${keeper.sn}" <c:if test="${keeper.sn eq param.keeper_sn}">SELECTED</c:if>>${keeper.name}</option>   
                            </c:if>
                        </c:forEach>
                   </select>
                </td>
                <td width="120" align="center">�౨�� &le;</td>
                <td width="100" align="left">
                   <input name="remainder" type="text" value="${param.remainder}" style="width:20px; "/>&nbsp;��λ(��)
                </td>

                <td width="50" align="left">
                    <img name="imageField" type="image" onclick="document.form1.submit();" src="/em/front/tab/images/search.gif" width="45" height="20" border="0" class="np" />
                </td>
                <td width="50" align="left">
                    <img name="imageField" onclick="print();" src="/em/front/tab/images/print.gif" width="45" height="20" border="0" class="np" />
                </td>
                
                <td colSpan="4"></td>		
              </tr>
            </table>
            </form>
        </td>
        
       </tr>
       </c:if>
       <c:if test="${sessionScope.admin.authority eq 'M'}">
       <tr>
        <td height='60' width='10%' style="border-right:1px solid #CBD8AC;">
            <span class="STYLE4">������ѯ</span>
        </td>
        <td><form name='form1' action='/em/admin/query_for_manage.do?forward=m_alarm_query&page=first' method='post'>
            <table>
              <tr>
                <td width='100' align='right'>�������ƣ�&nbsp;</td>
		        <td width='100' align="left">
                    <select name='class_sn' style='width:100px' id='class_sn' onchange="fillItem(this.options[this.options.selectedIndex].value)">
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "class" items = "${sessionScope.class_list}">
                        <option value = "${class.sn}" <c:if test="${(class.sn eq requestScope.hierarchy.group) or (class.sn eq param.class_sn)}">SELECTED</c:if>>${class.name}</option>
                        </c:forEach>
                    </select>
                </td> 
                <td width='100' align='right'>С�����ƣ�&nbsp;</td>
		        <td width='100' align="left">
                    <select name='item_sn' style='width:100px' onchange="fillModel(document.getElementById('class_sn').options[document.getElementById('class_sn').options.selectedIndex].value, this.options[this.options.selectedIndex].value)">
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "item" items = "${sessionScope.item_list}">
                          <c:if test="${(item.class_sn eq requestScope.hierarchy.group) or (item.class_sn eq param.class_sn)}">
                            <option value = "${item.sn}" <c:if test="${(item.sn eq requestScope.hierarchy.item) or (item.sn eq param.item_sn)}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>  
                <td width='110' align="right">�ͺţ� </td>
                <td width='100' align="left">
                    <select name='model_sn' style='width:100px'>
                        <option value='0' >��ѡ��</option>
                        <c:forEach var = "item" items = "${sessionScope.model_list}">
                          <c:if test="${(item.item_sn eq requestScope.hierarchy.item) or (item.item_sn eq param.item_sn)}">
                            <option value = "${item.sn}" <c:if test="${item.sn eq param.model_sn}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>
                <td width="50">
                </td>
                <td width="50">
                </td>
                <td colSpan="4"></td>
                
                
              </tr>
                
              <tr>
                <TD width='100' align="right">�������ڣ�&nbsp;</TD>
                <TD width='100' align="left">
                  <input name="from_date" type="text" value="${param.from_date}" onClick="calendar.show(this);" style="width:96px; "/>
                  
                </TD>
                <td width="100"><span class="STYLE4">&#8212;&#8212;&#8212;&rarr;</span></td>
                <TD width='100' align="left">
                  <input name="to_date" type="text" value="${param.to_date}" onClick="calendar.show(this);" style="width:96px; "/>                
                </TD>
                <td width="110" align="right">�౨��ʱ�� &le;</td>
                <td width="50" align="left">
                   <input name="remainder" type="text" value="${param.remainder}" style="width:40px; "/>&nbsp;��λ(��)
                </td>
                <td width="50" align="left">
                    <img name="imageField" onclick="document.form1.submit();" src="/em/front/tab/images/search.gif" width="45" height="20" border="0" class="np" />
                </td>
                <td width="50" align="left">
                    <img name="imageField" onclick="print();" src="/em/front/tab/images/print.gif" width="45" height="20" border="0" class="np" />
                </td>
                <td colSpan="4"></td>		
              </tr>
            </table>
            </form>
        </td>
        
       </tr>

       </c:if>
        
       
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
        <td width="700" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">�豸��������</span></td>
        <td width="682" background="/em/front/tab/images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td><span class="STYLE1">�౨��ʱ�䣺&nbsp;</span><img src="/em/front/tab/images/red.gif" width="12" height="14" />&nbsp;&le;0��&nbsp;&nbsp;</td>
              <td><img src="/em/front/tab/images/orange.gif" width="12" height="14" />&nbsp;&le;1��&nbsp;&nbsp;</td>
              <td><img src="/em/front/tab/images/yellow.gif" width="12" height="14" />&nbsp;&le;2��&nbsp;&nbsp;</td>
              <td><img src="/em/front/tab/images/green.gif" width="12" height="14" />&nbsp;&gt;&nbsp;2��&nbsp;&nbsp;</td>

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
            <td  height="18" width="10%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">�豸���</span></td>
            <td  height="18" width="10%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">С��</span></td>           
            <td  height="18" width="10%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">�ͺ�</span></td>
            <td  height="18" width="8%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">��������</span></td>
            <td  height="18" width="12%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">ʹ������(��)</span></td>
            <td  height="18" width="12%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">�౨��ʱ��(��)</span></td>
            <td  height="18" width="6%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">����</span></td>
            <td  height="18" width="16%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">���λ��</span></td>
            <td  height="18" width="6%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">������</span></td>
                
            
          </tr>
          <c:forEach var = "dev" items ="${sessionScope.dev_list}" begin="${(sessionScope.split_page.currentPage-1)*sessionScope.split_page.pageRows}" end="${sessionScope.split_page.currentPage*sessionScope.split_page.pageRows-1}"> 
          <tr>
            <td height="18" width="10%" bgcolor="#FFFFFF">
              <div align="left" class="STYLE1">
                 ${dev.sn}
              </div>
            </td>
            <td height="18" width="10%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                
                 <c:forEach var = "item"  items ="${sessionScope.item_list}">
                     <c:forEach var = "model"  items ="${sessionScope.model_list}">
                         <c:if test="${(item.sn eq model.item_sn) and (model.sn eq dev.model_sn)}">${item.name}</c:if>
                     </c:forEach>
                 </c:forEach>
              </div>
            </td>
            <td height="18" width="10%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 <c:forEach var = "model"  items ="${sessionScope.model_list}">
                     <c:if test="${model.sn eq dev.model_sn}">${model.name}</c:if>
                 </c:forEach>
              </div>
            </td>
            <td height="18" width="8%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 ${dev.buy_date}
              </div>
            </td>
            <td height="18" width="12%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:forEach var = "model"  items ="${sessionScope.model_list}">
                     <c:if test="${model.sn eq dev.model_sn}">${model.life}</c:if>
                 </c:forEach>
              </div>
            </td>
            <td height="18" width="12%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:forEach var = "model"  items ="${sessionScope.model_list}">
                     <c:if test="${model.sn eq dev.model_sn}">
                         <fmt:formatNumber value="${model.life - dev.elapsed_year}" pattern="0.0"/>
                     </c:if>
                 </c:forEach>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" class="STYLE2"  width="6%">
               <div align="center" class="STYLE2 STYLE1">
                   <c:forEach var = "model"  items ="${sessionScope.model_list}">
                     <c:if test="${(model.sn eq dev.model_sn) and ((model.life - dev.elapsed_year) le 0)}"><img src="/em/front/tab/images/red.gif" width="12" height="14" /></c:if>
                     <c:if test="${(model.sn eq dev.model_sn) and ((model.life - dev.elapsed_year) le 1) and ((model.life - dev.elapsed_year) gt 0)}"><img src="/em/front/tab/images/orange.gif" width="12" height="14" /></c:if>
                     <c:if test="${(model.sn eq dev.model_sn) and ((model.life - dev.elapsed_year) le 2) and ((model.life - dev.elapsed_year) gt 1)}"><img src="/em/front/tab/images/yellow.gif" width="12" height="14" /></c:if>
                     <c:if test="${(model.sn eq dev.model_sn) and ((model.life - dev.elapsed_year) gt 2)}"><img src="/em/front/tab/images/green.gif" width="12" height="14" /></c:if>
                   </c:forEach>
               </div>
            </td>
            <td height="18" width="16%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 <c:forEach var = "loc"  items ="${sessionScope.loc_list}">
                     <c:if test="${loc.sn eq dev.position}">${loc.address}</c:if>
                 </c:forEach>
              </div>
            </td>
            <td align="center" bgcolor="#FFFFFF" class="STYLE2"  width="6%">
               <div align="center" class="STYLE2 STYLE1">
                 <c:forEach var = "keeper"  items ="${sessionScope.keeper_list}">
                     <c:if test="${keeper.sn eq dev.keeper}">${keeper.name}</c:if>
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
            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE4">��${fn:length(sessionScope.dev_list) }����¼</span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">
            <form name='form3' action='/em/front/split_page.do?forward=m_alarm_query&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&dep_sn=${param.dep_sn}&keeper_sn=${param.keeper_sn}&remainder=${param.remainder}' method='post'>  
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="62" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=m_alarm_query&page=first&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&dep_sn=${param.dep_sn}&keeper_sn=${param.keeper_sn}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}&remainder=${param.remainder}'" src="/em/front/tab/images/first.gif" width="37" height="15" /></div></td>
                  <td width="50" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=m_alarm_query&page=previous&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&dep_sn=${param.dep_sn}&keeper_sn=${param.keeper_sn}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}&remainder=${param.remainder}'" src="/em/front/tab/images/back.gif" width="43" height="15" /></div></td>
                  <td width="54" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=m_alarm_query&page=next&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&dep_sn=${param.dep_sn}&keeper_sn=${param.keeper_sn}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}&remainder=${param.remainder}'" src="/em/front/tab/images/next.gif" width="43" height="15" /></div></td>
                  <td width="49" height="22" valign="middle"><div align="right"><img onclick="document.location.href='/em/front/split_page.do?forward=m_alarm_query&page=last&class_sn=${param.class_sn}&item_sn=${param.item_sn}&model_sn=${param.model_sn}&from_date=${param.from_date}&to_date=${param.to_date}&dep_sn=${param.dep_sn}&keeper_sn=${param.keeper_sn}&status=${param.status}&isElectric=${param.isElectric}&loc_sn=${param.loc_sn}&remainder=${param.remainder}'" src="/em/front/tab/images/last.gif" width="37" height="15" /></div></td>
                  
                  <td width="59" height="22" valign="middle"><div align="right">ת����</div></td>
                  <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input name="page" type="text" class="STYLE1" style="height:15px; width:25px;" size="5" />
                  </span></td>
                  <td width="23" height="22" valign="middle">ҳ</td>
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
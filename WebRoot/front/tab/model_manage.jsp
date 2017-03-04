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
		window.open( location,'' ,'top='+(window.screen.availHeight-120)/2+',left='+(window.screen.availWidth-560)/2+',height=120,width=560,status=no,toolbar=no,menubar=no,location=no,resizable=no,titlebar=no,scrollbars=no');
	}
function deleteModel(uri){
    if (confirm("确定要删除该型号吗？")){
        window.location.href=uri;
    }
}

function fillItem(group){
      var group=group;
      document.location.href="/em/admin/cascade.do?forward=model_manage&group="+group; 
  }
</script>
</head>

<body>
<!--  搜索表单  -->
<form name='form2' action='/em/admin/add_model.do' method='post'>

<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
        <td height='40' width='10%' style="border-right:1px solid #CBD8AC;">
            <span class="STYLE4">型号<br/>录入</span>
        </td>
        <td>
            <table>
              <tr>
                <td width='100' align='right'>大项名称：&nbsp;</td>
		        <td width='100' align="left">
                    <select name='class_sn' style='width:100px' onchange="fillItem(this.options[this.options.selectedIndex].value)">
                        <option value='0' >请选择</option>
                        <c:forEach var = "class" items = "${sessionScope.class_list}">
                        <option value = "${class.sn}" <c:if test="${class.sn eq requestScope.hierarchy.group}">SELECTED</c:if>>${class.name}</option>
                        </c:forEach>
                    </select>
                </td> 
                <td width='100' align='right'>小项名称：&nbsp;</td>
		        <td width='100' align="left">
                    <select name='item_sn' style='width:100px'>
                        <option value='0' >请选择</option>
                        <c:forEach var = "item" items = "${sessionScope.item_list}">
                          <c:if test="${item.class_sn eq requestScope.hierarchy.group}">
                            <option value = "${item.sn}" <c:if test="${item.sn eq requestScope.sn}">SELECTED</c:if>>${item.name}</option>
                          </c:if>
                        </c:forEach>
                    </select>
                </td>  
                <td width='100' align="right">型号名称： </td>
                <td width='100' align="left">
                    <input name="name" type="text"  style='width:100px'  height="20" />
                </td>
              </tr>
                
              <tr>
                <td width='100' align='right'>厂家名称：&nbsp;</td>
		        <td width='100' align="left">
                    <select name='supplier_sn' style='width:100px' >
                        <option value='0' >请选择</option>
                        <c:forEach var = "supplier" items = "${sessionScope.supplier_list}">
                        <option value = "${supplier.sn}" <c:if test="${supplier.sn eq requestScope.sn}">SELECTED</c:if>>${supplier.name}</option>
                        </c:forEach>
                    </select>
                </td> 
                <td width='100' align='right'>使用年限：&nbsp;</td>
		        <td width='100' align="left">
                    <input name="life" type="text"  style='width:97px' height="20" />
                </td>  
                <td width='100' align="right">有备件： </td>
                <td width='100' align="left">
                   <table>
                      <tr>
                         <td><input name="hasAppendix" type="checkbox" style='width:20px'  value="Y" align="left"/></td>
                         <td>&nbsp;&nbsp;<input name="imageField" align="right" type="image" onclick="fsubmit(document.form2);" src="/em/front/tab/images/insert.gif" class="np" /></td>
                     </tr>
                   </table>                                     
                </td>		
              </tr>
            </table>
        </td>
        
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
        <td width="1101" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">型号信息管理</span></td>
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
            <td  height="18" width="5%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">编号</span></td>
            <td  height="18" width="15%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">小项</span></td>
            <td>
               <table width="100%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                      <td  height="18" width="19%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">型号</span></td>
                      <td  height="18" width="36%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">厂家</span></td>
                      <td  height="18" width="10%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">使用年限</span></td>
                      <td  height="18" width="10%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">有备件</span></td>
                      <td  height="18" width="12.5%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">更新</span></td>
                      <td  height="18" width="12.5%" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">删除</span></td>
                  </tr>
               </table>
            </td>
            
          </tr>
          <c:forEach var = "item"  items ="${sessionScope.item_list}"> 
          <tr>
            <td height="18" width="5%" bgcolor="#FFFFFF">
              <div align="center" class="STYLE1">
                 ${item.sn}
              </div>
            </td>
            <td height="18" width="15%" bgcolor="#FFFFFF" class="STYLE2">
              <div align="left" class="STYLE2 STYLE1">
                 ${item.name}
              </div>
            </td>
            <td colSpan="6" bgcolor="#FFFFFF" class="STYLE2">
                <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover="changeto()"  onmouseout="changeback()">
                   <c:forEach var = "model"  items ="${sessionScope.model_list}"> 
                   <c:if test="${model.item_sn eq item.sn}">
                   <tr>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2" width="19%">
                       <div align="left" class="STYLE2 STYLE1">                          
                          ${model.name}
                       </div>
                   </td>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2" width="36%">
                       <div align="left" class="STYLE2 STYLE1">
                       <c:forEach var = "supplier"  items ="${sessionScope.supplier_list}">
                          <c:if test="${supplier.sn eq model.supplier_sn}">
                             ${supplier.name}
                          </c:if>
                       </c:forEach>
                       </div>
                   </td>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2" width="10%">
                       <div align="center" class="STYLE2 STYLE1">
                          ${model.life}
                       </div>
                   </td>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2" width="10%">
                       <div align="center" class="STYLE2 STYLE1">
                         <c:if test="${model.hasAppendix eq 'Y'}">
                             <input name="hasAppendix" type="checkbox" style='width:20px' align="left" disabled checked/>
                         </c:if>
                         <c:if test="${model.hasAppendix eq 'N'}">
                             <input name="hasAppendix" type="checkbox" style='width:20px' align="left" disabled/>
                         </c:if>
                       </div>
                   </td>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2"  width="12.5%">
                       <div align="center" class="STYLE2 STYLE1">
                          <a href="#" class="coolbg" onclick='ToLink("/em/front/tab/model_update.jsp?item_sn=${model.item_sn}&sn=${model.sn}&name=${model.name}&life=${model.life}&hasAppendix=${model.hasAppendix}&supplier_sn=${model.supplier_sn}")'>&nbsp;更新&nbsp;</a>
                       </div>
                   </td>
                   <td align="center" height="18" bgcolor="#FFFFFF" class="STYLE2"  width="12.5%">
                       <div align="center" class="STYLE2 STYLE1">
                          <a href="#" class="coolbg" onclick='deleteModel("/em/admin/delete_model.do?sn=${model.sn}")'>&nbsp;删除&nbsp;</a>
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

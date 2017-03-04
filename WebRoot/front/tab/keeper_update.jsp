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
		window.open( location,'' ,'top='+(window.screen.availHeight-250)/2+',left='+(window.screen.availWidth-544)/2+',height=250,width=544,status=no,toolbar=no,menubar=no,location=no,resizable=no,titlebar=no,scrollbars=no');
	}
function deleteKeeper(uri){
    if (confirm("确定要删除该用户吗？")){
        window.location.href=uri;
    }
}

</script>
</head>

<body>
<!--  搜索表单  -->

<table><tr><td height="5"></td></tr></table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="/em/front/tab/images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="/em/front/tab/images/tab_05.gif"><img src="/em/front/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">用户信息更新</span></td>
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
            <td  width="20%" height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">账号</span></td>
            <td  width="20%" height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">姓名</span></td>
            <td  width="20%" height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">部门</span></td>
            <td  width="20%" height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">权限</span></td>
            <c:if test="${sessionScope.admin.authority eq 'M'}">
                <td  width="20%" height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">更新</span></td>
            </c:if>
            <c:if test="${sessionScope.admin.authority eq 'S'}">
                <td  width="10%" height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">更新</span></td>
                <td  width="10%" height="18" background="/em/front/tab/images/tab_14.gif" align="center"><span class="STYLE4">删除</span></td>
            </c:if>
            
          </tr>
          <c:if test="${sessionScope.admin.authority eq 'M'}">
              <c:forEach var = "keeper"  items ="${sessionScope.keeper_list}">
                  <tr>
            <td height="18" bgcolor="#FFFFFF">
              <div align="center" class="STYLE1">
                 ${keeper.sn}
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 ${keeper.name}
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:forEach var = "dep"  items ="${sessionScope.dep_list}">
                    <c:if test="${dep.sn eq keeper.dep_sn}">
                        ${dep.name}
                    </c:if>
                 </c:forEach>
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:if test="${keeper.authority eq 'S'}">
                                                      管理员
                 </c:if>
                 <c:if test="${keeper.authority eq 'B'}">
                                                      监察员
                 </c:if>
                 <c:if test="${keeper.authority eq 'M'}">
                                                      部门管理员
                 </c:if>
                 <c:if test="${keeper.authority eq 'N'}">
                                                      保管员
                 </c:if>
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                <div align="center" class="STYLE2 STYLE1">
                   <a href="#" class="coolbg" onclick='ToLink("/em/front/keeper_update.jsp?sn=${keeper.sn}&name=${keeper.name}&password=${keeper.password}&authority=${keeper.authority}&dep_sn=${keeper.dep_sn}")'>&nbsp;更新&nbsp;</a>
                </div>
            </td>
            </tr>
              </c:forEach> 
          </c:if>
          <c:if test="${sessionScope.admin.authority eq 'S'}">
              <c:forEach var = "keeper"  items ="${sessionScope.adminer_list}"> 
                  <tr>
            <td height="18" bgcolor="#FFFFFF">
              <div align="center" class="STYLE1">
                 ${keeper.sn}
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 ${keeper.name}
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:forEach var = "dep"  items ="${sessionScope.dep_list}">
                    <c:if test="${dep.sn eq keeper.dep_sn}">
                        ${dep.name}
                    </c:if>
                 </c:forEach>
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
              <div align="center" class="STYLE2 STYLE1">
                 <c:if test="${keeper.authority eq 'S'}">
                                                      管理员
                 </c:if>
                 <c:if test="${keeper.authority eq 'B'}">
                                                      监察员
                 </c:if>
                 <c:if test="${keeper.authority eq 'M'}">
                                                      部门管理员
                 </c:if>
                 <c:if test="${keeper.authority eq 'N'}">
                                                      保管员
                 </c:if>
              </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                <div align="center" class="STYLE2 STYLE1">
                   <a href="#" class="coolbg" onclick='ToLink("/em/front/keeper_update.jsp?sn=${keeper.sn}&name=${keeper.name}&password=${keeper.password}&authority=${keeper.authority}&dep_sn=${keeper.dep_sn}")'>&nbsp;更新&nbsp;</a>
                </div>
                </td>
                <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                <div align="center" class="STYLE2 STYLE1">
                   <a href="#" class="coolbg" onclick='deleteKeeper("/em/admin/delete_keeper.do?sn=${keeper.sn}")'>&nbsp;删除&nbsp;</a>
                </div>
            </td>
            </tr>
              </c:forEach>
          </c:if>
          
          
          
          
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

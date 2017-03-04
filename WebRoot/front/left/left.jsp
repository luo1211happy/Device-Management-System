<%@ page language="java" contentType="text/html; charset=gb2312" isELIgnored="false" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>无标题页</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css> 
{
	FONT-SIZE: 12px
}
#menuTree A {
	COLOR: gray; TEXT-DECORATION: bold;
}
</STYLE>
<SCRIPT src="Left.files/TreeNode.js" type=text/javascript></SCRIPT>
<SCRIPT src="Left.files/Tree.js" type=text/javascript></SCRIPT>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
</HEAD>
<BODY 
style="BACKGROUND-POSITION-Y: -120px; BACKGROUND-IMAGE: url(../images/bg.gif); BACKGROUND-REPEAT: repeat-x">
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%">
  <TBODY>
    <TR>
      <TD height=29 colspan="3" align="left"><IMG src="../images/main_21.gif"></TD>
    </TR>
    <TR>
      <TD style="BACKGROUND-IMAGE: url(../images/bg_left_ls.gif)"></TD>
      <TD id=menuTree 
    style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign=top></TD>
      <TD style="BACKGROUND-IMAGE: url(../images/bg_left_rs.gif)"></TD>
    </TR>
    <TR>
      
      <TD style="BACKGROUND-IMAGE: url(../images/bg_left_bc.gif)"></TD>
   
    </TR>
  </TBODY>
</TABLE>
<SCRIPT type=text/javascript>
var tree = null;
var root = new TreeNode('系统菜单');


<c:if test="${(sessionScope.keeper ne null) and (sessionScope.keeper.authority eq 'N')}">
var fun1 = new TreeNode('设备管理');
var fun11 = new TreeNode('设备录入', '/em/front/tab/add_device.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun1.add(fun11);
var fun12 = new TreeNode('信息管理', '/em/front/split_page.do?forward=manage_device', 'tree_node.gif', null, 'tree_node.gif', null);
fun1.add(fun12);
root.add(fun1);

var fun2 = new TreeNode('设备查询');
var fun21 = new TreeNode('基本查询', '/em/front/split_page.do?forward=basic_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun2.add(fun21);
var fun22 = new TreeNode('使用年限', '/em/front/split_page.do?forward=scrap_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun2.add(fun22);
root.add(fun2);

var fun3 = new TreeNode('设备报警');
var fun31 = new TreeNode('寿命报警', '/em/front/split_page.do?forward=alarm_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun3.add(fun31);
root.add(fun3);

var fun4 = new TreeNode('故障管理');
var fun41 = new TreeNode('故障登记', '/em/front/split_page.do?forward=fault_record', 'tree_node.gif', null, 'tree_node.gif', null);
fun4.add(fun41);
var fun42 = new TreeNode('维修记录', '/em/front/get_unfinished_record.do', 'tree_node.gif', null, 'tree_node.gif', null);
fun4.add(fun42);
var fun43 = new TreeNode('历史故障', '/em/front/tab/fault_history.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun4.add(fun43);
var fun44 = new TreeNode('故障查询', '/em/front/split_page.do?forward=fault_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun4.add(fun44);
root.add(fun4);

var fun5 = new TreeNode('基础数据管理');
var fun53 = new TreeNode('厂家管理','/em/front/tab/supplier_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun5.add(fun53);
var fun54 = new TreeNode('型号管理','/em/front/tab/model_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun5.add(fun54);
var fun55 = new TreeNode('位置管理','/em/front/tab/location_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun5.add(fun55);
root.add(fun5);

var fun6 = new TreeNode('用户管理');
var fun61 = new TreeNode('用户信息更新', '/em/front/cipher_update.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun6.add(fun61);
root.add(fun6);
</c:if>

<c:if test="${(sessionScope.admin ne null) and (sessionScope.admin.authority eq 'S')}">
var fun10 = new TreeNode('设备查询');
var fun101 = new TreeNode('基本查询', '/em/front/split_page.do?forward=m_basic_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun10.add(fun101);
var fun102 = new TreeNode('使用年限', '/em/front/split_page.do?forward=m_scrap_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun10.add(fun102);
root.add(fun10);

var fun12 = new TreeNode('设备报警');
var fun121 = new TreeNode('寿命报警', '/em/front/split_page.do?forward=m_alarm_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun12.add(fun121);
root.add(fun12);

var fun13 = new TreeNode('故障管理');
var fun131 = new TreeNode('历史故障', '/em/front/tab/m_fault_history.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun13.add(fun131);
var fun132 = new TreeNode('故障查询', '/em/front/split_page.do?forward=m_fault_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun13.add(fun132);
root.add(fun13);

var fun14 = new TreeNode('基础数据管理');
var fun141 = new TreeNode('大项管理','/em/front/tab/class_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun141);
var fun142 = new TreeNode('小项管理','/em/front/tab/item_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun142);
var fun143 = new TreeNode('厂家管理','/em/front/tab/supplier_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun143);
var fun144 = new TreeNode('型号管理','/em/front/tab/model_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun144);
root.add(fun14);

var fun15 = new TreeNode('用户管理');
var fun151 = new TreeNode('管理员注册', '/em/front/keeper_enter.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun15.add(fun151);
var fun152 = new TreeNode('管理员信息更新', '/em/front/tab/keeper_update.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun15.add(fun152);
var fun153 = new TreeNode('用户信息更新', '/em/front/m_cipher_update.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun15.add(fun153);
root.add(fun15);

var fun16 = new TreeNode('部门管理');
var fun161 = new TreeNode('部门添加', '/em/front/tab/add_department.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun16.add(fun161);
var fun162 = new TreeNode('部门信息更新', '/em/front/tab/manage_department.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun16.add(fun162);
root.add(fun16);

</c:if>


<c:if test="${(sessionScope.admin ne null) and (sessionScope.admin.authority eq 'M')}">
var fun10 = new TreeNode('设备查询');
var fun101 = new TreeNode('基本查询', '/em/front/split_page.do?forward=m_basic_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun10.add(fun101);
var fun102 = new TreeNode('使用年限', '/em/front/split_page.do?forward=m_scrap_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun10.add(fun102);
root.add(fun10);

var fun11 = new TreeNode('设备管理');
var fun111 = new TreeNode('信息更新', '/em/front/split_page.do?forward=m_update_keeper', 'tree_node.gif', null, 'tree_node.gif', null);
fun11.add(fun111);
root.add(fun11);

var fun12 = new TreeNode('设备报警');
var fun121 = new TreeNode('寿命报警', '/em/front/split_page.do?forward=m_alarm_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun12.add(fun121);
root.add(fun12);

var fun13 = new TreeNode('故障管理');
var fun131 = new TreeNode('历史故障', '/em/front/tab/m_fault_history.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun13.add(fun131);
var fun132 = new TreeNode('故障查询', '/em/front/split_page.do?forward=m_fault_query', 'tree_node.gif', null, 'tree_node.gif', null);
fun13.add(fun132);
root.add(fun13);

var fun14 = new TreeNode('基础数据管理');
var fun141 = new TreeNode('大项管理','/em/front/tab/class_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun141);
var fun142 = new TreeNode('小项管理','/em/front/tab/item_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun142);
var fun143 = new TreeNode('厂家管理','/em/front/tab/supplier_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun143);
var fun144 = new TreeNode('型号管理','/em/front/tab/model_manage.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun14.add(fun144);
root.add(fun14);

var fun15 = new TreeNode('用户管理');
var fun151 = new TreeNode('保管员注册', '/em/front/keeper_enter.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun15.add(fun151);
var fun152 = new TreeNode('保管员信息更新', '/em/front/tab/keeper_update.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun15.add(fun152);
var fun153 = new TreeNode('管理员信息更新', '/em/front/m_cipher_update.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
fun15.add(fun153);
root.add(fun15);

</c:if>

tree = new Tree(root);
tree.show('menuTree')
</SCRIPT>
</BODY>
</HTML>

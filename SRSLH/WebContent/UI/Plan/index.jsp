<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
	<h4>&nbsp;&nbsp;&nbsp;&nbsp;您好！<s:property value="name"/>同学，您的学习计划如下：</h4>
	<table class="table table-bordered table-hover definewidth m10">
    	<thead>
	    	<tr>
		        <th>课程编号</th>
		        <th>课程名称</th>
		        <th>学分</th>
		        <th>先修课程</th>
		        <th>备注</th>
	  		</tr>
	    </thead>
	    <tbody>
	   		<s:iterator value="list">
		        <tr>
		                <td><s:property value="courseNo"/></td>
		                <td><s:property value="courseName"/></td>
		                <td><s:property value="credits"/></td>
		                <td>
							<s:iterator value="prerequisites">
								<s:property value="courseName"/>&nbsp;&nbsp;&nbsp;
							</s:iterator>
						</td>
		                <td></td>
		         </tr>
	         </s:iterator>
         </tbody>
    </table>
</body>
</html>
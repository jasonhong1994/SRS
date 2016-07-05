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
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
</head>
<body>
	<form action="addCourse" method="post" class="definewidth m20">
	    <table class="table table-bordered table-hover definewidth m10">
	        <tr>
	            <td class="tableleft">课程号</td>
	            <td><input type="text" name="courseNo"/></td>
	        </tr>
	        <tr>
	            <td class="tableleft">周次</td>
	            <td>
		            <input name="week" type="radio" value="Monday"/>周一
		            <input name="week" type="radio" value="Thursday"/>周二
		            <input name="week" type="radio" value="Wednesday"/>周三
		            <input name="week" type="radio" value="Tuesday"/>周四
		            <input name="week" type="radio" value="Friday"/>周五
		            <input name="week" type="radio" value="Saturday"/>周六
		            <input name="week" type="radio" value="Sunday"/>周日
	            </td>
	        </tr>
	        <tr>
	            <td class="tableleft">时间</td>
	            <td><input type="text" name="time"/></td>
	        </tr> 
	         <tr>
	            <td class="tableleft">教室</td>
	            <td><input type="text" name="room"/></td>
	        </tr> 
	        <tr>
	            <td class="tableleft">容量</td>
	            <td><input type="text" name="seat"/></td>
	        </tr> 
	        <tr>
	            <td class="tableleft"></td>
	            <td>
	                <input type="submit" class="btn btn-primary" value="提交">
	            </td>
	        </tr>
	    </table>
	</form>
</body>
</html>

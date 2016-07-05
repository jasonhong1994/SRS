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
	<script type="text/javascript">
		$(document).ready(function(){
			var ssn = $.getUrlParam('ssn');
			$.post("section.action?ssn="+ssn,function(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					html += "<tr><td>"+data[i].SectionNo+"</td>";
					html += "<td>"+data[i].Offered+"</td>";
					html += "<td>"+data[i].CourseNo+"</td>";
					html += "<td>"+data[i].InRoom+"</td>";
					html += "<td>"+data[i].Semester+"</td>";
					html += "<td>"+data[i].Total+"</td>";
					html += "<td>"+data[i].Professor+"</td>";
					if(data[i].mark==1){
						html += "<td><input type=\"button\" disabled=\"disabled\" onclick=\"del("+data[i].SectionNo+");\" class=\"btn btn-success\" value=\"已选\" /></td>";
					}else{
						html += "<td><input type=\"button\" onclick=\"del("+data[i].SectionNo+");\" class=\"btn btn-primary\" value=\"选课\" /></td>";
					}
					html += "<td><input type=\"hidden\" class=\"hid\" value="+data[i].SectionNo+" /></td></tr>";
				}	
					$("#content").html(html);
			});
		});
		//利用正则表达式，为jQuery扩展的获取url参数的方法
		(function ($) {
            $.getUrlParam = function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]); return null;
            }
        })(jQuery);
		function del(o){
			var ssn = $.getUrlParam('ssn');
			$.post("inSection.action?ssn="+ssn+"&sectionno="+o,function(data){
				alert(data);
				//可以改为异步
				window.location.reload();//刷新当前页面.
			});
		}
	</script>
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
<h4>&nbsp;&nbsp;&nbsp;&nbsp;当前您可以选择的课程如下：</h4>
	<table class="table table-bordered table-hover definewidth m10">
	    <thead>
		    <tr>
		        <th>班次号</th>
		        <th>时间</th>
		        <th>所属课程</th>
		        <th>教室</th>
		        <th>学期</th>
		        <th>已选数量</th>
		        <th>教师</th>
		        <th>操作</th>
		        <th>备注</th>
		    </tr>
	    </thead>
	    <tbody id="content">
	    </tbody>
    </table>
</body>
</html>
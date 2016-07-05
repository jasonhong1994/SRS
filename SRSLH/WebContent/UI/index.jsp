<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统——学生端</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="header">

    <div class="dl-title">
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${person.name }</span><a href="logout.action" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
          <div class="dl-inform"><div class="dl-inform-title"></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>

        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>

    BUI.use('common/main',function(){
        var config = [{id:'1',menu:
            [{text:'系统管理',items:[{id:'12',text:'学习计划',href:'Plan/planOfStudy.action?ssn=${person.ssn}&name=${person.name }'},
                                 {id:'3',text:'选课系统',href:'Section/index.jsp?ssn=${person.ssn}'},
                                 {id:'4',text:'查看成绩',href:'Course/hasCourse.action?ssn=${person.ssn}'},
                                 {id:'6',text:'扩展功能',href:'User/index.html'}]
            }]}];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
<div style="text-align:center;">
</div>
</body>
</html>
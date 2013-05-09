<%@tag description="Template Tag" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Delteria</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" media="screen" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>

<div id="wrapper">
<div id="main">

<div id="top">&nbsp;</div>
<div id="header">
  <img src="${pageContext.request.contextPath}/img/logo.png" />
</div>
<div id="menu" class="rw_header_menu">
<ul>
  <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
  <li><a href="http://forums.delteria.net/">Forums</a></li>
  <li><a href="https://www.facebook.com/graalonlinedelteria">Media</a></li>
</ul>
</div>
<div id="content">
<jsp:doBody/>
</div>
</div>
<div id="footer">
Delteria &copy;
</div>
</div>

</body>
</html> 

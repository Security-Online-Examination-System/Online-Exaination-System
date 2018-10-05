<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Home </title>
<meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

 	<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
  <link rel="stylesheet" href="assets/style.css"/>
  <script src="js/jquery-1.9.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.js"></script>
  <script src="assets/script.js"></script>



<!-- Owl stylesheet -->
<link rel="stylesheet" href="assets/owl-carousel/owl.carousel.css">
<link rel="stylesheet" href="assets/owl-carousel/owl.theme.css">
<script src="assets/owl-carousel/owl.carousel.js"></script>
<!-- Owl stylesheet -->


<!-- slitslider -->
    <link rel="stylesheet" type="text/css" href="assets/slitslider/css/style.css" />
    <link rel="stylesheet" type="text/css" href="assets/slitslider/css/custom.css" />
    <script type="text/javascript" src="assets/slitslider/js/modernizr.custom.79639.js"></script>
    <script type="text/javascript" src="assets/slitslider/js/jquery.ba-cond.min.js"></script>
    <script type="text/javascript" src="assets/slitslider/js/jquery.slitslider.js"></script>
<!-- slitslider -->

<script src='/google_analytics_auto.js'></script>

<style>
	.rTable {
  	display: block;
  	width: 100%;
}
.rTableHeading, .rTableBody, .rTableFoot, .rTableRow{
  	clear: both;
}
.rTableHead, .rTableFoot{
  	background-color: #DDD;
  	font-weight: bold;
}
.rTableCell, .rTableHead {
  	border: 1px solid #999999;
  	float: left;
  	height: 27px;
  	overflow: hidden;
  	padding: 2px 1.8%;
  	width: 20%;
}
.rTable:after {
  	visibility: hidden;
  	display: block;
  	font-size: 0;
  	content: " ";
  	clear: both;
  	height: 0;
}

.dropdown-submenu {
    position: relative;
}

.dropdown-submenu .dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -1px;
}
</style>
</head>

<body>


<!-- Header Starts -->
<div class="navbar-wrapper">

        <div class="navbar-inverse" role="navigation" style=" background-color: skyblue; ">
		<nav class="navbar navbar-default navbar-static-top" style=" background-color: skyblue; "> <div class="container"> <div class="row" style=" padding-top:5px;"> <div class="col-xs-12 col-sm-1 rit"><img src="images/jntua.png" alt="JNTUA" width="60px" height="70px" /> </div> <div class="col-xs-12 col-sm-5"> <span class="text-primary myh1">JNTUA</span><span class="text-primary myh2">, ANANTHAPURAMU</span><br /> <span class="text-primary myh1">Online Examination System</span> </div> <div class="col-xs-12 col-sm-6 rit" >  </div> </div>  </div> </nav>  
          <div class="container">
            <div class="navbar-header">


              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>

            </div>



            <!-- Nav Starts -->
            <div class="navbar-collapse  collapse">
              <ul class="nav navbar-nav navbar-right">
               <li><a href="adminhome.htm">Home</a></li>
               <li><a href="admindept.htm">Departments</a></li>
			    <li class="dropdown ">
				  <a href="#" class="dropdown-toggle active" data-toggle="dropdown">Faculty <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminfaculty.htm">Faculty</a></li>
					<li class="kopie"><a href="adminaddfaculty.htm">AddFaculty</a></li>
					</ul>
				</li>
			 <li class="dropdown active">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Courses <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="admincourses.htm">Courses</a></li>
					<li class="kopie"><a href="adminaddcourses.htm">Add Course</a></li>
					</ul>
				</li>
				 <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Subjects <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminsubjects.htm">Subjects</a></li>
					<li class="kopie"><a href="adminaddsubject.htm">Add Subjects</a></li>
					</ul>
				</li>
				 <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Subject Allotment <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminallotedsubjects.htm">Alloted Subjects</a></li>
					<li class="kopie"><a href="adminallotsubject.htm">Allot Subject</a></li>
					</ul>
				</li>
				<li><a href="logout.htm">Logout</a></li>
              
              </ul>
            </div>
            <!-- #Nav Ends -->

          </div>
        </div>

    </div>
<!-- #Header Starts -->





<div class="container">


<!-- #Header Starts -->
</div><!-- banner -->

<!-- banner -->



<div class="container">
<div class="spacer">
<div class="row register">
  <div class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
 
<h2>Courses</h2>
<div class="rTable">
<div class="rTableRow">
<div class="rTableHead" style="width: 10%;"><strong>SNO</strong></div>
<div class="rTableHead"><span style="font-weight: bold;">DeptName</span></div>
<div class="rTableHead"><span style="font-weight: bold;">CourseCode</span></div>
<div class="rTableHead"><span style="font-weight: bold;">CourseName</span></div>
<div class="rTableHead" style="width: 30%;">CourseDesctiption</div>
</div>

<c:forEach var="course" items="${courses }" varStatus="st">
<div class='rTableRow'>
				<div class='rTableCell' style="width: 10%;">${st.count }</div>
				<div class='rTableCell'>${course.deptName}</div>
				<div class='rTableCell'>${course.ccode}</div>
				<div class='rTableCell'>${course.courseName}</div>
				<div class='rTableCell' style="width: 30%;">${course.descr} </div>
			</div>
			

</c:forEach>




</div>
                
        </div>
  
</div>
</div>
</div>


<div class="footer">

<div class="container">



<div class="row">
            <div class="col-lg-3 col-sm-3">
                   <h4>Information</h4>
                   <ul class="row">
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="about.html">About JNTU</a></li>
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="staff.html">Staff</a></li>         
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="blog.html">Student Blog</a></li>
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="contact.html">Contact</a></li>
              </ul>
            </div>
            
            <div class="col-lg-3 col-sm-3">
                    <h4>Newsletter</h4>
                    <p>Get notified about the latest news in jntu.</p>
                    <form class="form-inline" role="form">
                            <input type="text" placeholder="Enter Your email address" class="form-control">
                                <button class="btn btn-success" type="button">Notify Me!</button></form>
            </div>
            
            <div class="col-lg-3 col-sm-3">
                    <h4>Follow us</h4>
                    <a href="#"><img src="images/facebook.png" alt="facebook"></a>
                    <a href="#"><img src="images/twitter.png" alt="twitter"></a>
                    <a href="#"><img src="images/linkedin.png" alt="linkedin"></a>
                    <a href="#"><img src="images/instagram.png" alt="instagram"></a>
            </div>

             <div class="col-lg-3 col-sm-3">
                    <h4>Contact us</h4>
                 
<span class="glyphicon glyphicon-map-marker"></span> Jawaharlal Nehru Technological University Anantapur <br>
<span class="glyphicon glyphicon-envelope"></span> jntua@gmail.com<br>
<span class="glyphicon glyphicon-earphone"></span> 08554-242438</p>
            </div>
        </div>
<p class="copyright">Copyright 2017. All rights reserved.	</p>


</div></div>





</body>
</html>
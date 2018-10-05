
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login </title>
<link rel="icon" type="image/jpg" href="images//logo.jpg"/>

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

<script type="text/javascript">

$(window).resize(function(){
    window.resizeTo(2500,2500);
});

if (window.windowState === 2) {
	 window.resizeTo(2500,2500);
}

window.addEventListener("minimize", alert("Welcome to online examination"));
</script>

		<script>
	  		history.pushState(null, null, document.URL);
			window.addEventListener('popstate', function () {
    			history.pushState(null, null, document.URL);
			});
	 	</script>
		
		</head>
<body oncontextmenu="return false;" onclick="disable();">


<!-- Header Starts -->
<div class="navbar-wrapper">

       <div class="navbar-inverse" role="navigation" style=" background-color: skyblue; ">
		<nav class="navbar navbar-default navbar-static-top" style=" background-color: skyblue; "> <div class="container"> <div class="row" style=" padding-top:5px;"> <div class="col-xs-12 col-sm-1 rit"> <!--<img src="images/logo.jpg" alt="JNTUA" width="60px" height="70px" /> --> </div> 
		<h3><div class="col-xs-12 col-sm-5"> <span class="text-primary myh1">
		 <font face="Lucida Calligraphy" color="green">JNTUA</span><span class="text-primary myh2">,<font face="Lucida Calligraphy" color="green"> ANANTHAPURAMU</span><br /> <span class="text-primary myh1"><font face="Lucida Calligraphy" color="green">Online Examination System</span> </font></div> <div class="col-xs-12 col-sm-6 rit" > 
		 </div> </div>  </div> </nav>  
          </h3>
		  <div class="container">
            <div class="navbar-header">


              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
               <<span class="sr-only">Toggle navigation</span> 
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>

            </div>


            <!-- Nav Starts -->
            <div class="navbar-collapse  collapse" >
              <ul class="nav navbar-nav navbar-right">
               <li class="active"><a href="login.htm">Home</a></li>
                <li><a href="http://jntua.ac.in/aboutus.php" target="blank">About JNTU</a></li>
               
              </ul>
            </div>
            <!-- #Nav Ends -->

          </div>
        </div>

    </div>
<!-- #Header Starts -->

<div class="container">
<div class="spacer">
<div class="row register">
  <div class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
  <form method="post" action="login.htm">
 <div style = "font-size:11px; color:#cc0000; margin-top:10px">${errMsg }</div>

                <input required type="text" class="form-control" placeholder="User Name" name="Username">
               
                <input required type="password" class="form-control" placeholder="Password" name="Password">
               
      <button type="submit" class="btn btn-success" name="Submit">Login</button>
	  <a href="forgot.htm">Forgot Password</a>  |  <a href="studentregs.htm">Student Registration</a>
          

</form>
                
        </div>
  
</div>
</div>
</div>




<div class="footer" >

<div class="container" style=" background-color: skyblue; ">



<div class="row" style=" background-color: skyblue; ">
            <div class="col-lg-3 col-sm-3">
                   <h4>Information</h4>
                   <ul class="row">
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="http://jntua.ac.in/aboutus.php">About JNTU</a></li>
              
              </ul>
            </div>
            
            <div class="col-lg-3 col-sm-3">
                    <h4>Newsletter</h4>
                    <p>Get notified about the latest news in jntu.</p>
                    <form class="form-inline" role="form">
                            <input type="text" placeholder="Enter Your email address" class="form-control">
                                <button class="btn btn-success" type="button"><a href="https://mail.google.com/mail/u/1/#inbox?compose=new">Notify Me!</a></button></form>
            </div>
            
            <div class="col-lg-3 col-sm-3">
                    <h4>Follow us</h4>
                    <a href="https://www.facebook.com/pages/JNTUACEAAnantapur/522601804421603"><img src="images/facebook.png" alt="facebook"></a>
                    <a href="https://twitter.com/jntuau?lang=en"><img src="images/twitter.png" alt="twitter"></a>
                    <a href="https://in.linkedin.com/company/jntu-college-of-engineering-anantapur"><img src="images/linkedin.png" alt="linkedin"></a>
                    <a href="https://www.instagram.com/explore/tags/jntua/"><img src="images/instagram.png" alt="instagram"></a>
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




<!DOCTYPE html>
<html>
<head>	
	<title>Image Upload</title>
	<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
	<meta http-equiv="Content-Language" content="en-us">
    <meta name="viewport" content="width=device-width, maximum-scale=1.0">
    <meta name="description" content="This sample demonstrates how to upload images grabbed from camera in a web application.">
	<link rel="shortcut icon" href="Images/favicon.ico" />
	<link rel="stylesheet" href="Styles/style.css">
</head>
<body>
	
	<div class="content-container">
		<div class="tc">
			<div class="left-container vt">
				<div id="video-container" class="video-container"></div>
				
			</div>
			<div class="right-container tl">
				<div id="image-container" class="image-container"></div>
				<div id="upload-container" class="upload-container">
					<p><input id="txtFileName" class="file-name-input" type="hidden"><br></p>
					<p>
                       
                        <label><input type="hidden" name="img-format" value="JPEG" onchange="setCheckboxEnable(this.value);"></label>
                    
                    </p>
					
					
				</div>
				<div id="uploaded-container" class="uploaded-container">
					<p>Uploaded images</p>
                    <div id="image-links">
                    </div>
				</div>
			</div>
		</div>
	</div>

	
	<div id="loaderContent" class="invisible" >
		<img id="imgLoader" src="Images/loader.gif" />
		<span id="spLoader">Initiating...</span>
	</div>
    <div id="loadingLayer" class="invisible"></div>	

    <script type="text/javascript" src="DCSResources/dynamsoft.camera.config.js"> </script>
    <script type="text/javascript" src="DCSResources/dynamsoft.camera.initiate.js"> </script>
    <script type="text/javascript" src="Scripts/script.js"></script>
</body>
</html>
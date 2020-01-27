
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel = "stylesheet" href = "style.css">
    <title>Webb Application with Java</title>
 	 </head>

  <body>
  <div class = "container-fluid">
   <header> 
   <img src = "skane-logo.jpg" /> 
   <h1>AKOH-N Skånetrafiken </h1>
	</header>
	<h3> Get the nearest buss lines near you!</h3>
		<form action = "getLatAndLongServlet" method="get">
		<input type = "text"  id ="addr" name = "address"  placeholder= "Your position">
		
  	    <input type="button" onclick="showPosition();" class="btn btn-info" value = "Get Position">
		<input type = "submit" value = "Submit" class="btn btn-primary" > 
		<input type = "reset"  value= "Refresh" class="btn btn-secondary">
		<div id = "result"> </div>
		</form>
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
     <script>
	
     function showPosition() {
         if(navigator.geolocation) {
             navigator.geolocation.getCurrentPosition(function(position) {
                 var positionInfo =  (position.coords.latitude + ","+ position.coords.longitude);
                 document.getElementById("addr").value = positionInfo;
                 
                 //document.getElementById("myText").value = "Johnny Bravo";
             });
         } else {
             alert("Sorry, your browser does not support HTML5 geolocation.");
         }
     }
     
	</script> 
    
    
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>

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
	<input type = "text"  id ="addr" name = "coord"  placeholder= "Your position">
	
 	    <input type="button" onclick="showPosition();" class="btn btn-info" value = "Get Your Position">
		<input type = "submit" value = "Submit" class="btn btn-primary" > 
		<input type = "reset"  value= "Refresh" class="btn btn-secondary">
		  <form action="CookieController" method="post">
		  <input type="submit" value="Search History" class="btn btn-secondary">
		  </form>
		<div id = "result"> </div>
		</form>
	</div>






    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.3.14/proj4.js"></script>
     <script>
	
     function showPosition() {
         if(navigator.geolocation) {
             navigator.geolocation.getCurrentPosition(function(position) {
                 var positionInfo =  (position.coords.longitude + ","+ position.coords.latitude);
               // document.getElementById("addr").value = positionInfo;
                proj4.defs([
     				['WGS84', 
     				"+title=WGS 84 (long/lat) +proj=longlat +ellps=WGS84 +datum=WGS84 +units=degrees"],
     				['RT90',"+proj=tmerc +lat_0=0 +lon_0=15.80827777777778 +k=1 +x_0=1500000 +y_0=0 +ellps=bessel +towgs84=414.1,41.3,603.1,-0.855,2.141,-7.023,0 +units=m +no_defs"]

     				]);
     				var source='WGS84';
     				var target='RT90';
     				
     				var result = proj4(source, target, [ position.coords.longitude , position.coords.latitude,]);
     				
                 document.getElementById("addr").value = (result[0] + "," + result[1]);
                     
                 
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

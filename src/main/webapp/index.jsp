
<html lang="en">
    <head>
        <title>CSV To XLS Converter</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    	<h1>Convert your CSV File to XLS File!</h1>
        <form action="ConverterServlet" method="post" enctype="multipart/form-data">
    		Enter the name for target file:<input type="text" name="targetFileName" /> <br><br>
    		Upload your .csv file<br><input type="file" name="uploadedFile" /><br><br>
    		<input type="submit" value="Convert and Download" />
		</form>
    </body>
</html>
package com.anurag.mva;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ConverterServlet")
@MultipartConfig
public class ConverterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String targetFileName = request.getParameter("targetFileName"); 
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + targetFileName + ".xls" + "\"");
		
	    Part filePart = request.getPart("uploadedFile"); 
	    InputStream fileContent = filePart.getInputStream();
	    byte[] buffer = new byte[fileContent.available()];
	    fileContent.read(buffer);
	 
	    String tempPath = System.getProperty("java.io.tmpdir") + targetFileName;
	    
	    File tempFile = new File(tempPath + ".csv");
	    OutputStream outStream = new FileOutputStream(tempFile);
	    outStream.write(buffer);
	    
	    try {
			CsvToXlsConverter.convert(tempPath);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    FileInputStream fileInputStream = new FileInputStream(new File(tempPath + ".xls"));
	    
		int i;
		while ((i = fileInputStream.read()) != -1) {
			response.getWriter().write(i);
		}
		
		fileContent.close();
		outStream.close();
		fileInputStream.close();
		response.getWriter().close();
	    
	}

}

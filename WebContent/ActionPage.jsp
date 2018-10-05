<%@page import="java.util.*,java.io.File,java.io.FileOutputStream,org.apache.commons.fileupload.FileUpload,org.apache.commons.fileupload.FileItem,org.apache.commons.fileupload.disk.DiskFileItemFactory,org.apache.commons.fileupload.servlet.ServletFileUpload,sun.misc.BASE64Decoder"%>
<%@page contentType="application/json; charset=utf-8" %>
<%@page language="java" %>
<%
    String strJson = "{\"success\":false}";

    try{
    	  System.out.println("fff");

        // get more info from: http://commons.apache.org/proper/commons-fileupload/

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> iter = items.iterator();

        String fileName = null;
        String tempFileName = null;
        String contentType = null;
        FileItem fileItem = null;

        while (iter.hasNext()) {
            FileItem item = iter.next();
            String fieldName = item.getFieldName();

            if(fieldName.equals("fileName")){
                fileName = item.getString();
            }else if(fieldName.equals("RemoteFile")){
                tempFileName = item.getName();
                contentType = item.getContentType();
                fileItem = item;
            }
        }

        if(fileName == null || fileName.isEmpty()){
            fileName = tempFileName;
        }
        String path = application.getRealPath(request.getServletPath());
        String dir = new java.io.File(path).getParent();
        String filePath = dir + "/UploadedImages/" + fileName;

        File file = new File(filePath);
        System.out.println(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        if(!contentType.contains("text/plain")){
            fileItem.write(file);
        }else{
            String base64Str = fileItem.getString();
            byte[] b = null;
            b = (new BASE64Decoder()).decodeBuffer(base64Str);
            FileOutputStream fileOutStream = new FileOutputStream(file);
            fileOutStream.write(b);
            fileOutStream.flush();
            fileOutStream.close();
        }

        strJson = "{\"success\":true, \"fileName\":\"" + fileName + "\"}";
    }
    catch(Exception ex){
        strJson = "{\"success\":false, \"error\": \"" + ex.getMessage().replace("\\", "\\\\") + "\"}";
    }

   
    out.write(strJson);
   
  
%>
package com.cn.test.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 操作文件类
 * @author jackzhao
 * @date 2012-10-17
 */
public class FileUtil {
	
	/**方法描述：写文件，内容不覆盖 .<br>
	 * 备          注:  .<br>
	 * 创  建   人: jackzhao<br>
	 * 创建日期: 2012-10-17 .<br>
	 * @param path
	 * @param content .<br>
	*/
	public static void writeFile(String path, String content) {
	      String s = new String();
	      String s1 = new String();
	      try {
	       File f = new File(path);
	       if (f.exists()) {
	       } 
	       else {
	    	    createFolder(path.substring(0,path.lastIndexOf("/")));
		        if (f.createNewFile()) {
		         System.out.println("文件创建成功！");
		        } else {
		         System.out.println("文件创建失败！");
		        }
	       }
	       BufferedReader input = new BufferedReader(new FileReader(f));

	       while ((s = input.readLine()) != null) {
	        s1 += s + "\n";
	       }
	       input.close();
	       s1 += content;

	       BufferedWriter output = new BufferedWriter(new FileWriter(f));
	       output.write(s1);
	       output.close();
	      } catch (Exception e) {
	       e.printStackTrace();
	      }
	}
	
	/**方法描述：创建多级文件夹 .<br>
	 * 备          注:  .<br>
	 * 创  建   人: jackzhao .<br>
	 * 创建日期: 2012-10-17 .<br>
	 * @param folderPath
	 * @throws Exception .<br>
	*/
	public static void createFolder(String folderPath)
	{
		String[] splitedFolder = folderPath.split("/");
		StringBuilder path = new StringBuilder();
		for (int i = 0; i < splitedFolder.length; i++)
		{
			if(i > 0)
			{
				path.append("/");
			}
			path.append(splitedFolder[i]);
			File filePath = new File(path.toString());
			if(!filePath.exists())
				filePath.mkdir();
		}
	}
	
	
	/**
	 * 删除文件
	 * @param filePath
	 * @param fileName
	 */
	public static void deleteFile(String filePath,String fileName) {
		
		File file=new File(filePath + fileName);
		if(file.exists()==true&&file.isFile()==true){
			file.delete();
		}
	}
	
	/**
	 * 记录日志
	 * @param sFirstEntity
	 * @param sSecondEntity
	 * @param sMessage
	 * @param sPara
	 * @param sPath
	 */
	public static  void WriteLogInfo(String sFirstEntity,String sSecondEntity,String sMessage,String sPara,String sPath)
	{
		try
		{
			int fileCount=0;
			sFirstEntity=sFirstEntity.replace("/", "").replace("\\", "").replace(":", "").replace("*", "").replace("?","").replace("\"", "").replace("<", "").replace(">", "").replace("|", "");
			sSecondEntity=sSecondEntity.replace("/", "").replace("\\", "").replace(":", "").replace("*", "").replace("?","").replace("\"", "").replace("<", "").replace(">", "").replace("|", "");
			sPath=sPath+"/logs";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sPath=sPath+"/"+sdf.format(new Date());
			sPath=sPath+"/"+sFirstEntity;
			sPath=sPath+"/"+sSecondEntity;
			
			SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd_hh:mm:ss");
			String sFileName=sSecondEntity+time.format(new Date())+".txt";
			String sFilePath=sPath+"/"+sFileName;

				 int index=sFileName.lastIndexOf(".");
				 int index2=sFileName.lastIndexOf("_");
				 fileCount++;
				 sFileName=sFileName.substring(0,index2+1)+String.valueOf(fileCount)+sFileName.substring(index);
				 sFilePath=sPath+"/"+sFileName;
				 SimpleDateFormat timetxt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				 String content=timetxt.format(new Date())+sPara+" "+sMessage;
				 FileUtil.writeFile(sFilePath, content);
			
			
		
		}
		catch(Exception ex)
		{
			
		}
		
	}

}

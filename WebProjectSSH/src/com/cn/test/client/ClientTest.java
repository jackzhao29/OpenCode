package com.cn.test.client;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.cn.test.util.FileUtil;

public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp currentTime=new Timestamp(System.currentTimeMillis());
		FileUtil.writeFile("e:test/test",sdf.format(currentTime)+":"+"测试");
		
		FileUtil.WriteLogInfo("10000", "tertse", "测试", "test", "e:");
		

	}

}

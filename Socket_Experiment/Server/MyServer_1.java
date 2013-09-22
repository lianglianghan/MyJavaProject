/**
 * 功能：Server客户端的后台
 * 作者：亮亮
 * 日期：2013.9.17
 */
package com.server.model;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.server.view.MyServerFrame;
public class MyServer implements Runnable{

	public  static ServerSocket ss;
	public   Socket s;
	PrintWriter pw=null;
	BufferedReader br;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer  ms=new MyServer();
		Thread t=new Thread(ms);
		t.start();
	}

	public MyServer()
	{
		
		try {
			ss=new ServerSocket(5678);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeMyServer()
	{
		try {
			ss.getLocalPort();
			ss.close();
			
			if(ss.isClosed())
			{
				s.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void SendMessage(String info)
	{
		try {
			if(!ss.isClosed())
			{
				
				pw.println(info);
				if(info.equals("bye"))
				{
					this.closeMyServer();
					info=new String("服务器正在关闭,请稍候...\r\n"+"服务器正常关闭!\r\n");
					MyServerFrame.jta.append(info);
					MyServerFrame.jta.setCaretPosition(MyServerFrame.jta.getText().length());
					MyServerFrame.ms=null;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			if(ss.isClosed())
			{
				return ;
			}
			try {
				System.out.println("我进来了");
				s=this.getSs().accept();
				br=new BufferedReader(new 
						InputStreamReader(s.getInputStream()));
				
				pw=new PrintWriter(s.getOutputStream(),true);
				
				
				while(true)
				{
					String info=br.readLine()+"\r\n";
					System.out.println(info);
					
					if(info.equals("bye"+"\r\n"))
					{
						this.closeMyServer();
						info=new String("服务器正在关闭,请稍候...\r\n"+"服务器正常关闭!\r\n");
						MyServerFrame.ms=null;
					}
					
					System.out.println(info);
				    MyServerFrame.jta.append(info);
					MyServerFrame.jta.setCaretPosition(MyServerFrame.jta.getText().length());
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	

	
	
}

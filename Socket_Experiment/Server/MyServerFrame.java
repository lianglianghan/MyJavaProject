/**
 * 功能：这是服务器端的管理界面（純界面）
 * 作者：亮亮
 * 日期：2013.9.17
 */
package com.server.view;

//引入所需要的包
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.server.model.*;

import java.net.*;
import java.io.*;
public class MyServerFrame extends JFrame implements ActionListener,KeyListener{

	//定义界面所需要的组件
	JButton jb1,jb2,jb3;
	public static JTextArea jta;
	JScrollPane jsp;
	JPanel jp1,jp2;
	JTextField jtf;
	
	//定义处理通讯的变量
	MyServer ms=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame  msf=new MyServerFrame();
		msf.GetMessage();
	}

	
	//构造函数
	public MyServerFrame()
	{
		//处理北部
		jb1=new JButton("启动服务器");
		jb1.setBorderPainted(false);
		jb1.setFocusable(false);
		//jb1.setBackground(Color.GRAY);
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		jb2.setBorderPainted(false);
		jb2.setFocusable(false);
		jb2.addActionListener(this);
		jp1=new JPanel();
		jp1.add(jb1);
		jp1.add(jb2);
		
		//处理中部
		jta=new JTextArea();
		//jta.setBorder(BorderFactory.createLoweredBevelBorder());
		jsp=new JScrollPane(jta);
		
		//处理南部
		jtf=new JTextField(20);
		jtf.addKeyListener(this);
		jtf.setBorder(BorderFactory.createLoweredBevelBorder());
		jb3=new JButton("发送");
		jb3.setBorderPainted(false);
		jb3.setFocusable(false);
		jb3.addActionListener(this);
		jp2=new JPanel();
		jp2.add(jtf);
		jp2.add(jb3);
		
		
		//将组件添加早JFrame上
		this.add(jp1,"North");
		this.add(jsp,"Center");
		this.add(jp2,"South");
		
		
		this.setSize(400,300);
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2-200,d.height/2-150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("服务器端" );
		
		
		
		
	}


	public void GetMessage()
	{
		
	}
	
	
	@Override
	//对各个按钮进行相应的处理
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//用户点击了启动服务器按钮
		if(e.getSource()==jb1)
		{
			if(ms==null)
			{
				ms=new MyServer();
				Thread t=new Thread(ms);
				t.start();
				jta.append("正在打开服务器，请稍等...\r\n");
				jta.append("服务器正常运行!\r\n");
			}else
			{
				JOptionPane.showMessageDialog(this,"服务器运行中...");
			}
			
		}else if(e.getSource()==jb2)     //用户点击了关闭了服务器按钮
		{
			if(ms==null)
			{
				JOptionPane.showMessageDialog(this, "抱歉，服务器没有启动...");
			}else 
			{
				ms.closeMyServer();
				ms=null;
				jta.append("正在关闭服务器，请稍等...\r\n");
				jta.append("服务器正常关闭!\r\n");
			}
			
		}else if(e.getSource()==jb3)    //用户点击了发送按钮
		{
			String info="服务器  对  客户端说： "+jtf.getText();
			jta.append(info+"\r\n");
			ms.SendMessage(info);
			jtf.setText("");
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			String info="服务器  对  客户端说： "+jtf.getText();
			jta.append(info+"\r\n");
			jtf.setText("");
			ms.SendMessage(info);
			
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
}

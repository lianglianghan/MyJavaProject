/**
 * 功能：这是客户端的界面
 * 作者：亮亮
 * 日期：2013.9.17
 */
package com.client.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class ClientFrame extends JFrame implements ActionListener,KeyListener{

	JTextArea jta;
	JTextField jtf;
	JScrollPane jsp;
	JButton jb1;
	JPanel jp1;
	
	PrintWriter pw=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientFrame cf=new ClientFrame();
	}

	public ClientFrame()
	{
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		
		jb1=new JButton("发送");
		jb1.addActionListener(this);
		jb1.setBorderPainted(false);
		jb1.setFocusable(false);
		jtf=new JTextField(20);
		jtf.addKeyListener(this);
		jtf.setBorder(BorderFactory.createLoweredBevelBorder());
		jp1=new JPanel();
		jp1.add(jtf);
		jp1.add(jb1);
		
		this.add(jsp);
		this.add(jp1,"South");
		
		
		this.setTitle("客户端");
		this.setSize(400,300);
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2-200, d.height/2-150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			Socket s=new Socket("127.0.0.1",5678);
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw=new PrintWriter(s.getOutputStream(),true);
			if(!s.isConnected())
			{
				jta.append("连接远程服务器失败，请重试...\r\n");
			}else
			{
				jta.append("连接远程服务器连接成功...\r\n");
				pw.println("用户从ip为："+InetAddress.getLocalHost().toString()+"的电脑登陆");
			}
			while(true)
			{
				if(s.isConnected())
				{
					//System.out.println("客户端连接成功");
					String info=br.readLine();
					if(info==null)
					{
						s.close();
					}else
					{
						jta.append(info+"\r\n");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			String info=new String("客户端  对  服务器  说 ："+jtf.getText());
			jta.append(info+"\r\n");
			jta.setForeground(Color.red);
			
			try {
				pw.println(info);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			String info="客户端  对  服务器  说 ："+jtf.getText();
			jta.append(info+"\r\n");
			try {
				pw.println(info);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jtf.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

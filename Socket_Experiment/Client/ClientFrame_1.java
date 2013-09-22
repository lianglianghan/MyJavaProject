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
public class ClientFrame extends JFrame implements ActionListener,KeyListener,Runnable{

	JTextArea jta;
	JTextField jtf;
	JScrollPane jsp;
	JButton jb1;
	JPanel jp1;
	
	PrintWriter pw=null;
	Socket s=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientFrame cf=new ClientFrame();
		Thread t=new Thread(cf);
		t.start();
	}

	public ClientFrame()
	{
		jta=new JTextArea();
		jta.setForeground(Color.blue);
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
		this.setResizable(false);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			
			String info=new String("客户端  对  服务器  说 ："+jtf.getText());
			if(jtf.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "对不起，发送的内容不能为空...");
			}else
			{
				
				jta.append(info+"\r\n");
				jta.setCaretPosition(jta.getText().length());
				if(jtf.getText().equals("bye"))
				{
					info=new String("bye");
				}
				
				try {
					if(info.equals("bye"))
					{
						int response=JOptionPane.showConfirmDialog(this, "您确定要关闭服务器和客户端吗？");
						if(response==0)
						{
							pw.println(info);
							this.dispose();
						}
					}else{
						pw.println(info);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jtf.setText("");
			}
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
			if(jtf.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "对不起，发送的内容不能为空...");
			}else
			{
				jta.append(info+"\r\n");
				jta.setCaretPosition(jta.getText().length());
				if(jtf.getText().equals("bye"))
				{
					info=new String("bye");
				}
				try {
					if(info.equals("bye"))
					{
						int response=JOptionPane.showConfirmDialog(this, "您确定要关闭服务器和客户端吗？");
						if(response==0)
						{
							pw.println(info);
							this.dispose();
						}
					}else{
						pw.println(info);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jtf.setText("");
		
				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				s=new Socket("127.0.0.1",5678);
				System.out.println("hahha");
				BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				pw=new PrintWriter(s.getOutputStream(),true);
				if(s.isConnected())
				{
					jta.append("连接远程服务器连接成功...\r\n");
					jta.setCaretPosition(jta.getText().length());
					
				    pw.println("用户从ip为："+InetAddress.getLocalHost().toString()+"的电脑登陆");
					
				}else
				{
					jta.append("连接远程服务器失败，请重试...\r\n");
				}
				while(true)
				{
					if(s.isConnected())
					{
						String info=br.readLine();
						if(info==null)
						{
							s.close();
						}else
						{
							
							jta.append(info+"\r\n");
							jta.setCaretPosition(jta.getText().length());
							if(info.equals("bye"))
							{
								jta.append("客户端正在关闭,请稍候..."+"\r\n");
								Thread.sleep(2000);
								this.dispose();
							}
						}
						
					}
				}
			} catch (Exception e) {
				if(s==null)
				{
					jta.append("连接远程服务器失败，请重试...\r\n");
					jta.setCaretPosition(jta.getText().length());
				}
				e.printStackTrace();
				// TODO: handle exception
			}
		}
			
	}
}

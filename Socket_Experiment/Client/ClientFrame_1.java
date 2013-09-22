/**
 * ���ܣ����ǿͻ��˵Ľ���
 * ���ߣ�����
 * ���ڣ�2013.9.17
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
		
		
		jb1=new JButton("����");
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
		
		
		this.setTitle("�ͻ���");
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
			
			String info=new String("�ͻ���  ��  ������  ˵ ��"+jtf.getText());
			if(jtf.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "�Բ��𣬷��͵����ݲ���Ϊ��...");
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
						int response=JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ�رշ������Ϳͻ�����");
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
			
			String info="�ͻ���  ��  ������  ˵ ��"+jtf.getText();
			if(jtf.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "�Բ��𣬷��͵����ݲ���Ϊ��...");
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
						int response=JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ�رշ������Ϳͻ�����");
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
					jta.append("����Զ�̷��������ӳɹ�...\r\n");
					jta.setCaretPosition(jta.getText().length());
					
				    pw.println("�û���ipΪ��"+InetAddress.getLocalHost().toString()+"�ĵ��Ե�½");
					
				}else
				{
					jta.append("����Զ�̷�����ʧ�ܣ�������...\r\n");
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
								jta.append("�ͻ������ڹر�,���Ժ�..."+"\r\n");
								Thread.sleep(2000);
								this.dispose();
							}
						}
						
					}
				}
			} catch (Exception e) {
				if(s==null)
				{
					jta.append("����Զ�̷�����ʧ�ܣ�������...\r\n");
					jta.setCaretPosition(jta.getText().length());
				}
				e.printStackTrace();
				// TODO: handle exception
			}
		}
			
	}
}

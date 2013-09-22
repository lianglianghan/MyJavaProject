/**
 * ���ܣ����Ƿ������˵Ĺ�����棨�����棩
 * ���ߣ�����
 * ���ڣ�2013.9.17
 */
package com.server.view;

//��������Ҫ�İ�
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.server.model.*;

import java.net.*;
import java.io.*;
public class MyServerFrame extends JFrame implements ActionListener,KeyListener{

	//�����������Ҫ�����
	JButton jb1,jb2,jb3;
	public static JTextArea jta;
	JScrollPane jsp;
	JPanel jp1,jp2;
	JTextField jtf;
	
	//���崦��ͨѶ�ı���
	public static MyServer ms=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame  msf=new MyServerFrame();
		
	}

	
	//���캯��
	public MyServerFrame()
	{
		//������
		jb1=new JButton("����������");
		jb1.setBorderPainted(false);
		jb1.setFocusable(false);
		//jb1.setBackground(Color.GRAY);
		jb1.addActionListener(this);
		jb2=new JButton("�رշ�����");
		jb2.setBorderPainted(false);
		jb2.setFocusable(false);
		jb2.addActionListener(this);
		jp1=new JPanel();
		jp1.add(jb1);
		jp1.add(jb2);
		
		//�����в�
		jta=new JTextArea();
		jta.setForeground(Color.red);
		//jta.setBorder(BorderFactory.createLoweredBevelBorder());
		jsp=new JScrollPane(jta);
		
		//�����ϲ�
		jtf=new JTextField(20);
		jtf.addKeyListener(this);
		jtf.setBorder(BorderFactory.createLoweredBevelBorder());
		jb3=new JButton("����");
		jb3.setBorderPainted(false);
		jb3.setFocusable(false);
		jb3.addActionListener(this);
		jp2=new JPanel();
		jp2.add(jtf);
		jp2.add(jb3);
		
		
		//����������JFrame��
		this.add(jp1,"North");
		this.add(jsp,"Center");
		this.add(jp2,"South");
		
		
		this.setSize(400,300);
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2-200,d.height/2-150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("��������" );
		this.setResizable(false);
		
		
		
		
	}

	
	
	@Override
	//�Ը�����ť������Ӧ�Ĵ���
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�û������������������ť
		if(e.getSource()==jb1)
		{
			if(ms==null)
			{
				ms=new MyServer();
				Thread t=new Thread(ms);
				t.start();
				jta.append("���ڴ򿪷����������Ե�...\r\n");
				jta.append("��������������!\r\n");
				jta.setCaretPosition(jta.getText().length());
			}else
			{
				JOptionPane.showMessageDialog(this,"������������...");
			}
			
		}else if(e.getSource()==jb2)     //�û�����˹ر��˷�������ť
		{
			if(ms==null)
			{
				JOptionPane.showMessageDialog(this, "��Ǹ��������û������...");
			}else 
			{
				if(ms.ss.isClosed())
				{
					JOptionPane.showMessageDialog(this, "�������Ѿ��ر�...");
				}else
				{
					ms.closeMyServer();
					ms=null;
					jta.append("���ڹرշ����������Ե�...\r\n");
					jta.append("�����������ر�!\r\n");
					jta.setCaretPosition(jta.getText().length());
				}
			}
			
		}else if(e.getSource()==jb3)    //�û�����˷��Ͱ�ť
		{
			String info="������  ��  �ͻ���˵�� "+jtf.getText();
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
				jtf.setText("");
				if(info.equals("bye"))
				{
					int response=JOptionPane.showConfirmDialog(this,"��ȷ��Ҫǿ�йرտͻ��˺ͷ�������");
					
					if(response==0)
					{
						ms.SendMessage(info);
					}
				}else{
					ms.SendMessage(info);
				}
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
			
			String info="������  ��  �ͻ���˵�� "+jtf.getText();
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
				jtf.setText("");
				if(info.equals("bye"))
				{
					int response=JOptionPane.showConfirmDialog(this,"��ȷ��Ҫǿ�йرտͻ��˺ͷ�������");
					if(response==0)
					{
						ms.SendMessage(info);
					}
				}else{
					ms.SendMessage(info);
				}
				
			}
			
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
}

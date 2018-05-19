package lab5;

import java.net.*;
import java.io.*;

public class Lab5 {
	public static void sendMail(String smtpServer, String sender, String recipient, String content)throws Exception {
		   Socket socket=new Socket(smtpServer, 25);
		   BufferedReader br=new BufferedReader(new InputStreamReader( socket.getInputStream() ) );
		   PrintWriter pw=new PrintWriter( socket.getOutputStream(), true );
		   System.out.println("������ ����Ǿ����ϴ�.");
		
		   String line=br.readLine();
		   System.out.println("����:"+line);
		   if (!line.startsWith("220")) throw new Exception("SMTP������ �ƴ�:"+line);
		
		   System.out.println("HELO ����");
		   pw.println("HELO gmail.com");
		   line=br.readLine();
		   System.out.println("����:"+line);
		   if (!line.startsWith("250")) throw new Exception("HELO ����:"+line);
		
		   System.out.println("MAIL FROM ����");
		   pw.println("MAIL FROM:"+sender);
		   line=br.readLine();
		   System.out.println("����:"+line);
		   if (!line.startsWith("250")) throw new Exception("MAIL FROM ����:"+line);
		
		   System.out.println("RCPT ����");
		   pw.println("RCPT TO:"+recipient);
		   line=br.readLine();
		   System.out.println("����:"+line);
		   if (!line.startsWith("250")) throw new Exception("RCPT TO ����:"+line);
		
		   System.out.println("DATA ����");
		   pw.println("DATA");
		   line=br.readLine();
		   System.out.println("����:"+line);
		   if (!line.startsWith("354")) throw new Exception("DATA ����:"+line);
		   
		   pw.println("From: " + sender);
		   pw.println("Subject: Kim Ga Yeon(20163085)");
		   pw.println("To: " + recipient);
		
		   System.out.println("���� ����");
		   pw.println(content);
		   pw.println(".");
		   line=br.readLine();
		   System.out.println("����:"+line);
		   if (!line.startsWith("250")) throw new Exception("���� ���� ����:"+line);
		
		   System.out.println("���� ����");
		   pw.println("quit");
		
		   br.close();
		   pw.close();
		   socket.close();
	   }

	   public static void main(String args[]) {
	      try {
	         Lab5.sendMail(
	               "localhost", 
	               "gayeonKim_20163085@naver.com", 
	               "object1602@gmail.com", 
	               "lab5 20163085 �谡���Դϴ�"
	               );
	         System.out.println("==========================");
	         System.out.println("���� ���� �Ϸ�");
	      } catch(Exception e) {
	         System.out.println("==========================");
	         System.out.println("���� ���� �Ұ�");
	         System.out.println(e.toString());
	      }
	   }
}

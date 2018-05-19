package lab5;

import java.net.*;
import java.io.*;

public class Lab5 {
	public static void sendMail(String smtpServer, String sender, String recipient, String content)throws Exception {
		   Socket socket=new Socket(smtpServer, 25);
		   BufferedReader br=new BufferedReader(new InputStreamReader( socket.getInputStream() ) );
		   PrintWriter pw=new PrintWriter( socket.getOutputStream(), true );
		   System.out.println("서버에 연결되었습니다.");
		
		   String line=br.readLine();
		   System.out.println("응답:"+line);
		   if (!line.startsWith("220")) throw new Exception("SMTP서버가 아님:"+line);
		
		   System.out.println("HELO 실행");
		   pw.println("HELO gmail.com");
		   line=br.readLine();
		   System.out.println("응답:"+line);
		   if (!line.startsWith("250")) throw new Exception("HELO 실패:"+line);
		
		   System.out.println("MAIL FROM 실행");
		   pw.println("MAIL FROM:"+sender);
		   line=br.readLine();
		   System.out.println("응답:"+line);
		   if (!line.startsWith("250")) throw new Exception("MAIL FROM 실패:"+line);
		
		   System.out.println("RCPT 실행");
		   pw.println("RCPT TO:"+recipient);
		   line=br.readLine();
		   System.out.println("응답:"+line);
		   if (!line.startsWith("250")) throw new Exception("RCPT TO 실패:"+line);
		
		   System.out.println("DATA 실행");
		   pw.println("DATA");
		   line=br.readLine();
		   System.out.println("응답:"+line);
		   if (!line.startsWith("354")) throw new Exception("DATA 실패:"+line);
		   
		   pw.println("From: " + sender);
		   pw.println("Subject: Kim Ga Yeon(20163085)");
		   pw.println("To: " + recipient);
		
		   System.out.println("본문 전송");
		   pw.println(content);
		   pw.println(".");
		   line=br.readLine();
		   System.out.println("응답:"+line);
		   if (!line.startsWith("250")) throw new Exception("내용 전송 실패:"+line);
		
		   System.out.println("접속 종료");
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
	               "lab5 20163085 김가연입니다"
	               );
	         System.out.println("==========================");
	         System.out.println("메일 전송 완료");
	      } catch(Exception e) {
	         System.out.println("==========================");
	         System.out.println("메일 전송 불가");
	         System.out.println(e.toString());
	      }
	   }
}

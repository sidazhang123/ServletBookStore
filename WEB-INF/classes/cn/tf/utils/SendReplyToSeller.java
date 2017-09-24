package cn.tf.utils;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.tf.bean.NoticeList;


public class SendReplyToSeller extends Thread{
	
	private NoticeList list;
	private String email;
	public SendReplyToSeller(String email, NoticeList list){
		this.email = email;
		this.list = list;
	}
	
	public void run(){
		
	
		
		
		final String username = "9321shoptest@gmail.com";
		final String password = "cscscs123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		String msg="<br/>";
		for(int i=0;i<list.getSize();i++){
			msg+=list.getBookName(i)+"<br/>";
		}
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("9321shoptest@gmail.com"));
			
			
			message.setSubject("Your item has been bought.");
			message.setContent("","text/html;charset=UTF-8");
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse( email));
			message.setContent("These items have recently been sold:"+msg, "text/html;charset=UTF-8");
			message.saveChanges();

			Transport.send(message);

			System.out.println("Don1");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
	
	}

}

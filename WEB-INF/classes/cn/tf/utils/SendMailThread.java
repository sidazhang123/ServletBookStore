package cn.tf.utils;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.tf.domain.Customer;

public class SendMailThread extends Thread{
	
	private Customer customer;
	public SendMailThread(Customer customer){
		this.customer=customer;
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

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("9321shoptest@gmail.com"));
			
			
			message.setSubject("Activate your Account @ 9321Bookstore");
			message.setContent("","text/html;charset=UTF-8");
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse( customer.getEmail()));
			message.setContent("Dear "+customer.getUsername()+",<br/>Please click this link to activate your account at out store<a href='http://localhost:8080/BookStore/servlet/ClientServlet?op=activeCustomer&code="+customer.getCode()+"'>ACTIVE</a><br/>", "text/html;charset=UTF-8");
			message.saveChanges();

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
	
	}

}

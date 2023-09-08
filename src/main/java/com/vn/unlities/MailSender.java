package com.vn.unlities;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.module.Configuration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.Template;
import com.vn.model.HoaDon;
import com.vn.service.AccountService;
import com.vn.service.HoaDonChiTietService;
import com.vn.service.IAccountService;
import com.vn.service.IHoaDonChiTietService;

public class MailSender {
	private IAccountService accountService = new AccountService();
	public void SendMail(String username ,String email) {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				String username = "linhnkph24164@fpt.edu.vn";
				String password ="isfuogsanvgzqreq";
				return new PasswordAuthentication(username, password);
			}
		});
		String pass = "";
		for(int i = 0 ; i < 8 ; i++) {
			Integer so = new Random().nextInt(9);
			pass+= so;
		}
		accountService.doiMatKhau(username, pass);
		String to = email;
		String subject = "Cập nhật lại mật khẩu";
		String body = "Xin chào "+username +" !\nMật khẩu mới của bạn là : "+pass + "<img src ='https://i.pinimg.com/originals/30/d5/e6/30d5e6fc8b2fab452555c9314d72be59.gif'>";

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("linhnkph24164@fpt.edu.vn"));
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject(subject);
			message.setText(body);
			message.setReplyTo(message.getFrom());
			Transport.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
	public void SendMailBill(String usename , String email , HoaDon hoaDon) {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				String username = "linhnkph24164@fpt.edu.vn";
				String password ="isfuogsanvgzqreq";
				return new PasswordAuthentication(username, password);
			}
		});
		
		String to = email;
		String subject = "Hóa Đơn #"+hoaDon.getIdHoaDon();
//		String emailContext = getEmailContext(username,hoaDon);
//		String body = emailContext;
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("linhnkph24164@fpt.edu.vn"));
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject(subject);
			
			// tạo đối tượng Mime để chứa nội dung email
			MimeMultipart multipart = new MimeMultipart();
			BodyPart htmlPart = new MimeBodyPart();
//			htmlPart.setContent(emailContext, "text/html;charset=UTF-8");
			multipart.addBodyPart(htmlPart);
			message.setContent(multipart);
			message.setReplyTo(message.getFrom());
			
			Transport.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		
		
	}
//	
//	String getEmailContent(String username , HoaDon hoaDon)   {
//		StringWriter stringWriter = new StringWriter();
//		Map<String, Object> model = new HashMap<>();
//		model.put("user", username);
//		model.put("hd", hoaDon);
//		
//		IHoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
//		List<HoaDonChiTietService> hoaDonChiTietServices = hoaDonChiTietService.getAll(hoaDon.getIdHoaDon());
//		model.put("hdct", hoaDonChiTietServices);
//		Configuration cfg = new ConfigurationException(Configuratio.VERSION_2_3_31);
//		
//		return username;
//		
//	}
}

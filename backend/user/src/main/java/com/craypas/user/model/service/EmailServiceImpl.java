package com.craypas.user.model.service;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	JavaMailSender emailSender; // Bean 등록해둔 MailConfig 를 emailsender 라는 이름으로 autowired

	@Override
	public MimeMessage createMessage(String to, String ePw) throws Exception {
		System.out.println("보내는 대상 : "+ to);
		System.out.println("인증 번호 : "+ePw);
		MimeMessage  message = emailSender.createMimeMessage();

		message.addRecipients(RecipientType.TO, to);//보내는 대상
		message.setSubject("이음 회원가입 이메일 인증");//제목

		String msgg="";
		msgg+= "<div style='margin:100px;'>";
		msgg+= "<h1> 안녕하세요 이음입니다. </h1>";
		msgg+= "<br>";
		msgg+= "<p>아래 코드를 인증 화면으로 돌아가 입력해주세요<p>";
		msgg+= "<br>";
		msgg+= "<p>감사합니다!<p>";
		msgg+= "<br>";
		msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg+= "<h3 style='color:blue;'>이메일 인증 코드입니다.</h3>";
		msgg+= "<div style='font-size:130%'>";
		msgg+= "CODE : <strong>";
		msgg+= ePw+"</strong><div><br/> ";
		msgg+= "</div>";
		message.setText(msgg, "utf-8", "html");//내용
		message.setFrom(new InternetAddress("${AdminMail.id}","이음"));//보내는 사람

		return message;
	}

	@Override
	public String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 6; i++) { // 인증코드 6자리
			key.append((rnd.nextInt(10)));
		}
		return key.toString();
	}

	@Override
	public String sendSimpleMessage(String to) throws Exception {
		String ePw = createKey();
		MimeMessage message = createMessage(to, ePw);
		try {//예외처리
			emailSender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}
		return ePw;
	}
}

// // 인증코드 만들기
// public static String createKey() {
// 	StringBuffer key = new StringBuffer();
// 	Random rnd = new Random();
//
// 	for (int i = 0; i < 6; i++) { // 인증코드 6자리
// 		key.append((rnd.nextInt(10)));
// 	}
// 	return key.toString();
// }
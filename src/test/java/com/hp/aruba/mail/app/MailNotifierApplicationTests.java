package com.hp.aruba.mail.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.hp.aruba.mail.bean.MailInfo;
import com.hp.aruba.mail.bean.MailParticipant;
import com.hp.aruba.mail.bean.MailSecurityId;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailNotifierApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("Hola");
		MailInfo mi = new MailInfo();
		MailSecurityId msi = new MailSecurityId();
		msi.setKey("key");
		msi.setName("name");
		mi.setSecurityId(msi);
		MailParticipant mp1 = new MailParticipant();
		mp1.setEmail("cc@gmail.com");
		mp1.setName("Juan Perez");
		mp1.setType("sender");
		MailParticipant mp2 = new MailParticipant();
		mp2.setEmail("cc2@gmail.com");
		mp2.setName("Pedro Perez");
		mp2.setType("receiver");
		mi.setGeneralText("Texto General");
		mi.setSubject("Test correo");
		mi.setMailSender(mp1);
		mi.setMailReceiver(mp2);
		
		String json = new Gson().toJson(mi);
		System.out.println("Objeto "+ json);
	}

}

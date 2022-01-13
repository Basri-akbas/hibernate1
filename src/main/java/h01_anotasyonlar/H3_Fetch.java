package h01_anotasyonlar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H3_Fetch {
	public static void main(String[] args) {
		
		Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(H1_Sehir.class);
		
		SessionFactory sf=con.buildSessionFactory();
		
		Session session=sf.openSession();
		
		Transaction tx=session.beginTransaction();
		
		//sehir_tablosundan istenilen id li kisiyi getir
		System.out.println(session.get(H1_Sehir.class,35)); //select * from sehir_tablosu where sehir_plaka=35
		System.out.println(session.get(H1_Sehir.class, 37).getSehirNufus());  //select sehirNufus from sehir_tablosu where sehirPlaka=37
		
		
		
		tx.commit();
		sf.close();
		session.close();
	}

}

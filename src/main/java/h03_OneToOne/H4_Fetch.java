package h03_OneToOne;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H4_Fetch {

	public static void main(String[] args) {
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(H1_Kisi.class)
				.addAnnotatedClass(H2_Gunluk.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		
		// id=101 olan kisi bilgilerini getir
		System.out.println("1.soru");
		System.out.println(session.get(H1_Kisi.class, 101));
		System.out.println("***********************************************************************************");
		//id=12 olan gunluk
		System.out.println("2.soru");
		System.out.println(session.get(H2_Gunluk.class, 12));
		System.out.println("******************************************************************************************************************");
		
		//gunluk class indaki butun verileri sorgula
		//1 JPA ile yapmak tercih edilmez
		for (int i = 12; i < 15; i++) {
			System.out.println(session.get(H2_Gunluk.class, i));
		}
		System.out.println("******************************************************************************************************************");
		
		//2. yol hibernate ile yapmak
		List<Object[]> liste=session.createQuery("from H1_Kisi  k  join H2_Gunluk g  on k.kisiId=g.kisi").getResultList();
		liste.forEach(t-> System.out.println(Arrays.toString(t)));              //lambda ile yaptik
		System.out.println("******************************************************************************************************************");
		
		//3) Kisiler ve Gunlukler tablolarindaki ortak olan (one to one ile birebir bağladığımız) kayıtların,
		// Kisi adi, gunluk yazisi(yazilar) ve kisi yası (kisiYas) bilgilerini sorgulayiniz.
		
		//SQL ile
		String sorgu="select k.kisi_ad,g.owner,k.kisiYas from kisiler k inner join gunlukler g on k.kisi_id=g.baglanti";
		List<Object[]> sonuc=session.createSQLQuery(sorgu).getResultList();
		sonuc.forEach(t-> System.out.println(Arrays.toString(t)));
				
		
		tx.commit();
		
	}

}

package h07_crudProje;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class CrudMetodlar {
	private static SessionFactory sf;
	
	public void sessionFactoryOlustur() {
		
		try {
			Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Personel.class);
			 sf = con.buildSessionFactory();
			
		} catch (Throwable e) {    		 //oturum açmak veya yanlış gidebilecek her şeyi kesinlikle ele almak 
            							//istediğiniz bir iş parçacığının en yüksek "tümünü yakala" düzeyi
			{System.out.println("Session faktory olusma sirasinda hata olustu. "+e);}
			
			throw new ExceptionInInitializerError(e);   //tercihen bunu veya usttekini	
		}
	}
	
	//veri tabanina personel ekleyen method (insert)
			public void personelEkle(String ad,String soyad,int maas) {
				
				Session session = sf.openSession();				
				Transaction tx = session.beginTransaction();
				
				try {
					Personel personel=new Personel(ad, soyad, maas);				
					session.save(personel);
					tx.commit();
					System.out.println("Veriler gitti");
				} catch (HibernateException e) {
					if(tx!=null){
						tx.rollback();   	 //bu method tx i bosaltiyor
					}
					e.printStackTrace();     //exception olan satiri goster uzun aciklama yap
				}
				finally {session.close();    // herhalukarda yani catch olsun olmasin calis
				}
			}
	
	
	//veritabanindan personel silen method(delete)
			public void  idIlePersonelSil(long personelId) {
				Session session = sf.openSession();				
				Transaction tx = session.beginTransaction();
				
				try {
					Personel personel=	session.get(Personel.class,personelId);
						
					if(personel==null) {
						System.out.println(personelId+" nolu kisinin kaydı bulunamamıştır");
						
					}else {
						session.delete(personel);
						tx.commit();
						System.out.println(personelId+ " nolu kişinin kaydı silinmiştir");
						System.out.println("silinen" + personel);
						
					}
					}catch(HibernateException e) {
						if(tx!=null) {
							tx.rollback();
						}
						e.printStackTrace(); //exception olan satırı göster uzun açıklama yap
						
					}finally { //herhalukarda yani catch olsun olmasın çalış
						session.close();
					}
			}
	

			
			//id ile kaydin maas bilgisini guncelleyen mthod(update)
			
			public void idIleMaasGuncelle(long personelId, int maas) {
				Session session = sf.openSession();
			    Transaction tx = session.beginTransaction();
			
			    try {
			 Personel personel=   session.get(Personel.class,personelId );
				
			 if(personel==null) {
				 System.out.println(personelId+" nolu kisinin kaydı bulunamamıştır");
			 }else {
				 
				 personel.setMaas(maas); //set le tabloyu güncelleyebiliyoruz
				 tx.commit();
				 System.out.println(personelId + " nolu personelin yeni maası "+ maas);
				 
			 }
			 
			    }catch(HibernateException e) {
					if(tx!=null) {
						tx.rollback();
					}
					e.printStackTrace(); //exception olan satırı göster uzun açıklama yap
					
				}finally { //herhalukarda yani catch olsun olmasın çalış
					session.close();
				}
			 
				
			}
			
			//veri tabanindan tum verileri  getir (read)  hql sorgusu ile
			
			public void tumPersoneliListele() {
				Session session = sf.openSession();
			    Transaction tx = session.beginTransaction();
			
			  try  { 
			  List<Personel> personeller= session.createQuery("FROM Personel").getResultList();  
				
				personeller.stream().forEach((t)->System.out.println(t));
				tx.commit();
			
			}	catch(HibernateException e) {
				if(tx!=null) {
					tx.rollback();
				}
				e.printStackTrace(); //exception olan satırı göster uzun açıklama yap
				
			}finally { //herhalukarda yani catch olsun olmasın çalış
				session.close();
			}
			}
			
			public void sil() {
				
				
				Session session = sf.openSession();
				 
				Transaction	tx = session.beginTransaction();
				
			   Scanner scan=new Scanner(System.in);
			   System.out.println("lütfen silmek istediğiniz kişinin id sini giriniz");
			   long no =scan.nextLong();

				
		      Personel personel = session.get(Personel.class, no);
					
					if(personel == null) {
						System.out.println(no + " nolu kisinin kaydi bulunamamistir.");
					}else {
						session.delete(personel);
						tx.commit(); //önce silinmeyi kaydet sonra syso ile göster
						System.out.println(no + " nolu kisinin kaydi silinmistir.");
					}
					scan.close();
					session.close();
			}
			
}

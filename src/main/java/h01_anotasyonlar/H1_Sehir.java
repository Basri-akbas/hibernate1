package h01_anotasyonlar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//================================================================
//HIBERNATE ICIN TABLO TANIMLAMASININ YAPILMASI 
//tablo oluşturabilmek için içerik hazırlama )alttakiler)
//POJO: Plain Old Java Object									
//1) Private degiskenler tanimlanir.
//2) constructor olusturulur.
//3) Getter ve Setter metotlar tanimlanir.
//4) toString() metodu ile nesne yazdirilabilir hale getirilir.
//================================================================
/*
	Bir Class Hibernate ile tablo olusturmada kullanilacaksa degiskenlerin
	“final” veya “static” tanimlanMAMAsi gerekir. 
*/
//- @Entity anotasyonu bu class’ın bir tabloya donusturulmesi
//gerektigini Hibernate’e gosterir.(sql ortamına mapping yapıo uyarlıo,translate)
//Her bir degisken bir sutun olacaktir.
//- @Table annotasyonu tablo adinin degistirilmesini saglar.javada ve sql deki isimler aynı olmak zorunda değil
//- @Id anotasyonu tablo icerisinde primary key olusturur.
//- @Transient anotasyonu bir degiskenin (sutun) tabloda yer almayacagini gosterir.

@Entity        //alttaki class ismi ile table olusturuyor.
@Table(name="sehir_tablosu")   //eger table ismini degistirmek  istersem 
public class H1_Sehir {
	@Id                          //bu sutun primary key=not null ve unique 
	@Column(name="sehir_plaka")  //column ismini sql de  bu sekilde degistebilirim
	private int sehirPlaka;
	
	private String sehirAdi;
	
	private  int sehirNufus;
	
	@Transient     //javada kullanabiliriz ama sql de tabloda gorunmez
	private String adres;
	
	

	public H1_Sehir() {
		
	}


	public H1_Sehir(int sehirPlaka, String sehirAdi, int sehirNufus) {
		this.sehirPlaka = sehirPlaka;
		this.sehirAdi = sehirAdi;
		this.sehirNufus = sehirNufus;
	
	}


	public int getSehirPlaka() {
		return sehirPlaka;
	}


	public void setSehirPlaka(int sehirPlaka) {
		this.sehirPlaka = sehirPlaka;
	}


	public String getSehirAdi() {
		return sehirAdi;
	}


	public void setSehirAdi(String sehirAdi) {
		this.sehirAdi = sehirAdi;
	}


	public int getSehirNufus() {
		return sehirNufus;
	}


	public void setSehirNufus(int sehirNufus) {
		this.sehirNufus = sehirNufus;
	}


	@Override
	public String toString() {
		return "H1_Sehir [sehirPlaka=" + sehirPlaka + ", sehirAdi=" + sehirAdi + ", sehirNufus=" + sehirNufus + "]";
	}




}

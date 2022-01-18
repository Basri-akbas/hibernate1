package h07_crudProje;

public class Main {

	public static void main(String[] args) {
		
		CrudMetodlar metod=new CrudMetodlar();
		
		metod.sessionFactoryOlustur();
		
	//	metod.personelEkle("Omer", "tufan", 7700);
	//	metod.personelEkle("Suleyman", "matkuliyev", 8800);
	//	metod.personelEkle("Kursat", "turgut", 9900);
	
	//	metod.idIlePersonelSil(3);
		
	//	metod.idIleMaasGuncelle(1, 10000);
		
	//	metod.sil();
		
		metod.tumPersoneliListele();
	}

}

package h05_caching;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="kitaplar2")
@Cacheable
@Cache(region="H2_Kitap", usage=CacheConcurrencyStrategy.READ_WRITE)
public class H2_Kitap {
	
	@Id
	private int id;
	private String isim;
	
	@ManyToOne
	@JoinColumn(name="birlesme")
	private H1_Ogrenci ogrenci;

	public H2_Kitap() {
		
	}
	
	public H2_Kitap(int id, String isim) {
		this.id = id;
		this.isim = isim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	

	public H1_Ogrenci getOgrenci() {
		return ogrenci;
	}

	public void setOgrenci(H1_Ogrenci ogrenci) {
		this.ogrenci = ogrenci;
	}
	@Override
	public String toString() {
		return "Kitap id=" + id + ", isim=" + isim;
	}
	
	
	
	
	// Ayrı session'ların aynı veriyi cache'den alabilmesi için L2 cache sisteminin acilmasi gerekir.
//	// Bunun için 
//	// 1) Aşağıda anotasyonların ilgili nesnelere eklenemsi gerekir.
//	//      @Cacheable
//    //      @Cache(region="H2_Kitap", usage=CacheConcurrencyStrategy.READ_WRITE)
//	
//	// 2) POM dosyasına Cache ile ilgili dependency'leri eklemek gerekir.
//	//     https://mvnrepository.com/artifact/org.hibernate/hibernate-ehcache
//	
//	// 3) cfg dosyasına asagidaki konfigürasyonları eklemek gerekir. 
//	//		<!-- Following 2 lines are for Second Level Cache -->
//	//   	<property name="hibernate.cache.use_second_level_cache">true</property>         
//	//		<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.internal.EhcacheRegionFactory</property>
//	//		<property name="hibernate.cache.provider_class">org.hibernate.cache.internal.EhcacheProvider</property>
//
}
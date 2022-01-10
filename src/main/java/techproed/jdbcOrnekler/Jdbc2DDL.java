package techproed.jdbcOrnekler;

import java.sql.*;

public class Jdbc2DDL {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Pass.4150");
		Statement st=con.createStatement();
		
		/*
	 	A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet) 
	 	   dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.  
	 	    1) execute() metodu 
	 	    2) excuteUpdate() metodu.  
	 	    
	 	B) - execute() metodu her tur SQL ifadesiyle kullanibilen genel bir komuttur. 
	 	   - execute(), Boolean bir deger dondurur. DDL islemlerinde false dondururken, 
	 	     DML islemlerinde true deger dondurur. 
	 	   - Ozellikle, hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi 
	 	     durumlarda tercih edilmektedir.  
	 	     
	 	C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
	 	   - bu islemlerde islemden etkilenen satir sayisini dondurur.
	 	   - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
	 */
		
		 /*=======================================================================
		  ORNEK1:isciler adinda bir tablo olusturunuz id int, 
		  birim VARCHAR(10), maas int
		========================================================================*/ 
	  /*  String crt = "CREATE TABLE isciler"
				 + " (id int primary key,"
		         + " birim VARCHAR(10),"
		         + " maas int)";
	    */
	    
	    //1.yöntem 
	    
	    	//boolean s1=st.execute(crt);
  
	//    	System.out.println("isciler tablosu oluşturuldu" +s1);
	    
	    // execute() metodu DDL komutlarinda hep false deger dondurdugu icin 
 		// donus degerine bakmak gerekli degildir. Zaten komutun calismasi ile 
 		// ilgili bir sorun var ise SQL EXception olusacaktir.
	  
	   /* 
	   st.execute(crt);
	   System.out.println("isciler tablosu oluşturuldu");
		*/
	   
	   /*======================================================================
		  ORNEK2:isciler tablosunu siliniz 		
		========================================================================*/
	   /*
	   String silme="DROP TABLE isciler";
	   
	   st.execute(silme);
	   System.out.println("Isciler tablosu silindi");
	   */
	   
		/*=======================================================================
	    ORNEK3:isciler tablosuna yeni bir sutun {isim Varchar(20)} ekleyiniz.   
	    ========================================================================*/
		/*
		st.execute("ALTER table isciler ADD isim varchar(20)");
		
		System.out.println("Sutun eklendi");
		*/
		
		/*=======================================================================
		  ORNEK4:isciler tablosuna soyisim VARCHAR(20) ve sehir VARCHAR(10)) 
		  adinda 2 yeni sutun ekleyiniz.  
		========================================================================*/
		/*
		st.execute("ALTER table isciler ADD (soyisim varchar(20), sehir varchar(10))");
				System.out.println("Sutunler eklendi");
		*/
		
		/*=======================================================================
		  ORNEK5:isciler tablosundaki soyisim sutunu siliniz.
		========================================================================*/ 
		/*String alterQuery3 = "ALTER TABLE isciler DROP COLUMN birim";
		st.execute(alterQuery3);
		
		System.out.println("isciler tablosundan birim sutunu silindi.."); */
	
		/*=======================================================================
		  ORNEK6:isciler tablosunun adini calisanlar olarak degistiriniz.  
		========================================================================*/
	/*	String alterQuery4 = "ALTER TABLE isciler RENAME TO calisanlar";
		st.execute(alterQuery4);
		System.out.println("isciler tablosunun adi calisanlar olarak degismistir..");
		*/
		/*=======================================================================
		  ORNEK7:calisanlar tablosunu siliniz.  
		========================================================================*/
		String dropQuery2 = "DROP TABLE calisanlar ";
		st.execute(dropQuery2);
	
		System.out.println("calisanlar tablosu silindi..");
		
	}

}

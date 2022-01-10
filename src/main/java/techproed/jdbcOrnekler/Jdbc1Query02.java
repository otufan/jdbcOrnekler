package techproed.jdbcOrnekler;

import java.sql.*;  // bu sekilde buradan gelecek butun importlari otomatik aliyor


public class Jdbc1Query02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Pass.4150");
		Statement st=con.createStatement();
		
		
		/*=======================================================================
		 ORNEK1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.
		========================================================================*/ 
		ResultSet veri1=st.executeQuery("SELECT * FROM bolumler");
		
		while(veri1.next()) {
			System.out.println(veri1.getInt(1) + " " + veri1.getString("bolum_isim") + " " + veri1.getString(3));
		}
		
		System.out.println("=================================================================");
		System.out.println("");

		/*=======================================================================
		 ORNEK2: SATIS ve MUHASEBE bolumlerinde calisan personelin isimlerini ve 
		 maaslarini, maas ters sirali olarak listeleyiniz
		========================================================================*/ 
		
		ResultSet veri2=st.executeQuery("SELECT isim,maas from personel where bolum_id in(10,30) order by maas desc");
		
		while (veri2.next()) {
			
			System.out.println("Personel Isim : " + veri2.getString(1) + "\nPersonel maasi : " + veri2.getInt("maas"));
			System.out.println("---");
			
			
		}
		
		System.out.println("=================================================================");
		System.out.println("");
		
		/*=======================================================================
		  ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini 
		  ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa 
		  bile bolum ismi gosterilmelidir.
		========================================================================*/ 
		
		ResultSet veri3=st.executeQuery("select b.bolum_isim, p.isim, p.maas from bolumler b "
				+ "left join personel p on p.bolum_id=b.bolum_id order by b.bolum_isim, p.maas");
		
		while (veri3.next()) {
			System.out.println("Bolum isim    : " + veri3.getString("b.bolum_isim") + 
					"\nPersonel isim : " + veri3.getString("p.isim") + "\nPersonel maas :  " + veri3.getInt("p.maas"));
			System.out.println("---");
			
		}
		
		System.out.println("=================================================================");
		System.out.println("Maasi Yüksek ilk on kisi");
		
		/*=======================================================================
 	     ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
	     ========================================================================*/
		
		ResultSet veri4=st.executeQuery("select b.bolum_isim, p.isim, p.maas from personel p "
				+ "left join bolumler b on p.bolum_id=b.bolum_id order by maas desc limit 10");
		int sayi=1;
		while (veri4.next()) {System.out.println(sayi + ". Kisi icin Bolum isim    : " + veri4.getString("b.bolum_isim") + 
					"\nPersonel isim : " + veri4.getString("p.isim") + "\nPersonel maas :  " + veri4.getInt("p.maas"));
		sayi++;
		System.out.println("---");
		}
		
		con.close();
		st.close();
		veri1.close();
		veri2.close();
		veri3.close();
		veri4.close();
	}

}

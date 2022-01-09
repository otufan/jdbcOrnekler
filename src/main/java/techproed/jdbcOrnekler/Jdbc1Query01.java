package techproed.jdbcOrnekler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// 1. Ilgili driver i yüklememiz gerekiyor. Ne calisacagini bilmeli
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. Baglanti olusturmamiz gerekiyor
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Pass.4150");
		
		// 3. SQL komutlari icin bir statement olusturulacak
		
		Statement st=con.createStatement();
		
		// 4. SQL ifadeleri yazabilir ve calistirabiliriz
		
		ResultSet veri=st.executeQuery("SELECT isim, maas FROM personel WHERE id=123456789");
	
		//5. sonuclari aldik ve isledik
		
		while(veri.next()) {
			System.out.println(veri.getString("isim") + " "+veri.getInt("maas"));
			
			System.out.println("Personel adi   : " + veri.getString(1)+ "\nPersonel maasi : " + veri.getInt(2));
			// sorgu ekraninda veri.getString() parantez icine istedigimiz verinin sirasini yazabiliriz
		}
		
		// 6. olusuturulan nesneleri bellekten kaldirilmasi lazim
		
		con.close();
		st.close();
		veri.close();
		
	}

}

package techproed.jdbcOrnekler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jdbc3DML {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Pass.4150");
		Statement st=con.createStatement();
		
		/*=======================================================================
		  ORNEK1: Bolumler tablosuna yeni bir kayit (80, 'ARGE', 'ISTANBUL') 
		  ekleyelim 
		========================================================================*/
		
	/*	
		int cikti = st.executeUpdate("INSERT INTO bolumler VALUES(80, 'ARGE', 'ISTANBUL')");
		
		System.out.println(cikti + " satir eklendi");
		*/
		 /*=======================================================================
	      ORNEK2: Bolumler tablosuna birden fazla yeni kayıt ekleyelim.
	     ========================================================================*/ 
	    
	    /* 1.YONTEM
	 	 -----------------------------------------------
	 	 Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin 
	 	 yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi dusunuldugunde
	     bu kotu bir yaklasimdir.*/
		
		// Lambda ile
		/*List<String> liste=new ArrayList<String>(Arrays.asList("INSERT INTO bolumler VALUES(105, 'YEMEKHANE', 'ISTANBUL')",
   	  	"INSERT INTO bolumler VALUES(115, 'OFIS','ANKARA')",
   	  	"INSERT INTO bolumler VALUES(1255, 'OFIS2', 'VAN')"));
		System.out.println(liste);
		liste.stream().forEach(t->{
			try {
				st.executeUpdate(t);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
		
		/*
		String [] sorgular={"INSERT INTO bolumler VALUES(95, 'YEMEKHANE', 'ISTANBUL')",
   	  	"INSERT INTO bolumler VALUES(85, 'OFIS','ANKARA')",
   	  	"INSERT INTO bolumler VALUES(75, 'OFIS2', 'VAN')"};
		
		int count=0;
		for (String each : sorgular) {
			count++;
			st.executeUpdate(each);
			}
		System.out.println(count+"Veriler eklendi");
		*/
		
		// 2.YONTEM (addBath ve excuteBatch() metotlari ile)
	 	// ----------------------------------------------------
	 	// addBatch metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()
	 	// metodu ile veritabanina bir kere gonderilebilir.
	 	// excuteBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda 
	 	// etkilenen satir sayisini gosterir. 
		
	 	String [] sorgular1 = {"INSERT INTO bolumler VALUES(81, 'YEMEKHANE2', 'MUS')",
	 		            	  "INSERT INTO bolumler VALUES(82, 'OFIS3','ORDU')",
	 		            	  "INSERT INTO bolumler VALUES(83, 'OFIS4', 'MUGLA')"};
		
	 	for (String each : sorgular1) {
			st.addBatch(each); // for dongusuyle array den bir bir gelen verileri bir araya toplar
	 		
		}
		st.executeBatch(); //verileri bir seferde database e yollar

	}

}

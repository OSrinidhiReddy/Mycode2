package com.csvfile.db;

import java.awt.TextField;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DB_Connection
{
	static String url = "jdbc:mysql://localhost:3306/ab";
	static String user = "root";
	static String password = "root";
	static Connection con;
	static java.sql.PreparedStatement pstmt;
	
	public static void main(String[] args) 
	{
		String sql = "INSERT INTO ab.mab VALUES(?,?,?)";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded sucessfully");
			
		
		    con = (Connection) DriverManager.getConnection(url,user,password);
			System.out.println("Connection Established");
			} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		} 
		
		
		try 
		{	
			String line = "";
			String path="D:\\doc\\textbook\\hy.csv";
			String [] values = null;
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			System.out.println(br.readLine());
			pstmt =con.prepareStatement(sql);
			while ((line = br.readLine()) != null ) 
			{
				values = line.split(",");
			
            	String company = values[0];
                String name = values[1];
         		String gmail = values[2];
	//String colon = "path"; 
         	//	String[] strings = colon.split(",");         	
         	
         	   //String rating = values[3];
			  //  String comments = values[4];
				
			//	System.out.println(course_name +","+ company+","+name+","+gmail);
				
				
				pstmt.setString(1, values[0]);
				pstmt.setString(2, values[1]);
				pstmt.setString(3, values[2]);
			    //  pstmt.setString(4, values[3]);
				//pstmt.setString(5, values[4]);
			    pstmt.addBatch();
			}
			int[] x =  pstmt.executeBatch();
			for (int i=0;i<x.length;i++)
			{
				System.out.println(x[i]);
			}
			br.close();
			con.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	 
	}
}
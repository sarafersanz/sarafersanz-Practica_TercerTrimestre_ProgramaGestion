package es.studium.ProgramaUniversidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDatos 
{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/programa_gestion?serverTimezone=UTC"; //programa_gestion database
	String login = "root"; //usuario database
	String password = "Studium2020;"; //contraseña database
	Connection connection = null;

	public BaseDatos()
	{

	}

	//Contectar BD
	public Connection conectar()
	{
		try
		{
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD Programa_gestion
			connection = DriverManager.getConnection(url, login, password);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return connection;
	}

	//Desconectar BD
	public void desconectar(Connection con)
	{
		try
		{
			con.close();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}


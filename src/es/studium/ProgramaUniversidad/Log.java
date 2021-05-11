package es.studium.ProgramaUniversidad;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log 
{
	//funcion de guardar
	public void guardar(String usuario, String sentencia)
	{
		//Para generar la fecha
		Date fechaActual = new Date();
		//Para darle formato a la fecha
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//FileWriter también puede lanzar una excepción
		try
		{
			// Destino de los datos
			FileWriter fw = new FileWriter("Log.log", true); // Para que no machaque
			// Buffer de escritura
			BufferedWriter bw = new BufferedWriter(fw);
			// Objeto para la escritura
			PrintWriter salida = new PrintWriter(bw);
			//Guardamos la primera línea
			salida.print("[" + formato.format(fechaActual) + "]");
			salida.print("[" + usuario + "]");
			salida.println("[" + sentencia + "]");
			//Cerrar el objeto salida, el objeto bw y el fw
			salida.close();
			bw.close();
			fw.close();
		}
		catch(IOException i)
		{
			System.out.println("Se produjo un error de Archivo");
		}
	}
}

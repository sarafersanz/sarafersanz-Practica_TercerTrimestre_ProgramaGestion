package es.studium.ProgramaUniversidad;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login implements WindowListener, ActionListener
{
	Log objLog = new Log();

	//Ventana Usuario
	Frame frmLogin = new Frame("Login");
	Label lblUsuario = new Label("Usuario: ");
	TextField txtUsuario = new TextField(20);
	Label lblClave = new Label("Clave: ");
	TextField txtClave = new TextField(20);
	Button btnAcpetarUsuario = new Button("Aceptar");
	Button btnLimpiarUsuario = new Button("Limpiar");

	//Dialogo Usuario
	Dialog dlgLogin = new Dialog(frmLogin, "Error", true);
	Label lblMendajeLogin = new Label("Credenciales incorrectas");

	//Base de datos
	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;


	//Constructor
	public Login() 

	{
		frmLogin.setLayout(new FlowLayout());
		frmLogin.add(lblUsuario);
		frmLogin.add(txtUsuario);
		frmLogin.add(lblClave);
		txtClave.setEchoChar('*');
		frmLogin.add(txtClave);
		btnAcpetarUsuario.addActionListener(this);
		frmLogin.add(btnAcpetarUsuario);
		btnLimpiarUsuario.addActionListener(this);
		frmLogin.add(btnLimpiarUsuario);

		frmLogin.setSize(280,130);
		frmLogin.addWindowListener(this);
		frmLogin.setResizable(true);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}

	public static void main(String[] args) 
	{
		new Login();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(dlgLogin.isActive())
		{
			dlgLogin.setVisible(false);
		}
		System.exit(0);


	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		//Si pulsamos el botón limpiar se borran los campos
		if(evento.getSource().equals(btnLimpiarUsuario))
		{
			txtUsuario.setText("");
			txtClave.setText("");
			txtUsuario.requestFocus();
		}
		//Si pulsamos el botón Aceptar conectamos con BD
		if(evento.getSource().equals(btnAcpetarUsuario))
		{
			// Conectar BD
			bd= new BaseDatos();
			connection = bd.conectar();
			// Buscar lo que el usuario ha escrito en los TextField
			sentencia = "SELECT * FROM usuarios WHERE nombreUsuario='"
					+txtUsuario.getText()+"' AND claveUsuario = SHA2('"
					+txtClave.getText()+"',256);";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = statement.executeQuery(sentencia);
				if(rs.next()) // Si ha encontrado algo
				{
					// Si existe en la BD, mostrar Menú Principal
					int tipo = rs.getInt("tipoUsuario");

					//metodo guardar para que nos guarde las entradas de los usuarios
					objLog.guardar(txtUsuario.getText(), "Acceso al sistema");

					//pasamos el txtusuario por parametro a la clase principal
					new ProgramaUniversidad(tipo, txtUsuario.getText());
				}
				else // Si no encuentra nada
				{
					// Si no existe en la BD, mostrar Diálogo de Error
					dlgLogin.setLayout(new FlowLayout());
					dlgLogin.add(lblMendajeLogin);
					dlgLogin.addWindowListener(this);
					dlgLogin.setSize(180,75);
					dlgLogin.setLocationRelativeTo(null);
					dlgLogin.setResizable(false);
					dlgLogin.setVisible(true);
				}
			}
			catch (SQLException sqle)
			{
			}
			frmLogin.setVisible(false);

			// Desconectar la BD
			bd.desconectar(connection);
		}

	}

}


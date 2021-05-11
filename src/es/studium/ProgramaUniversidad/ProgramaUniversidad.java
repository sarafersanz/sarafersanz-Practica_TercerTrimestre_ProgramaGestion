package es.studium.ProgramaUniversidad;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ProgramaUniversidad implements ActionListener, WindowListener
{
	//------- log-------//
	// Crear el objeto  para usar objetos de la clase Log
	Log objLog = new Log();
	//Crear string usuario que es el que vamos a usar para meter el usuario que vamos a guardar en el log
	String usuario;


	//Ventana principal
	Frame ventana = new Frame("Programa Gestión");

	Label lblMensaje = new Label();

	//-----------------------------ALUMNOS-------------------------------------//

	//Ventana Alta Alumno
	Frame frmAltaAlumno = new Frame("Alta Alumno");
	Label lblNombreAlumno = new Label("Nombre:");
	TextField txtNombreAlumno = new TextField(20);
	Label lblApellidosAlumno = new Label("Apellidos:");
	TextField txtApellidosAlumno = new TextField(20);
	Label lblDniAlumno = new Label("DNI:");
	TextField txtDniAlumno = new TextField(20);
	Label lblTelefonoAlumno = new Label("Teléfono:");
	TextField txtTelefonoAlumno= new TextField(20);
	Button btnAltaAlumno = new Button("Aceptar");
	Button btnCancelarAltaAlumno = new Button("Cancelar");

	//Dialogo confirmación Alta Alumno
	Dialog dlgConfirmacionAltaAlumno = new Dialog(frmAltaAlumno, "Alta Alumno", true);
	//Label lblMensajeAltaAlumno = new Label("Alta realizada con éxito");

	//Ventana Baja Alumno
	Frame frmBajaAlumno = new Frame("Baja Alumno");
	Label lblBajaAlumno = new Label("Elige el alumno a borrar: ");
	Choice choListaAlumnosBaja = new Choice();
	Button btnBorrarAlumno = new Button("Borrar");

	//Dialogo seguro Baja Alumno
	Dialog dlgSeguroBorrarAlumno = new Dialog(frmBajaAlumno, "Baja Alumno", true);
	Label lblSeguroBorrarAlumno = new Label ("¿Está seguro del borrado?");
	Button btnSiSeguroBorradoAlumno = new Button("Si");
	Button btnNoSeguroBorradoAlumno = new Button("No");

	//Dialogo Confirmación Baja Alumno
	Dialog dlgConfirmacionBajaAlumno = new Dialog(frmBajaAlumno, "Baja Alumno", true);
	//Label lblMensajeBajaAlumno = new Label("Eliminado");


	//Ventana Modificación Alumno
	Frame frmModificacionAlumno = new Frame("Modificación Alumno");
	Label lblModificacionAlumno = new Label("Elige el alumno a Editar: ");
	Choice choListaAlumnosEditar = new Choice();
	Button btnAceptarModificarAlumno = new Button("Editar");
	Button btnCancelarModificacionAlumno = new Button("Cancelar");

	//Ventana donde editar al Alumno
	Frame frmEditarAlumno = new Frame("Editar Alumno");
	Label lblEditarIdAlumno = new Label("Id:");
	TextField txtEditarIdAlumno = new TextField(20);
	Label lblEditarNombreAlumno = new Label("Nombre:");
	TextField txtEditarNombreAlumno = new TextField(20);
	Label lblEditarApellidosAlumno = new Label("Apellidos:");
	TextField txtEditarApellidosAlumno = new TextField(20);
	Label lblEditarDniAlumno = new Label("DNI:");
	TextField txtEditarDniAlumno = new TextField(20);
	Label lblEditarTelefonoAlumno = new Label("Teléfono:");
	TextField txtEditarTelefonoAlumno= new TextField(20);
	Button btnEditarAlumno = new Button("Aceptar");
	Button btnEditarCancelarAlumno = new Button("Cancelar");

	//Dialogo Confirmacion Modificacion Alumno
	Dialog dlgConfirmacionEdicionAlumno = new Dialog(frmModificacionAlumno, "Confirmación Alumno", true);
	//Label lblMensajeEdicionAlumno = new Label();

	//Ventana Consulta Alumno
	Frame frmConsultaAlumno = new Frame("Consulta Alumno");
	TextArea listadoConsultaAlumno = new TextArea(5,50);
	Label lblConsultaAlumno = new Label();
	Button btnPdfAlumno = new Button("Exportar PDF");
	Button btnAceptarConsultaAlumno = new Button("Aceptar");

	//Dialogo Confirmacion PDF
	Dialog dlgConfirmacionPdfAlumno = new Dialog(frmConsultaAlumno, "Confirmación Exportación", true);



	//-----------------------------PROFESORES-------------------------------------//

	//Ventana Alta Profesor
	Frame frmAltaProfesor = new Frame("Alta Profesor");
	Label lblNombreProfesor = new Label("Nombre:");
	TextField txtNombreProfesor = new TextField(20);
	Label lblApellidosProfesor = new Label("Apellidos:");
	TextField txtApellidosProfesor= new TextField(20);
	Label lblDniProfesor = new Label("DNI:");
	TextField txtDniProfesor = new TextField(20);
	Label lblTelefonoProfesor = new Label("Teléfono:");
	TextField txtTelefonoProfesor = new TextField(20);
	Label lblEspecialidadProfesor = new Label("Especialidad:");
	TextField txtEspecialidadProfesor = new TextField(20);
	Button btnAceptarAltaProfesor = new Button("Aceptar");
	Button btnCancelarAltaProfesor = new Button("Cancelar");

	//Dialogo confirmación Alta Profesor
	Dialog dlgConfirmacionAltaProfesor = new Dialog(frmAltaProfesor, "Alta Profesor", true);
	//Label lblMensajeAltaProfesor = new Label("Alta realizada con éxito");

	//Ventana Baja Profesor
	Frame frmBajaProfesor = new Frame("Baja Profesor");
	Label lblBajaProfesor = new Label("Elige el profesor a borrar: ");
	Choice choListaProfesorBaja= new Choice();
	Button btnBorrarProfesor = new Button("Borrar");

	//Dialogo seguro Baja Profesor
	Dialog dlgSeguroBorrarProfesor = new Dialog(frmBajaProfesor, "Baja Profesor", true);
	Label lblSeguroBorrarProfesor= new Label ("¿Está seguro del borrado?");
	Button btnSiSeguroBorradoProfesor = new Button("Si");
	Button btnNoSeguroBorradoProfesor = new Button("No");

	//Dialogo Confirmación Baja Profesor
	Dialog dlgConfirmacionBajaProfesor= new Dialog(frmBajaProfesor, "Baja Profesor", true);
	//Label lblMensajeBajaProfesor = new Label("Eliminado");


	//Ventana Modificación Profesor
	Frame frmModificacionProfesor = new Frame("Modificación Profesor");
	Label lblModificacionProfesor= new Label("Elige el profesor a Editar: ");
	Choice choListaProfesorEditar = new Choice();
	Button btnAceptarModificarProfesor = new Button("Editar");
	Button btnCancelarModificacionProfesor = new Button("Cancelar");

	//Ventana donde editar al Profesor
	Frame frmEditarProfesor = new Frame("Editar Profesor");
	Label lblEditarIdProfesor = new Label("Id:");
	TextField txtEditarIdProfesor = new TextField(20);
	Label lblEditarNombreProfesor= new Label("Nombre:");
	TextField txtEditarNombreProfesor= new TextField(20);
	Label lblEditarApellidosProfesor = new Label("Apellidos:");
	TextField txtEditarApellidosProfesor = new TextField(20);
	Label lblEditarDniProfesor = new Label("DNI:");
	TextField txtEditarDniProfesor = new TextField(20);
	Label lblEditarTelefonoProfesor = new Label("Teléfono:");
	TextField txtEditarTelefonoProfesor= new TextField(20);
	Label lblEditarEspecialidadProfesor = new Label("Especilidad:");
	TextField txtEditarEspecialidadProfesor= new TextField(20);
	Button btnEditarProfesor = new Button("Aceptar");
	Button btnEditarCancelarProfesor = new Button("Cancelar");

	//Dialogo Confirmacion Modificacion Profesor
	Dialog dlgConfirmacionEdicionProfesor = new Dialog(frmModificacionProfesor, "Confirmación Profesor", true);
	//Label lblMensajeEdicionProfesor = new Label();

	//Ventana Consulta Profesor
	Frame frmConsultaProfesor = new Frame("Consulta Profesor");
	TextArea listadoConsultaProfesor = new TextArea(5, 50);
	Button btnPdfProfesor = new Button("Exportar PDF");
	Button btnAceptarConsultaProfesor = new Button("Aceptar");

	//Dialogo Confirmacion PDF Profesores
	Dialog dlgConfirmacionPdfProfesor = new Dialog(frmConsultaProfesor, "Confirmación Exportación", true);

	//-----------------------------ASIGNATURAS--------------------------------------------//

	//Ventana Alta Asignatura
	Frame frmAltaAsignatura = new Frame("Alta Asignatura");
	Label lblNombreAsignatura = new Label("Asignatura:");
	TextField txtNombreAsignatura = new TextField(20);
	Label lblTurnoAsignatura = new Label("Turno:");
	TextField txtTurnoAsignatura = new TextField(20);
	Label lblCreditosAsignatura = new Label("Créditos:");
	TextField txtCreditosAsignatura = new TextField(20);
	Label lblnombreProfesor = new Label("Profesor");
	Choice choListaProfesores = new Choice();
	Button btnAceptarAltaAsignatura = new Button("Aceptar");
	Button btnCancelarAltaAsignatura = new Button("Cancelar");
	Label lblMensajeAsignatura = new Label();

	//Dialogo Confirmación Alta Asignatura
	Dialog dlgConfirmacionAltaAsignatura = new Dialog(frmAltaAsignatura, "Alta Asignatura", true);
	//Label lblMensajeAltaAsignatura = new Label("Alta realizada con éxito");

	//Ventana Baja Asignatura
	Frame frmBajaAsignatura = new Frame("Baja Asignatura");
	Label lblBajaAsignatura = new Label("Elige Asignatura a borrar: ");
	Choice choListaAsignaturaBaja= new Choice();
	Button btnBorrarAsignatura = new Button("Borrar");

	//Dialogo seguro Baja Asignatura
	Dialog dlgSeguroBorrarAsignatura = new Dialog(frmBajaAsignatura, "Baja Asignatura", true);
	Label lblSeguroBorrarAsignatura= new Label ("¿Está seguro del borrado?");
	Button btnSiSeguroBorradoAsignatura = new Button("Si");
	Button btnNoSeguroBorradoAsignatura = new Button("No");

	//Dialogo Confirmación Baja Asignatura
	Dialog dlgConfirmacionBajaAsignatura= new Dialog(frmBajaAsignatura, "Baja Asignatura", true);
	//Label lblMensajeBajaProfesor = new Label("Eliminada");

	//Ventana Modificación Asignatura
	Frame frmModificacionAsignatura = new Frame("Modificación Asignatura");
	Label lblModificacionAsignatura= new Label("Elige la Asignatura a Editar: ");
	Choice choListaAsignaturaEditar = new Choice();
	Button btnAceptarModificarAsignatura = new Button("Editar");
	Button btnCancelarModificacionAsignatura = new Button("Cancelar");

	//Ventana donde editar al Profesor
	Frame frmEditarAsignatura = new Frame("Editar Asignatura");
	Label lblEditarIdAsignatura = new Label("Id:");
	TextField txtEditarIdAsignatura = new TextField(20);
	Label lblEditarNombreAsignatura= new Label("Asignatura:");
	TextField txtEditarNombreAsignatura= new TextField(20);
	Label lblEditarTurnoAsignatura = new Label("Turno:");
	TextField txtEditarTurnoAsignatura = new TextField(20);
	Label lblEditarCreditosAsignatura = new Label("Créditos:");
	TextField txtEditarCreditosAsignatura = new TextField(20);
	Label lblChoListProfesorAsignatura = new Label("Profesor:");
	Choice choEditarProfesoresAsignatura = new Choice();
	Button btnEditarAsignatura = new Button("Aceptar");
	Button btnEditarCancelarAsignatura = new Button("Cancelar");


	//Dialogo Confirmacion Modificacion Asignatura
	Dialog dlgConfirmacionEdicionAsignatura = new Dialog(frmModificacionAsignatura, "Confirmación Asignatura", true);


	//Ventana Consulta Asignatura
	Frame frmConsultaAsignatura = new Frame("Consulta Asignatura");
	TextArea listadoConsultaAsignatura = new TextArea(5, 50);
	Button btnPdfAsignatura = new Button("Exportar PDF");
	Button btnAceptarConsultaAsignatura = new Button("Aceptar");

	//Dialogo Confirmacion PDF Asignatura
	Dialog dlgConfirmacionPdfAsignatura = new Dialog(frmConsultaAsignatura, "Confirmación Exportación", true);

	//-----------------------------MATRICULACIONES-------------------------------------//
	//Ventana Matriculacion
	Frame frmAltaMatricula = new Frame("Matriculaciones");
	Label lblAlumnoMatricula = new Label("Alumno: ");
	Label lblAsignaturaMatricula = new Label("Asignatura: ");
	Choice choListaAlumnosMatricula = new Choice();
	Choice choListaAsignaturasMatricula = new Choice();
	Button btnAceptarMatricula = new Button("Aceptar");
	Button btnCancelarMatricula = new Button("Cancelar");

	//Dialogo Confirmación Matriculación
	Dialog dlgConfirmacionMatricula = new Dialog(frmAltaMatricula, "Alta Matricula", true);
	//Label lblMensajeConfirmacionMatricula = new Label("Matricula realizada con éxito");
	//Label lblMensajeMatricula = new Label();

	//Ventana Baja Matricula
	Frame frmBajaMatricula= new Frame("Baja Matricula");
	Label lblBajaMatricula = new Label("Elige Nº de Matricula a borrar: ");
	Choice choListaMatriculaBaja= new Choice();
	Button btnBorrarMatricula = new Button("Borrar");

	//Dialogo seguro Baja Matricula
	Dialog dlgSeguroBorrarMatricula = new Dialog(frmBajaMatricula, "Baja Matricula", true);
	Label lblSeguroBorrarMatricula= new Label ("¿Está seguro del borrado?");
	Button btnSiSeguroBorradoMatricula = new Button("Si");
	Button btnNoSeguroBorradoMatricula = new Button("No");

	//Dialogo Confirmación Baja Asignatura
	Dialog dlgConfirmacionBajaMatricula= new Dialog(frmBajaMatricula, "Baja Matricula", true);
	//Label lblMensajeBajaProfesor = new Label("Eliminada");

	//Ventana Modificación Matricula
	Frame frmModificacionMatricula = new Frame("Modificación Matricula");
	Label lblModificacionMatricula= new Label("Elige la Matricula a editar: ");
	Choice choListaMatriculaEditar = new Choice();
	Button btnAceptarModificarMatricula = new Button("Editar");
	Button btnCancelarModificacionMatricula = new Button("Cancelar");

	//Ventana donde editar la Matricula
	Frame frmEditarMatricula= new Frame("Modificación Matricula");
	Label lblEditarMatricula= new Label("Matricula: ");
	TextField txtEditarIdMatricula = new TextField(10);
	Label lblListaAlumnoMatriculaEditar = new Label("Alumno:");
	Choice choListaAlumnoMatriculaEditar = new Choice();
	Label lblListaAsignaturaMatriculaEditar = new Label("Asignatura:");
	Choice choListaAsignaturaMatriculaEditar = new Choice();
	Button btnAceptarEditarMatricula = new Button("Editar");
	Button btnCancelarEditarMatricula= new Button("Cancelar");

	//Dialogo Confirmacion Modificacion Matricula
	Dialog dlgConfirmacionEdicionMatricula = new Dialog(frmModificacionMatricula, "Confirmación Matricula", true);

	//Ventana consulta Matricula
	Frame frmConsultaMatricula = new Frame("Consulta Matricula");
	TextArea listadoConsultaMatricula = new TextArea(5, 50);
	Button btnPdfMatricula = new Button("Exportar PDF");
	Button btnAceptarConsultaMatricula = new Button("Aceptar");


	//Dialogo Confirmacion PDF Matricula
	Dialog dlgConfirmacionPdfMatricula = new Dialog(frmConsultaMatricula, "Confirmación Exportación", true);


	//-----------------------------MENÚ PRINCIPAL--------------------------------------------//

	//Menú Principal
	MenuBar mnBar = new MenuBar();

	//Menú ALumnos
	Menu mnuAlumnos = new Menu("Alumnos");
	MenuItem mniAltaAlumno = new MenuItem("Alta");
	MenuItem mniBajaAlumno = new MenuItem("Baja");
	MenuItem mniModificacionAlumno = new MenuItem("Modificación");
	MenuItem mniConsultaAlumno = new MenuItem("Consulta");

	//Menú Profesores
	Menu mnuProfesores = new Menu("Profesores");
	MenuItem mniAltaProfesor = new MenuItem("Alta");
	MenuItem mniBajaProfesor = new MenuItem("Baja");
	MenuItem mniModificacionProfesor = new MenuItem("Modificación");
	MenuItem mniConsultaProfesor = new MenuItem("Consulta");

	//Menú Asignaturas
	Menu mnuAsignaturas = new Menu("Asignaturas");
	MenuItem mniAltaAsignatura = new MenuItem("Alta");
	MenuItem mniConsultaAsignatura = new MenuItem("Consulta");
	MenuItem mniModificacionAsignatura = new MenuItem("Modificación");
	MenuItem mniBajaAsignatura= new MenuItem("Baja");

	//Menú Matriculaciones
	Menu mnuMatriculaciones = new Menu("Matriculaciones");
	MenuItem mniAltaMatriculacion = new MenuItem("Alta");
	MenuItem mniConsultaMatriculacion= new MenuItem("Consulta");
	MenuItem mniModificacionMatricula = new MenuItem("Modificación");
	MenuItem mniBajaMatricula = new MenuItem("Baja");

	//Menú Ayuda
	Menu mnuAyuda = new Menu("Ayuda");
	MenuItem mniAyuda = new MenuItem("Ver Ayuda");

	//-----------------------------BASE DE DATOS--------------------------------------------//

	//Base de datos
	BaseDatos bd;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	//-------------------------------------------------------------------------------------//


	//Constructor de la clase
	//le pasamos el tipo y el string donde estará metido el usuario
	public ProgramaUniversidad(int tipo, String u)
	{
		this.usuario = u;

		ventana.setLayout(new FlowLayout());

		//Establecer la barra de menú
		ventana.setMenuBar(mnBar);

		//Añadir el menu Alumnos a la barra de menú
		mnBar.add(mnuAlumnos);

		//Añadir ActionListener a los submenús
		mniAltaAlumno.addActionListener(this);
		mniBajaAlumno.addActionListener(this);
		mniModificacionAlumno.addActionListener(this);
		mniConsultaAlumno.addActionListener(this);

		//Crear menú Alumnos añadiendo los submenús
		mnuAlumnos.add(mniAltaAlumno);
		if(tipo==0) // ¿Es administrador?
		{
			mnuAlumnos.add(mniBajaAlumno);
			mnuAlumnos.add(mniModificacionAlumno);
			mnuAlumnos.add(mniConsultaAlumno);
		}
		//Añadir el menu Profesores a la barra de menú
		mnBar.add(mnuProfesores);

		//Añadir actionListener a los submenús
		mniAltaProfesor.addActionListener(this);
		mniBajaProfesor.addActionListener(this);
		mniModificacionProfesor.addActionListener(this);
		mniConsultaProfesor.addActionListener(this);

		//Crear menu profesor añadiendo los submenús
		mnuProfesores.add(mniAltaProfesor);
		if(tipo==0) // ¿Es administrador?
		{
			mnuProfesores.add(mniBajaProfesor);
			mnuProfesores.add(mniModificacionProfesor);
			mnuProfesores.add(mniConsultaProfesor);
		}


		//Añadir el menu Asignaturas a la barra de menú
		mnBar.add(mnuAsignaturas);

		//Añadir Listener a los submenús
		mniAltaAsignatura.addActionListener(this);
		mniBajaAsignatura.addActionListener(this);
		mniModificacionAsignatura.addActionListener(this);
		mniConsultaAsignatura.addActionListener(this);

		//Crear menu Asignaturas añadiendo los submenús
		mnuAsignaturas.add(mniAltaAsignatura);
		if(tipo==0) // ¿Es administrador?
		{
			mnuAsignaturas.add(mniBajaAsignatura);
			mnuAsignaturas.add(mniModificacionAsignatura);
			mnuAsignaturas.add(mniConsultaAsignatura);
		}
		//Añadir el menu Matriculaciones a la barra de menú
		mnBar.add(mnuMatriculaciones);

		//Añadir Listener a los submenús
		mniAltaMatriculacion.addActionListener(this);
		mniBajaMatricula.addActionListener(this);
		mniModificacionMatricula.addActionListener(this);
		mniConsultaMatriculacion.addActionListener(this);


		//Crear menu Matriculaciones añadiendo los submenús
		mnuMatriculaciones.add(mniAltaMatriculacion);
		if(tipo==0) // ¿Es administrador?
		{
			mnuMatriculaciones.add(mniBajaMatricula);
			mnuMatriculaciones.add(mniModificacionMatricula);
			mnuMatriculaciones.add(mniConsultaMatriculacion);
		}
		//Añadimos menú ayuda a la barra menú
		mnBar.add(mnuAyuda);

		//Añadir submenú al menú Ayuda
		mnuAyuda.add(mniAyuda);

		// Añadir Listener al submenú
		mniAyuda.addActionListener(this);

		ventana.setSize(400,160);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.addWindowListener(this);
		ventana.setVisible(true);

	}

	@Override
	public void windowActivated(WindowEvent arg0) {}


	@Override
	public void windowClosed(WindowEvent arg0) {}


	@Override
	public void windowClosing(WindowEvent arg0)
	{

		//-----------------------------ALUMNOS-------------------------------------//

		//Cerrar ventana de Alta Alumno
		if(frmAltaAlumno.isActive())
		{
			frmAltaAlumno.setVisible(false);
		}
		//Cerrar dialgo Confirmacion Alta Alumno
		else if(dlgConfirmacionAltaAlumno.isActive())
		{

			//Para que los campos aparezcan en blanco y tabulador en el primer campo
			txtNombreAlumno.setText("");
			txtApellidosAlumno.setText("");
			txtDniAlumno.setText("");
			txtTelefonoAlumno.setText("");
			txtNombreAlumno.requestFocus();
			//btnAltaAlumno.removeActionListener(this);

			dlgConfirmacionAltaAlumno.setVisible(false);
		}
		//Cerrar ventana de Baja Alumno
		else if(frmBajaAlumno.isActive())
		{

			frmBajaAlumno.setVisible(false);
		}
		//Cerrar Dialogo de ¿Seguro borrar alumno?
		else if(dlgSeguroBorrarAlumno.isActive())
		{	

			dlgSeguroBorrarAlumno.setVisible(false);
		}
		//Cerra Dialogo Confirmacion del borrado Alumno

		else if(dlgConfirmacionBajaAlumno.isActive())
		{
			btnSiSeguroBorradoAlumno.removeActionListener(this);
			btnNoSeguroBorradoAlumno.removeActionListener(this);
			btnBorrarAlumno.removeActionListener(this);
			dlgConfirmacionBajaAlumno.setVisible(false);
			dlgSeguroBorrarAlumno.setVisible(false);
			frmBajaAlumno.setVisible(false);

		}
		//Cerrar ventana Modificiacion Alumno
		else if(frmModificacionAlumno.isActive())
		{

			frmModificacionAlumno.setVisible(false);
		}
		//Cerrar ventana Edición alumno
		else if(frmEditarAlumno.isActive())
		{
			frmEditarAlumno.setVisible(false);
		}
		//Cerrar dialogo Confirmacion Edición alumno
		else if(dlgConfirmacionEdicionAlumno.isActive())
		{
			btnAceptarModificarAlumno.removeActionListener(this);
			btnCancelarModificacionAlumno.removeActionListener(this);
			btnEditarAlumno.removeActionListener(this);
			btnEditarCancelarAlumno.removeActionListener(this);
			dlgConfirmacionEdicionAlumno.setVisible(false);
			frmEditarAlumno.setVisible(false);
			frmModificacionAlumno.setVisible(false);
		}

		//Cerrar ventana Consulta Alumno
		else if(frmConsultaAlumno.isActive())
		{
			frmConsultaAlumno.setVisible(false);
		}
		//Cerrar dialogo confirmación PDF
		else if(dlgConfirmacionPdfAlumno.isActive())
		{
			dlgConfirmacionPdfAlumno.setVisible(false);
		}


		//-----------------------------PROFESORES-------------------------------------//


		//Cerrar ventana de Alta Profesor
		else if(frmAltaProfesor.isActive())
		{
			frmAltaProfesor.setVisible(false);
		}
		//Cerrar dialgo Confirmacion Alta Profesor
		else if(dlgConfirmacionAltaProfesor.isActive())
		{
			//Para que los campos aparezcan en blanco y tabulador en el primer campo
			txtNombreProfesor.setText("");
			txtApellidosProfesor.setText("");
			txtDniProfesor.setText("");
			txtTelefonoProfesor.setText("");
			txtEspecialidadProfesor.setText("");
			txtNombreProfesor.requestFocus();

			dlgConfirmacionAltaProfesor.setVisible(false);
		}
		//Cerrar ventana de baja Profesor
		else if(frmBajaProfesor.isActive())
		{
			frmBajaProfesor.setVisible(false);
		}
		//Cerrar Dialogo de ¿Seguro borrar Profesor?
		else if(dlgSeguroBorrarProfesor.isActive())
		{

			dlgSeguroBorrarProfesor.setVisible(false);
		}
		//Cerrar dialogo confirmación del borrado Profesor

		else if(dlgConfirmacionBajaProfesor.isActive())
		{
			btnSiSeguroBorradoProfesor.removeActionListener(this);
			btnNoSeguroBorradoProfesor.removeActionListener(this);
			btnBorrarProfesor.removeActionListener(this);
			dlgConfirmacionBajaProfesor.setVisible(false);
			dlgSeguroBorrarProfesor.setVisible(false);
			frmBajaProfesor.setVisible(false);
		}
		//cerrar ventana de modificiación profesor
		else if(frmModificacionProfesor.isActive())
		{
			btnAceptarModificarProfesor.removeActionListener(this);
			btnCancelarModificacionProfesor.removeActionListener(this);
			frmModificacionProfesor.setVisible(false);
		}
		//cerrar ventana de edición profesor
		else if(frmEditarProfesor.isActive())
		{
			frmEditarProfesor.setVisible(false);
		}
		//cerrar dialogo confirmación edición profesor
		else if(dlgConfirmacionEdicionProfesor.isActive())
		{
			btnAceptarModificarProfesor.removeActionListener(this);
			btnCancelarModificacionProfesor.removeActionListener(this);
			btnEditarProfesor.removeActionListener(this);
			dlgConfirmacionEdicionProfesor.setVisible(false);
			frmEditarProfesor.setVisible(false);
			frmModificacionProfesor.setVisible(false);

		}
		//cerrar ventena de consulta profesor
		else if(frmConsultaProfesor.isActive())
		{
			frmConsultaProfesor.setVisible(false);
		}
		//Cerrar dialogo confirmación PDF
		else if(dlgConfirmacionPdfProfesor.isActive())
		{
			dlgConfirmacionPdfProfesor.setVisible(false);
		}


		//-----------------------------ASIGNATURAS-------------------------------------//


		//Cerrar ventana Alta Asignatura
		else if(frmAltaAsignatura.isActive())
		{
			//Para que los campos aparezcan en blanco y tabulador en el primer campo

			btnAceptarAltaAsignatura.removeActionListener(this);
			btnCancelarAltaAsignatura.removeActionListener(this);
			frmAltaAsignatura.setVisible(false);
		}
		//Cerrar dialogo Confirmación Alta Asignatura
		else if(dlgConfirmacionAltaAsignatura.isActive())
		{
			txtNombreAsignatura.setText("");
			txtTurnoAsignatura.setText("");
			txtCreditosAsignatura.setText("");
			txtNombreAsignatura.requestFocus();

			dlgConfirmacionAltaAsignatura.setVisible(false);
		}
		//Cerrar ventana de baja Asignatura
		else if(frmBajaAsignatura.isActive())
		{
			frmBajaAsignatura.setVisible(false);
		}
		//Cerrar Dialogo de ¿Seguro borrar Asignatura?
		else if(dlgSeguroBorrarAsignatura.isActive())
		{

			dlgSeguroBorrarAsignatura.setVisible(false);
		}
		//Cerrar dialogo confirmación del borrado Asignatura

		else if(dlgConfirmacionBajaAsignatura.isActive())
		{
			btnSiSeguroBorradoAsignatura.removeActionListener(this);
			btnNoSeguroBorradoAsignatura.removeActionListener(this);
			btnBorrarAsignatura.removeActionListener(this);
			dlgConfirmacionBajaAsignatura.setVisible(false);
			dlgSeguroBorrarAsignatura.setVisible(false);
			frmBajaAsignatura.setVisible(false);
		}
		//cerrar ventana de modificiación Asignatura
		else if(frmModificacionAsignatura.isActive())
		{
			btnAceptarModificarAsignatura.removeActionListener(this);
			btnCancelarModificacionAsignatura.removeActionListener(this);
			frmModificacionAsignatura.setVisible(false);
		}
		//cerrar ventana de edición Asignatura
		else if(frmEditarAsignatura.isActive())
		{
			frmEditarAsignatura.setVisible(false);
		}
		//cerrar dialogo confirmación edición Asignatura
		else if(dlgConfirmacionEdicionAsignatura.isActive())
		{
			btnAceptarModificarAsignatura.removeActionListener(this);
			btnCancelarModificacionAsignatura.removeActionListener(this);
			btnEditarAsignatura.removeActionListener(this);
			dlgConfirmacionEdicionAsignatura.setVisible(false);
			frmEditarAsignatura.setVisible(false);
			frmModificacionAsignatura.setVisible(false);
		}

		//Cerrar ventana Consutla Asignaturas
		else if(frmConsultaAsignatura.isActive())
		{
			frmConsultaAsignatura.setVisible(false);	
		}
		//Cerrar dialogo Confirmacion PDF
		else if(dlgConfirmacionPdfAsignatura.isActive())
		{
			dlgConfirmacionPdfAsignatura.setVisible(false);
		}


		//-----------------------------MATRICULACIONES-------------------------------------//


		//Cerrar ventana Alta Matriculaciones
		else if(frmAltaMatricula.isActive())
		{
			frmAltaMatricula.setVisible(false);
		}
		//Cerrar dialogo Confirmación Alta Matriculaciones
		else if(dlgConfirmacionMatricula.isActive())
		{
			btnAceptarMatricula.removeActionListener(this);
			btnCancelarMatricula.removeActionListener(this);
			btnAceptarConsultaMatricula.removeActionListener(this);
			dlgConfirmacionMatricula.setVisible(false);
			frmAltaMatricula.setVisible(false);
		}
		//Cerrar ventana de baja Matricula
		else if(frmBajaMatricula.isActive())
		{
			frmBajaMatricula.setVisible(false);
		}
		//Cerrar Dialogo de ¿Seguro borrar Matricula?
		else if(dlgSeguroBorrarMatricula.isActive())
		{

			dlgSeguroBorrarMatricula.setVisible(false);
		}
		//Cerrar dialogo confirmación del borrado Asignatura

		else if(dlgConfirmacionBajaMatricula.isActive())
		{
			btnSiSeguroBorradoMatricula.removeActionListener(this);
			btnNoSeguroBorradoMatricula.removeActionListener(this);
			btnBorrarMatricula.removeActionListener(this);
			dlgConfirmacionBajaMatricula.setVisible(false);
			dlgSeguroBorrarMatricula.setVisible(false);
			frmBajaMatricula.setVisible(false);
		}
		//cerrar ventana de modificiación Matricula
		else if(frmModificacionMatricula.isActive())
		{
			btnAceptarModificarMatricula.removeActionListener(this);
			btnCancelarModificacionMatricula.removeActionListener(this);
			frmModificacionMatricula.setVisible(false);
		}
		//cerrar ventana de edición Matricula
		else if(frmEditarMatricula.isActive())
		{
			frmEditarMatricula.setVisible(false);
		}
		//cerrar dialogo confirmación edición Asignatura
		else if(dlgConfirmacionEdicionMatricula.isActive())
		{
			btnAceptarModificarMatricula.removeActionListener(this);
			btnCancelarModificacionMatricula.removeActionListener(this);
			btnAceptarEditarMatricula.removeActionListener(this);
			dlgConfirmacionEdicionMatricula.setVisible(false);
			frmEditarMatricula.setVisible(false);
			frmModificacionMatricula.setVisible(false);
		}
		//Cerrar ventana Consulta Matriculaciones
		else if(frmConsultaMatricula.isActive())
		{
			frmConsultaMatricula.setVisible(false);
		}
		else if (dlgConfirmacionMatricula.isActive())
		{
			frmConsultaMatricula.setVisible(false);
		}
		//Cerrar ventana princial
		else
		{
			System.exit(0);

		}

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
	public void actionPerformed(ActionEvent eventoBoton) 
	{
		//-----------------------------ALUMNO-------------------------------------//

		//Si pulsamos botón de alta dentro del menu alumno aparece la ventana de Alta Alumno
		if(eventoBoton.getSource().equals(mniAltaAlumno))
		{
			frmAltaAlumno.setLayout(new FlowLayout());
			frmAltaAlumno.add(lblNombreAlumno);
			frmAltaAlumno.add(txtNombreAlumno);
			frmAltaAlumno.add(lblApellidosAlumno);
			frmAltaAlumno.add(txtApellidosAlumno);
			frmAltaAlumno.add(lblDniAlumno);
			frmAltaAlumno.add(txtDniAlumno);
			frmAltaAlumno.add(lblTelefonoAlumno);
			frmAltaAlumno.add(txtTelefonoAlumno);
			btnAltaAlumno.removeActionListener(this); //Solución para doble actionListener al pulsar el botón AceptarAlta
			btnAltaAlumno.addActionListener(this);
			frmAltaAlumno.add(btnAltaAlumno);
			btnCancelarAltaAlumno.addActionListener(this);
			frmAltaAlumno.add(btnCancelarAltaAlumno);

			frmAltaAlumno.setSize(280,190);
			frmAltaAlumno.setResizable(false);
			frmAltaAlumno.setLocationRelativeTo(null);

			frmAltaAlumno.addWindowListener(this);
			frmAltaAlumno.setVisible(true);

		}
		//Si pulsamos  botón aceptar conectamos con la base de datos y damos de alta al alumno
		else if(eventoBoton.getSource().equals(btnAltaAlumno))
		{
			bd = new BaseDatos();
			connection = bd.conectar();
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Si algún campo no se rellena aparece mensaje "Faltan datos"
				if(((txtNombreAlumno.getText().length())!= 0)
						&&((txtApellidosAlumno.getText().length())!=0)&&((txtDniAlumno.getText().length())!=0)&&((txtTelefonoAlumno.getText().length())!=0))
				{
					//Ejecutar la sentencia SQL
					sentencia = "INSERT INTO alumnos VALUES (null, '"
							+txtNombreAlumno.getText()+"', '"+txtApellidosAlumno.getText()+"', '"+txtDniAlumno.getText()+"', '"+txtTelefonoAlumno.getText()+"')";
					// le pasamos la funcion guardar para el log
					objLog.guardar(usuario, sentencia);
					statement.executeUpdate(sentencia);
					lblMensaje.setText("Alta realizada con éxito");
				}
				else
				{	
					lblMensaje.setText("Faltan datos");
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en ALTA");
			}
			finally
			{
				//Diálogo de Confirmación de Alta Alumno
				dlgConfirmacionAltaAlumno.setLayout(new FlowLayout());
				dlgConfirmacionAltaAlumno.setSize(170,120);
				dlgConfirmacionAltaAlumno.setResizable(false);
				dlgConfirmacionAltaAlumno.setLocationRelativeTo(null);
				dlgConfirmacionAltaAlumno.addWindowListener(this);
				dlgConfirmacionAltaAlumno.add(lblMensaje);
				dlgConfirmacionAltaAlumno.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}

		}
		//Si pulsamos botón cancelar volvemos al menú principal
		else if (eventoBoton.getSource().equals(btnCancelarAltaAlumno))
		{
			frmAltaAlumno.setVisible(false);
		}
		//Si pulsamos el boton Baja en el menú alumno aparece ventana de Baja Alumno
		else if(eventoBoton.getSource().equals(mniBajaAlumno))
		{
			frmBajaAlumno.setLayout(new FlowLayout());
			frmBajaAlumno.add(lblBajaAlumno);
			//Rellenar el choice
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM alumnos";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaAlumnosBaja.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAlumnosBaja.add(rs.getInt("idAlumno") 
							+ "-" + rs.getString("nombreAlumno")
							+ "-" + rs.getString("apellidosAlumno")
							+ "-" + rs.getString("dniAlumno"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en la Baja");
			}
			frmBajaAlumno.add(choListaAlumnosBaja);
			btnBorrarAlumno.addActionListener(this);
			frmBajaAlumno.add(btnBorrarAlumno);

			frmBajaAlumno.setSize(280,140);
			frmBajaAlumno.setResizable(false);
			frmBajaAlumno.setLocationRelativeTo(null);
			frmBajaAlumno.addWindowListener(this);
			frmBajaAlumno.setVisible(true);	

			//Desconectar con la BD
			bd.desconectar(connection);

		}
		//Si pulsamos botón Borrar nos aparece dialogo ¿Seguro?
		else if(eventoBoton.getSource().equals(btnBorrarAlumno))
		{
			dlgSeguroBorrarAlumno.setLayout(new FlowLayout());
			dlgSeguroBorrarAlumno.setSize(180,120);
			dlgSeguroBorrarAlumno.setResizable(false);
			dlgSeguroBorrarAlumno.setLocationRelativeTo(null);
			dlgSeguroBorrarAlumno.addWindowListener(this);
			dlgSeguroBorrarAlumno.add(lblSeguroBorrarAlumno);
			btnSiSeguroBorradoAlumno.addActionListener(this);
			dlgSeguroBorrarAlumno.add(btnSiSeguroBorradoAlumno);
			btnNoSeguroBorradoAlumno.addActionListener(this);
			dlgSeguroBorrarAlumno.add(btnNoSeguroBorradoAlumno);
			dlgSeguroBorrarAlumno.setVisible(true);
		}
		//Si pulsamos botón No, en el dialogo Seguro se cierra
		else if (eventoBoton.getSource().equals(btnNoSeguroBorradoAlumno))
		{
			dlgSeguroBorrarAlumno.setVisible(false);
		}
		//Si pulsamos Si, en el dialogo Seguro conectamos con base de datos 
		//y ejecutamos sentencia
		else if (eventoBoton.getSource().equals(btnSiSeguroBorradoAlumno))
		{
			bd = new BaseDatos();
			connection = bd.conectar();
			//En elegido[0]--> id
			//En elegido[1]--> nombre
			//En elegido[2]--> apellidos
			//...
			String[] elegido = choListaAlumnosBaja.getSelectedItem().split("-");

			//Hacer un DELETE FROM alumnos WHERE idAlumno = x
			sentencia = "DELETE FROM alumnos WHERE idAlumno = "+ elegido[0];
			try
			{
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);
				lblMensaje.setText("Alumno Eliminado");
				dlgSeguroBorrarAlumno.setVisible(false);
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en Baja");
			}
			finally
			{
				//Dialogo confirmacion baja alumno
				dlgConfirmacionBajaAlumno.setLayout(new FlowLayout());
				dlgConfirmacionBajaAlumno.setSize(180,120);
				dlgConfirmacionBajaAlumno.setResizable(false);
				dlgConfirmacionBajaAlumno.setLocationRelativeTo(null);
				dlgConfirmacionBajaAlumno.addWindowListener(this);
				dlgConfirmacionBajaAlumno.add(lblMensaje);
				dlgConfirmacionBajaAlumno.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}
		}
		//Si pulsamos boton modificación del menu aparece ventana Modificiación Alumno
		else if(eventoBoton.getSource().equals(mniModificacionAlumno))
		{
			frmModificacionAlumno.setLayout(new FlowLayout ());

			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM alumnos";
			try
			{
				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs= statement.executeQuery(sentencia);
				choListaAlumnosEditar.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAlumnosEditar.add(rs.getInt("idAlumno") 
							+ "-" + rs.getString("nombreAlumno")
							+ "-" + rs.getString("apellidosAlumno")
							+ "-" + rs.getString("dniAlumno")
							+ "-" + rs.getInt("telefonoAlumno"));
				}

			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en Modificación");
			}
			//Desconectar con la BD
			bd.desconectar(connection);

			frmModificacionAlumno.add(lblModificacionAlumno);
			frmModificacionAlumno.add(choListaAlumnosEditar);
			btnAceptarModificarAlumno.addActionListener(this);
			frmModificacionAlumno.add(btnAceptarModificarAlumno);
			btnCancelarModificacionAlumno.addActionListener(this);
			frmModificacionAlumno.add(btnCancelarModificacionAlumno);

			frmModificacionAlumno.setSize(350,140);
			frmModificacionAlumno.setResizable(false);
			frmModificacionAlumno.setLocationRelativeTo(null);
			frmModificacionAlumno.addWindowListener(this);
			frmModificacionAlumno.setVisible(true);


		}
		//Si pulsamos el boton cancelar en la modificacion se cierra la ventana
		else if (eventoBoton.getSource().equals(btnCancelarModificacionAlumno))
		{
			frmModificacionAlumno.setVisible(false);
		}
		//Si el botón aceptar bajo el choice es pulsado aparece la ventana de editar alumno 
		//con los datos del alumno seleccionado
		else if (eventoBoton.getSource().equals(btnAceptarModificarAlumno))
		{

			String[] elegido = choListaAlumnosEditar.getSelectedItem().split("-");

			frmEditarAlumno.setLayout(new FlowLayout());
			frmEditarAlumno.add(lblEditarIdAlumno);
			frmEditarAlumno.add(txtEditarIdAlumno);
			txtEditarIdAlumno.setText(elegido[0]);
			txtEditarIdAlumno.setEnabled(false);
			frmEditarAlumno.add(lblEditarNombreAlumno);
			frmEditarAlumno.add(txtEditarNombreAlumno);
			txtEditarNombreAlumno.setText(elegido[1]);
			frmEditarAlumno.add(lblEditarApellidosAlumno);
			frmEditarAlumno.add(txtEditarApellidosAlumno);
			txtEditarApellidosAlumno.setText(elegido[2]);
			frmEditarAlumno.add(lblEditarDniAlumno);
			frmEditarAlumno.add(txtEditarDniAlumno);
			txtEditarDniAlumno.setText(elegido[3]);
			frmEditarAlumno.add(lblEditarTelefonoAlumno);
			frmEditarAlumno.add(txtEditarTelefonoAlumno);
			txtEditarTelefonoAlumno.setText(elegido[4]);
			btnEditarAlumno.addActionListener(this);
			frmEditarAlumno.add(btnEditarAlumno);
			btnEditarCancelarAlumno.addActionListener(this);
			frmEditarAlumno.add(btnEditarCancelarAlumno);

			frmEditarAlumno.setSize(280,220);
			frmEditarAlumno.setResizable(false);
			frmEditarAlumno.setLocationRelativeTo(null);
			frmEditarAlumno.addWindowListener(this);
			frmEditarAlumno.setVisible(true);

		}
		//Si puslamos el botón cancelar en la ventana Edición Alumno la ventana se cierra
		else if(eventoBoton.getSource().equals(btnEditarCancelarAlumno))
		{
			frmEditarAlumno.setVisible(false);
		}
		//Si el botón editar alumno es puslado conectamos y se ejecuta sentencia
		else if(eventoBoton.getSource().equals(btnEditarAlumno))
		{
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			String nombreAlumnoModificado = txtEditarNombreAlumno.getText();
			String apellidosAlumnoModificado = txtEditarApellidosAlumno.getText();
			String dniAlumnoModificado = txtEditarDniAlumno.getText();
			String telefonoAlumnoModificado = txtEditarTelefonoAlumno.getText();
			//Crear sentencia
			sentencia = ("UPDATE alumnos SET nombreAlumno = '"+ nombreAlumnoModificado  
					+ "' , apellidosAlumno = '"+ apellidosAlumnoModificado 
					+ "' , dniAlumno = '"+ dniAlumnoModificado 
					+ "' , telefonoAlumno = '"+ telefonoAlumnoModificado +
					"' WHERE idAlumno ="+txtEditarIdAlumno.getText());

			try
			{

				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);

				lblMensaje.setText("Modificación realizada con éxito");

			}
			catch(SQLException sqle)
			{
				lblMensaje.setText("Error en la Modificación");
			}
			finally
			{
				//Dialogo confirmación de edición
				dlgConfirmacionEdicionAlumno.setLayout(new FlowLayout());
				dlgConfirmacionEdicionAlumno.setSize(220,120);
				dlgConfirmacionEdicionAlumno.setResizable(false);
				dlgConfirmacionEdicionAlumno.setLocationRelativeTo(null);
				dlgConfirmacionEdicionAlumno.addWindowListener(this);
				dlgConfirmacionEdicionAlumno.add(lblMensaje);
				dlgConfirmacionEdicionAlumno.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}


		}

		//Si el botón del menú Consulta en el apartado Alumno es pulsado
		//se abre la ventana de consulta
		else if (eventoBoton.getSource().equals(mniConsultaAlumno))
		{
			frmConsultaAlumno.setLayout(new FlowLayout());
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM alumnos";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				listadoConsultaAlumno.setText("");
				listadoConsultaAlumno.append("Nombre\tApellidos\tDni\tTeléfono\n");
				while(rs.next())
				{
					listadoConsultaAlumno.append(rs.getString("nombreAlumno")
							+ "\t"+rs.getString("apellidosAlumno")
							+ "\t"+rs.getString("DniAlumno")
							+ "\t"+rs.getString("telefonoAlumno")
							+"\n");
				}

			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}

			//Desconectar con la BD
			bd.desconectar(connection);

			frmConsultaAlumno.add(listadoConsultaAlumno);
			frmConsultaAlumno.add(btnPdfAlumno);
			btnPdfAlumno.addActionListener(this);
			btnAceptarConsultaAlumno.addActionListener(this);
			frmConsultaAlumno.add(btnAceptarConsultaAlumno);
			frmConsultaAlumno.setSize(400,200);
			frmConsultaAlumno.setResizable(false);
			frmConsultaAlumno.setLocationRelativeTo(null);
			frmConsultaAlumno.addWindowListener(this);
			frmConsultaAlumno.setVisible(true);


		}
		//Si se pulsa el botón Aceptar la ventana se cierra
		else if (eventoBoton.getSource().equals(btnAceptarConsultaAlumno))
		{
			frmConsultaAlumno.setVisible(false);
		}
		// Exportación a PDF Alumnos
		else if (eventoBoton.getSource().equals(btnPdfAlumno))
		{

			try
			{
				// Creamos el documento
				Document docPdfAlumnos = new Document();
				try 
				{
					// Fichero donde se deja el documento
					FileOutputStream ficheroDocAlumnos = new FileOutputStream("docAlumnos.pdf");
					// Se asocia el documento y se indica el espacio entre lineas
					PdfWriter.getInstance(docPdfAlumnos, ficheroDocAlumnos).setInitialLeading(20);
				}
				catch  (FileNotFoundException fileNotFoundException)
				{
					JOptionPane.showMessageDialog(null, "No se encontró el fichero para generar el pdf." 
							+ fileNotFoundException,"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				// Se abre el documento
				docPdfAlumnos.open();

				// Imagen
				//Añadir la imagen con sus características
				Image imagen;
				try 
				{
					imagen = Image.getInstance("logo.jpg");
					imagen.scaleToFit(100,100);
					imagen.setAlignment(Chunk.ALIGN_LEFT);
					docPdfAlumnos.add(imagen);
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				//Desconectar con la BD
				bd.desconectar(connection);

				// Encabezado
				Paragraph encabezado = new Paragraph(" Práctica: Programa Gestión - Universidad", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color
				encabezado.setAlignment(Element.ALIGN_LEFT);


				// Salto de línea
				Paragraph saltoDeLinea = new Paragraph();
				saltoDeLinea.add("\n\n");


				// Titulo
				Paragraph titulo = new Paragraph(" Listado Alumnos", 
						FontFactory.getFont("arial", // fuente
								20, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.BLUE)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);


				// Pie de Página
				Paragraph piePagina = new Paragraph("Sara Fernández - 1º DAW", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color)
				piePagina.setAlignment(Element.ALIGN_RIGHT);


				// Crear la tabla
				PdfPTable tabla = new PdfPTable(4);

				//Rellenar las cabeceras de las columnas
				Paragraph columna1 = new Paragraph("NOMBRE");
				columna1.getFont().setStyle(Font.BOLD);
				columna1.getFont().setSize(12);
				tabla.addCell(columna1);

				Paragraph columna2 = new Paragraph("APELLIDOS");
				columna2.getFont().setStyle(Font.BOLD);
				columna2.getFont().setSize(12);
				tabla.addCell(columna2);

				Paragraph columna3 = new Paragraph("DNI");
				columna3.getFont().setStyle(Font.BOLD);
				columna3.getFont().setSize(12);
				tabla.addCell(columna3);

				Paragraph columna4 = new Paragraph("TELÉFONO");
				columna4.getFont().setStyle(Font.BOLD);
				columna4.getFont().setSize(12);
				tabla.addCell(columna4);

				//Rellenar las filas de la tabla.
				//Conectar
				bd = new BaseDatos();
				connection = bd.conectar();
				sentencia = "SELECT * FROM alumnos";
				try
				{
					//Crear una sentencia
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

					//Crear un objeto ResultSet para guardar lo obtenido
					//y ejecutar la sentencia SQL
					rs= statement.executeQuery(sentencia);
					while(rs.next())
					{
						tabla.addCell(rs.getString("nombreAlumno"));
						tabla.addCell(rs.getString("apellidosAlumno"));
						tabla.addCell(rs.getString("DniAlumno"));
						tabla.addCell(rs.getString("telefonoAlumno"));
					}
				}
				catch (SQLException sqle)
				{
					lblMensaje.setText("Error");
				}

				// Añadir los componente creados
				docPdfAlumnos.add(encabezado);
				docPdfAlumnos.add(saltoDeLinea);
				docPdfAlumnos.add(titulo);
				docPdfAlumnos.add(saltoDeLinea);
				docPdfAlumnos.add(tabla);
				docPdfAlumnos.add(saltoDeLinea);
				docPdfAlumnos.add(piePagina);

				//Cerrar el documento
				docPdfAlumnos.close();

			}
			catch (DocumentException e)
			{

				e.printStackTrace();
			}

			dlgConfirmacionPdfAlumno.setLayout(new FlowLayout());
			dlgConfirmacionPdfAlumno.setSize(220,120);
			dlgConfirmacionPdfAlumno.setResizable(false);
			dlgConfirmacionPdfAlumno.setLocationRelativeTo(null);
			dlgConfirmacionPdfAlumno.addWindowListener(this);
			lblMensaje.setText("Exportación realizada con éxito");
			dlgConfirmacionPdfAlumno.add(lblMensaje);
			dlgConfirmacionPdfAlumno.setVisible(true);

		}





		//-----------------------------PROFESORES-------------------------------------//

		//Si pulsamos botón de alta dentro del menu profesor aparece la ventana de Alta Profesor
		else if(eventoBoton.getSource().equals(mniAltaProfesor))
		{
			frmAltaProfesor.setLayout(new FlowLayout());
			frmAltaProfesor.add(lblNombreProfesor);
			frmAltaProfesor.add(txtNombreProfesor);
			frmAltaProfesor.add(lblApellidosProfesor);
			frmAltaProfesor.add(txtApellidosProfesor);
			frmAltaProfesor.add(lblDniProfesor);
			frmAltaProfesor.add(txtDniProfesor);
			frmAltaProfesor.add(lblTelefonoProfesor);
			frmAltaProfesor.add(txtTelefonoProfesor);
			frmAltaProfesor.add(lblEspecialidadProfesor);
			frmAltaProfesor.add(txtEspecialidadProfesor);
			btnAceptarAltaProfesor.addActionListener(this);
			frmAltaProfesor.add(btnAceptarAltaProfesor);
			btnCancelarAltaProfesor.addActionListener(this);
			frmAltaProfesor.add(btnCancelarAltaProfesor);

			frmAltaProfesor.setSize(280,220);
			frmAltaProfesor.setResizable(false);
			frmAltaProfesor.setLocationRelativeTo(null);
			frmAltaProfesor.addWindowListener(this);
			frmAltaProfesor.setVisible(true);
		}
		//Si puslamos cancelar la ventana se cierra
		else if(eventoBoton.getSource().equals(btnCancelarAltaProfesor))
		{
			frmAltaProfesor.setVisible(false);
		}
		//Si puslamos botón aceptar contectamos con la base de datos
		else if(eventoBoton.getSource().equals(btnAceptarAltaProfesor))
		{
			bd = new BaseDatos();
			connection = bd.conectar();
			try
			{
				//Crear una sentencia
				//y ejecutar la sentencia SQL
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//No dejar campos sin rellenar sino aparece mensaje Faltan datos
				if(((txtNombreProfesor.getText().length())!= 0)
						&&((txtApellidosProfesor.getText().length())!=0)&&((txtDniProfesor.getText().length())!=0)&&((txtTelefonoProfesor.getText().length())!=0)
						&&((txtEspecialidadProfesor.getText().length())!=0))
				{
					sentencia = "INSERT INTO profesores VALUES (null, '"
							+txtNombreProfesor.getText()+"', '"+txtApellidosProfesor.getText()+"', '"+txtDniProfesor.getText()+"', '"+txtTelefonoProfesor.getText()+"', '"+txtEspecialidadProfesor.getText()+"')";
					// le pasamos la funcion guardar para el log
					objLog.guardar(usuario, sentencia);
					statement.executeUpdate(sentencia);
					lblMensaje.setText("Alta realizada con éxito");
				}
				else
				{
					lblMensaje.setText("Faltan datos");
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en ALTA");
			}
			finally
			{
				//Dioalogo confirmación del Alta del Profesor
				dlgConfirmacionAltaProfesor.setLayout(new FlowLayout());
				dlgConfirmacionAltaProfesor.setSize(170,120);
				dlgConfirmacionAltaProfesor.setResizable(false);
				dlgConfirmacionAltaProfesor.setLocationRelativeTo(null);
				dlgConfirmacionAltaProfesor.addWindowListener(this);
				dlgConfirmacionAltaProfesor.add(lblMensaje);
				dlgConfirmacionAltaProfesor.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}
		}
		//Si pulsamos el boton Baja en el menú Profesor conectamos a base de datos
		else if(eventoBoton.getSource().equals(mniBajaProfesor))
		{
			frmBajaProfesor.setLayout(new FlowLayout());
			frmBajaProfesor.add(lblBajaProfesor);
			//Rellenar el choice
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM Profesores";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaProfesorBaja.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaProfesorBaja.add(rs.getInt("idProfesor") 
							+ "-" + rs.getString("nombreProfesor")
							+ "-" + rs.getString("apellidosProfesor")
							+ "-" + rs.getString("dniProfesor"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en la Baja");
			}
			frmBajaProfesor.add(choListaProfesorBaja);
			btnBorrarProfesor.addActionListener(this);
			frmBajaProfesor.add(btnBorrarProfesor);

			frmBajaProfesor.setSize(280,140);
			frmBajaProfesor.setResizable(false);
			frmBajaProfesor.setLocationRelativeTo(null);
			frmBajaProfesor.addWindowListener(this);
			frmBajaProfesor.setVisible(true);	

			//Desconectar con la BD
			bd.desconectar(connection);
		}

		//Si pulsamos botón Borrar nos aparece dialogo Seguro
		else if(eventoBoton.getSource().equals(btnBorrarProfesor))
		{
			dlgSeguroBorrarProfesor.setLayout(new FlowLayout());
			dlgSeguroBorrarProfesor.setSize(180,120);
			dlgSeguroBorrarProfesor.setResizable(false);
			dlgSeguroBorrarProfesor.setLocationRelativeTo(null);
			dlgSeguroBorrarProfesor.addWindowListener(this);
			dlgSeguroBorrarProfesor.add(lblSeguroBorrarProfesor);
			btnSiSeguroBorradoProfesor.addActionListener(this);
			dlgSeguroBorrarProfesor.add(btnSiSeguroBorradoProfesor);
			btnNoSeguroBorradoProfesor.addActionListener(this);
			dlgSeguroBorrarProfesor.add(btnNoSeguroBorradoProfesor);
			dlgSeguroBorrarProfesor.setVisible(true);
		}
		//Si pulsamos botón No, en el dialogo Seguro se cierra
		else if (eventoBoton.getSource().equals(btnNoSeguroBorradoProfesor))
		{
			dlgSeguroBorrarProfesor.setVisible(false);
		}
		//Si pulsamos Si, en el dialogo Seguro conectamos con BD y ejecutamos sentencia
		else if (eventoBoton.getSource().equals(btnSiSeguroBorradoProfesor))
		{
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			//En elegido[0]--> id
			//En elegido[0]--> nombre
			//En elegido[0]--> apellidos
			//...
			String[] elegido = choListaProfesorBaja.getSelectedItem().split("-");

			//Hacer un DELETE FROM alumnos WHERE idAlumno = x
			sentencia = "DELETE FROM profesores WHERE idProfesor = "+ elegido[0];
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);
				lblMensaje.setText("Profesor Eliminado");
				dlgSeguroBorrarProfesor.setVisible(false);


			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en Baja");
			}
			finally
			{
				//Dialogo confirmacion baja Profesor
				dlgConfirmacionBajaProfesor.setLayout(new FlowLayout());
				dlgConfirmacionBajaProfesor.setSize(180,120);
				dlgConfirmacionBajaProfesor.setResizable(false);
				dlgConfirmacionBajaProfesor.setLocationRelativeTo(null);
				dlgConfirmacionBajaProfesor.addWindowListener(this);
				dlgConfirmacionBajaProfesor.add(lblMensaje);
				dlgConfirmacionBajaProfesor.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}
		}
		//Si pulsamos boton modificación del menu Profesor
		else if(eventoBoton.getSource().equals(mniModificacionProfesor))
		{
			frmModificacionProfesor.setLayout(new FlowLayout ());

			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM profesores";
			try
			{	//Crear objeto Resultset para ir guardando datos
				// yrear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs= statement.executeQuery(sentencia);

				choListaProfesorEditar.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaProfesorEditar.add(rs.getInt("idProfesor") 
							+ "-" + rs.getString("nombreProfesor")
							+ "-" + rs.getString("apellidosProfesor")
							+ "-" + rs.getString("dniProfesor")
							+ "-" + rs.getInt("telefonoProfesor")
							+ "-" + rs.getString("especialidadProfesor"));
				}

			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en Modificación");
			}

			//Desconectar con la BD
			bd.desconectar(connection);


			frmModificacionProfesor.add(lblModificacionProfesor);
			frmModificacionProfesor.add(choListaProfesorEditar);
			btnAceptarModificarProfesor.addActionListener(this);
			frmModificacionProfesor.add(btnAceptarModificarProfesor);
			btnCancelarModificacionProfesor.addActionListener(this);
			frmModificacionProfesor.add(btnCancelarModificacionProfesor);

			frmModificacionProfesor.setSize(360,140);
			frmModificacionProfesor.setResizable(false);
			frmModificacionProfesor.setLocationRelativeTo(null);
			frmModificacionProfesor.addWindowListener(this);
			frmModificacionProfesor.setVisible(true);


		}
		//Si pulsamos el boton cancelar en la modificacion se cierra la ventana
		else if (eventoBoton.getSource().equals(btnCancelarModificacionProfesor))
		{
			frmModificacionProfesor.setVisible(false);
		}
		//Si el botón aceptar bajo el choice es pulsado aparece la ventana de editar profesor 
		//con los datos del profesor seleccionado
		else if (eventoBoton.getSource().equals(btnAceptarModificarProfesor))
		{

			String[] elegido = choListaProfesorEditar.getSelectedItem().split("-");

			frmEditarProfesor.setLayout(new FlowLayout());
			frmEditarProfesor.add(lblEditarIdProfesor);
			frmEditarProfesor.add(txtEditarIdProfesor);
			txtEditarIdProfesor.setText(elegido[0]);
			txtEditarIdProfesor.setEnabled(false);
			frmEditarProfesor.add(lblEditarNombreProfesor);
			frmEditarProfesor.add(txtEditarNombreProfesor);
			txtEditarNombreProfesor.setText(elegido[1]);
			frmEditarProfesor.add(lblEditarApellidosProfesor);
			frmEditarProfesor.add(txtEditarApellidosProfesor);
			txtEditarApellidosProfesor.setText(elegido[2]);
			frmEditarProfesor.add(lblEditarDniProfesor);
			frmEditarProfesor.add(txtEditarDniProfesor);
			txtEditarDniProfesor.setText(elegido[3]);
			frmEditarProfesor.add(lblEditarTelefonoProfesor);
			frmEditarProfesor.add(txtEditarTelefonoProfesor);
			txtEditarTelefonoProfesor.setText(elegido[4]);
			frmEditarProfesor.add(lblEditarEspecialidadProfesor);
			frmEditarProfesor.add(txtEditarEspecialidadProfesor);
			txtEditarEspecialidadProfesor.setText(elegido[5]);
			btnEditarProfesor.addActionListener(this);
			frmEditarProfesor.add(btnEditarProfesor);
			btnEditarCancelarProfesor.addActionListener(this);
			frmEditarProfesor.add(btnEditarCancelarProfesor);

			frmEditarProfesor.setSize(280,240);
			frmEditarProfesor.setResizable(false);
			frmEditarProfesor.setLocationRelativeTo(null);
			frmEditarProfesor.addWindowListener(this);
			frmEditarProfesor.setVisible(true);

		}
		//Si el botón cancelar edición de profesor es pulsado, la ventana se cierra
		else if(eventoBoton.getSource().equals(btnEditarCancelarProfesor))
		{
			frmEditarProfesor.setVisible(false);
		}
		//Si el botón editar profesor es puslado conectamos y ejecutamos sentencia
		else if(eventoBoton.getSource().equals(btnEditarProfesor))
		{
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			String nombreProfesorModificado = txtEditarNombreProfesor.getText();
			String apellidosProfesorModificado = txtEditarApellidosProfesor.getText();
			String dniProfesorModificado = txtEditarDniProfesor.getText();
			String telefonoProfesorModificado = txtEditarTelefonoProfesor.getText();
			String especialidadProfesorModificado = txtEditarEspecialidadProfesor.getText();

			//Crear sentencia
			sentencia = ("UPDATE profesores SET nombreProfesor = '"+ nombreProfesorModificado  
					+ "' , apellidosProfesor = '"+ apellidosProfesorModificado 
					+ "' , dniProfesor = '"+ dniProfesorModificado 
					+ "' , telefonoProfesor = '"+ telefonoProfesorModificado
					+ "' , especialidadProfesor = '"+ especialidadProfesorModificado +
					"' WHERE idProfesor ="+txtEditarIdProfesor.getText());
			try
			{

				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);

				lblMensaje.setText("Modificación realizada con éxito");

			}
			catch(SQLException sqle)
			{
				lblMensaje.setText("Error en la Modificación");
			}
			finally
			{
				//Dialogo confirmación de edición
				dlgConfirmacionEdicionProfesor.setLayout(new FlowLayout());
				dlgConfirmacionEdicionProfesor.setSize(220,120);
				dlgConfirmacionEdicionProfesor.setResizable(false);
				dlgConfirmacionEdicionProfesor.setLocationRelativeTo(null);
				dlgConfirmacionEdicionProfesor.addWindowListener(this);
				dlgConfirmacionEdicionProfesor.add(lblMensaje);
				dlgConfirmacionEdicionProfesor.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}

		}

		//Si el botón del menú Consulta en el apartado Profesor es pulsado
		//se abre la ventana de consulta
		else if (eventoBoton.getSource().equals(mniConsultaProfesor))
		{
			frmConsultaProfesor.setLayout(new FlowLayout());
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM profesores";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				listadoConsultaProfesor.setText("");
				listadoConsultaProfesor.append("Nombre\tApellidos\tDni\tTeléfono\tEspecialidad\n");

				while(rs.next())
				{
					listadoConsultaProfesor.append(rs.getString("nombreProfesor")
							+ "\t"+rs.getString("apellidosProfesor")
							+ "\t"+rs.getString("DniProfesor")
							+ "\t"+rs.getString("telefonoProfesor")
							+ "\t"+rs.getString("especialidadProfesor")
							+"\n");
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en ALTA");
			}

			//Desconectar con la BD
			bd.desconectar(connection);

			frmConsultaProfesor.add(listadoConsultaProfesor);
			frmConsultaProfesor.add(btnPdfProfesor);
			btnPdfProfesor.addActionListener(this);
			btnAceptarConsultaProfesor.addActionListener(this);
			frmConsultaProfesor.add(btnAceptarConsultaProfesor);
			frmConsultaProfesor.setSize(400,200);
			frmConsultaProfesor.setResizable(false);
			frmConsultaProfesor.setLocationRelativeTo(null);
			frmConsultaProfesor.addWindowListener(this);
			frmConsultaProfesor.setVisible(true);
		}
		//Si se pulsa el botón Aceptar la ventana se cierra
		else if (eventoBoton.getSource().equals(btnAceptarConsultaProfesor))
		{
			frmConsultaProfesor.setVisible(false);
		}

		// Exportación a PDF Profesores
		else if (eventoBoton.getSource().equals(btnPdfProfesor))
		{

			try
			{
				// Creamos el documento
				Document docPdfprofesores = new Document();
				try 
				{
					// Fichero donde se deja el documento
					FileOutputStream ficheroDocProfesor = new FileOutputStream("docProfesores.pdf");
					// Se asocia el documento y se indica el espacio entre lineas
					PdfWriter.getInstance(docPdfprofesores, ficheroDocProfesor).setInitialLeading(20);
				}
				catch  (FileNotFoundException fileNotFoundException)
				{
					JOptionPane.showMessageDialog(null, "No se encontró el fichero para generar el pdf." 
							+ fileNotFoundException,"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				// Se abre el documento
				docPdfprofesores.open();

				// Imagen
				//Añadir la imagen con sus características
				Image imagen;
				try 
				{
					imagen = Image.getInstance("logo.jpg");
					imagen.scaleToFit(100,100);
					imagen.setAlignment(Chunk.ALIGN_LEFT);
					docPdfprofesores.add(imagen);
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				//Desconectar con la BD
				bd.desconectar(connection);

				// Encabezado
				Paragraph encabezado = new Paragraph(" Práctica: Programa Gestión - Universidad", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color
				encabezado.setAlignment(Element.ALIGN_LEFT);


				// Salto de línea
				Paragraph saltoDeLinea = new Paragraph();
				saltoDeLinea.add("\n\n");


				// Titulo
				Paragraph titulo = new Paragraph(" Listado Profesores", 
						FontFactory.getFont("arial", // fuente
								20, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.BLUE)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);


				// Pie de Página
				Paragraph piePagina = new Paragraph("Sara Fernández - 1º DAW", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color)
				piePagina.setAlignment(Element.ALIGN_RIGHT);


				// Crear la tabla
				PdfPTable tabla = new PdfPTable(5);

				//Rellenar las cabeceras de las columnas
				Paragraph columna1 = new Paragraph("NOMBRE");
				columna1.getFont().setStyle(Font.BOLD);
				columna1.getFont().setSize(12);
				tabla.addCell(columna1);

				Paragraph columna2 = new Paragraph("APELLIDOS");
				columna2.getFont().setStyle(Font.BOLD);
				columna2.getFont().setSize(12);
				tabla.addCell(columna2);

				Paragraph columna3 = new Paragraph("DNI");
				columna3.getFont().setStyle(Font.BOLD);
				columna3.getFont().setSize(12);
				tabla.addCell(columna3);

				Paragraph columna4 = new Paragraph("TELÉFONO");
				columna4.getFont().setStyle(Font.BOLD);
				columna4.getFont().setSize(12);
				tabla.addCell(columna4);

				Paragraph columna5 = new Paragraph("ESPECIALIDAD");
				columna5.getFont().setStyle(Font.BOLD);
				columna5.getFont().setSize(12);
				tabla.addCell(columna5);


				//Rellenar las filas de la tabla.
				//Conectar
				bd = new BaseDatos();
				connection = bd.conectar();
				sentencia = "SELECT * FROM profesores";
				try
				{
					//Crear una sentencia
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

					//Crear un objeto ResultSet para guardar lo obtenido
					//y ejecutar la sentencia SQL
					rs= statement.executeQuery(sentencia);
					while(rs.next())
					{
						tabla.addCell(rs.getString("nombreProfesor"));
						tabla.addCell(rs.getString("apellidosProfesor"));
						tabla.addCell(rs.getString("DniProfesor"));
						tabla.addCell(rs.getString("telefonoProfesor"));
						tabla.addCell(rs.getString("especialidadProfesor"));
					}
				}
				catch (SQLException sqle)
				{
					lblMensaje.setText("Error");
				}

				// Añadir los componente creados
				docPdfprofesores.add(encabezado);
				docPdfprofesores.add(saltoDeLinea);
				docPdfprofesores.add(titulo);
				docPdfprofesores.add(saltoDeLinea);
				docPdfprofesores.add(tabla);
				docPdfprofesores.add(saltoDeLinea);
				docPdfprofesores.add(piePagina);

				//Cerrar el documento
				docPdfprofesores.close();

			}
			catch (DocumentException e)
			{

				e.printStackTrace();
			}

			dlgConfirmacionPdfProfesor.setLayout(new FlowLayout());
			dlgConfirmacionPdfProfesor.setSize(220,120);
			dlgConfirmacionPdfProfesor.setResizable(false);
			dlgConfirmacionPdfProfesor.setLocationRelativeTo(null);
			dlgConfirmacionPdfProfesor.addWindowListener(this);
			lblMensaje.setText("Exportación realizada con éxito");
			dlgConfirmacionPdfProfesor.add(lblMensaje);
			dlgConfirmacionPdfProfesor.setVisible(true);

		}



		//-----------------------------ASIGNATURAS-------------------------------------//

		//Si pulsamos el botón Alta dentro del menú Asignaturas nos aparece ventana de Alta de Asignatura
		else if (eventoBoton.getSource().equals(mniAltaAsignatura))
		{
			frmAltaAsignatura.setLayout(new FlowLayout());
			frmAltaAsignatura.add(lblNombreAsignatura);
			frmAltaAsignatura.add(txtNombreAsignatura);
			frmAltaAsignatura.add(lblTurnoAsignatura);
			frmAltaAsignatura.add(txtTurnoAsignatura);
			frmAltaAsignatura.add(lblCreditosAsignatura);
			frmAltaAsignatura.add(txtCreditosAsignatura);
			frmAltaAsignatura.add(lblnombreProfesor);

			//Conectar para rellenar el choice
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM profesores";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				System.out.println(sentencia);
				choListaProfesores.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaProfesores.add(rs.getString("idProfesor")+"-" + rs.getString("nombreProfesor")
					+ "-" + rs.getString("especialidadProfesor"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}
			//Desconectar con la BD
			bd.desconectar(connection);

			frmAltaAsignatura.add(choListaProfesores);
			btnAceptarAltaAsignatura.addActionListener(this);
			frmAltaAsignatura.add(btnAceptarAltaAsignatura);
			btnCancelarAltaAsignatura.addActionListener(this);
			frmAltaAsignatura.add(btnCancelarAltaAsignatura);

			frmAltaAsignatura.setSize(280,190);
			frmAltaAsignatura.setResizable(false);
			frmAltaAsignatura.setLocationRelativeTo(null);
			frmAltaAsignatura.addWindowListener(this);
			frmAltaAsignatura.setVisible(true);

		}
		//Si pulsamos botón Cancelar la ventana se cierra
		else if (eventoBoton.getSource().equals(btnCancelarAltaAsignatura))
		{
			frmAltaAsignatura.setVisible(false);
		}

		//Si pulsamos botón Aceptar conectamos con base de datos
		else if (eventoBoton.getSource().equals(btnAceptarAltaAsignatura))
		{

			String[] profesorElegido = choListaProfesores.getSelectedItem().split("-");
			String idProfesorElegido = profesorElegido[0];
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			try
			{
				//Crear una sentencia
				//y ejecutar la sentencia SQL
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				//Para que ningún campo quede sin rellenar
				if(((txtNombreAsignatura.getText().length())!= 0)
						&&((txtTurnoAsignatura.getText().length())!=0)&&((txtCreditosAsignatura.getText().length())!=0))
				{
					sentencia = "INSERT INTO asignaturas VALUES (null, '"
							+txtNombreAsignatura.getText()+"', '"+txtTurnoAsignatura.getText()+"', '"+txtCreditosAsignatura.getText()+"', '"+idProfesorElegido+"')";
					// le pasamos la funcion guardar para el log
					objLog.guardar(usuario, sentencia);
					statement.executeUpdate(sentencia);
					lblMensaje.setText("Alta realizada con éxito");
				}
				else
				{
					lblMensaje.setText("Faltan datos");
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en ALTA");
			}
			finally
			{
				//Dialogo de Confirmación Alta asignatura
				dlgConfirmacionAltaAsignatura.setLayout(new FlowLayout());
				dlgConfirmacionAltaAsignatura.setSize(200,150);
				dlgConfirmacionAltaAsignatura.setResizable(false);
				dlgConfirmacionAltaAsignatura.setLocationRelativeTo(null);
				dlgConfirmacionAltaAsignatura.addWindowListener(this);
				dlgConfirmacionAltaAsignatura.add(lblMensaje);
				dlgConfirmacionAltaAsignatura.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}
		}
		//Si pulsamos el boton Baja en el menú Asignatura aparece ventana de Baja Asignatura
		else if(eventoBoton.getSource().equals(mniBajaAsignatura))
		{
			frmBajaAsignatura.setLayout(new FlowLayout());
			frmBajaAsignatura.add(lblBajaAsignatura);
			//Rellenar el choice
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM asignaturas";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaAsignaturaBaja.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAsignaturaBaja.add(rs.getInt("idAsignatura") 
							+ "-" + rs.getString("nombreAsignatura")
							+ "-" + rs.getString("turnoAsignatura"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en la Baja");
			}
			frmBajaAsignatura.add(choListaAsignaturaBaja);
			btnBorrarAsignatura.addActionListener(this);
			frmBajaAsignatura.add(btnBorrarAsignatura);

			frmBajaAsignatura.setSize(280,140);
			frmBajaAsignatura.setResizable(false);
			frmBajaAsignatura.setLocationRelativeTo(null);
			frmBajaAsignatura.addWindowListener(this);
			frmBajaAsignatura.setVisible(true);	

			//Desconectar con la BD
			bd.desconectar(connection);

		}
		//Si pulsamos botón Borrar nos aparece dialogo Seguro
		else if(eventoBoton.getSource().equals(btnBorrarAsignatura))
		{
			dlgSeguroBorrarAsignatura.setLayout(new FlowLayout());
			dlgSeguroBorrarAsignatura.setSize(180,120);
			dlgSeguroBorrarAsignatura.setResizable(false);
			dlgSeguroBorrarAsignatura.setLocationRelativeTo(null);
			dlgSeguroBorrarAsignatura.addWindowListener(this);
			dlgSeguroBorrarAsignatura.add(lblSeguroBorrarAsignatura);
			btnSiSeguroBorradoAsignatura.addActionListener(this);
			dlgSeguroBorrarAsignatura.add(btnSiSeguroBorradoAsignatura);
			btnNoSeguroBorradoAsignatura.addActionListener(this);
			dlgSeguroBorrarAsignatura.add(btnNoSeguroBorradoAsignatura);
			dlgSeguroBorrarAsignatura.setVisible(true);
		}
		//Si pulsamos botón No, en el dialogo Seguro se cierra
		else if (eventoBoton.getSource().equals(btnNoSeguroBorradoAsignatura))
		{
			dlgSeguroBorrarAsignatura.setVisible(false);
		}
		//Si pulsamos Si, en el dialogo Seguro conectamos con BD y ejecutamos sentencia
		else if (eventoBoton.getSource().equals(btnSiSeguroBorradoAsignatura))
		{
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			String[] elegido = choListaAsignaturaBaja.getSelectedItem().split("-");

			//Hacer un DELETE FROM asignatura WHERE idsignatura = x
			sentencia = "DELETE FROM asignaturas WHERE idAsignatura = "+ elegido[0];
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);
				lblMensaje.setText("Asignatura Eliminada");
				dlgSeguroBorrarAsignatura.setVisible(false);


			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en Baja");
			}
			finally
			{
				//Dialogo confirmacion baja Asignatura
				dlgConfirmacionBajaAsignatura.setLayout(new FlowLayout());
				dlgConfirmacionBajaAsignatura.setSize(180,120);
				dlgConfirmacionBajaAsignatura.setResizable(false);
				dlgConfirmacionBajaAsignatura.setLocationRelativeTo(null);
				dlgConfirmacionBajaAsignatura.addWindowListener(this);
				dlgConfirmacionBajaAsignatura.add(lblMensaje);
				dlgConfirmacionBajaAsignatura.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}
		}
		//Si pulsamos boton modificación del menu Asignatura
		else if(eventoBoton.getSource().equals(mniModificacionAsignatura))
		{
			frmModificacionAsignatura.setLayout(new FlowLayout ());

			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM Asignaturas";
			try
			{	//Crear objeto Resultset para ir guardando datos
				// yrear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs= statement.executeQuery(sentencia);

				choListaAsignaturaEditar.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAsignaturaEditar.add(rs.getInt("idAsignatura") 
							+ "-" + rs.getString("nombreAsignatura")
							+ "-" + rs.getString("turnoAsignatura")
							+ "-" + rs.getString("creditosAsignatura"));
				}

			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}

			//Desconectar con la BD
			bd.desconectar(connection);


			frmModificacionAsignatura.add(lblModificacionAsignatura);
			frmModificacionAsignatura.add(choListaAsignaturaEditar);
			btnAceptarModificarAsignatura.addActionListener(this);
			frmModificacionAsignatura.add(btnAceptarModificarAsignatura);
			btnCancelarModificacionAsignatura.addActionListener(this);
			frmModificacionAsignatura.add(btnCancelarModificacionAsignatura);

			frmModificacionAsignatura.setSize(360,140);
			frmModificacionAsignatura.setResizable(false);
			frmModificacionAsignatura.setLocationRelativeTo(null);
			frmModificacionAsignatura.addWindowListener(this);
			frmModificacionAsignatura.setVisible(true);
		}
		//Si pulsamos el boton cancelar en la modificacion se cierra la ventana
		else if (eventoBoton.getSource().equals(btnCancelarModificacionAsignatura))
		{
			frmModificacionAsignatura.setVisible(false);
		}
		//Si el botón aceptar bajo el choice es pulsado aparece la ventana de editar Asignatura 
		//con los datos de la Asignatura seleccionada
		else if (eventoBoton.getSource().equals(btnAceptarModificarAsignatura))
		{

			String[] elegido = choListaAsignaturaEditar.getSelectedItem().split("-");

			frmEditarAsignatura.setLayout(new FlowLayout());
			frmEditarAsignatura.add(lblEditarIdAsignatura);
			frmEditarAsignatura.add(txtEditarIdAsignatura);
			txtEditarIdAsignatura.setText(elegido[0]);
			txtEditarIdAsignatura.setEnabled(false);
			frmEditarAsignatura.add(lblEditarNombreAsignatura);
			frmEditarAsignatura.add(txtEditarNombreAsignatura);
			txtEditarNombreAsignatura.setText(elegido[1]);
			frmEditarAsignatura.add(lblEditarTurnoAsignatura);
			frmEditarAsignatura.add(txtEditarTurnoAsignatura);
			txtEditarTurnoAsignatura.setText(elegido[2]);
			frmEditarAsignatura.add(lblEditarCreditosAsignatura);
			frmEditarAsignatura.add(txtEditarCreditosAsignatura);
			txtEditarCreditosAsignatura.setText(elegido[3]);


			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM profesores";
			try
			{	//Crear objeto Resultset para ir guardando datos
				// yrear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs= statement.executeQuery(sentencia);

				choEditarProfesoresAsignatura.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choEditarProfesoresAsignatura.add(rs.getInt("idProfesor") 
							+ "-" + rs.getString("nombreProfesor")
							+ "-" + rs.getString("apellidosProfesor")
							+ "-" + rs.getString("especialidadProfesor"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}
			//Desconectar con la BD
			bd.desconectar(connection);


			frmEditarAsignatura.add(lblChoListProfesorAsignatura);
			frmEditarAsignatura.add(choEditarProfesoresAsignatura);
			btnEditarAsignatura.addActionListener(this);
			frmEditarAsignatura.add(btnEditarAsignatura);
			btnEditarCancelarAsignatura.addActionListener(this);
			frmEditarAsignatura.add(btnEditarCancelarAsignatura);

			frmEditarAsignatura.setSize(280,240);
			frmEditarAsignatura.setResizable(false);
			frmEditarAsignatura.setLocationRelativeTo(null);
			frmEditarAsignatura.addWindowListener(this);
			frmEditarAsignatura.setVisible(true);
		}
		//Si el botón cancelar edición de Asignatura es pulsado, la ventana se cierra
		else if(eventoBoton.getSource().equals(btnEditarCancelarAsignatura))
		{
			frmEditarAsignatura.setVisible(false);
		}
		//Si el botón editar Asignatura es puslado conectamos y ejecutamos sentencia
		else if(eventoBoton.getSource().equals(btnEditarAsignatura))
		{
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			String nombreAsignaturaModificado = txtEditarNombreAsignatura.getText();
			String turnoAsignaturarModificado = txtEditarTurnoAsignatura.getText();
			String creditosAsgignaturaModificado = txtEditarCreditosAsignatura.getText();
			String[] elegido = choEditarProfesoresAsignatura.getSelectedItem().split("-");


			//Crear sentencia
			sentencia = ("UPDATE asignaturas SET nombreAsignatura = '"+ nombreAsignaturaModificado  
					+ "' , turnoAsignatura = '"+ turnoAsignaturarModificado 
					+ "' , creditosAsignatura = '"+ creditosAsgignaturaModificado 
					+ "' , idProfesorFK = '" + elegido[0] +
					"' WHERE idAsignatura ="+txtEditarIdAsignatura.getText());
			try
			{
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);

				lblMensaje.setText("Modificación realizada con éxito");

			}
			catch(SQLException sqle)
			{
				lblMensaje.setText("Error en la Modificación");
			}
			finally
			{
				//Dialogo confirmación de edición

				dlgConfirmacionEdicionAsignatura.setLayout(new FlowLayout());
				dlgConfirmacionEdicionAsignatura.setSize(220,120);
				dlgConfirmacionEdicionAsignatura.setResizable(false);
				dlgConfirmacionEdicionAsignatura.setLocationRelativeTo(null);
				dlgConfirmacionEdicionAsignatura.addWindowListener(this);
				dlgConfirmacionEdicionAsignatura.add(lblMensaje);
				dlgConfirmacionEdicionAsignatura.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}

		}

		//Si pulsamos el botón Consulta dentro del menú Asignaturas aparece ventana de Consulta de Asignaturas
		else if (eventoBoton.getSource().equals(mniConsultaAsignatura))
		{
			frmConsultaAsignatura.setLayout(new FlowLayout());
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT nombreAsignatura, turnoAsignatura, creditosAsignatura, nombreProfesor FROM asignaturas, profesores where idProfesor = idProfesorFK; ";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				listadoConsultaAsignatura.setText("");
				listadoConsultaAsignatura.append("Asignatura\tTurno\tCréditos\tProfesor\n");

				while(rs.next())
				{
					listadoConsultaAsignatura.append(rs.getString("nombreAsignatura")
							+ "\t"+rs.getString("turnoAsignatura")
							+ "\t"+rs.getString("creditosAsignatura")
							+ "\t"+rs.getString("nombreProfesor")
							+"\n");
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en ALTA");
			}
			//Desconectar con la BD
			bd.desconectar(connection);

			frmConsultaAsignatura.add(listadoConsultaAsignatura);
			frmConsultaAsignatura.add(btnPdfAsignatura);
			btnPdfAsignatura.addActionListener(this);
			btnAceptarConsultaAsignatura.addActionListener(this);
			frmConsultaAsignatura.add(btnAceptarConsultaAsignatura);

			frmConsultaAsignatura.setSize(400,200);
			frmConsultaAsignatura.setResizable(false);
			frmConsultaAsignatura.setLocationRelativeTo(null);
			frmConsultaAsignatura.addWindowListener(this);
			frmConsultaAsignatura.setVisible(true);
		}
		//Si pulsamos el botón Aceptar la ventana se cierra
		else if (eventoBoton.getSource().equals(btnAceptarConsultaAsignatura))
		{
			frmConsultaAsignatura.setVisible(false);
		}
		// Exportación a PDF Asignatura
		else if (eventoBoton.getSource().equals(btnPdfAsignatura))
		{

			try
			{
				// Creamos el documento
				Document docPdfAsignatura = new Document();
				try 
				{
					// Fichero donde se deja el documento
					FileOutputStream ficheroDocProfesor = new FileOutputStream("docAsignaturas.pdf");
					// Se asocia el documento y se indica el espacio entre lineas
					PdfWriter.getInstance(docPdfAsignatura, ficheroDocProfesor).setInitialLeading(20);
				}
				catch  (FileNotFoundException fileNotFoundException)
				{
					JOptionPane.showMessageDialog(null, "No se encontró el fichero para generar el pdf." 
							+ fileNotFoundException,"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				// Se abre el documento
				docPdfAsignatura.open();

				// Imagen
				//Añadir la imagen con sus características
				Image imagen;
				try 
				{
					imagen = Image.getInstance("logo.jpg");
					imagen.scaleToFit(100,100);
					imagen.setAlignment(Chunk.ALIGN_LEFT);
					docPdfAsignatura.add(imagen);
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				//Desconectar con la BD
				bd.desconectar(connection);

				// Encabezado
				Paragraph encabezado = new Paragraph(" Práctica: Programa Gestión - Universidad", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color
				encabezado.setAlignment(Element.ALIGN_LEFT);


				// Salto de línea
				Paragraph saltoDeLinea = new Paragraph();
				saltoDeLinea.add("\n\n");


				// Titulo
				Paragraph titulo = new Paragraph("Listado Asignaturas", 
						FontFactory.getFont("arial", // fuente
								20, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.BLUE)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);


				// Pie de Página
				Paragraph piePagina = new Paragraph("Sara Fernández - 1º DAW", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color)
				piePagina.setAlignment(Element.ALIGN_RIGHT);


				// Crear la tabla
				PdfPTable tabla = new PdfPTable(4);

				//Rellenar las cabeceras de las columnas
				Paragraph columna1 = new Paragraph("ASIGNATURA");
				columna1.getFont().setStyle(Font.BOLD);
				columna1.getFont().setSize(12);
				tabla.addCell(columna1);

				Paragraph columna2 = new Paragraph("TURNO");
				columna2.getFont().setStyle(Font.BOLD);
				columna2.getFont().setSize(12);
				tabla.addCell(columna2);

				Paragraph columna3 = new Paragraph("CRÉDITOS");
				columna3.getFont().setStyle(Font.BOLD);
				columna3.getFont().setSize(12);
				tabla.addCell(columna3);

				Paragraph columna4 = new Paragraph("PROFESOR");
				columna4.getFont().setStyle(Font.BOLD);
				columna4.getFont().setSize(12);
				tabla.addCell(columna4);



				//Rellenar las filas de la tabla.
				//Conectar
				bd = new BaseDatos();
				connection = bd.conectar();
				sentencia = "SELECT nombreAsignatura, turnoAsignatura, creditosAsignatura, nombreProfesor FROM asignaturas, profesores where idProfesor = idProfesorFK; ";
				try
				{
					//Crear una sentencia
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

					//Crear un objeto ResultSet para guardar lo obtenido
					//y ejecutar la sentencia SQL
					rs= statement.executeQuery(sentencia);
					while(rs.next())
					{
						tabla.addCell(rs.getString("nombreAsignatura"));
						tabla.addCell(rs.getString("turnoAsignatura"));
						tabla.addCell(rs.getString("creditosAsignatura"));
						tabla.addCell(rs.getString("nombreProfesor"));
					}
				}
				catch (SQLException sqle)
				{
					lblMensaje.setText("Error");
				}

				// Añadir los componente creados
				docPdfAsignatura.add(encabezado);
				docPdfAsignatura.add(saltoDeLinea);
				docPdfAsignatura.add(titulo);
				docPdfAsignatura.add(saltoDeLinea);
				docPdfAsignatura.add(tabla);
				docPdfAsignatura.add(saltoDeLinea);
				docPdfAsignatura.add(piePagina);

				//Cerrar el documento
				docPdfAsignatura.close();

			}
			catch (DocumentException e)
			{

				e.printStackTrace();
			}

			dlgConfirmacionPdfProfesor.setLayout(new FlowLayout());
			dlgConfirmacionPdfProfesor.setSize(220,120);
			dlgConfirmacionPdfProfesor.setResizable(false);
			dlgConfirmacionPdfProfesor.setLocationRelativeTo(null);
			dlgConfirmacionPdfProfesor.addWindowListener(this);
			lblMensaje.setText("Exportación realizada con éxito");
			dlgConfirmacionPdfProfesor.add(lblMensaje);
			dlgConfirmacionPdfProfesor.setVisible(true);

		}



		//-----------------------------MATRICULACIONES-------------------------------------//

		//Si pulsamos el botón Alta en el menú Matriculaciones se nos abre ventana de Alta Matricula
		//Conectamos con la base de datos y ejecutamos sentencia
		else if (eventoBoton.getSource().equals(mniAltaMatriculacion))
		{
			frmAltaMatricula.setLayout(new FlowLayout());
			frmAltaMatricula.add(lblAlumnoMatricula);
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM alumnos";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaAlumnosMatricula.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAlumnosMatricula.add(rs.getString("idAlumno") + "-" + rs.getString("nombreAlumno")
					+ "-" + rs.getString("apellidosAlumno"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}
			//Desconectar con la BD
			bd.desconectar(connection);

			frmAltaMatricula.add(choListaAlumnosMatricula);
			frmAltaMatricula.add(lblAsignaturaMatricula);

			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM asignaturas";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaAsignaturasMatricula.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAsignaturasMatricula.add(rs.getString("idAsignatura") + "-" + rs.getString("nombreAsignatura")
					+ "-" + rs.getString("turnoAsignatura"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}
			//Desconectar con la BD
			bd.desconectar(connection);

			frmAltaMatricula.add(choListaAsignaturasMatricula);
			btnAceptarMatricula.addActionListener(this);
			frmAltaMatricula.add(btnAceptarMatricula);
			btnCancelarMatricula.addActionListener(this);
			frmAltaMatricula.add(btnCancelarMatricula);

			frmAltaMatricula.setSize(240,180);
			frmAltaMatricula.setResizable(false);
			frmAltaMatricula.setLocationRelativeTo(null);
			frmAltaMatricula.addWindowListener(this);
			frmAltaMatricula.setVisible(true);
		}
		//Si pulsamos botón Cancelar se cierra la ventana de Alta
		else if (eventoBoton.getSource().equals(btnCancelarMatricula))
		{
			frmAltaMatricula.setVisible(false);
		}
		//Si pulsamos botón Aceptar conectamos con base de datos
		else if (eventoBoton.getSource().equals(btnAceptarMatricula))
		{
			bd = new BaseDatos();
			connection = bd.conectar();

			// Obtenemos el id del alumno y de la asignatura del choice para pasarlos al insert
			String[] alumnoElegido = choListaAlumnosMatricula.getSelectedItem().split("-");
			String idAlumnoElegido = alumnoElegido[0];
			String[] asignaturaElegida = choListaAsignaturasMatricula.getSelectedItem().split("-");
			String idAsignaturaElegida = asignaturaElegida[0];

			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				//y ejecutar la sentencia SQL
				{
					sentencia = "INSERT INTO matriculaciones VALUES (null, '"+idAlumnoElegido+"','"+idAsignaturaElegida+"')";
					// le pasamos la funcion guardar para el log
					objLog.guardar(usuario, sentencia);
					statement.executeUpdate(sentencia);
					lblMensaje.setText("Alta realizada con éxito");
				}
			}

			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en ALTA");
			}
			finally
			{
				//Dialogo confirmación del alta de la matrícula
				dlgConfirmacionMatricula.setLayout(new FlowLayout());
				dlgConfirmacionMatricula.setSize(190,120);
				dlgConfirmacionMatricula.setResizable(false);
				dlgConfirmacionMatricula.setLocationRelativeTo(null);
				dlgConfirmacionMatricula.addWindowListener(this);
				dlgConfirmacionMatricula.add(lblMensaje);
				dlgConfirmacionMatricula.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}

		}
		//Si pulsamos el boton Baja en el menú Asignatura aparece ventana de Baja Matricula
		else if(eventoBoton.getSource().equals(mniBajaMatricula))
		{
			frmBajaMatricula.setLayout(new FlowLayout());
			frmBajaMatricula.add(lblBajaMatricula);
			//Rellenar el choice
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			//Crear una sentencia
			sentencia ="SELECT matriculaciones.idMatriculacion, alumnos.nombreAlumno, asignaturas.nombreAsignatura FROM matriculaciones, alumnos, asignaturas where matriculaciones.idAlumnoFK = alumnos.idAlumno and matriculaciones.idAsignaturaFK = asignaturas.idAsignatura; ";

			try
			{

				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaMatriculaBaja.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{

					choListaMatriculaBaja.add(rs.getInt("idMatriculacion") 
							+ "-" + rs.getString("nombreAlumno")
							+ "-" + rs.getString("nombreAsignatura"));

				}

			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en la Baja");
			}
			frmBajaMatricula.add(choListaMatriculaBaja);
			btnBorrarMatricula.addActionListener(this);
			frmBajaMatricula.add(btnBorrarMatricula);

			frmBajaMatricula.setSize(280,140);
			frmBajaMatricula.setResizable(false);
			frmBajaMatricula.setLocationRelativeTo(null);
			frmBajaMatricula.addWindowListener(this);
			frmBajaMatricula.setVisible(true);	

			//Desconectar con la BD
			bd.desconectar(connection);
		}
		//Si pulsamos botón Borrar nos aparece dialogo Seguro
		else if(eventoBoton.getSource().equals(btnBorrarMatricula))
		{
			dlgSeguroBorrarMatricula.setLayout(new FlowLayout());
			dlgSeguroBorrarMatricula.setSize(180,120);
			dlgSeguroBorrarMatricula.setResizable(false);
			dlgSeguroBorrarMatricula.setLocationRelativeTo(null);
			dlgSeguroBorrarMatricula.addWindowListener(this);
			dlgSeguroBorrarMatricula.add(lblSeguroBorrarMatricula);
			btnSiSeguroBorradoMatricula.addActionListener(this);
			dlgSeguroBorrarMatricula.add(btnSiSeguroBorradoMatricula);
			btnNoSeguroBorradoMatricula.addActionListener(this);
			dlgSeguroBorrarMatricula.add(btnNoSeguroBorradoMatricula);
			dlgSeguroBorrarMatricula.setVisible(true);
		}
		//Si pulsamos botón No, en el dialogo Seguro se cierra
		else if (eventoBoton.getSource().equals(btnNoSeguroBorradoMatricula))
		{
			dlgSeguroBorrarMatricula.setVisible(false);
		}
		//Si pulsamos Si, en el dialogo Seguro conectamos con BD y ejecutamos sentencia
		else if (eventoBoton.getSource().equals(btnSiSeguroBorradoMatricula))
		{
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			String[] elegido = choListaMatriculaBaja.getSelectedItem().split("-");

			//Hacer un DELETE FROM Matricula WHERE idsignatura = x
			sentencia = "DELETE FROM Matriculaciones WHERE idMatriculacion = "+ elegido[0];
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);
				lblMensaje.setText("Matricula Eliminada");
				dlgSeguroBorrarMatricula.setVisible(false);


			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error en Baja");
			}
			finally
			{
				//Dialogo confirmacion baja Asignatura
				dlgConfirmacionBajaMatricula.setLayout(new FlowLayout());
				dlgConfirmacionBajaMatricula.setSize(180,120);
				dlgConfirmacionBajaMatricula.setResizable(false);
				dlgConfirmacionBajaMatricula.setLocationRelativeTo(null);
				dlgConfirmacionBajaMatricula.addWindowListener(this);
				dlgConfirmacionBajaMatricula.add(lblMensaje);
				dlgConfirmacionBajaMatricula.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}
		}
		//Si pulsamos boton modificación del menu Matricula
		else if(eventoBoton.getSource().equals(mniModificacionMatricula))
		{
			frmModificacionMatricula.setLayout(new FlowLayout ());

			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT matriculaciones.idMatriculacion, alumnos.nombreAlumno, asignaturas.nombreAsignatura FROM matriculaciones, alumnos, asignaturas where matriculaciones.idAlumnoFK = alumnos.idAlumno and matriculaciones.idAsignaturaFK = asignaturas.idAsignatura; ";
			try
			{	//Crear objeto Resultset para ir guardando datos
				// yrear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs= statement.executeQuery(sentencia);
				choListaMatriculaEditar.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{

					choListaMatriculaEditar.add(rs.getInt("idMatriculacion") 
							+ "-" + rs.getString("nombreAlumno")
							+ "-" + rs.getString("nombreAsignatura"));

				}

			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}

			//Desconectar con la BD
			bd.desconectar(connection);


			frmModificacionMatricula.add(lblModificacionMatricula);
			frmModificacionMatricula.add(choListaMatriculaEditar);
			btnAceptarModificarMatricula.addActionListener(this);
			frmModificacionMatricula.add(btnAceptarModificarMatricula);
			btnCancelarModificacionMatricula.addActionListener(this);
			frmModificacionMatricula.add(btnCancelarModificacionMatricula);

			frmModificacionMatricula.setSize(360,140);
			frmModificacionMatricula.setResizable(false);
			frmModificacionMatricula.setLocationRelativeTo(null);
			frmModificacionMatricula.addWindowListener(this);
			frmModificacionMatricula.setVisible(true);
		}
		//Si pulsamos el boton cancelar en la modificacion se cierra la ventana
		else if (eventoBoton.getSource().equals(btnCancelarModificacionMatricula))
		{
			frmModificacionMatricula.setVisible(false);
		}
		//Si el botón aceptar bajo el choice es pulsado aparece la ventana de editar Matricula 
		//con los datos de la Matricula seleccionada
		else if (eventoBoton.getSource().equals(btnAceptarModificarMatricula))
		{

			String[] elegido = choListaMatriculaEditar.getSelectedItem().split("-");

			frmEditarMatricula.setLayout(new FlowLayout());
			frmEditarMatricula.add(lblEditarMatricula);
			frmEditarMatricula.add(txtEditarIdMatricula);
			txtEditarIdMatricula.setText(elegido[0]);
			txtEditarIdMatricula.setEnabled(false);

			//choice alumnos
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM alumnos";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaAlumnoMatriculaEditar.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAlumnoMatriculaEditar.add(rs.getString("idAlumno") + "-" + rs.getString("nombreAlumno")
					+ "-" + rs.getString("apellidosAlumno"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}
			//Desconectar con la BD
			bd.desconectar(connection);

			frmEditarMatricula.add(lblListaAlumnoMatriculaEditar);
			frmEditarMatricula.add(choListaAlumnoMatriculaEditar);


			//choice asignaturas
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT * FROM asignaturas";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				choListaAsignaturasMatricula.removeAll(); //Antes de rellenar el chocice lo vaciamos
				while(rs.next())
				{
					choListaAsignaturasMatricula.add(rs.getString("idAsignatura") + "-" + rs.getString("nombreAsignatura")
					+ "-" + rs.getString("turnoAsignatura"));
				}
			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}
			//Desconectar con la BD
			bd.desconectar(connection);

			frmEditarMatricula.add(lblListaAsignaturaMatriculaEditar);
			frmEditarMatricula.add(choListaAsignaturasMatricula);
			btnAceptarEditarMatricula.addActionListener(this);
			frmEditarMatricula.add(btnAceptarEditarMatricula);
			btnCancelarEditarMatricula.addActionListener(this);
			frmEditarMatricula.add(btnCancelarEditarMatricula);

			frmEditarMatricula.setSize(230,210);
			frmEditarMatricula.setResizable(false);
			frmEditarMatricula.setLocationRelativeTo(null);
			frmEditarMatricula.addWindowListener(this);
			frmEditarMatricula.setVisible(true);
		}
		//Si el botón cancelar edición de Matriculas es pulsado, la ventana se cierra
		else if(eventoBoton.getSource().equals(btnCancelarEditarMatricula))
		{
			frmEditarMatricula.setVisible(false);
		}
		//Si el botón editar Asignatura es puslado conectamos y ejecutamos sentencia
		else if(eventoBoton.getSource().equals(btnAceptarEditarMatricula))
		{
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			String[] alumnoElegido = choListaAlumnoMatriculaEditar.getSelectedItem().split("-");
			String[] asignaturaElegida = choListaAsignaturasMatricula.getSelectedItem().split("-");

			//Crear sentencia
			sentencia = ("UPDATE matriculaciones SET idAlumnoFK = '" + alumnoElegido[0]
					+ "' , idAsignaturaFK = '" + asignaturaElegida[0] +
					"' WHERE idMatriculacion ="+txtEditarIdMatricula.getText());
			try
			{
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				// le pasamos la funcion guardar para el log
				objLog.guardar(usuario, sentencia);
				statement.executeUpdate(sentencia);

				lblMensaje.setText("Modificación realizada con éxito");

			}
			catch(SQLException sqle)
			{
				lblMensaje.setText("Error en la Modificación");
			}
			finally
			{
				//Dialogo confirmación de edición
				dlgConfirmacionEdicionMatricula.setLayout(new FlowLayout());
				dlgConfirmacionEdicionMatricula.setSize(220,120);
				dlgConfirmacionEdicionMatricula.setResizable(false);
				dlgConfirmacionEdicionMatricula.setLocationRelativeTo(null);
				dlgConfirmacionEdicionMatricula.addWindowListener(this);
				dlgConfirmacionEdicionMatricula.add(lblMensaje);
				dlgConfirmacionEdicionMatricula.setVisible(true);

				//Desconectar con la BD
				bd.desconectar(connection);
			}

		}


		//Si pulsamos el botón Consulta dentro del menú Matriculaciones nos aparece la ventana de Consulta de matriculas
		else if (eventoBoton.getSource().equals(mniConsultaMatriculacion))
		{
			frmConsultaMatricula.setLayout(new FlowLayout());
			//Conectar
			bd = new BaseDatos();
			connection = bd.conectar();
			sentencia = "SELECT matriculaciones.idMatriculacion, alumnos.nombreAlumno, asignaturas.nombreAsignatura FROM matriculaciones, alumnos, asignaturas where matriculaciones.idAlumnoFK = alumnos.idAlumno and matriculaciones.idAsignaturaFK = asignaturas.idAsignatura; ";
			try
			{
				//Crear una sentencia
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				rs= statement.executeQuery(sentencia);
				listadoConsultaMatricula.setText("");
				listadoConsultaMatricula.append("Matricula\tAlumno\tAsignatura\n");

				while(rs.next())
				{
					listadoConsultaMatricula.append(rs.getString("idMatriculacion")
							+ "\t"+rs.getString("nombreAlumno")
							+ "\t"+rs.getString("nombreAsignatura")
							+"\n");

				}

			}
			catch (SQLException sqle)
			{
				lblMensaje.setText("Error");
			}

			//Desconectar con la BD
			bd.desconectar(connection);

			frmConsultaMatricula.add(listadoConsultaMatricula);
			frmConsultaMatricula.add(btnPdfMatricula);
			btnPdfMatricula.addActionListener(this);
			btnAceptarConsultaMatricula.addActionListener(this);
			frmConsultaMatricula.add(btnAceptarConsultaMatricula);

			frmConsultaMatricula.setSize(400,200);
			frmConsultaMatricula.setResizable(false);
			frmConsultaMatricula.setLocationRelativeTo(null);
			frmConsultaMatricula.addWindowListener(this);
			frmConsultaMatricula.setVisible(true);
		}
		//Si pulsamos el botón Aceptar la ventana se cierra
		else if (eventoBoton.getSource().equals(btnAceptarConsultaMatricula))
		{
			frmConsultaMatricula.setVisible(false);
		}
		// Exportación a PDF Matricula
		else if (eventoBoton.getSource().equals(btnPdfMatricula))
		{

			try
			{
				// Creamos el documento
				Document docPdfMatricula = new Document();
				try 
				{
					// Fichero donde se deja el documento
					FileOutputStream ficheroDocMatricula = new FileOutputStream("docMatriculaciones.pdf");
					// Se asocia el documento y se indica el espacio entre lineas
					PdfWriter.getInstance(docPdfMatricula, ficheroDocMatricula).setInitialLeading(20);
				}
				catch  (FileNotFoundException fileNotFoundException)
				{
					JOptionPane.showMessageDialog(null, "No se encontró el fichero para generar el pdf." 
							+ fileNotFoundException,"ERROR", JOptionPane.ERROR_MESSAGE);
				}

				// Se abre el documento
				docPdfMatricula.open();

				// Imagen
				//Añadir la imagen con sus características
				Image imagen;
				try 
				{
					imagen = Image.getInstance("logo.jpg");
					imagen.scaleToFit(100,100);
					imagen.setAlignment(Chunk.ALIGN_LEFT);
					docPdfMatricula.add(imagen);
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				//Desconectar con la BD
				bd.desconectar(connection);

				// Encabezado
				Paragraph encabezado = new Paragraph(" Práctica: Programa Gestión - Universidad", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color
				encabezado.setAlignment(Element.ALIGN_LEFT);


				// Salto de línea
				Paragraph saltoDeLinea = new Paragraph();
				saltoDeLinea.add("\n\n");


				// Titulo
				Paragraph titulo = new Paragraph("Listado Matriculaciones", 
						FontFactory.getFont("arial", // fuente
								20, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.BLUE)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);


				// Pie de Página
				Paragraph piePagina = new Paragraph("Sara Fernández - 1º DAW", 
						FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.BOLDITALIC, // estilo
								BaseColor.GRAY)); // color)
				piePagina.setAlignment(Element.ALIGN_RIGHT);


				// Crear la tabla
				PdfPTable tabla = new PdfPTable(3);

				//Rellenar las cabeceras de las columnas
				Paragraph columna1 = new Paragraph("Nº MATRÍCULA");
				columna1.getFont().setStyle(Font.BOLD);
				columna1.getFont().setSize(12);
				tabla.addCell(columna1);

				Paragraph columna2 = new Paragraph("ALUMNO");
				columna2.getFont().setStyle(Font.BOLD);
				columna2.getFont().setSize(12);
				tabla.addCell(columna2);

				Paragraph columna3 = new Paragraph("ASIGNATURA");
				columna3.getFont().setStyle(Font.BOLD);
				columna3.getFont().setSize(12);
				tabla.addCell(columna3);

				//Rellenar las filas de la tabla.
				//Conectar
				bd = new BaseDatos();
				connection = bd.conectar();
				sentencia ="SELECT matriculaciones.idMatriculacion, alumnos.nombreAlumno, asignaturas.nombreAsignatura FROM matriculaciones, alumnos, asignaturas where matriculaciones.idAlumnoFK = alumnos.idAlumno and matriculaciones.idAsignaturaFK = asignaturas.idAsignatura; ";
				try
				{
					//Crear una sentencia
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

					//Crear un objeto ResultSet para guardar lo obtenido
					//y ejecutar la sentencia SQL
					rs= statement.executeQuery(sentencia);
					while(rs.next())
					{
						tabla.addCell(rs.getString("idMatriculacion"));
						tabla.addCell(rs.getString("nombreAlumno"));
						tabla.addCell(rs.getString("nombreAsignatura"));
					}
				}
				catch (SQLException sqle)
				{
					lblMensaje.setText("Error");
				}

				// Añadir los componente creados
				docPdfMatricula.add(encabezado);
				docPdfMatricula.add(saltoDeLinea);
				docPdfMatricula.add(titulo);
				docPdfMatricula.add(saltoDeLinea);
				docPdfMatricula.add(tabla);
				docPdfMatricula.add(saltoDeLinea);
				docPdfMatricula.add(piePagina);

				//Cerrar el documento
				docPdfMatricula.close();

			}
			catch (DocumentException e)
			{

				e.printStackTrace();
			}

			dlgConfirmacionPdfProfesor.setLayout(new FlowLayout());
			dlgConfirmacionPdfProfesor.setSize(220,120);
			dlgConfirmacionPdfProfesor.setResizable(false);
			dlgConfirmacionPdfProfesor.setLocationRelativeTo(null);
			dlgConfirmacionPdfProfesor.addWindowListener(this);
			lblMensaje.setText("Exportación realizada con éxito");
			dlgConfirmacionPdfProfesor.add(lblMensaje);
			dlgConfirmacionPdfProfesor.setVisible(true);

		}
		// Ayuda
		else if (eventoBoton.getSource().equals(mniAyuda))
		{
			try
			{
				Runtime.getRuntime().exec("hh.exe ProgramaGestion.chm");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}


}



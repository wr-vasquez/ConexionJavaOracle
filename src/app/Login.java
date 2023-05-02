package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame implements ActionListener {
	
	//Variables de conexion
	public String usuario;
	public String clave;
	public static Connection cn;
	public static Statement st;
	public static ResultSet rs;
	

	private JPanel contentPane;
	private JTextField inputUser;
	private JPasswordField inputPassword;
	private JButton btnIngresar;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("USER");
		lblUsuario.setBounds(157, 176, 62, 19);
		contentPane.add(lblUsuario);
		
		inputUser = new JTextField();
		inputUser.setBounds(212, 176, 96, 19);
		contentPane.add(inputUser);
		inputUser.setColumns(10);
		
		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setBounds(157, 245, 45, 13);
		contentPane.add(lblClave);
		
		inputPassword = new JPasswordField();
		inputPassword.setBounds(212, 242, 96, 19);
		contentPane.add(inputPassword);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(this);
		btnIngresar.setIcon(null);
		btnIngresar.setBounds(478, 396, 96, 40);
		contentPane.add(btnIngresar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(38, 389, 85, 21);
		contentPane.add(btnSalir);
	}
	
	//METODO PARA CONECTAR LA BASE DE DATOS
	
	public void conectar()
	{
	    try {
	        String url="jdbc:oracle:thin:@localhost:1521:XE";
	    cn = DriverManager.getConnection(url, "SYSTEM","umg123");
	    st = cn.createStatement();       
	  

	    }
	    catch (Exception e)
	    {
	        JOptionPane.showMessageDialog(null,"no se pudo conectar)"+e);
	    }
	}
	 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalir) {
			do_btnNewButton_actionPerformed(e);
		}
		if (e.getSource() == btnIngresar) {
			do_btnIngresar_actionPerformed(e);
		}
	}
	protected void do_btnIngresar_actionPerformed(ActionEvent e) {
		 //new Menu().setVisible(true);
		// new Conexion().equals(e);
		
		usuario = inputUser.getText();
		clave = String.valueOf(inputPassword.getPassword());
		
		System.out.println(usuario);
		System.out.println(clave);
		
		conectar();
		
		try {
			String consulta = "select * from USUARIOS where USERNAME = '"+usuario+"' AND CLAVE=('"+clave+"') ";
			rs = st.executeQuery(consulta);
			rs= st.getResultSet();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Usuario correcto");
				dispose();
				new Menu().setVisible(true);
			}else {
				System.out.println("Error Usuario invalido");
				JOptionPane.showInternalMessageDialog(null, "ERROR");
			}
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
		 
		 
		
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		System.exit(ABORT);
	}
}

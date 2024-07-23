import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Clientes extends JFrame {
	
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/bdclientes";
	private static final String usuario="root";
	private static final String clave="ricardoconexion123";
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEdad;
	private JTextField textDireccion;
	private JTextField textCorreo;
	private JTextField textTelefono;
	private JTextField textCodigo;
	private JTextField textDPI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes frame = new Clientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Metodo para conectarse a la BD 
	
	public static Connection Conexion(){
		
		Connection conex=null;
		
		try {
			Class.forName(driver);
			conex=DriverManager.getConnection(url, usuario, clave);
		}catch(Exception e){
			System.out.println("Error en la conexion a la BD\n" + e.getMessage().toString());
		
		}
		
		/*
		finally {
			try {
				if(conex != null) {
					conex.close();
				}
			}catch(SQLException e) {
				System.out.println("Error al cerrar la base de datos");
			}
		}
		*/
		
		return conex;
	}
	
	
	void limpiar() {
		textDPI.setText(null);
		textNombre.setText(null);
		textApellido.setText(null);
		textEdad.setText(null);
		textDireccion.setText(null);
		textTelefono.setText(null);
		textCorreo.setText(null);		
		textCodigo.setText(null);
		
	}
	/**
	 * Create the frame.
	 */
	public Clientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(216, 10, 1, 1);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setBounds(60, 65, 68, 17);
		contentPane.add(lblApellido);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNombre = new JLabel("Nombres:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(60, 37, 68, 17);
		contentPane.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Edad: ");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdad.setBounds(60, 93, 68, 17);
		contentPane.add(lblEdad);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccion.setBounds(60, 121, 68, 17);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setBounds(60, 179, 68, 17);
		contentPane.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCorreo.setBounds(60, 151, 68, 17);
		contentPane.add(lblCorreo);
		
		textNombre = new JTextField();
		textNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textNombre.setBounds(138, 37, 149, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(138, 65, 149, 20);
		contentPane.add(textApellido);
		
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(138, 93, 55, 20);
		contentPane.add(textEdad);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(138, 121, 149, 20);
		contentPane.add(textDireccion);
		
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(138, 151, 149, 20);
		contentPane.add(textCorreo);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(138, 179, 149, 20);
		contentPane.add(textTelefono);
		
		JButton btnAgregar = new JButton("Guardar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conex = null;
				
				try {
					conex = Conexion();
					
					if(conex != null) {
						System.out.println("Conexion a BD Correcta");
					}else {
						System.out.println("No se pudo conectar a la BD");
					}
					
					//Insert a la BD
					
					String sqlinsert = "INSERT INTO clientes(DPI_cliente, Nombres_cliente, Apellidos_cliente, Edad_cliente, Direc_cliente, Telefono_cliente, Correo_cliente) VALUES(?, ?, ?, ?, ?, ?, ?)";
					
					PreparedStatement pstnt = conex.prepareStatement(sqlinsert);
					
					pstnt.setString(1, textDPI.getText());
					pstnt.setString(2, textNombre.getText());
					pstnt.setString(3, textApellido.getText());
					pstnt.setInt(4, Integer.parseInt(textEdad.getText()));
					pstnt.setString(5, textDireccion.getText());
					pstnt.setString(6, textTelefono.getText());
					pstnt.setString(7, textCorreo.getText());
					
					int res = pstnt.executeUpdate();
					if(res > 0) {
						JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
						
					}else {
						JOptionPane.showMessageDialog(null, "No se insertaron registros en la BD");
					}
					limpiar();					
					conex.close();
					
				}catch(Exception ex) {
					System.out.println(ex);
					limpiar();
					
				}
				
			}
		});
		btnAgregar.setBounds(335, 120, 89, 23);
		contentPane.add(btnAgregar);
		
		JLabel lblDPI = new JLabel("DPI:");
		lblDPI.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDPI.setBounds(60, 10, 68, 17);
		contentPane.add(lblDPI);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conex = null;
				
				try {
					conex = Conexion();
					
					if(conex != null) {
						System.out.println("Conexion a BD Correcta");
					}else {
						System.out.println("No se pudo conectar a la BD");
					}
					
					String sqlconsulta = "SELECT * FROM clientes WHERE idClientes = ?";
					PreparedStatement pstnt = conex.prepareStatement(sqlconsulta);
					
					pstnt.setInt(1, Integer.parseInt(textCodigo.getText()));
					ResultSet rs = pstnt.executeQuery();
					
					if(rs .next()) {
						
						textCodigo.setText(rs.getString("idClientes"));
						textDPI.setText(rs.getString("DPI_cliente"));
						textNombre.setText(rs.getString("Nombres_cliente"));
						textApellido.setText(rs.getString("Apellidos_cliente"));
						textEdad.setText(rs.getString("Edad_cliente"));
						textDireccion.setText(rs.getString("Direc_cliente"));
						textTelefono.setText(rs.getString("Telefono_cliente"));
						textCorreo.setText(rs.getString("Correo_cliente"));
						
					}else {
						JOptionPane.showInternalMessageDialog(null, "No existe el registro en la BD");
					}
					
					conex.close();
					
					
					}catch(Exception ex) {
						System.out.println(ex);
						limpiar();				
					}
				}
				
		});
		btnBuscar.setBounds(335, 92, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conex = null;
				
				try {
					conex = Conexion();
					
					if(conex != null) {
						System.out.println("Conexion a BD Correcta");
					}else {
						System.out.println("No se pudo conectar a la BD");
					}
					
					//Insert a la BD
					
					String sqlupdate = "UPDATE clientes SET DPI_cliente=?, Nombres_cliente=?, Apellidos_cliente=?, Edad_cliente=?, Direc_cliente=?, Telefono_cliente=?, Correo_cliente=? WHERE idClientes=?";
					
					PreparedStatement pstnt = conex.prepareStatement(sqlupdate);
					
					pstnt.setString(1, textDPI.getText());
					pstnt.setString(2, textNombre.getText());
					pstnt.setString(3, textApellido.getText());
					pstnt.setInt(4, Integer.parseInt(textEdad.getText()));
					pstnt.setString(5, textDireccion.getText());
					pstnt.setString(6, textTelefono.getText());
					pstnt.setString(7, textCorreo.getText());
					pstnt.setString(8, textCodigo.getText());
					
					int res = pstnt.executeUpdate();
					if(res > 0) {
						JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
						
					}else {
						JOptionPane.showMessageDialog(null, "No se midificaron registros en la BD");
					}
					limpiar();					
					conex.close();
					
				}catch(Exception ex) {
					System.out.println(ex);
					limpiar();
					
				}
				
			}
		});
		btnModificar.setBounds(335, 150, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conex = null;
				
				try {
					conex = Conexion();
					
					if(conex != null) {
						System.out.println("Conexion a BD Correcta");
					}else {
						System.out.println("No se pudo conectar a la BD");
					}
					
					//Insert a la BD
					
					String sqldelete = "DELETE FROM clientes WHERE idClientes=?";
					
					PreparedStatement pstnt = conex.prepareStatement(sqldelete);
					
					pstnt.setInt(1, Integer.parseInt(textCodigo.getText()));
					
					int res = pstnt.executeUpdate();
					
					if(res > 0) {
						JOptionPane.showMessageDialog(null, "Datos eleminados correctamente");
						
					}else {
						JOptionPane.showMessageDialog(null, "No se eliminaron registros en la BD");
					}
					limpiar();					
					conex.close();
					
				}catch(Exception ex) {
					System.out.println(ex);
					limpiar();
					
				}
				
			}
		});
		btnEliminar.setBounds(335, 178, 89, 23);
		contentPane.add(btnEliminar);
		
		textCodigo = new JTextField();
		textCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textCodigo.setColumns(10);
		textCodigo.setBounds(335, 53, 89, 20);
		contentPane.add(textCodigo);
		
		textDPI = new JTextField();
		textDPI.setColumns(10);
		textDPI.setBounds(138, 10, 149, 20);
		contentPane.add(textDPI);
	}
}

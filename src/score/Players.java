package score;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JScrollPane;

public class Players {
	public JFrame playersWindow;
	public static DefaultListModel<String> playersTeam1 = new DefaultListModel<>(), playersTeam2 = new DefaultListModel<>();
	public static ArrayList<String> playersList1 = new ArrayList<>(), playersList2 = new ArrayList<>();
	private JTextField field_NamePlayer1 = new JTextField(), field_NPlayer1 = new JTextField(), field_NamePlayer2 = new JTextField(), field_NPlayer2 = new JTextField();
	private JButton btAddPlayer1 = new JButton("Add"), btAddPlayer2 = new JButton("Add"), btConfirmarPlayers = new JButton("Confirmar Jogadores");
		
	public static void main(String[] args) {  /**Launch the application.**/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Players window = new Players();
					window.playersWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Players() {  /**Create the application.**/
		initialize();
		labels();
		playerLists();
		buttons();
		inputs();
	}
	private void initialize() {  /**Initialize the contents of the playersWindow.**/
		playersWindow = new JFrame();
		playersWindow.setTitle("Score - Adicionar Jogadores");
		playersWindow.getContentPane().setBackground(new Color(181, 223, 247));
		playersWindow.setBounds(100, 100, 620, 405);
		playersWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		playersWindow.getContentPane().setLayout(null);
		playersWindow.setLocationRelativeTo(null);
		
		btAddPlayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_NamePlayer1.getText().isBlank()==true && field_NamePlayer1.getText()!=(String)field_NamePlayer1.getText()) {
					JOptionPane.showMessageDialog(playersWindow,"Insira um nome valido!",null,JOptionPane.WARNING_MESSAGE);
				}else {
					String player = "("+field_NPlayer1.getText().replace(" ", "")+") "+field_NamePlayer1.getText();
					playersTeam1.addElement(player);
					playersList1.add(player);
					field_NamePlayer1.setText("");
					field_NPlayer1.setText("");
				}
			}
		});
		
		btAddPlayer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(field_NamePlayer2.getText().isBlank()==true && field_NamePlayer2.getText()!=(String)field_NamePlayer2.getText()) {
					JOptionPane.showMessageDialog(playersWindow,"Insira um nome valido!",null,JOptionPane.WARNING_MESSAGE);
				}else {
					String player = "("+field_NPlayer2.getText().replace(" ", "")+") "+field_NamePlayer2.getText();
					playersTeam2.addElement(player);
					playersList2.add(player);
					field_NamePlayer2.setText("");
					field_NPlayer2.setText("");
				}
			}
		});
		
		btConfirmarPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Score.isDefaultPlayers = true;
				playersWindow.dispose();
				System.out.println(playersTeam1);
				System.out.println(playersList1);
				
				System.out.println(playersTeam2);
				System.out.println(playersList2);
			}
		});
	}
	private void inputs() {
		field_NPlayer1.setVerifyInputWhenFocusTarget(false);
		field_NPlayer1.setFocusTraversalKeysEnabled(false);
		field_NPlayer1.setBackground(Color.WHITE);
		field_NPlayer1.setForeground(Color.BLACK);
		field_NPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		field_NPlayer1.setFont(new Font("Roboto", Font.PLAIN, 12));
		field_NPlayer1.setColumns(2);
		field_NPlayer1.setBounds(24, 95, 40, 25);
		playersWindow.getContentPane().add(field_NPlayer1);
		
		field_NamePlayer1.setVerifyInputWhenFocusTarget(false);
		field_NamePlayer1.setFocusTraversalKeysEnabled(false);
		field_NamePlayer1.setBackground(Color.WHITE);
		field_NamePlayer1.setForeground(Color.BLACK);
		field_NamePlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		field_NamePlayer1.setFont(new Font("Roboto", Font.PLAIN, 12));
		field_NamePlayer1.setBounds(70, 95, 145, 25);
		playersWindow.getContentPane().add(field_NamePlayer1);
		field_NamePlayer1.setColumns(10);
		
		field_NPlayer2.setVerifyInputWhenFocusTarget(false);
		field_NPlayer2.setFocusTraversalKeysEnabled(false);
		field_NPlayer2.setBackground(Color.WHITE);
		field_NPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		field_NPlayer2.setForeground(Color.BLACK);
		field_NPlayer2.setFont(new Font("Roboto", Font.PLAIN, 12));
		field_NPlayer2.setColumns(2);
		field_NPlayer2.setBounds(324, 95, 40, 25);
		playersWindow.getContentPane().add(field_NPlayer2);
		
		field_NamePlayer2.setVerifyInputWhenFocusTarget(false);
		field_NamePlayer2.setFocusTraversalKeysEnabled(false);
		field_NamePlayer2.setBackground(Color.WHITE);
		field_NamePlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		field_NamePlayer2.setForeground(Color.BLACK);
		field_NamePlayer2.setFont(new Font("Roboto", Font.PLAIN, 12));
		field_NamePlayer2.setColumns(10);
		field_NamePlayer2.setBounds(370, 95, 145, 25);
		playersWindow.getContentPane().add(field_NamePlayer2);	
	}
	private void buttons() {
		//Botão Adicionar Jogador no Time 1
		btAddPlayer1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		btAddPlayer1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btAddPlayer1.setBackground(new Color(18, 79, 148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btAddPlayer1.setBackground(new Color(4, 44, 89));
			}
		});
		btAddPlayer1.setBackground(new Color(4, 44, 89));
		btAddPlayer1.setBorder(null);
		btAddPlayer1.setFocusPainted(false);
		btAddPlayer1.setFocusTraversalKeysEnabled(false);
		btAddPlayer1.setFocusable(false);
		btAddPlayer1.setRequestFocusEnabled(false);
		btAddPlayer1.setRolloverEnabled(false);
		btAddPlayer1.setVerifyInputWhenFocusTarget(false);
		btAddPlayer1.setForeground(Color.WHITE);
		btAddPlayer1.setFont(new Font("Roboto", Font.PLAIN, 12));
		btAddPlayer1.setBounds(222, 95, 56, 25);
		playersWindow.getContentPane().add(btAddPlayer1);
		//Botão Adicionar Jogador no Time 2
		btAddPlayer2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btAddPlayer2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btAddPlayer2.setBackground(new Color(18, 79, 148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btAddPlayer2.setBackground(new Color(4, 44, 89));
			}
		});
		btAddPlayer2.setBackground(new Color(4, 44, 89));
		btAddPlayer2.setBorder(null);
		btAddPlayer2.setFocusPainted(false);
		btAddPlayer2.setFocusTraversalKeysEnabled(false);
		btAddPlayer2.setFocusable(false);
		btAddPlayer2.setRequestFocusEnabled(false);
		btAddPlayer2.setRolloverEnabled(false);
		btAddPlayer2.setVerifyInputWhenFocusTarget(false);
		btAddPlayer2.setForeground(Color.WHITE);
		btAddPlayer2.setFont(new Font("Roboto", Font.PLAIN, 12));
		btAddPlayer2.setBounds(522, 95, 56, 25);
		playersWindow.getContentPane().add(btAddPlayer2);
		//Botão Confirmar Jogadores
		btConfirmarPlayers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btConfirmarPlayers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btConfirmarPlayers.setBackground(new Color(18, 79, 148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btConfirmarPlayers.setBackground(new Color(4, 44, 89));
			}
		});
		btConfirmarPlayers.setForeground(Color.WHITE);
		btConfirmarPlayers.setBackground(new Color(4, 44, 89));
		btConfirmarPlayers.setBorder(null);
		btConfirmarPlayers.setFocusPainted(false);
		btConfirmarPlayers.setFocusTraversalKeysEnabled(false);
		btConfirmarPlayers.setFocusable(false);
		btConfirmarPlayers.setRequestFocusEnabled(false);
		btConfirmarPlayers.setRolloverEnabled(false);
		btConfirmarPlayers.setVerifyInputWhenFocusTarget(false);
		btConfirmarPlayers.setFont(new Font("Roboto", Font.PLAIN, 12));
		btConfirmarPlayers.setBounds(227, 330, 150, 25);
		playersWindow.getContentPane().add(btConfirmarPlayers);
	}
	private void playerLists() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 140, 200, 165);
		playersWindow.getContentPane().add(scrollPane);
		
		JList<String> list = new JList<>(playersTeam1);
		list.setVerifyInputWhenFocusTarget(false);
		list.setFocusTraversalKeysEnabled(false);
		list.setLocation(-56, 0);
		list.setBorder(null);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectionBackground(new Color(102, 204, 255));
		list.setBackground(Color.WHITE);
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(360, 140, 200, 165);
		playersWindow.getContentPane().add(scrollPane2);
		
		JList<String> list2 = new JList<String>(playersTeam2);
		list2.setVerifyInputWhenFocusTarget(false);
		list2.setFocusTraversalKeysEnabled(false);
		list2.setLocation(451, 0);
		list2.setBorder(null);
		scrollPane2.setViewportView(list2);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setSelectionBackground(new Color(102, 204, 255));
		list2.setForeground(Color.BLACK);
		list2.setFont(new Font("Roboto", Font.PLAIN, 18));
		list2.setBackground(Color.WHITE);
	}
	private void labels() {
		JLabel lblTitle = new JLabel("INSIRA O NOME E N\u00DAMERO DOS JOGADORES");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBounds(120, 8, 364, 30);
		playersWindow.getContentPane().add(lblTitle);
		
		JLabel lblNameTeam1 = new JLabel(Score.nTeam1);
		lblNameTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameTeam1.setFont(new Font("Roboto", Font.BOLD, 16));
		lblNameTeam1.setForeground(Color.BLACK);
		lblNameTeam1.setBounds(50, 40, 150, 20);
		playersWindow.getContentPane().add(lblNameTeam1);
		
		JLabel lblName1 = new JLabel("Nome:");
		lblName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblName1.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblName1.setForeground(Color.BLACK);
		lblName1.setBounds(70, 75, 50, 15);
		playersWindow.getContentPane().add(lblName1);
		
		JLabel lblN1 = new JLabel("N\u00B0");
		lblN1.setHorizontalAlignment(SwingConstants.CENTER);
		lblN1.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblN1.setForeground(Color.BLACK);
		lblN1.setBounds(24, 75, 20, 15);
		playersWindow.getContentPane().add(lblN1);
		
		JLabel lblNameTeam2 = new JLabel(Score.nTeam2);
		lblNameTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameTeam2.setForeground(Color.BLACK);
		lblNameTeam2.setFont(new Font("Roboto", Font.BOLD, 16));
		lblNameTeam2.setBounds(404, 40, 150, 20);
		playersWindow.getContentPane().add(lblNameTeam2);
		
		JLabel lblName2 = new JLabel("Nome:");
		lblName2.setHorizontalAlignment(SwingConstants.CENTER);
		lblName2.setForeground(Color.BLACK);
		lblName2.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblName2.setBounds(370, 75, 50, 15);
		playersWindow.getContentPane().add(lblName2);
		
		JLabel lblN2 = new JLabel("N\u00B0");
		lblN2.setHorizontalAlignment(SwingConstants.CENTER);
		lblN2.setForeground(Color.BLACK);
		lblN2.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblN2.setBounds(324, 75, 20, 15);
		playersWindow.getContentPane().add(lblN2);	
	}
}
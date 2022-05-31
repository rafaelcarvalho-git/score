package score;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class MatchData {
	public JFrame dataWindow;
	private JButton btSaveData = new JButton("Salvar"), btNewGame = new JButton("Novo Jogo"), btExit = new JButton("Sair");
	
	public static void main(String[] args) {  /**Launch the application.**/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchData data = new MatchData();
					data.dataWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MatchData() {  /**Create the application.**/
		initialize();
		labels();
		widgets();
	}
	private void initialize() {  /**Initialize the contents of the dataWindow.**/
		dataWindow = new JFrame();
		dataWindow.setResizable(false);
		dataWindow.getContentPane().setBackground(new Color(181, 223, 247));
		dataWindow.setTitle("Score - Dados da Partida");
		dataWindow.setBounds(100, 100, 650, 380);
		dataWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataWindow.getContentPane().setLayout(null);
		dataWindow.setLocationRelativeTo(null);
		
		Score.list.setSelectionBackground(new Color(181, 223, 247));
		Score.list.setBackground(new Color(181, 223, 247));
		Score.list.setBounds(25, 102, 175, 175);
		dataWindow.getContentPane().add(Score.list);
		Score.list.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		Score.list2.setSelectionBackground(new Color(181, 223, 247));
		Score.list2.setBackground(new Color(181, 223, 247));
		Score.list2.setBounds(436, 102, 175, 175);
		dataWindow.getContentPane().add(Score.list2);
		Score.list2.setFont(new Font("Roboto", Font.PLAIN, 18));
		/**BOTAO SALVAR DADOS DA PARTIDA**/
		btSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Partida\n"+Score.nTeam1+" "+Score.goalTeam1+" X "+Score.goalTeam2+" "+Score.nTeam2+"\n");
				System.out.println(Score.min);
				System.out.println(Score.goalTeam1);
				System.out.println(Score.goalTeam2);
				System.out.println(Score.count);
				System.out.println(Score.acrescimo);
				System.out.println(Score.acrescimoTotal);
				System.out.println(Score.minGol);
				System.out.println(Score.sec);
			}
		});
		
		/**BOTAO SAIR DO PROGRAMA**/
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Sim","Não"};
				int exit = JOptionPane.showOptionDialog(dataWindow,"Deseja sair do sistema?",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
				if (exit==0) {
					dataWindow.dispose();
				}
			}
		});
		
		/**BOTAO NOVA PARTIDA**/
		btNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Sim","Não"};
				int newGame = JOptionPane.showOptionDialog(dataWindow,"Deseja iniciar um novo jogo?",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
				if (newGame==0) {
					Score.nTeam1="";
					Score.nTeam2="";
					Score.tempo=0;
					Score.isDefaultPlayers=false;
					
					Players.playersTeam1.clear();
					Players.playersTeam2.clear();
					Players.playersList1.clear();
					Players.playersList2.clear();
					
					Score.goalList1.clear();
					Score.goalList2.clear();
					
					
					Score.goalTeam1=0;
					Score.goalTeam2=0;
					Score.count=0;
					Score.min=0;
					Score.acrescimo=0;
					Score.acrescimoTotal=0;
					Score.goalList1.clear();
					Score.goalList2.clear();
					Score.minGol="";
					Score.sec=0;
					Score.inicialTime=0;
					
					dataWindow.dispose();
					Score score = new Score();
					score.scoreWindow.setVisible(true);
				}
			}
		});
	}
	private void widgets() {
		btSaveData.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btSaveData.setBorder(null);
		btSaveData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btSaveData.setBackground(new Color(18, 79, 148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btSaveData.setBackground(new Color(4, 44, 89));
			}
		});
		btSaveData.setVerifyInputWhenFocusTarget(false);
		btSaveData.setFocusPainted(false);
		btSaveData.setFocusTraversalKeysEnabled(false);
		btSaveData.setFocusable(false);
		btSaveData.setBorderPainted(false);
		btSaveData.setRequestFocusEnabled(false);
		btSaveData.setRolloverEnabled(false);
		btSaveData.setBackground(new Color(4, 44, 89));
		btSaveData.setForeground(Color.WHITE);
		btSaveData.setFont(new Font("Roboto", Font.PLAIN, 15));
		btSaveData.setBounds(100, 300, 120, 25);
		dataWindow.getContentPane().add(btSaveData);
		//Botão para iniciar um novo jogo
		btNewGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btNewGame.setBorder(null);
		btNewGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btNewGame.setBackground(new Color(18, 79, 148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btNewGame.setBackground(new Color(4, 44, 89));
			}
		});
		btNewGame.setVerifyInputWhenFocusTarget(false);
		btNewGame.setRolloverEnabled(false);
		btNewGame.setRequestFocusEnabled(false);
		btNewGame.setBorderPainted(false);
		btNewGame.setBackground(new Color(4, 44, 89));
		btNewGame.setForeground(Color.WHITE);
		btNewGame.setFont(new Font("Roboto", Font.PLAIN, 15));
		btNewGame.setFocusable(false);
		btNewGame.setFocusTraversalKeysEnabled(false);
		btNewGame.setFocusPainted(false);
		btNewGame.setBounds(257, 300, 120, 25);
		dataWindow.getContentPane().add(btNewGame);
		//Botão para Encerrar e Fechar o Sistema
		btExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btExit.setBorder(null);
		btExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btExit.setBackground(new Color(18, 79, 148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btExit.setBackground(new Color(4, 44, 89));
			}
		});
		btExit.setVerifyInputWhenFocusTarget(false);
		btExit.setRolloverEnabled(false);
		btExit.setRequestFocusEnabled(false);
		btExit.setBorderPainted(false);
		btExit.setBackground(new Color(4, 44, 89));
		btExit.setForeground(Color.WHITE);
		btExit.setFont(new Font("Roboto", Font.PLAIN, 15));
		btExit.setFocusable(false);
		btExit.setFocusTraversalKeysEnabled(false);
		btExit.setFocusPainted(false);
		btExit.setBounds(414, 300, 120, 25);
		dataWindow.getContentPane().add(btExit);				
	}
	private void labels() {
		JLabel lblNameTeam1 = new JLabel(Score.nTeam1);
		lblNameTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameTeam1.setForeground(Color.BLACK);
		lblNameTeam1.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblNameTeam1.setBackground(Color.WHITE);
		lblNameTeam1.setBounds(15, 34, 225, 50);
		dataWindow.getContentPane().add(lblNameTeam1);

		JLabel lblScore1 = new JLabel(Integer.toString(Score.goalTeam1));
		lblScore1.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore1.setForeground(Color.WHITE);
		lblScore1.setBackground(Color.WHITE);
		lblScore1.setFont(new Font("Dialog", Font.PLAIN, 80));
		lblScore1.setBounds(231, 30, 70, 65);
		dataWindow.getContentPane().add(lblScore1);
			
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Dialog", Font.PLAIN, 45));
		lblX.setBackground(Color.WHITE);
		lblX.setBounds(292, 42, 50, 40);
		dataWindow.getContentPane().add(lblX);
		
		JLabel lblScore2 = new JLabel(Integer.toString(Score.goalTeam2));
		lblScore2.setForeground(Color.WHITE);
		lblScore2.setBackground(Color.WHITE);
		lblScore2.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore2.setFont(new Font("Dialog", Font.PLAIN, 80));
		lblScore2.setBounds(335, 30, 70, 65);
		dataWindow.getContentPane().add(lblScore2);
		
		JLabel lblNameTeam2 = new JLabel(Score.nTeam2);
		lblNameTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameTeam2.setForeground(Color.BLACK);
		lblNameTeam2.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblNameTeam2.setBackground(Color.WHITE);
		lblNameTeam2.setBounds(395, 34, 225, 50);
		dataWindow.getContentPane().add(lblNameTeam2);
		
		JLabel lblGameTime = new JLabel("Tempo total de jogo:");
		lblGameTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameTime.setBackground(Color.WHITE);
		lblGameTime.setForeground(Color.BLACK);
		lblGameTime.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblGameTime.setBounds(217, 100, 200, 30);
		dataWindow.getContentPane().add(lblGameTime);
		
		JLabel lblTime = new JLabel("12:00");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.BLACK);
		lblTime.setBackground(Color.WHITE);
		lblTime.setFont(new Font("Dialog", Font.PLAIN, 35));
		lblTime.setBounds(272, 135, 90, 30);
		dataWindow.getContentPane().add(lblTime);
	
	}
}
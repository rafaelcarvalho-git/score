package score;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class Score {
	public JFrame scoreWindow;
	public static String minGol="", goalPlayer="", nTeam1="",nTeam2="";
	public static int goalTeam1=0, goalTeam2=0, tempo, inicialTime=tempo, count=0, sec=0, min=0, acrescimo=0, acrescimoTotal=0;
	private static Timer cronometro;
	public static DefaultListModel<String> goalList1 = new DefaultListModel<>(), goalList2 = new DefaultListModel<>();
	public static JList<String> list = new JList<>(goalList1), list2 = new JList<>(goalList2);	
	public static boolean second=false,end=false, isDefaultPlayers=false, secondTime=false;
	private JTextField nameField1, nameField2;
	private JButton btGoalTeam1 = new JButton("0"), btGoalTeam2 = new JButton("0");
	private JButton btStartGame = new JButton("Iniciar"), btSecondTime = new JButton("Segundo Tempo"), btEndGame = new JButton("Finalizar Jogo");
	private JButton btAddTime = new JButton("Acr\u00E9scimo"), btConfirmAddTime = new JButton("Confirmar"), btConfirmGame = new JButton("Confirmar Jogo");
	private JCheckBox chkSecondTime = new JCheckBox("2\u00B0 Tempo"), chkPlayersName = new JCheckBox("Predefinir nome dos jogadores");
	private JSeparator separator = new JSeparator(), separator2 = new JSeparator();
	private JSpinner spinnerAddTime = new JSpinner(), spinnerTime = new JSpinner();
	JLabel nameTeam1 = new JLabel(), nameTeam2 = new JLabel(), time = new JLabel("00:00"), lblTeamName1 = new JLabel("Nome do Time:"), lblTeamName2 = new JLabel("Nome do Time:"), lbPlus = new JLabel("+"), lbAddTime = new JLabel("00"), lbTempAcrescimo = new JLabel("Tempo de Acr\u00E9scimo:"), lbDadosDaPartida = new JLabel("Dados da Partida"), lbTempoDeJogo = new JLabel("Tempo de Jogo:");
	JScrollPane scrollPane = new JScrollPane(), scrollPane2 = new JScrollPane();
	
	public static void main(String[] args) {  /**Launch the application.**/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Score windowScore = new Score();
					windowScore.scoreWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Score() {  /**Create the application.**/
		initialize();  //CHAMA A TELA PRINCIPAL
		labels();  //ADICIONA LABELS E DETALHES
		lists();  //ADICIONA AS LISTAS DE NOMES DOS JOGADORES
		widgets(); //ADICIONA OS EFEITOS - BOTOES - CHKBOXES
	}
	private void initialize() {  /**Initialize the contents of the scoreWindow.**/
		scoreWindow = new JFrame();
		scoreWindow.getContentPane().setBackground(new Color(51, 172, 242));
		scoreWindow.setBounds(100, 100, 1280, 720);
		scoreWindow.setTitle("Score");
		scoreWindow.setResizable(false);
		scoreWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreWindow.setLocationRelativeTo(null);
		
		/**BOTAO GOL TIME 1**/
		btGoalTeam1.setBounds(465, 255, 112, 160);
		btGoalTeam1.setEnabled(false);  
		btGoalTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minGol = "";
				minGol = time.getText().replace(":", "");
				minGol = minGol.substring(0,2);
		        if (isDefaultPlayers == true) {
		        	Object[] optPlayersTeam1 = Players.playersList1.toArray();
			        try {
			        	goalPlayer = (String)JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam1, JOptionPane.QUESTION_MESSAGE, null, optPlayersTeam1, "");
					} catch (NullPointerException nexc) {
						if (goalPlayer.isBlank()==true || goalPlayer==null) {
							JOptionPane.showMessageDialog(scoreWindow,"Insira o nome do Jogador!","Erro",JOptionPane.WARNING_MESSAGE);
							goalPlayer = (String)JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam1, JOptionPane.QUESTION_MESSAGE, null, optPlayersTeam1, "");
						}
					} finally {
						if (goalPlayer!=null && goalPlayer.isBlank()==false){
							goalList1.addElement(minGol+"' "+goalPlayer);
							goalTeam1 += 1;
							btGoalTeam1.setText(Integer.toString(goalTeam1));
						}
					}
		        }else {
		        	try {
						goalPlayer = JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam1, JOptionPane.INFORMATION_MESSAGE);
					} catch (NullPointerException nexc) {
						if (goalPlayer.isBlank()==true || goalPlayer==null) {
							JOptionPane.showMessageDialog(scoreWindow,"Insira o nome do Jogador!","Erro",JOptionPane.WARNING_MESSAGE);
							goalPlayer = JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam1, JOptionPane.INFORMATION_MESSAGE);
						}
					} finally {
						if (goalPlayer!=null && goalPlayer.isBlank()==false){
							goalList1.addElement(minGol+"' "+goalPlayer);
							goalTeam1 += 1;
							btGoalTeam1.setText(Integer.toString(goalTeam1));
						}
					}
		        }		        				
			}
		});		
		
		/**BOTAO GOL TIME 2**/
		btGoalTeam2.setBounds(690, 255, 112, 160);
		btGoalTeam2.setEnabled(false);  
		btGoalTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minGol = "";
				minGol = time.getText().replace(":", "");
				minGol = minGol.substring(0,2);
		        if (isDefaultPlayers == true) {
		        	Object [] optPlayersTeam2 = Players.playersList2.toArray();
			        try {
			        	goalPlayer = (String)JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam2, JOptionPane.QUESTION_MESSAGE, null, optPlayersTeam2, "");
					} catch (NullPointerException nexc) {
						if (goalPlayer.isBlank()==true || goalPlayer==null) {
							JOptionPane.showMessageDialog(scoreWindow,"Insira o nome do Jogador!","Erro",JOptionPane.WARNING_MESSAGE);
							goalPlayer = (String)JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam2, JOptionPane.QUESTION_MESSAGE, null, optPlayersTeam2, "");
						}
					} finally {
						if (goalPlayer!=null && goalPlayer.isBlank()==false){
							goalList2.addElement(minGol+"' "+goalPlayer);
							goalTeam2 += 1;
							btGoalTeam2.setText(Integer.toString(goalTeam2));
						}
					}
		        }else {
		        	try {
						goalPlayer = JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam2, JOptionPane.INFORMATION_MESSAGE);
					} catch (NullPointerException nexc) {
						if (goalPlayer.isBlank()==true || goalPlayer==null) {
							JOptionPane.showMessageDialog(scoreWindow,"Insira o nome do Jogador!","Erro",JOptionPane.WARNING_MESSAGE);
							goalPlayer = JOptionPane.showInputDialog(scoreWindow, "Nome do Jogador:", "Gol para o "+nTeam2, JOptionPane.INFORMATION_MESSAGE);
						}
					} finally {
						if (goalPlayer!=null && goalPlayer.isBlank()==false){
							goalList2.addElement(minGol+"' "+goalPlayer);
							goalTeam2 += 1;
							btGoalTeam2.setText(Integer.toString(goalTeam2));
						}
					}
		        }					 				
			}
		});
		
		/**BOTAO INICIAR PARTIDA**/
		btStartGame.setBounds(562, 480, 150, 40);
		btStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btGoalTeam1.setEnabled(true);
				btGoalTeam2.setEnabled(true);
				btStartGame.setVisible(false);
				btAddTime.setVisible(true);
				btSecondTime.setVisible(false);
				crono(); //INICIA O CRONOMETRO												
			}
		});
		
		/**BOTAO ACRESCIMO TEMPO**/
		btAddTime.setBounds(562, 532, 150, 40);
		btAddTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btAddTime.setVisible(false);
				lbTempAcrescimo.setVisible(true);								
				spinnerAddTime.setVisible(true);							
				btConfirmAddTime.setVisible(true);
				btConfirmAddTime.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btConfirmAddTime.setVisible(false);
						spinnerAddTime.setVisible(false);
						lbTempAcrescimo.setVisible(false);
						btAddTime.setVisible(true);						
						if ((int) spinnerAddTime.getValue()==0) {
							btConfirmAddTime.setVisible(false);
							spinnerAddTime.setVisible(false);
							lbTempAcrescimo.setVisible(false);
							btAddTime.setVisible(true);
						}else {
							acrescimo = (int) spinnerAddTime.getValue();
							acrescimoTotal +=acrescimo;
							tempo += acrescimo;
							lbPlus.setVisible(true);	
							lbAddTime.setText(String.valueOf(acrescimoTotal));
							lbAddTime.setVisible(true);
							lbTempAcrescimo.setVisible(false);
						}
						acrescimo = 0;
						spinnerAddTime.setValue(0);
					}
				});				
			}
		});	
		
		/**BOTAO FINALIZAR PARTIDA**/
		btEndGame.setBounds(562, 584, 150, 40);
		btEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				cronometro.cancel();
				scoreWindow.dispose();
				MatchData matchdata = new MatchData();
				matchdata.dataWindow.setVisible(true);
			}
		});	
		
		/**INPUT NOME TIME 1 e 2**/
		nameField1 = new JTextField();
		nameField1.setOpaque(false);
		nameField2 = new JTextField();
		nameField2.setOpaque(false);
	
		/**CHECKBOX PREDEFINIR NOMES DE JOGADORES**/
		chkPlayersName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nTeam1 = nameField1.getText();
				nTeam2 = nameField2.getText();
				if (chkPlayersName.isSelected()==true) {
					if (nTeam1.isBlank()==true || nTeam2.isBlank()==true) {
						JOptionPane.showMessageDialog(scoreWindow,"Insira o nome dos times!",null,JOptionPane.WARNING_MESSAGE);
						chkPlayersName.setSelected(false);
					}else {				
						Players players = new Players();
						players.playersWindow.setVisible(true);
					}
				}
			}
		});
		
		/**BOTAO CONFIRMAR DADOS DA PARTIDA**/
		btConfirmGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nTeam1 = nameField1.getText();
				nTeam2 = nameField2.getText();
				tempo = (int) spinnerTime.getValue();
				inicialTime = tempo;
				if (nTeam1.isBlank()==true || nTeam2.isBlank()==true) {
					JOptionPane.showMessageDialog(scoreWindow,"Insira o nome dos times!",null,JOptionPane.WARNING_MESSAGE);
				}else if (tempo!=(int)tempo || tempo==0){				
					JOptionPane.showMessageDialog(scoreWindow,"Valor de tempo incorreto!",null,JOptionPane.WARNING_MESSAGE);
				}else {
					nameField1.setVisible(false);
					nameField2.setVisible(false);
					lblTeamName1.setVisible(false);
					lblTeamName2.setVisible(false);
					btConfirmGame.setVisible(false);
					separator.setVisible(false);
					separator2.setVisible(false);
					chkSecondTime.setVisible(false);
					chkPlayersName.setVisible(false);
					nameTeam1.setText(nTeam1); 
					nameTeam2.setText(nTeam2);
					time.setVisible(true);
					btStartGame.setVisible(true);
					if (chkSecondTime.isSelected()==true) {
						tempo/=2;
						secondTime=true;	
						lbDadosDaPartida.setVisible(false);
						lbTempoDeJogo.setVisible(false);
						spinnerTime.setVisible(false);
					}else {
						lbDadosDaPartida.setVisible(false);
						lbTempoDeJogo.setVisible(false);
						spinnerTime.setVisible(false);
					}
				}
			}
		});
	}
	private void widgets() {  /**Details - effects - buttons - chkboxes**/
		//input nome time 1
		nameField1.setCaretColor(Color.WHITE);
		nameField1.setBorder(null);
		nameField1.setForeground(Color.WHITE);
		nameField1.setBackground(new Color(51, 172, 242));
		nameField1.setHorizontalAlignment(SwingConstants.CENTER);
		nameField1.setFont(new Font("Dialog", Font.PLAIN, 60));
		nameField1.setBounds(10, 285, 450, 115);
		scoreWindow.getContentPane().add(nameField1);
		nameField1.setColumns(10);
		//input nome time 2
		nameField2.setForeground(Color.WHITE);
		nameField2.setCaretColor(Color.WHITE);
		nameField2.setBorder(null);
		nameField2.setBackground(new Color(51, 172, 242));
		nameField2.setHorizontalAlignment(SwingConstants.CENTER);
		nameField2.setFont(new Font("Dialog", Font.PLAIN, 60));
		nameField2.setBounds(807, 285, 450, 115);
		scoreWindow.getContentPane().add(nameField2);
		nameField2.setColumns(10);
		//Detalhes e Efeitos Botão Gol Time 1
		btGoalTeam1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btGoalTeam1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btGoalTeam1.isEnabled()==true) {
					btGoalTeam1.setBackground(new Color(18, 79, 148));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btGoalTeam1.setBackground(new Color(4, 44, 89));
			}
		});
		btGoalTeam1.setFocusable(false);
		btGoalTeam1.setFocusTraversalKeysEnabled(false);
		btGoalTeam1.setBorderPainted(false);
		btGoalTeam1.setRolloverEnabled(false);
		btGoalTeam1.setRequestFocusEnabled(false);
		btGoalTeam1.setHorizontalTextPosition(SwingConstants.CENTER);
		btGoalTeam1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btGoalTeam1.setForeground(Color.WHITE);
		btGoalTeam1.setBackground(new Color(4, 44, 89));
		btGoalTeam1.setFont(new Font("Roboto", Font.PLAIN, 70));
		btGoalTeam1.setFocusPainted(false);
		scoreWindow.getContentPane().add(btGoalTeam1);	
		//Detalhes e Efeitos Botão Gol Time 2
		btGoalTeam2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btGoalTeam2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btGoalTeam1.isEnabled()==true) {
					btGoalTeam2.setBackground(new Color(18, 79, 148));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btGoalTeam2.setBackground(new Color(4, 44, 89));
			}
		});
		btGoalTeam2.setFocusable(false);
		btGoalTeam2.setFocusTraversalKeysEnabled(false);
		btGoalTeam2.setBorderPainted(false);
		btGoalTeam2.setRolloverEnabled(false);
		btGoalTeam2.setRequestFocusEnabled(false);
		btGoalTeam2.setHorizontalTextPosition(SwingConstants.CENTER);
		btGoalTeam2.setBorder(new EmptyBorder(0, 0, 0, 0));
		btGoalTeam2.setForeground(Color.WHITE);
		btGoalTeam2.setBackground(new Color(4, 44, 89));
		btGoalTeam2.setFont(new Font("Roboto", Font.PLAIN, 70));
		btGoalTeam2.setFocusPainted(false);
		scoreWindow.getContentPane().add(btGoalTeam2);
		//Detalhes e Efeitos Botão de Acréscimo
		btAddTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btAddTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btAddTime.setBackground(new Color(191, 232, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btAddTime.setBackground(Color.WHITE);
			}
		});
		btAddTime.setBorder(null);
		btAddTime.setHorizontalTextPosition(SwingConstants.CENTER);
		btAddTime.setForeground(Color.BLACK);
		btAddTime.setBackground(Color.WHITE);
		btAddTime.setFont(new Font("Roboto", Font.PLAIN, 15));
		btAddTime.setFocusPainted(false);
		btAddTime.setVisible(false);
		scoreWindow.getContentPane().add(btAddTime);
		btConfirmAddTime.setBounds(577, 535, 120, 25);
		//Detalhes e Efeitos Botão Confirmar Acréscimo
		btConfirmAddTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btConfirmAddTime.setBackground(new Color(191, 232, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btConfirmAddTime.setBackground(Color.WHITE);
			}
		});
		btConfirmAddTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btConfirmAddTime.setBorder(null);
		btConfirmAddTime.setHorizontalTextPosition(SwingConstants.CENTER);
		btConfirmAddTime.setForeground(Color.BLACK);
		btConfirmAddTime.setBackground(Color.WHITE);
		btConfirmAddTime.setFont(new Font("Roboto", Font.PLAIN, 12));
		btConfirmAddTime.setVisible(false);
		scoreWindow.getContentPane().add(btConfirmAddTime);
		spinnerAddTime.setBounds(612, 495, 50, 28);
		//Spinner tempo de jogo
		spinnerTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinnerTime.setBorder(new LineBorder(new Color(51, 204, 255)));
		spinnerTime.setOpaque(false);
		spinnerTime.setFocusTraversalKeysEnabled(false);
		spinnerTime.setFocusable(false);
		spinnerTime.setFont(new Font("Dialog", Font.PLAIN, 30));
		spinnerTime.setForeground(Color.BLACK);
		spinnerTime.setBackground(new Color(135,206,250));
		spinnerTime.setBounds(602, 105, 70, 35);
		spinnerTime.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinnerTime.setRequestFocusEnabled(false);
		scoreWindow.getContentPane().add(spinnerTime);
		//Spinner Acréscimo						
		spinnerAddTime.setBorder(new LineBorder(new Color(51, 204, 255)));
		spinnerAddTime.setOpaque(false);
		spinnerAddTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spinnerAddTime.setFocusTraversalKeysEnabled(false);
		spinnerAddTime.setFocusable(false);
		spinnerAddTime.setVerifyInputWhenFocusTarget(false);
		spinnerAddTime.setFont(new Font("Roboto", Font.PLAIN, 18));
		spinnerAddTime.setForeground(Color.BLACK);
		spinnerAddTime.setBackground(new Color(135,206,250));
		spinnerAddTime.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spinnerAddTime.setRequestFocusEnabled(false);	
		spinnerAddTime.setVisible(false);
		scoreWindow.getContentPane().add(spinnerAddTime);
		//Botao Confirmar dados
		btConfirmGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btConfirmGame.setBorder(null);
		btConfirmGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btConfirmGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btConfirmGame.setBackground(new Color(191, 232, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btConfirmGame.setBackground(Color.WHITE);
			}
		});
		btConfirmGame.setBackground(Color.WHITE);
		btConfirmGame.setForeground(Color.BLACK);
		btConfirmGame.setFont(new Font("Dialog", Font.PLAIN, 15));
		btConfirmGame.setBounds(567, 200, 140, 35);
		btConfirmGame.setFocusPainted(false);
		btConfirmGame.setFocusTraversalKeysEnabled(false);
		btConfirmGame.setFocusable(false);
		scoreWindow.getContentPane().add(btConfirmGame);
		//Detalhes e Efeitos Botão Iniciar Jogo
		btStartGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btStartGame.setBorder(null);
		btStartGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btStartGame.setBackground(new Color(191, 232, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btStartGame.setBackground(Color.WHITE);
			}
		});
		btStartGame.setBackground(Color.WHITE);
		btStartGame.setForeground(Color.BLACK);
		btStartGame.setFont(new Font("Roboto", Font.PLAIN, 15));
		btStartGame.setFocusPainted(false);
		btStartGame.setVisible(false);
		scoreWindow.getContentPane().add(btStartGame);
		//Detalhes e Efeitos Botão Finalizar Jogo
		btEndGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btEndGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btEndGame.setBackground(new Color(191, 232, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btEndGame.setBackground(Color.WHITE);
			}
		});
		btEndGame.setBorder(null);
		btEndGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btEndGame.setBackground(Color.WHITE);
		btEndGame.setForeground(Color.BLACK);
		btEndGame.setFont(new Font("Roboto", Font.PLAIN, 15));
		btEndGame.setFocusPainted(false);
		btEndGame.setVisible(false);
		scoreWindow.getContentPane().add(btEndGame);
		//Detalhes e Efeitos Botão Segundo Tempo
		btSecondTime.setBounds(562, 480, 150, 40);
		btSecondTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btSecondTime.setBorder(null);
		btSecondTime.setHorizontalTextPosition(SwingConstants.CENTER);
		btSecondTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btSecondTime.setBackground(new Color(191, 232, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btSecondTime.setBackground(Color.WHITE);
			}
		});
		btSecondTime.setBackground(Color.WHITE);
		btSecondTime.setForeground(Color.BLACK);
		btSecondTime.setFont(new Font("Roboto", Font.PLAIN, 15));
		btSecondTime.setFocusPainted(false);
		btSecondTime.setVisible(false);
		scoreWindow.getContentPane().add(btSecondTime);
		//CheckBox Predefinir nome de Jogadores
		chkPlayersName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chkPlayersName.setHorizontalAlignment(SwingConstants.CENTER);
		chkPlayersName.setForeground(Color.BLACK);
		chkPlayersName.setFont(new Font("Dialog", Font.PLAIN, 18));
		chkPlayersName.setFocusable(false);
		chkPlayersName.setFocusPainted(false);
		chkPlayersName.setBackground(new Color(51, 172, 242));
		chkPlayersName.setBounds(420, 156, 270, 23);
		scoreWindow.getContentPane().add(chkPlayersName);
		chkPlayersName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				chkPlayersName.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				chkPlayersName.setForeground(Color.BLACK);
			}
		});
		//CheckBox 2° Tempo
		chkSecondTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chkSecondTime.setFocusPainted(false);
		chkSecondTime.setFocusable(false);
		chkSecondTime.setForeground(Color.BLACK);
		chkSecondTime.setHorizontalAlignment(SwingConstants.CENTER);
		chkSecondTime.setFont(new Font("Dialog", Font.PLAIN, 18));
		chkSecondTime.setBackground(new Color(51, 172, 242));
		chkSecondTime.setBounds(718, 156, 105, 23);
		scoreWindow.getContentPane().add(chkSecondTime);
		chkSecondTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				chkSecondTime.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				chkSecondTime.setForeground(Color.BLACK);
			}
		});
	}
	private void lists() {  /**Listas que armazenam nomes de jogadores, e o scroll lateral que as controla**/
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setSelectionForeground(Color.BLACK);
		list.setRequestFocusEnabled(false);
		list.setBorder(null);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectionBackground(new Color(51, 172, 242));
		list.setVerifyInputWhenFocusTarget(false);
		list.setFocusTraversalKeysEnabled(false);
		list.setFocusable(false);
		list.setBackground(new Color(51, 172, 242));
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Roboto", Font.PLAIN, 22));
		
		list2.setSelectionForeground(Color.BLACK);
		list2.setVerifyInputWhenFocusTarget(false);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setSelectionBackground(new Color(51, 172, 242));
		list2.setRequestFocusEnabled(false);
		list2.setForeground(Color.BLACK);
		list2.setFont(new Font("Roboto", Font.PLAIN, 22));
		list2.setFocusable(false);
		list2.setFocusTraversalKeysEnabled(false);
		list2.setBorder(null);
		list2.setBackground(new Color(51, 172, 242));
		
		scrollPane.setBounds(90, 425, 325, 200);
		scrollPane.setViewportView(list);
		scrollPane.setVerifyInputWhenFocusTarget(false);
		scrollPane.setRequestFocusEnabled(false);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 11));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(null);
		scoreWindow.getContentPane().add(scrollPane);
		
		scrollPane2.setVerifyInputWhenFocusTarget(false);
		scrollPane2.setRequestFocusEnabled(false);
		scrollPane2.setForeground(Color.BLACK);
		scrollPane2.setFont(new Font("Roboto", Font.PLAIN, 22));
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setOpaque(false);
		scrollPane2.setBackground(Color.WHITE);
		scrollPane2.setBorder(null);
		scrollPane2.setBounds(868, 425, 325, 200);
		scrollPane2.setViewportView(list2);
		scoreWindow.getContentPane().add(scrollPane2);	
	}
	private void crono() {  /**Cronometro e funcoes de Segundo Tempo e Finalizar Partida**/
		cronometro = new Timer();
		cronometro.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {						
					if (min != tempo) {
						count++;
						sec = count % 60;
						min = count / 60;
						min %= 60; //ficar somente minutos
						time.setText(String.format("%02d:%02d", min, sec));							
					}else {	
						cronometro.cancel();
						min=0;
						sec=0;
						count=0;
						btGoalTeam1.setEnabled(false);
						btGoalTeam2.setEnabled(false);
						btAddTime.setVisible(false);
						spinnerAddTime.setVisible(false);
						btConfirmAddTime.setVisible(false);
						lbTempAcrescimo.setVisible(false);
						secondTime();
					}					
			}
		}, 1000, 50); // 1000ms = 1 segundo
	}
	private void secondTime() {  /**Metodo para segundo tempo**/
		if(secondTime==true && second==false) {
			btAddTime.setVisible(false);
			btSecondTime.setVisible(true);
			btSecondTime.addActionListener(new ActionListener() {  //Troca de lado
				public void actionPerformed(ActionEvent e) {
					System.out.println(inicialTime);
					System.out.println(tempo);
					time.setText("00:00");
					nameTeam2.setBounds(10, 278, 450, 115);
					nameTeam1.setBounds(814, 278, 450, 115);
					btGoalTeam2.setBounds(465, 255, 112, 160);
					btGoalTeam1.setBounds(699, 255, 112, 160);
					scrollPane2.setBounds(90, 425, 325, 200);
					scrollPane.setBounds(868, 425, 325, 200);
					btStartGame.setVisible(true);
					/**limpar as variaveis**/
					min=0;
					sec=0;
					count=0;
					acrescimo=0;
					acrescimoTotal=0;
					lbPlus.setVisible(false);	
					lbAddTime.setVisible(false);
					lbAddTime.setText("00");
					lbTempAcrescimo.setVisible(false);
					second=true;
				}
			});
		}else {
			end = true;	
			if(end==true) {
				btEndGame.setVisible(true);
			}
		}
	}
	private void labels() {	/**Details - effects - labels**/
		scoreWindow.getContentPane().setLayout(null);
		JLabel lbX = new JLabel("X");
		lbX.setHorizontalAlignment(SwingConstants.CENTER);
		lbX.setBounds(606, 285, 60, 100);
		lbX.setForeground(Color.BLACK);
		lbX.setFont(new Font("Roboto", Font.PLAIN, 90));
		lbX.setHorizontalTextPosition(SwingConstants.CENTER);
		scoreWindow.getContentPane().add(lbX);
		nameTeam1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		nameTeam1.setBounds(10, 285, 450, 115);
		nameTeam1.setBackground(new Color(51, 172, 242));
		nameTeam1.setVerifyInputWhenFocusTarget(false);
		nameTeam1.setHorizontalTextPosition(SwingConstants.CENTER);
		nameTeam1.setForeground(Color.WHITE);
		nameTeam1.setFont(new Font("Roboto", Font.PLAIN, 60));
		nameTeam1.setHorizontalAlignment(SwingConstants.CENTER);
		scoreWindow.getContentPane().add(nameTeam1);
		nameTeam2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		nameTeam2.setBounds(814, 285, 450, 115);
		nameTeam2.setBackground(new Color(51, 172, 242));
		nameTeam2.setVerifyInputWhenFocusTarget(false);
		nameTeam2.setHorizontalTextPosition(SwingConstants.CENTER);
		nameTeam2.setForeground(Color.WHITE);
		nameTeam2.setHorizontalAlignment(SwingConstants.CENTER);
		nameTeam2.setFont(new Font("Roboto", Font.PLAIN, 60));
		scoreWindow.getContentPane().add(nameTeam2);	
		
		lblTeamName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName1.setForeground(Color.BLACK);
		lblTeamName1.setFont(new Font("Dialog", Font.PLAIN, 40));
		lblTeamName1.setBounds(85, 235, 300, 50);
		scoreWindow.getContentPane().add(lblTeamName1);

		lblTeamName2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName2.setForeground(Color.BLACK);
		lblTeamName2.setFont(new Font("Dialog", Font.PLAIN, 40));
		lblTeamName2.setBounds(882, 235, 300, 50);
		scoreWindow.getContentPane().add(lblTeamName2);
		
		time.setBounds(424, 85, 415, 115);
		time.setForeground(Color.WHITE); //Cronometro
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setFont(new Font("Roboto", Font.PLAIN, 99));
		time.setVisible(false);
		scoreWindow.getContentPane().add(time);
		
		lbPlus.setBounds(748, 80, 40, 40);
		lbPlus.setForeground(Color.WHITE);
		lbPlus.setFont(new Font("Roboto", Font.PLAIN, 36));
		lbPlus.setHorizontalAlignment(SwingConstants.CENTER);
		scoreWindow.getContentPane().add(lbPlus);
		lbPlus.setVisible(false);
		
		lbAddTime.setBounds(782, 75, 60, 50);				
		lbAddTime.setForeground(Color.WHITE);
		lbAddTime.setHorizontalAlignment(SwingConstants.LEFT);
		lbAddTime.setFont(new Font("Roboto", Font.PLAIN, 46));
		scoreWindow.getContentPane().add(lbAddTime);						
		lbAddTime.setVisible(false);
		
		lbTempAcrescimo.setBounds(549, 464, 175, 26);
		lbTempAcrescimo.setVisible(false);		
		lbTempAcrescimo.setForeground(Color.WHITE);
		lbTempAcrescimo.setFont(new Font("Roboto", Font.PLAIN, 15));
		lbTempAcrescimo.setHorizontalAlignment(SwingConstants.CENTER);
		scoreWindow.getContentPane().add(lbTempAcrescimo);
		
		lbDadosDaPartida.setHorizontalAlignment(SwingConstants.CENTER);
		lbDadosDaPartida.setForeground(Color.WHITE);
		lbDadosDaPartida.setFont(new Font("Dialog", Font.PLAIN, 28));
		lbDadosDaPartida.setBounds(516, 12, 242, 40);
		scoreWindow.getContentPane().add(lbDadosDaPartida);
		
		lbTempoDeJogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTempoDeJogo.setForeground(Color.WHITE);
		lbTempoDeJogo.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbTempoDeJogo.setBounds(556, 70, 162, 26);
		scoreWindow.getContentPane().add(lbTempoDeJogo);
		
		separator.setBounds(13, 400, 445, 4);
		scoreWindow.getContentPane().add(separator);
		
		separator2.setBounds(810, 400, 445, 4);
		scoreWindow.getContentPane().add(separator2);
	}	
}
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class Editor extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
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
	public Editor() {
		setResizable(false);
		setTitle("Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1148, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(10, 0, 1124, 576);
		contentPane.add(scrollPane);
		
		JTextArea text = new JTextArea();
		scrollPane.setViewportView(text);
		Font f=new Font("Arial",Font.BOLD,17);
		text.setFont(f);
				
		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);
		
		JMenu FILE = new JMenu("FILE");
		menuBar.add(FILE);
		
		JMenuItem NEW = new JMenuItem("NEW");
		NEW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
			}
		});
		FILE.add(NEW);
		
		JMenuItem OPEN = new JMenuItem("OPEN");
		OPEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch= new JFileChooser();
				ch.showOpenDialog(null);
				File f=ch.getSelectedFile();
				String fn=f.getAbsolutePath();
				
				try {
					
					FileReader reader = new FileReader(fn);
					BufferedReader br =new BufferedReader(reader);
					text.read(br,null);
					br.close();
					text.requestFocus();
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				};
			}
		});
		FILE.add(OPEN);
		
		JMenuItem SAVE = new JMenuItem("SAVE");
		SAVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch=new JFileChooser();
				int res=ch.showSaveDialog(null);
				if(res==JFileChooser.APPROVE_OPTION) {
					String content=text.getText();
					File f=ch.getSelectedFile();
					try {
						FileWriter fw=new FileWriter(f.getPath());
						fw.write(content);
						fw.flush();
						fw.close();
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,e1);
					}
				}
			}
		});
		FILE.add(SAVE);
		
		JMenuItem EXIT = new JMenuItem("EXIT");
		EXIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		FILE.add(EXIT);
		
		JMenu EDIT = new JMenu("EDIT");
		menuBar.add(EDIT);
		
		JMenuItem CUT = new JMenuItem("CUT       Ctrl+X");
		CUT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.cut();
			}
		});
		EDIT.add(CUT);
		
		JMenuItem COPY = new JMenuItem("COPY    Ctrl+C");
		COPY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});
		EDIT.add(COPY);
		
		JMenuItem PASTE = new JMenuItem("PASTE   Ctrl+V");
		PASTE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.paste();
			}
		});
		EDIT.add(PASTE);
		
		JMenu mnNewMenu = new JMenu("PRINT");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Print");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					text.print();
				} catch (PrinterException e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
	}
}

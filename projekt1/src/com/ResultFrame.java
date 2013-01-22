package com;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ResultFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel label;
	private JTextField textField_3;
	private JToggleButton tglbtnPdf;
	private File file;
	private JFileChooser chooser;
	private TextAnalysis text;
	private ExtractPageContent text1;
	private JTextField textField_4;
	private JLabel lblDugoNajduszegoZdania;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblWspczynnikMglistoci;
	private JLabel lblIloNiebiaychZnakw;
	private JScrollPane scrollPane;
	private JTextField textField_7;
	private JTextField txtWpiszSowo;
	private JTextField textField_8;

	public ResultFrame(String filePath)  throws Exception {
		setTitle("Statystyki");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 691, 455);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.getContentPane().setLayout(null);
		
		text1 = new ExtractPageContent(filePath);
		text = new TextAnalysis(text1.getFileContents());
		text.getStatistics();
		
		chooser = new JFileChooser("C:\\Users\\Paulina\\Desktop");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText(text.getNumberOfSigns().toString());
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText(text.getNumberOfWords().toString());
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText(text.getNumberOfSentences().toString());
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText(text.detectLanguage());
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setText(text.getMinSentenceLength().toString());
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText(text.getMaxSentenceLength().toString());
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setText(new Float(text.getGunningFogIndex()).toString());
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setText(text.getNumberOfSignsWtSpaces().toString());
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setText((String) null);
		textField_8.setEditable(false);
		textField_8.setColumns(10);	
		
		txtWpiszSowo = new JTextField();
		txtWpiszSowo.setColumns(10);
		txtWpiszSowo.addActionListener(this);
		
		JLabel lblIloZnakw = new JLabel("Ilo\u015B\u0107 znak\u00F3w:");
		lblIloZnakw.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblIloSw = new JLabel("Ilo\u015B\u0107 s\u0142\u00F3w:");
		lblIloSw.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JLabel lblIloZda = new JLabel("J\u0119zyk:");
		lblIloZda.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		label = new JLabel("Ilo\u015B\u0107 zda\u0144:");
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		tglbtnPdf = new JToggleButton("Zapisz tekst");
		tglbtnPdf.addActionListener(this);
		
		JLabel lblDugoNajkrtszegoZdania = new JLabel("D\u0142ugo\u015B\u0107 najkr\u00F3tszego zdania:");
		lblDugoNajkrtszegoZdania.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		lblDugoNajduszegoZdania = new JLabel("D\u0142ugo\u015B\u0107 najd\u0142u\u017Cszego zdania:");
		lblDugoNajduszegoZdania.setFont(new Font("Verdana", Font.PLAIN, 12));		
		
		lblWspczynnikMglistoci = new JLabel("Wsp\u00F3\u0142czynnik mglisto\u015Bci:");
		lblWspczynnikMglistoci.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		lblIloNiebiaychZnakw = new JLabel("Ilo\u015B\u0107 niebia\u0142ych znak\u00F3w:");
		lblIloNiebiaychZnakw.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		scrollPane = new JScrollPane();
		
		JLabel lblSprawdzaniePoprawnoci = new JLabel("Sprawdzanie poprawno\u015Bci, wpisz s\u0142owo:");
		lblSprawdzaniePoprawnoci.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblWspczynnikMglistoci, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
											.addGap(18))
										.addComponent(lblDugoNajduszegoZdania, GroupLayout.PREFERRED_SIZE, 214, Short.MAX_VALUE)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDugoNajkrtszegoZdania)
										.addComponent(lblIloNiebiaychZnakw, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIloSw, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIloZda, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIloZnakw))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(0)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(textField_3, Alignment.LEADING)
												.addComponent(textField_4, Alignment.LEADING)
												.addComponent(textField, Alignment.LEADING)
												.addComponent(textField_2, Alignment.LEADING)
												.addComponent(textField_1, Alignment.LEADING)))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(textField_5, Alignment.LEADING)
											.addComponent(textField_6, Alignment.LEADING)
											.addComponent(textField_7, Alignment.LEADING))))
								.addComponent(tglbtnPdf, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(txtWpiszSowo, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSprawdzaniePoprawnoci, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
					.addGap(16))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIloZda, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIloZnakw))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIloSw, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDugoNajkrtszegoZdania, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDugoNajduszegoZdania, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWspczynnikMglistoci, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIloNiebiaychZnakw, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addComponent(lblSprawdzaniePoprawnoci, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtWpiszSowo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(tglbtnPdf)
					.addContainerGap(35, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
		);
		
		JTextPane txtpnN = new JTextPane();
		
		StyledDocument doc = txtpnN.getStyledDocument();
	    Style normal = txtpnN.addStyle("normal", null);
	    Style underlined = txtpnN.addStyle("underlined", normal);
	    StyleConstants.setUnderline(underlined, true);
	  
	    doc.insertString(doc.getLength(), text1.getFileContents(), normal);
	    //petla po tablicy
	    //doc.remove(indeks pierwszego znaku, slowo.length());
	    //doc.insertString(indeks pierwszego znaku, slowo, underlined);
	
	    
		txtpnN.setEditable(false);
		txtpnN.setFont(new Font("Arial", Font.PLAIN, 11));
		scrollPane.setViewportView(txtpnN);
		contentPane.setLayout(gl_contentPane);
		System.out.println(text.isWordCorrect(new String("change")));
		
	}
	
	public void actionPerformed(ActionEvent e) throws NullPointerException {
		if (e.getSource() == tglbtnPdf) {
			int odpowiedz = chooser.showSaveDialog(this);
			   if (odpowiedz == chooser.APPROVE_OPTION) {
			       file = chooser.getSelectedFile();
			       try {
			           FileWriter out = new FileWriter(file);
			           out.write(text1.getFileContents());
			           out.close();
			       } catch (IOException e_) {
			           System.out.println("Nie mogê zapisaæ pliku: "+file.getAbsolutePath());
			           System.out.println("Problem: "+e);
			       }
			   }
		} 
		else {
			System.out.println(txtWpiszSowo.getText());
			if(!text.isWordCorrect(txtWpiszSowo.getText())) {
				textField_8.setText("S³owo nie wystêpuje w s³owniku.");
			}
			else {
				textField_8.setText("S³owo wystêpuje w s³owniku.");
			}
		}
	}
}

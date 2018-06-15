import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
/**
 * klasa Menu dziedzicz¹ca po JFrame odpowiedzialna za ca³e menu
 */
public class Menu extends JFrame{
	
/** tworzenie obiektu klasy Rozrywka */	
	Rozgrywka rozgrywka; 
	
/** tworzenie buttonów */
	JButton bStart,bInformacje,bListawynikow,bWyjscie;
	JButton bPowrotdomenu;
	JButton bPowrotdomenu2;
	JButton bPowrotdomenu3;
	JButton Wyjscie2;
	JButton czychceszwyjsc;
	JButton TAK;
	JButton autor;
	JButton pauza;
	
	
/** tworzenie paneli */
	JPanel pinformacji = new JPanel();
	JPanel pmenu= new JPanel();
	JPanel pstart = new JPanel();
	JPanel plistawynikow= new JPanel();
	JPanel pwyjscie= new JPanel();

	
	
/** tworzenie labeli */
	JLabel background=new JLabel();
	JLabel backgroundwinformacji=new JLabel();
	JLabel doscrolla= new JLabel();
	JLabel logopg= new JLabel();
	JLabel labelautor= new JLabel();
	JLabel menu= new JLabel("Menu");
	
	
	
/** tworzenie pola tekstowego, w ktorym znajduje siê tekst dotyczacy informacji o zasadach gry */
    private JTextArea console;
    
/** tworzenie JScrollaPane, ktore uzyte jest w TextArea do odczytu tekstu dotyczacego informacji o zasadach gry */
    JScrollPane jscrollPane2 = new JScrollPane();

    
 
/**
 *  klasa RoundButton dziedziczaca po JButton, dzieki ktorej niektore z uzytych buttonow sa okragle
 * @author Anna
 *
 */
	public class RoundButton extends JButton {
/**konstruktor klasy RoundButton */
		public RoundButton(String label) {
/** wywolanie konstruktora klasy JButoon , po ktorej dziedziczy klasa RoundButton */
		    super(label);
		 
/** ustawienie wlasciwosci Roundbuttonow */
		    setBackground(Color.lightGray);
		    Font myFont = new Font("Courier", Font.ITALIC,20);
		    setFont(myFont);
		    setFocusable(false);
		    setContentAreaFilled(false);
		  }
		 
		
/**
 * metoda paintComponent, ktora nadaje kolor Roundbuttonom
 */
		protected void paintComponent(Graphics g) {
		    if (getModel().isArmed()) {
		      g.setColor(Color.darkGray);
		    } else {
		      g.setColor(getBackground());
		    }
		    g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
		 
		    super.paintComponent(g);
		  }
		  
/**
 * metoda, ktora po kliknieciu mysza na button zmienia kolor tla buttona
 */
		  protected void paintBorder(Graphics g) {
		    g.setColor(Color.darkGray);
		    g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
		  }
	}
	
	
	/** konstuktor klasy Menu */
	public Menu(String nazwa){
		
		super(nazwa);
		
	/** inicjalizacja buttonow glownego menu, ustawienie ich wlasciwosci, dodanie do nich sluchaczy zdarzen */
		bStart=new JButton(new ImageIcon("obrazki\\button_start (1).png"));
		bStart.setBounds(630,200,200,50);
		bStart.setOpaque(false);
		bStart.setContentAreaFilled(false);
		bStart.setBorderPainted(false);
		bStart.addActionListener(new B1(this));

		bInformacje=new JButton(new ImageIcon("obrazki\\button_informacje.png"));
		bInformacje.setBounds(590,270,300,50);
		bInformacje.setOpaque(false);
		bInformacje.setContentAreaFilled(false);
		bInformacje.setBorderPainted(false);
		bInformacje.addActionListener(new B2());
		
		
		bWyjscie=new JButton("Wyjœcie");
		bWyjscie=new JButton(new ImageIcon("obrazki\\button_wyjscie.png"));
		bWyjscie.setBounds(590,340,300,50);
		bWyjscie.setOpaque(false);
		bWyjscie.setContentAreaFilled(false);
		bWyjscie.setBorderPainted(false);
		bWyjscie.addActionListener(new B4());
		
	
		bPowrotdomenu=new RoundButton("Powrót do Menu");
		bPowrotdomenu.addActionListener(new B7());
	
		
		bPowrotdomenu3=new RoundButton("Powrót do\n Menu");
		bPowrotdomenu3.addActionListener(new B9());

		
	/**ustawienie Layouta na null - wszystkie elementy ustawiane sa "recznie", dodanie tla do labela i ustawienie jego wlsciwosci, dodanie labela do panelu menu glownego */
		pmenu.setLayout(null);
		background=new JLabel(new ImageIcon("obrazki\\tlomenu.jpg"));
		background.setBounds(0, 0, 1024, 768);
		add(pmenu);
		pmenu.add(background);
		
	/**ustawienie wlasciowsci labela z napisem "Menu" w menu glownym gry */
		menu.setBounds(600, 10, 500, 200);
		menu.setFont(new Font("Edwardian Script ITC", Font.BOLD, 120));
	    menu.setForeground(Color.RED);
	    
	/** dodanie wszystkich buttonow menu do labela z tlem i ustawienie wlasciwosci */
	    background.add(menu);
		background.add(bStart);
		background.add(bInformacje);
		background.add(bWyjscie);
		setResizable(false);
		setBounds(0,0,1024,768);
		setVisible(true);
		
		
		
		
		
		/** ustawienia wlasciowsci panelu po klikniêciu buttona "Informacje" */
		pinformacji.setLayout(null);
		JLabel background2=new JLabel(new ImageIcon("obrazki\\tlo1k.jpg"));
		pinformacji.add(background2);
		background2.setBounds(0,0,1024,768);
		bPowrotdomenu.setBounds(724,608,200,100);
		
		/**pole tekstowe zawierajace informacje o zasadach gry, ustawienie jego wlasciwosci */
		JTextArea area = new JTextArea("IT'S MY LIFE\n\n"
				+ "It's my life to zarowna gra intelektualna\noraz zrecznosciowa. Zadaniem gracza jest odpowiadanie\na,"
				+ "pojawiajace sie na ekranie pytania, które dotycza\nogolnej wiedzy o czlowieku.\n\n\n\n"
				+ "Zasady i cel gry\n\n\n"
				+ "Po kliknieciu w 'Start' znajdujacym sie w menu\nprzechodzisz do gry wlasciwej. Automatycznie "
				+ "wybierany\njest poziom 1.Zadaniem gracza jest sterowanie kropelka\nkrwi za pomoca klawiszy "
				+ "strzalek prawo/lewo\ni odpowiednio omijanie, badz lapanie spadajacy z gory\nelementow. "
				+ "Elementy, które gracz powinien lapac to\nwitaminy, tlen oraz owoce i warzywa."
				+ " Sa one niezbedne do\nprawidlowego funkcjonowania organizmu ludzkiego."
				+ " W\nutrzymaniu dobrego stanu fizycznego przeszkadzaja nam\nroznego rodzaju wirusy,"
				+ " bakterie, grzyby, dlatego\nzarowno w zyciu, jak i podczas gry powinnismy ich unikac\n"
				+ "i omijac. Za kazdorazowe zlapanie dobrych elementow\ngracz otrzymuje +1 pkt, natomiast"
				+ " za zle elementy -1 pkt.\nCo jakis czas na ekranie pojawiac sie bedzie losowo wybane\npytanie."
				+ " Po udzieleniu dobrej odpowiedzi rowniez\ndoliczany jest +1 pkt, natomiast za niepoprawna\nodpowiedz -1 pkt."
				+ "Po uzyskaniu 5 pkt automatycznie wlacza\nsie runda 2, w ktorej elementy spadaja z wieksza\npredkoscia.\n\n\n\n"
				+ "Powodzenia! Milej zabawy!\n\n ");
		area.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		area.setBackground(new Color(255,204,153));
		area.setEditable(false);
		
		/** ustawienie wlasciowosci JScrollPane */
		JScrollPane jscrollPane = new JScrollPane(area);
		jscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  	
		jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  	
		jscrollPane.setBounds(100,100,550,550);
		
		/** dodanie buttona "Powrot do menu" i JScrollPane do labelu */
		background2.add(bPowrotdomenu);
	    background2.add(jscrollPane);
		setVisible(true);
		
		
	    
	    
	    
	    /** ustawienia panelu po klikniêciu buttona "Wyjœcie, ustawienie Layouta na null - wszystkie elementy ustawiane sa "recznie", dodanie tla do labela i ustawienie jego wlsciwosci, dodanie labela do panelu Wyjscie, dodanie wszytskich elementow zawartch w panelu Wyjscie i ustaiwnei ich wlasciwosci */
		pwyjscie.setLayout(null);
	    JLabel background4=new JLabel(new ImageIcon("obrazki\\tlowyjscia.gif"));
		pwyjscie.add(background4);
		background4.setBounds(0,0,1024,768);
		labelautor.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
		labelautor.setForeground(Color.BLACK);
		labelautor.setBounds(40,40,500,700);
		background4.add(labelautor);
		bPowrotdomenu3.setBounds(350,470, 200, 200);
		background4.add(bPowrotdomenu3);
		czychceszwyjsc= new JButton(new ImageIcon("obrazki\\button_czy-na-pewno-chcesz-opuscic-gre (4).png"));
		czychceszwyjsc.setBounds(5,10,700,100);
		background4.add(czychceszwyjsc);
		czychceszwyjsc.setOpaque(false);
		czychceszwyjsc.setContentAreaFilled(false);
		czychceszwyjsc.setBorderPainted(false);
		
		TAK=new RoundButton("TAK");
		TAK.setBounds(200,200,150,100);
		TAK.addActionListener(new B10());
		
		autor=new RoundButton("Autor");
		autor.setBounds(820,20,150,150);
		autor.addActionListener(new B11());
		background4.add(TAK);
		background4.add(autor);
		
		logopg=new JLabel(new ImageIcon("obrazki\\pglogo2.gif"));
		logopg.setBounds(10, 480, 250, 200);
		background4.add(logopg);

		setVisible(true);
		
		
		
		
	}
	/**
	 * metoda umozliwiajaca przejscie z gry wlasciwej do menu
	 */
	public void powrot() {
		
		
    	remove(rozgrywka);
    	add(pmenu);
    	repaint();
    	validate();
    }
	
	/** ActionListener buttona "Start", ktory przenosi nas do gry wlasciwej */
	private class B1 implements ActionListener{
		Menu menu;
		B1(Menu menu)
		{
			this.menu = menu;
		}
		public void actionPerformed(ActionEvent z) {
		remove(pmenu);
		rozgrywka = new Rozgrywka(menu);
		rozgrywka.addKeyListener(rozgrywka);
		rozgrywka.setFocusable(true);
		add(rozgrywka);
		repaint();
		validate();
		rozgrywka.requestFocusInWindow();
			
		}
	}
	
	/** ActionListener buttona "Informacje", ktory przenosi nas do panelu Informacje */
	private class B2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		        remove(pmenu);
				add(pinformacji);
				repaint();
				validate();
		}
	}
	
	/** ActionListener buttona "Wyjscie", ktory przenosi nas do panelu Wyjscie */
	private class B4 implements ActionListener{
		public void actionPerformed(ActionEvent w) {
		remove(pmenu);
		add(pwyjscie);
		repaint();
		validate();
		
		}
	}
		  
	/** ActionListener buttona "Powrot do menu", ktory przenosi nas z panelu Informacje ponownie do menu glownego */
	private class B7 implements ActionListener{
		public void actionPerformed(ActionEvent x) {
			remove(pinformacji);
			add(pmenu);
			repaint();
			validate();
			
			}
	}
	
	/**ActionListener buttona "Powrot do menu", ktory przenosi nas z panelu Wyjscie ponownie do menu glownego */
			
		private class B9 implements ActionListener{
			public void actionPerformed(ActionEvent b) {
				remove(pwyjscie);
				add(pmenu);
				repaint();
				validate();
				
			}
	}	
		/** ActionListener buttona "TAK" umieszczonego w panelu wyjscie, za pomoca ktorego wychodzimy calkowicie z gry */
		private class B10 implements ActionListener{
			public void actionPerformed(ActionEvent z) {
				System.exit(0);
			
			}
		}
		/** ActionListener buttona "Autor", umieszczonego w panelu wyjscie, za pomoca ktorego pokazauja sie informacje o autorze gry */
		private class B11 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			        labelautor.setText("<html>Anna Wozniak<br><br>Wydzial Elektroniki, Telekomunikacji, Informatyki</html>");
				
			}
		}
		
}




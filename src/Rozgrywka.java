import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * klasa Rozgrywka dziedziczaca po JPanel i implementujaca KeyListener
 * @author Anna
 *
 */
public class Rozgrywka extends JPanel implements KeyListener{
	public void keyTyped(KeyEvent arg0)
	{
		
	}
/** deklarazja labeli */
	JLabel punktacja=new JLabel();
	JLabel zmianarundy=new JLabel();
	
/** deklaracja i przypisanie poczatkowej wartosci 0 liczbie punktow */
	int points=0;
	
/** deklaracja zmiennych */
	private Image tlo;
	private Image kropelka;
	private int wspx;
	private int wspy;
	
	Random r = new Random();
	
/** deklaracja i przypisanie wartosci dla predkosci poruszania kropla krwi oraz predkosc spadnia elementow w pierwszej i drugiej rundzie */
	private int predkosckropli=7;
	private int predkoscspadaniawdrugiejrundzie=13;
	private int predkoscmikroorg=40;
	
/**deklaracja elementow */
	JPanel pstart;
	JButton pauza;
	JButton wznow;
	JButton powrotdomenu;
	Timer czas;
	Timer czasspadku;
	Timer czaszegara;
	Timer czasNapisuZmianaRundy;
	
	TimerTask timer_task;
	
/** utworzenie zbioru spadajacych elementow */
	 Set<Postaci> objects = new HashSet<Postaci>();
/** utworzenie listy pytan */
	 List<Pytanie> pytania = new ArrayList<Pytanie>();
	
	
	private boolean przyciski[];

	private Menu menu;
	
/** konstruktor klasy Rozgrywka */
	public Rozgrywka(Menu menu) {
		this.menu=menu;
		
/** ustawienie wlasciwosci labelu ktory umozliwia wyswietlenie liczby punktow */
        punktacja.setFont(new Font("arial", Font.BOLD, 20));
        punktacja.setForeground(Color.WHITE);
        punktacja.setBounds(40,40,150,300);
        add(punktacja);
	
/** ustawienie Layout na null - polozenie okreslane "recznie",dodanie tla do buttona pauza, ustawienie jego wlasciwosci,dodanie ActionListenera */
		setLayout(null);
		pauza=new JButton(new ImageIcon("obrazki\\button_pauza.png"));
		pauza.setBounds(5,5,100,50);
		pauza.setOpaque(false);
		pauza.setContentAreaFilled(false);
		pauza.setBorderPainted(false);
		add(pauza);
		pauza.addActionListener(new B1());
		
		
		
/** dodanie tla do buttona powrot do menu, ustawienie jego wlasciwosci,dodanie ActionListenera */
		powrotdomenu=new JButton(new ImageIcon("obrazki\\button_powrot-do-menu.png"));
		powrotdomenu.setBounds(105,5,200,50);
		powrotdomenu.setOpaque(false);
		powrotdomenu.setContentAreaFilled(false);
		powrotdomenu.setBorderPainted(false);
		this.add(powrotdomenu);
		powrotdomenu.addActionListener(new B2());
		
/** dodanie tla do buttona wznow, ustawienie jego wlasciwosci, dodanie ActionListenera */
		wznow=new JButton(new ImageIcon("obrazki\\button_wznow.png"));
		wznow.setBounds(5,50,100,50);
		wznow.setOpaque(false);
		wznow.setContentAreaFilled(false);
		wznow.setBorderPainted(false);
		this.add(wznow);
		wznow.addActionListener(new B3());
		
		
		addKeyListener(this);
		setFocusable(true);
		this.setVisible(true);
		
/** poczatkowe ustawienie polozenia kropelki krwi */
		przyciski = new boolean[2];
		wspx=450;
		wspy=600;

/** dodanie obiektow spdajacych do listy	*/
        objects.add(new Wrog(new ImageIcon("obrazki\\virus.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Wrog(new ImageIcon("obrazki\\virus2.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Wrog(new ImageIcon("obrazki\\virus3.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Wrog(new ImageIcon("obrazki\\bakteria2.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Wrog(new ImageIcon("obrazki\\zarazek.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Wrog(new ImageIcon("obrazki\\wirusek.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Wrog(new ImageIcon("obrazki\\grzyby.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));

        objects.add(new Witaminy(new ImageIcon("obrazki\\tlen.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Witaminy(new ImageIcon("obrazki\\vitamin.png").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Witaminy(new ImageIcon("obrazki\\jablko.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Witaminy(new ImageIcon("obrazki\\marchew.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Witaminy(new ImageIcon("obrazki\\burak.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        objects.add(new Witaminy(new ImageIcon("obrazki\\witaminy.gif").getImage(), r.nextInt(100), r.nextInt(930), 0));
        
/** warunek ustawiajacy predkosc spadania elementow, zalezny od ilosci zdobytych punktow */
		if(points<2)
		{
		startpomiaryczasow();
		wczytajPytania();
		}
		else if(points>=2)
			
		{
			runda2startpomiaryczasow();
			wczytajPytania();
	
		}
		
	}
	
/**
 * metoda wczytujaca pytania, ktore beda losowo pojawialy sie w trakcie gry
 */
	private void wczytajPytania() {
    	Pytanie q1 = new Pytanie("Od czego zale¿y kolor skóry?");
    	q1.dodajPytanie("Od iloœci zawartego w niej pigmentu - melaniny", true);
    	q1.dodajPytanie("Od wieku. Im jesteœmy starsi, tym skóra ciemniejsza", false);
    	q1.dodajPytanie("Od liczby k¹pieli. Im czêœciej siê k¹piemy, tym skóra jaœniejsza", false);
		pytania.add(q1);
    	Pytanie q2 = new Pytanie("Najmniejsz¹ koœci¹ w ogranizmie cz³owieka jest?");
    	q2.dodajPytanie("¯uchwa", false);
    	q2.dodajPytanie("Strzemi¹czko", true);
    	q2.dodajPytanie("Rzepka", false);
    	pytania.add(q2);
    	Pytanie q3 = new Pytanie("Których pierwiastków jest najwiêcej w organizmie cz³owieka?");
    	q3.dodajPytanie("Wodoru", false);
    	q3.dodajPytanie("Azotu", false);
    	q3.dodajPytanie("Tlenu", true);
		pytania.add(q3);
		Pytanie q4 = new Pytanie("Gdzie znajduje siê koœc udowa?");
    	q4.dodajPytanie("W nodze", false);
    	q4.dodajPytanie("W g³owie", false);
    	q4.dodajPytanie("W ramieniu", true);
		pytania.add(q4);
		Pytanie q5 = new Pytanie("Która zastawka zapobiega cofaniu siê krwi z komory lewej do przedsionka lewego?");
    	q5.dodajPytanie("Trójdzielna", false);
    	q5.dodajPytanie("Pnia p³ucnego", false);
    	q5.dodajPytanie("Dwudzielna", true);
		pytania.add(q5);
		Pytanie q6 = new Pytanie("Rodzaj skurczu w którym zmienia siê d³ugoœæ miêsnia przy sta³ym poziomie napiêcia niêsniowego to skurcz?");
    	q6.dodajPytanie("Izotoniczny", true);
    	q6.dodajPytanie("Izometryczny", false);
    	q6.dodajPytanie("auksotoniczny", false);
		pytania.add(q6);
		Pytanie q7 = new Pytanie("W którym elemencie oka wytêpuje najgêstsze skupisko czopków?");
    	q7.dodajPytanie("Plamka œlepa", false);
    	q7.dodajPytanie("Plamka ¿ó³ta", true);
    	q7.dodajPytanie("Cia³ko szkliste", false);
		pytania.add(q7);
		Pytanie q8 = new Pytanie("Rodzaj skurczu w którym zmienia siê d³ugoœæ miêsnia przy sta³ym poziomie napiêcia niêsniowego to skurcz?");
    	q8.dodajPytanie("Izotoniczny", true);
    	q8.dodajPytanie("Izometryczny", false);
    	q8.dodajPytanie("auksotoniczny", false);
		pytania.add(q8);
		Pytanie q9 = new Pytanie("Nieparzysty gruczo³ wydzielania zewnêtrznego, który wytwarza miêdzy innymi trójjodotyroninê i tyroksynê to?");
		q9.dodajPytanie("Grasica", false);
    	q9.dodajPytanie("Tarczyca", true);
    	q9.dodajPytanie("Podwzgórze", false);
		pytania.add(q9);
		Pytanie q10 = new Pytanie("Jaka jest potoczna nazwa zaburzenia od¿ywiania, która polega na celowej utracie wagi?");
    	q10.dodajPytanie("Anoreksja", true);
    	q10.dodajPytanie("Bulimia", false);
    	q10.dodajPytanie("ortoreksja", false);
		pytania.add(q10);
		Pytanie q11 = new Pytanie("W jakim napoju znajdziemy najwiêcej cukru?");
    	q11.dodajPytanie("Mleko", false);
    	q11.dodajPytanie("Coca-cola", true);
    	q11.dodajPytanie("Wino", false);
		pytania.add(q11);
		Pytanie q12 = new Pytanie("Bia³ka, które s¹ podstawowym materia³em budulcowym organizmów zwierzêcych to?");
    	q12.dodajPytanie("bia³ka globularne", false);
    	q12.dodajPytanie("bia³ka fibrylarne", true);
    	q12.dodajPytanie("albuminy", false);
		pytania.add(q12);
		Pytanie q13 = new Pytanie("Który pierwiastek jest sk³adnikiem hemoglobiny?");
    	q13.dodajPytanie("Magnez", false);
    	q13.dodajPytanie("Selen", false);
    	q13.dodajPytanie("¯elazo", true);
		pytania.add(q13);
		Pytanie q14 = new Pytanie("Jakich produktów powinniœmy spo¿ywaæ najwiêcej?");
    	q14.dodajPytanie("Owoców", false);
    	q14.dodajPytanie("Warzyw", true);
    	q14.dodajPytanie("Wêglowodanów", false);
		pytania.add(q14);
		Pytanie q15 = new Pytanie("Co to jest insulina?");
    	q15.dodajPytanie("Cukier", false);
    	q15.dodajPytanie("Gruczo³", false);
    	q15.dodajPytanie("Hormon", true);
		pytania.add(q15);
	}
	
/**
 * metoda rozpoczynajaca pomiary czasow w rundzie 1
 */
		void startpomiaryczasow() {
		czas = new Timer();
		czas.scheduleAtFixedRate(new Poruszanie(),0,predkosckropli); // umozliwia poruszanie kropelka
		
		czasspadku = new Timer();
		czasspadku.scheduleAtFixedRate(new Spadanie(),0,predkoscmikroorg); // szybkosc spadania
	
		czaszegara = new Timer();
		czaszegara.scheduleAtFixedRate(new PytanieTask(),5000,8000); // czestotliwosc pojawiania sie pytan
		};
		
/**
 * metoda rozpoczynajaca pomiary czasow w rundzie 2
 */
		void runda2startpomiaryczasow() {
			czas = new Timer();
			czas.scheduleAtFixedRate(new Poruszanie(),0,predkosckropli); // umozliwia poruszanie kropelka
			
			czasspadku = new Timer();
			czasspadku.scheduleAtFixedRate(new Spadanie(),0,predkoscspadaniawdrugiejrundzie); // szybkosc spadania
		
			czaszegara = new Timer();
			czaszegara.scheduleAtFixedRate(new PytanieTask(),5000,8000); // czestotliwosc pojawiania sie pytan
			};
		
/**
 * metoda zatrzymujaca wszystkie timery
 */
		void stoppomiaryczasow() {
			czas.cancel();
			czasspadku.cancel();
			czaszegara.cancel();
		}
/**
 * metda mowiaca o bsludze wcisnietego przyciski
 */
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT: przyciski[0] = true; break;
		case KeyEvent.VK_RIGHT: przyciski[1] = true; break;
		}
	}
		
/**
 * metoda mowiaca o obsludze 'uwolnionego' przycisku		
 */
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT: przyciski[0] = false; break;
			case KeyEvent.VK_RIGHT: przyciski[1] = false; break;
			}
		}
		
/**
 * klasa wewnetrzna Spadanie dziedziczaca po TimerTask
 * @author Anna
 *
 */
		
	class Spadanie extends TimerTask {
			public void run() {
			
/** petla prezentujaca spadanie elementow */
				for(Postaci mikroorganizmy : objects) {
					mikroorganizmy.uaktualnijPozycje();
					
/** warunek dzieki ktoremu elementy po wyjsciu poza dolny ekran ponownie pojawiaja sie na gorze */
					 if (mikroorganizmy.getPosY() > 700) {
		                    resetPosition(mikroorganizmy);
		                }
		            }
		            
		            repaint();
		            
		        }
		    }
/**
 * metoda resetujaca pozycje, ustawiajaca losowe polozenie X, Y spadajacych na nowo elementow	    
 * @param mikroorganizmy
 */
		    void resetPosition(Postaci mikroorganizmy) {
		        mikroorganizmy.resetPosX(r.nextInt(930));
		        mikroorganizmy.resetPosY(r.nextInt(10));
		    }
				
/**
 * wewnetrzna klasa poruszanie dziedziczaca po TimerTask umozliwiajaca sterowanie kropelka krwi za pomoca klawiatury
 * @author Anna
 *
 */
	class Poruszanie extends TimerTask {
		public void run() {
/** lewa strzalka, wspolrzedna x maleje w lewo */
			if(przyciski[0]) 
				wspx-=3;	
/** prawa strzalka, wspolrzedna x maleje w prawo */
			if(przyciski[1]) 
				wspx+=3;	
			
/** int a= (warunek) ? jesli tak : jesli nie */
			wspx=(wspx<-11) ? -11:wspx;
			wspx=(wspx>930) ? 930:wspx;
			
			
			
			repaint();
		}
	}
/**
 * klasa wewnetrza PytanieTask dziedziczaca po TimerTask, po pojawieniu sie pytania nastepuje zatrzymanie timerow, ustawienie wlasciwosci wizualnych pojawiajacego sie pytania
 * @author Anna
 *
 */
		class PytanieTask extends TimerTask {
		  
			public void run() {
				stoppomiaryczasow();
				Pytanie pytanie = wylosujPytanie();
				
				Object[] options = new Object[] {"a", "b", "c"};
				String[] abc = {"a","b","c"};
                String message = pytanie.text + "\n\n";
                for (int i = 0; i < pytanie.answers.size(); i++) {
                    message += abc[i] + ") " + pytanie.answers.get(i).text + "\n";
                }

                int odpowiedz = JOptionPane.showOptionDialog(null, message,
                        "Pytanie",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
/** sprawdzenie odpowiedzi udzielonej przez gracza - za pozytywna odp +1 pkt, za negatywna -1pkt */
                if (pytanie.sprawdzOdpowiedz(odpowiedz)) {
                    points++;
                } else {
                    points--;
                }
/** warunek, który w zaleznosci od liczby zdobytych pkt ustawiaa automatycznie runde 1 lub runde 2 */
                if(points<5)
        		{
        		startpomiaryczasow();
        		wczytajPytania();
        		}
        		else if(points>=5)
        			
        		{
        			runda2startpomiaryczasow();
        			wczytajPytania();
        	
        		}

			}
		}
/**
 * metoda losujaca pytania
 * @return wylosowane pytanie
 */
		Pytanie wylosujPytanie() {
			int idx = r.nextInt(pytania.size()-1);
			return pytania.get(idx);
		}
		

/**
 * metoda paintCmponent, ktora umozliwia wyswietlenie tla gry, ikonky kropelki
 */
	public void paintComponent(Graphics g) {
		Graphics2D g2= (Graphics2D)g;
		tlo=new ImageIcon("obrazki\\tlo1k.jpg").getImage();
		g2.drawImage(tlo, 0, 0, this);
		
		kropelka =new ImageIcon("obrazki\\m.gif").getImage();
		g2.drawImage(kropelka, wspx, wspy, this);

/** petla dzieki ktorej elementy spadajace pojawaiaja sie na ekranie */
	      for (Postaci mikroorganizmy : objects) {
	    	  g2.drawImage(mikroorganizmy.image, mikroorganizmy.getPosX(), mikroorganizmy.getPosY(), this);
	      }
		
		updatePoints();			
		}
	
/**
 * metoda odpowiedzialna za kolizje elementow z kropelka krwi
 */
	private void updatePoints() {

        Rectangle dropRec = new Rectangle(wspx, wspy, kropelka.getWidth(this), kropelka.getHeight(this));

        for (Postaci obj : objects) {
            Rectangle micRec = new Rectangle(obj.posX, obj.posY, obj.image.getWidth(this), obj.image.getHeight(this));
/** warunek zderzenie elementow z kropelka */
            if (dropRec.intersects(micRec)) {
/** warunek sprawdzajacy czy kropelka zderzyla sie z "dobrym" lub "zlym" elementem i dodanie/odjecie punktu */
                if (obj instanceof Witaminy) {
                   points++;
                } else {
                	points--;
                }
                resetPosition(obj);


            }
/** wyswietlenie aktualnej punktacji */
            punktacja.setText("Punkty: " + points);
            if ( points <5)
            {
            	zmianarundy.setFont(new Font("Broadway", Font.BOLD, 30));
	        	zmianarundy.setForeground(Color.WHITE);
	            zmianarundy.setBounds(700,5,200,100);
                add(zmianarundy);
                zmianarundy.setText("RUNDA 1");
            }
           
            else if ( points >=5)
            {
            	zmianarundy.setFont(new Font("Broadway", Font.BOLD, 30));
	        	zmianarundy.setForeground(Color.WHITE);
	            zmianarundy.setBounds(700,5,200,100);
                add(zmianarundy);
                zmianarundy.setText("RUNDA 2");
            }
        }
	}
/**
 * metoda zatrzymuja wszystkie timery
 */
	public void pause() {
	    this.czas.cancel();
	    this.czasspadku.cancel();
	    this.czaszegara.cancel();
	}
/**
 * metoda wznawiajaca wszytskie timery
 */
	public void resume() {
		
	    this.czasspadku = new Timer();
	    this.czasspadku.schedule( new Spadanie(),0,predkoscmikroorg );
	    this.czas = new Timer();
	    this.czas.schedule( new Poruszanie(),0,predkosckropli);
	    this.czaszegara = new Timer();
	    this.czaszegara.schedule( new PytanieTask(),5000,8000);
	    
	}
/** ActionListener buttona "Pauza" */
		private class B1 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				pause();
		
			}
		}
/** ActionListener buttona "Wznow" */
		private class B3 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				resume();
				requestFocusInWindow();
		
			}
		}
/** ActionListener buttona "Powrot do menu" */
		private class B2 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
					
				stoppomiaryczasow();
				menu.powrot();
				
				
		
			}
		}
}
		

	

	
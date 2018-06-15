import java.util.ArrayList;
import java.util.List;
/**
 * klasa Pytanie
 * @author Anna
 *
 */
public class Pytanie {
/** deklaracja zmiennej */
    public String text;
/** utworzenie listy */
    public List<Odpowiedz> answers = new ArrayList<Odpowiedz>();
/** konstruktor klasy Pytanie */
    public Pytanie(String text) {
        this.text = text;
    }
/**
 * metoda umozliwiajaca dodawanie pytan, ktore beda losowo wyswietlane
 * @param text
 * @param ok
 */
    public void dodajPytanie(String text, boolean ok) {
        answers.add(new Odpowiedz(text, ok));
    }
/**
 * metoda sprawdzajaca czy podana przez gracza odpowiedz jest poprawna
 * @param answer
 * @return odpowiedz
 */
    public boolean sprawdzOdpowiedz(int answer) {
        try {
            Odpowiedz odpowiedz = answers.get(answer);
			return odpowiedz.ok;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
            
    }
}

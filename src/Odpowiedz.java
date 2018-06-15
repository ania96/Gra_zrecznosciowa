
/**
 * klasa Odpowiedz
 * @author Anna
 *
 */
public class Odpowiedz {
    
    public String text;
    public boolean ok;
/** konstruktor klasy odpowiedz */
    public Odpowiedz(String text, boolean ok) {
        this.text = text;
        this.ok = ok;
    }
/** nadpisanie */
    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", ok=" + ok +
                '}';
    }
}

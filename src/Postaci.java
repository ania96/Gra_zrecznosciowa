import java.awt.Image;
import java.util.UUID;
/**
 * klasa Postaci
 * @author Anna
 *
 */
public class Postaci {
/** deklaracja zmiennych */
static final int factor =2;
	public Image image;
    int delay = 0;
    int posX;
    int posY;
/** konstruktor klasy Postaci zawierajacy 3 argumenty   */
    public Postaci(Image image, int delay, int posX, int posY) {
        this.image = image;
        this.delay = delay;
        this.posX = posX;
        this.posY = posY - factor* delay;
    }
/**
 *  metoda ustawiajaca pozycje X spadajacych elementow
 * @return posX
 */
    public int getPosX() {
        return posX;
    }
/**
 * metoda resetujaca pozycje Y spadajacych elementow, dzieki ktorej elementy ponownie pojawiaja sie u gory ekranu
 * @param delay
 */
    public void resetPosY(int delay) {
        this.posY =  delay;
    }
/**
 * metoda ustawiajaca pozycje Y spadajacych elementow
 * @return posY
 */
    public int getPosY() {
        return posY;
    }
/**
 * metoda dzieki ktorej elementy spadaja pionowo w dol
 */
    public void uaktualnijPozycje() {
        posY += 5;
    }
  /**
   * metoda resetujaca pozycje X spadajacych elementow, dzieki ktorej elementy pojawiaja sie za kazdym razem w innym polozeniu X
   * @param posX
   */
    public void resetPosX(int posX) {
        this.posX = posX;
    }
}

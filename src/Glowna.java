import javax.swing.JFrame;

/**
 * klasa glowna zawierajaca main'a
 * @author Anna
 *
 */

public class Glowna {
	static Menu m;

	public static void main(String[] args) {
		m = new Menu ("It's my Life");
		 m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

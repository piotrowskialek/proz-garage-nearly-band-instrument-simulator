

public class Test {
	public static void main(String[] args) {

		GarageBandView view = new GarageBandView();

		GarageBandPlayer player = new GarageBandPlayer();
		
		GarageBandController controller = new GarageBandController(view, player);

	}
}
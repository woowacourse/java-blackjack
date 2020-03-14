import controller.BlackJackController;

public class Main {
	public static void main(String[] args) {
		try {
			new BlackJackController().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

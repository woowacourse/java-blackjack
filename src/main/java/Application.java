import controller.BlackJackController;

public class Application {
	public static void main(String[] args) {
		try {
			new BlackJackController().run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

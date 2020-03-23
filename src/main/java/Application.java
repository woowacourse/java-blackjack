import controller.BlackjackController;

public class Application {
	public static void main(String[] args) {
		try {
			new BlackjackController().run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

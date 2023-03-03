import controller.BlackJackController;

public class BlackJackApplication {

    public static void main(String[] args) {
        try {
            new BlackJackController().run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

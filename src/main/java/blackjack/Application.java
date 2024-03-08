package blackjack;

public class Application {
    public static void main(String[] args) {
        try {
            BlackJackGame game = new BlackJackGame();
            game.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

import blackjack.domain.Blackjack;

public class Application {
    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(new Blackjack());
        blackjackController.playBlackJack();
    }
}

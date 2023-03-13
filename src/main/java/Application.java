import domain.CardGame;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        try {
            cardGame.run();
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }
}

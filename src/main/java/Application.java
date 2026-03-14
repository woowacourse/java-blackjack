import controller.BlackjackGame;
import domain.card.CardGenerator;
import domain.card.BlackjackGameCardGenerator;
import view.ApplicationView;
import view.InputReader;
import view.OutputWriter;

public class Application {

    public static void main(String[] args) {
        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();
        ApplicationView applicationView = new ApplicationView(reader, writer);
        CardGenerator gameCardGenerator = new BlackjackGameCardGenerator();

        BlackjackGame blackjackGame = new BlackjackGame(applicationView, gameCardGenerator);
        blackjackGame.start();
    }

}

import controller.BlackjackGame;
import domain.betting.manager.BettingPolicyManager;
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
        BettingPolicyManager bettingPolicyManager = new BettingPolicyManager();

        BlackjackGame blackjackGame = new BlackjackGame(applicationView, gameCardGenerator, bettingPolicyManager);
        blackjackGame.start();
    }

}

import domain.BlackjackGame;
import view.ConsoleHitServiceImpl;
import view.ConsoleInitServiceImpl;
import view.OutputView;

public class BlackJackApplication {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = BlackjackGame.init(new ConsoleInitServiceImpl());
        OutputView.printInitCards(blackjackGame.spreadCards());
        OutputView.printCardAndScore(blackjackGame.play(new ConsoleHitServiceImpl()));
        OutputView.printResult(blackjackGame.getResult());
    }
}

import domain.BlackJackGame;
import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {
    public static void main(String[] args) {
        Players players = generatePlayers();
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        blackJackGame.run();
    }

    private static Players generatePlayers() {
        try {
            return new Players(initPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return generatePlayers();
        }
    }

    private static List<String> initPlayerNames() {
        OutputView.printInputPlayerNameMessage();
        return InputView.inputPlayerNames();
    }

}

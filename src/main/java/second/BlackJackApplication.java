package second;

import second.domain.BlackJackGame;
import second.domain.player.Player;
import second.domain.answer.PlayerDecisions;
import second.view.InputView;
import second.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {
    public static void main(String[] args) {
        List<Player> players = inputPlayerNames();
        BlackJackGame blackJackGame = new BlackJackGame(players);

        blackJackGame.drawFirstPhase();
        OutputView.printInitialCards(blackJackGame.getPlayers(), blackJackGame.getDealer());

        final PlayerDecisions playerDecisions = new PlayerDecisions(InputView::choose, OutputView::printGamerCards);
        blackJackGame.doDrawMorePhase(playerDecisions);

        printResults(blackJackGame);
    }

    private static List<Player> inputPlayerNames() {
        return InputView.inputPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private static void printResults(BlackJackGame blackJackGame) {
        OutputView.printScore(blackJackGame);
        OutputView.printResults(blackJackGame.calculateResults());
    }
}

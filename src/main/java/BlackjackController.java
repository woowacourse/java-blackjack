import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import view.InputView;
import view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;
    private final BlackjackGame blackjackGame;

    public BlackjackController(InputView inputView, ResultView resultView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        readAndRegistPlayers();
        blackjackGame.giveHand();

        List<Player> players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        resultView.printParticipantsCards(players, dealer);

        playerHitStand(players);
        resultView.printDealerHitStand(blackjackGame.dealerHitsStand());
        resultView.printCardsWithResult(players, dealer);
        resultView.printResultStatistics(players, dealer);
    }

    private void readAndRegistPlayers() {
        List<String> names = inputView.readPlayerNames();
        blackjackGame.registPlayers(names);
    }

    private void playerHitStand(List<Player> players) {
        for (Player player : players) {
            hitStand(player);
        }
    }

    private void hitStand(Player player) {
        while (inputView.readHitStand(player.getName()).equals("y")) {
            blackjackGame.giveCard(player);
            resultView.printCards(player);
        }
    }
}

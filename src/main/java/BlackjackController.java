import domain.BlackjackGame;
import domain.Constant;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import view.InputView;
import view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;
    private BlackjackGame blackjackGame;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        readAndRegistPlayers();
        blackjackGame.giveHand();

        Players players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();
        resultView.printParticipantsCards(players.getPlayers(), dealer);

        playerHitStand(players.getPlayers());

        while (true) {
            boolean dealerHitStand = dealer.decideHitStand(Constant.DEALER_HIT_STAND_BOUNDARY);
            if (!dealerHitStand) {
                resultView.printDealerHitStand(dealerHitStand);
                break;
            }
            blackjackGame.giveCard(dealer);
            resultView.printDealerHitStand(dealerHitStand);
        }

        resultView.printCardsWithResult(players.getPlayers(), dealer);
        resultView.printResultStatistics(players.getPlayers(), dealer);
    }

    private void readAndRegistPlayers() {
        List<String> names = inputView.readPlayerNames();
        this.blackjackGame = new BlackjackGame(names);
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

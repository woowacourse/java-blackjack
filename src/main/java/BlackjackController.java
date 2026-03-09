import domain.BlackjackGame;
import domain.participant.Dealer;
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

        blackjackGame.playerHitStand((player) -> inputView.readHitStand(player),
                (player) -> resultView.printCards(player));

        blackjackGame.dealerHitStand((decision) -> resultView.printDealerHitStand(decision));

        resultView.printCardsWithResult(players.getPlayers(), dealer);
        resultView.printResultStatistics(players.getPlayers(), dealer);
    }

    private void readAndRegistPlayers() {
        List<String> names = inputView.readPlayerNames();
        this.blackjackGame = new BlackjackGame(names);
    }
}

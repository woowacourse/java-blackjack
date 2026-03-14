import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;
import view.InputView;
import view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        BlackjackGame blackjackGame = readAndRegistPlayers();

        blackjackGame.setBetMoney(inputView::readBetMoney);

        blackjackGame.giveHand();

        Players players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();

        resultView.printParticipantsCards(players.getPlayers(), dealer);
        blackjackGame.hitStandEachPlayers(inputView::readHitStand, resultView::printCards);
        blackjackGame.hitStandDealer(resultView::printDealerHitStand);

        resultView.printCardsWithResult(players.getPlayers(), dealer);

        resultView.printResult(blackjackGame.getResult());
    }

    private BlackjackGame readAndRegistPlayers() {
        List<String> names = inputView.readPlayerNames();
        return new BlackjackGame(names);
    }
}

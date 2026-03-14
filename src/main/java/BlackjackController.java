import domain.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
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
        BlackjackGame blackjackGame = readPlayerInfos();

        blackjackGame.giveHand();
        Players players = blackjackGame.getPlayers();
        Dealer dealer = blackjackGame.getDealer();

        resultView.printParticipantsCards(players.getPlayers(), dealer);
        blackjackGame.hitStandEachPlayers(inputView::readHitStand, resultView::printCards);
        blackjackGame.hitStandDealer(resultView::printDealerHitStand);

        resultView.printCardsWithResult(players.getPlayers(), dealer);

        resultView.printResult(blackjackGame.getResult());
    }

    private BlackjackGame readPlayerInfos() {
        List<String> names = inputView.readPlayerNames();
        List<String> betMoneys = inputView.readBetMoney(names);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(Player.of(names.get(i), betMoneys.get(i)));
        }

        return new BlackjackGame(players);
    }
}

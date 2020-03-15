import java.util.List;

import controller.BlackJackGame;
import domain.card.DeckFactory;
import domain.result.GameResult;
import domain.user.Dealer;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        List<String> playerNames = InputView.receiveNameInput();
        Players players = Players.of(playerNames);
        Dealer dealer = Dealer.appoint();

        BlackJackGame blackJackGame = BlackJackGame.set(DeckFactory.createDeck());

        blackJackGame.firstDealOut(dealer, players);
        OutputView.printFirstDealOutResult(dealer, players);

        blackJackGame.additionalDealOut(dealer, players);

        GameResult gameResult = GameResult.of(dealer, players);
        OutputView.printTotalResult(gameResult);
        OutputView.printWinningResult(gameResult);
    }
}

import controller.BlackJackGame;
import domain.card.DeckFactory;
import domain.result.GameResult;
import domain.user.Dealer;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        String names = InputView.receiveNameInput();
        Players players = Players.of(names);
        Dealer dealer = Dealer.appoint();

        BlackJackGame blackJackGame = BlackJackGame.set(DeckFactory.createDeck());

        blackJackGame.firstDealOut(dealer, players);
        OutputView.printFirstDealOutResult(dealer, players);

        blackJackGame.additionalDealOut(dealer, players);
        OutputView.printTotalResult(dealer, players);

        GameResult gameResult = GameResult.of(dealer, players);
        OutputView.printWinningResult(gameResult);
    }
}

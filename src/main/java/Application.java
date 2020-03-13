import controller.BlackJackGame;
import domain.card.DeckFactory;
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

        blackJackGame.reflectResult(dealer, players);
        OutputView.printWinningResult(dealer, players);
    }
}

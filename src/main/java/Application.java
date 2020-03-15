import controller.BlackJackGame;
import domain.user.Dealer;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        String names = InputView.receiveNameInput();
        Players players = Players.of(names);
        Dealer dealer = Dealer.appoint();

        BlackJackGame blackJackGame = new BlackJackGame();

        blackJackGame.firstDealOut(dealer, players);
        OutputView.printFirstDealOutResult(dealer, players);

        blackJackGame.additionalDealOut(dealer, players);
        OutputView.printAllTotalResult(dealer, players);

        blackJackGame.decideWinner(dealer, players);
        OutputView.printWinningResult(dealer, players);
    }
}

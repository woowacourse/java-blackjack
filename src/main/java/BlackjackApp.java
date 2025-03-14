import game.Blackjack;
import money.WagerMoney;
import view.InputView;
import view.OutputView;

public class BlackjackApp {

	public static void main(String[] args) {
		final InputView inputView = new InputView();
		final OutputView outputView = new OutputView();
		final Blackjack blackjack = Blackjack.from(inputView.readPlayerNames());
		final WagerMoney wagerMoney = new WagerMoney(inputView.readPlayersMoney(blackjack));

		blackjack.initPickCard();
		outputView.printHandOut(blackjack);

		blackjack.pickCardPlayersIfNotBust(inputView::readPlayerAnswer);
		blackjack.pickCardDealerIfNotMax();

		outputView.printDealerPickCard(blackjack);
		outputView.printDealerHandResult(blackjack);
		outputView.printPlayerHandResult(blackjack);

		blackjack.duel();

		outputView.printWagerResult(wagerMoney);
	}
}

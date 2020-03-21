import java.util.Map;

import domains.card.Deck;
import domains.result.GameResultFactory;
import domains.result.Profits;
import domains.result.ResultType;
import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;
import domains.user.money.BettingMoney;
import view.InputView;
import view.OutputView;

class GameController {
	GameController() {
		Deck deck = new Deck();
		Dealer dealer = new Dealer(deck);
		Players players = new Players(InputView.inputPlayerNames(), deck);
		Map<Player, BettingMoney> playersBettingMoney = players.bet(InputView::inputBettingMoney);
		OutputView.printInitialHands(players, dealer);

		hitOrStay(players, dealer, deck);

		Map<Player, ResultType> playerResult = GameResultFactory.create(players, dealer);
		OutputView.printAllHands(players, dealer);
		OutputView.printGameResult(new Profits(playerResult, playersBettingMoney));
	}

	private void hitOrStay(Players players, Dealer dealer, Deck deck) {
		players.hitOrStay(deck, InputView::inputYesOrNo, OutputView::printHands);
		dealer.hitOrStay(deck, OutputView::printDealerHitCard);
	}
}

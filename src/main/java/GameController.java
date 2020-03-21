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
		OutputView.printInputPlayerNames();
		Deck deck = new Deck();
		Dealer dealer = new Dealer(deck);
		Players players = new Players(InputView.inputPlayerNames(), deck);
		Map<Player, BettingMoney> playersBettingMoney = players.bet(OutputView::printInputBettingMoney, InputView::inputBettingMoney);
		OutputView.printInitialHands(players, dealer);

		hitOrStay(players, dealer, deck);
		if (dealer.isHit()) {
			OutputView.printDealerHitCard();
		}
		OutputView.printAllHands(players, dealer);

		Map<Player, ResultType> playerResult = GameResultFactory.create(players, dealer);
		OutputView.printGameResult(new Profits(playerResult, playersBettingMoney));
	}

	private void hitOrStay(Players players, Dealer dealer, Deck deck) {
		for (Player player : players) {
			needMoreCard(player, deck);
		}
		dealer.hitOrStay(deck);
	}

	private void needMoreCard(Player player, Deck deck) {
		while (player.needMoreCard(getAnswerForNeedMoreCard(player), deck)) {
			if (player.isBurst()) {
				OutputView.printBurst(player);
				break;
			}
			OutputView.printHands(player);
		}
	}

	private String getAnswerForNeedMoreCard(Player player) {
		OutputView.printNeedMoreCard(player);
		return InputView.inputYesOrNo();
	}
}

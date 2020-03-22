import java.util.HashMap;
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
		Map<Player, BettingMoney> playersBettingMoney = bet(players);
		OutputView.printInitialHands(players, dealer);

		hitOrStay(players, dealer, deck);

		Map<Player, ResultType> playerResult = GameResultFactory.create(players, dealer);
		OutputView.printAllHands(players, dealer);
		OutputView.printGameResult(new Profits(playerResult, playersBettingMoney));
	}

	private Map<Player, BettingMoney> bet(Players players) {
		Map<Player, BettingMoney> playerBettingMoney = new HashMap<>();
		for (Player player : players) {
			playerBettingMoney.put(player, new BettingMoney(InputView.inputBettingMoney(player)));
		}
		return playerBettingMoney;
	}

	private void hitOrStay(Players players, Dealer dealer, Deck deck) {
		for (Player player : players) {
			needMoreCard(deck, player);
		}

		dealer.hitOrStay(deck);
		if (dealer.isHit()) {
			OutputView.printDealerHitCard();
		}
	}

	private void needMoreCard(Deck deck, Player player) {
		while (player.needMoreCard(deck, InputView.inputYesOrNo(player))) {
			if (player.isBurst()) {
				OutputView.printBurst(player);
				break;
			}
			OutputView.printHands(player);
		}
	}
}

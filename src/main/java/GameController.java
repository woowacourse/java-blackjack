import domains.card.Deck;
import domains.result.GameResult;
import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;
import view.InputView;
import view.OutputView;

class GameController {
	GameController() {
		Deck deck = new Deck();
		OutputView.printInputPlayerNames();
		Players players = new Players(InputView.inputPlayerNames(), deck);
		Dealer dealer = new Dealer(deck);
		OutputView.printInitialHands(players, dealer);

		run(deck, dealer, players);
	}

	private void run(Deck deck, Dealer dealer, Players players) {
		hitOrStay(players, deck);
		dealer.hit(deck);

		if (dealer.handSize() == 3) {
			OutputView.printDealerHitCard();
		}
		OutputView.printAllHands(players, dealer);

		GameResult gameResult = new GameResult();
		gameResult.create(players, dealer);
		OutputView.printGameResult(gameResult);
	}

	private void hitOrStay(Players players, Deck deck) {
		for (Player player : players) {
			needMoreCard(player, deck);
		}
	}

	private void needMoreCard(Player player, Deck deck) {
		while (player.needMoreCard(getAnswerForNeedMoreCard(player), deck)) {
			if (player.checkBurst()) {
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
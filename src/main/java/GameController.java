import domains.card.Deck;
import domains.result.GameResult;
import domains.result.Profits;
import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;
import domains.user.name.PlayerName;
import domains.user.name.PlayerNames;
import view.InputView;
import view.OutputView;

class GameController {
	private Players players;

	GameController() {
		OutputView.printInputPlayerNames();
		PlayerNames playerNames = new PlayerNames(InputView.inputPlayerNames());
		Deck deck = new Deck();
		Dealer dealer = new Dealer(deck);
		bet(playerNames, deck);
		OutputView.printInitialHands(players, dealer);

		hitOrStay(dealer, deck);
		if (dealer.isHit()) {
			OutputView.printDealerHitCard();
		}
		OutputView.printAllHands(players, dealer);

		GameResult gameResult = new GameResult(players, dealer);
		OutputView.printGameResult(new Profits(gameResult));
	}

	private void bet(PlayerNames playerNames, Deck deck) {
		players = new Players();
		for (PlayerName name : playerNames) {
			OutputView.printInputBettingMoney(name);
			players.add(new Player(name, InputView.inputBettingMoney(), deck));
		}
	}

	private void hitOrStay(Dealer dealer, Deck deck) {
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
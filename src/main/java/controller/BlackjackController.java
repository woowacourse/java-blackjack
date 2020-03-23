package controller;

import java.util.List;

import domain.card.Deck;
import domain.gamer.Answer;
import domain.gamer.Dealer;
import domain.gamer.Money;
import domain.gamer.MoneyFactory;
import domain.gamer.Name;
import domain.gamer.NameFactory;
import domain.gamer.Player;
import domain.gamer.PlayersFactory;
import domain.result.GameResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {
	private final List<Player> players;
	private final Dealer dealer;
	private final Deck deck;

	public BlackjackController() {
		this.players = createPlayers();
		this.dealer = new Dealer();
		this.deck = new Deck();
	}

	public void run() {
		giveTwoCards();
		giveCardToPlayers();
		giveCardToDealer();

		GameResult gameResult = GameResult.of(players, dealer);
		OutputView.printCardsScore(gameResult);
		OutputView.printDealerResult(gameResult.dealerResult());
		OutputView.printPlayersResult(gameResult);
	}

	private List<Player> createPlayers() {
		List<Name> names = NameFactory.create(InputView.inputUserNames());
		List<Money> bettingMoney = MoneyFactory.create(InputView.inputMoney(names));
		return PlayersFactory.of(names, bettingMoney);
	}

	private void giveTwoCards() {
		for (Player player : players) {
			player.hit(deck.drawCard());
			player.hit(deck.drawCard());
		}
		dealer.hit(deck.drawCard());
		dealer.hit(deck.drawCard());
		OutputView.printGiving(players, dealer);
		OutputView.printDealerCard(dealer);
		OutputView.printPlayersCard(players);
	}

	private void giveCardToPlayers() {
		for (Player player : players) {
			giveCardToPlayer(player);
		}
	}

	private void giveCardToPlayer(Player player) {
		while (player.canHit() && Answer.of(InputView.inputReceiveMore(player)).isYes()) {
			player.hit(deck.drawCard());
			OutputView.printCards(player);
		}
	}

	private void giveCardToDealer() {
		while (dealer.canHit()) {
			dealer.hit(deck.drawCard());
			OutputView.printDealerCards();
		}
	}
}
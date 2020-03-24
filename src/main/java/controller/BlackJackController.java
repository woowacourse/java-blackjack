package controller;

import java.util.HashMap;
import java.util.Map;

import domain.BettingMoney;
import domain.BettingMoneys;
import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.PlayersFactory;
import domain.Profits;
import domain.WhetherAddCard;
import view.InputView;
import view.OutputView;

public class BlackJackController {
	private static final int DISTRIBUTE_CARD_SIZE = 2;

	private CardDeck cardDeck;
	private Dealer dealer;
	private Players players;
	private BettingMoneys bettingMoneys;

	public BlackJackController(String inputNames) {
		this.cardDeck = new CardDeck();
		this.dealer = new Dealer();
		this.players = PlayersFactory.create(inputNames);
		this.bettingMoneys = createBettingMoneys();
	}

	public void run() {
		cardDeck.shuffle();
		distributeTwoCard();
		if (dealer.isNotBlackJack()) {
			continueAddIfPlayersWantCard();
			continueAddIfDealerNeedCard();
		}
		OutputView.printCardsResults(dealer, players);
		OutputView.printProfits(Profits.calculate(dealer, players, bettingMoneys));
	}

	private BettingMoneys createBettingMoneys() {
		Map<Player, BettingMoney> bettingMoneys = new HashMap<>();
		players.forEach(
				player -> bettingMoneys.put(player, BettingMoney.from(InputView.inputBettingMoney(player.getName()))));
		return new BettingMoneys(bettingMoneys);
	}

	private void distributeTwoCard() {
		for (int i = 0; i < DISTRIBUTE_CARD_SIZE; i++) {
			dealer.giveCard(cardDeck, dealer);
			dealer.giveCard(cardDeck, players);
		}
		OutputView.printFirstDistribute(dealer, players);
	}

	private void continueAddIfPlayersWantCard() {
		players.forEach(this::addCardIfWant);
	}

	private void addCardIfWant(Player player) {
		while (player.shouldAddCard(WhetherAddCard.of(InputView.inputMoreCard(player)).isYes())) {
			dealer.giveCard(cardDeck, player);
			OutputView.printCards(player.getName(), player.getCards());
		}
	}

	private void continueAddIfDealerNeedCard() {
		while (dealer.shouldAddCard(true)) {
			dealer.addCard(cardDeck.pop());
			OutputView.printDealerAddCard();
		}
	}
}

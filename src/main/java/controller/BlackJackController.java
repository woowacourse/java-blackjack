package controller;

import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.PlayersFactory;
import domain.ResultCalculator;
import domain.WhetherAddCard;
import view.InputView;
import view.OutputView;

public class BlackJackController {
	private static final int DISTRIBUTE_CARD_SIZE = 2;

	private CardDeck cardDeck;
	private Dealer dealer;
	private Players players;

	public BlackJackController(String inputNames) {
		this.cardDeck = new CardDeck();
		this.dealer = new Dealer();
		this.players = PlayersFactory.create(inputNames);
	}

	public void run() {
		cardDeck.shuffle();
		distributeTwoCard();
		askMoreCard();
		addCardIfNeed();
		OutputView.printCardsResults(dealer, players);
		OutputView.printGameResult(ResultCalculator.calculate(dealer, players));
	}

	private void distributeTwoCard() {
		for (int i = 0; i < DISTRIBUTE_CARD_SIZE; i++) {
			dealer.giveCard(cardDeck, dealer);
			dealer.giveCard(cardDeck, players);
		}
		OutputView.printFirstDistribute(dealer, players);
	}

	private void askMoreCard() {
		players.forEach(this::addCardIfWant);
	}

	private void addCardIfWant(Player player) {
		while (player.isNotBust() && WhetherAddCard.of(InputView.inputMoreCard(player)).isYes()) {
			dealer.giveCard(cardDeck, player);
			OutputView.printCards(player.getName(), player.getCards());
		}
	}

	private void addCardIfNeed() {
		while (dealer.shouldAddCard()) {
			dealer.addCard(cardDeck.pop());
			OutputView.printDealerAddCard();
		}
	}
}

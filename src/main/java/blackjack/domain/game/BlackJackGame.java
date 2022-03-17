package blackjack.domain.game;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;

public class BlackJackGame {

	public static final int INIT_DISTRIBUTION_COUNT = 2;
	private static final int DEALER_OPEN_COUNT_FIRST = 1;

	private final Gamers gamers;
	private final Deck deck;

	public BlackJackGame(List<String> names, BettingInjector betting, Deck deck) {
		this.gamers = new Gamers(names, betting);
		this.deck = deck;
	}

	public BlackJackReferee play(HitRequester hitOrStay, CardsChecker cardChecker) {
		giveFirstCards();
		checkFirstCardsOfGamers(cardChecker);
		askPlayerHitOrStay(hitOrStay, cardChecker);
		askDealerHitOrStay();
		return createResult();
	}

	private void giveFirstCards() {
		for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
			gamers.giveCardToAllGamers(this.deck);
		}
	}

	private void checkFirstCardsOfGamers(CardsChecker cardChecker) {
		Dealer dealer = gamers.getDealer();
		cardChecker.check(dealer.getName(), bringDealerFirstCard(dealer));
		gamers.getPlayers()
			.forEach(player -> cardChecker.check(player.getName(), player.getCards()));
	}

	private List<Card> bringDealerFirstCard(Dealer dealer) {
		return dealer.getCards()
			.subList(0, DEALER_OPEN_COUNT_FIRST);
	}

	private void askPlayerHitOrStay(HitRequester hitOrStay, CardsChecker cardChecker) {
		for (String name : gamers.findPlayerNames()) {
			hitOrStay(name, hitOrStay, cardChecker);
		}
	}

	private void hitOrStay(String name, HitRequester hitOrStay, CardsChecker cardChecker) {
		while (!isBust(name) && hitOrStay.request(name)) {
			gamers.giveCardToPlayer(name, deck);
			cardChecker.check(name, gamers.findCardsOfPlayer(name));
		}
	}

	private boolean isBust(String name) {
		return gamers.checkPlayerDrawPossible(name);
	}

	private void askDealerHitOrStay() {
		while (gamers.checkDealerDrawPossible()) {
			gamers.giveCardToDealer(deck);
		}
	}

	private BlackJackReferee createResult() {
		return BlackJackReferee.create(gamers.getDealer(), gamers.getPlayers());
	}

	public Gamers getGamers() {
		return gamers;
	}
}

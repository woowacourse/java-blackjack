package domain;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

public class TestData {

	public static Player getScore19Player() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.NINE),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore19", 0);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getScore21Player() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore21", 0);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getScore20Player() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore20", 0);
		cards.forEach(player::addCard);
		return player;
	}

	public static Dealer getScore20Dealer() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Dealer dealer = new Dealer();
		cards.forEach(dealer::addCard);
		return dealer;
	}
}

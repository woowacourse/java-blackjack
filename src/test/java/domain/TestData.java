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
		Player player = new Player("playerScore19", 19);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getScore20Player() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore20", 20);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getScore21Player() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore21", 21);
		cards.forEach(player::addCard);
		player.addCard(new Card(Suit.CLOVER, Denomination.ACE));
		return player;
	}

	public static Player getBlackJackPlayer() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerBlackJack", 21);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getBustPlayer() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerBust", 22);
		cards.forEach(player::addCard);
		player.addCard(new Card(Suit.CLOVER, Denomination.TWO));
		return player;
	}

	public static Dealer getScore19Dealer() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.NINE),
			new Card(Suit.HEART, Denomination.TEN));
		Dealer dealer = new Dealer();
		cards.forEach(dealer::addCard);
		return dealer;
	}

	public static Dealer getScore20Dealer() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Dealer dealer = new Dealer();
		cards.forEach(dealer::addCard);
		return dealer;
	}

	public static Dealer getBlackJackDealer() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		Dealer dealer = new Dealer();
		cards.forEach(dealer::addCard);
		return dealer;
	}

	public static Dealer getBustDealer() {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Dealer dealer = new Dealer();
		cards.forEach(dealer::addCard);
		dealer.addCard(new Card(Suit.CLOVER, Denomination.TWO));
		return dealer;
	}
}

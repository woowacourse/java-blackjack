package domain.result;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Player;

public class TestData {

	public static Player getScore19Player(int bet) {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.NINE),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore19", bet);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getScore20Player(int bet) {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore20", bet);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getScore21Player(int bet) {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerScore21", bet);
		cards.forEach(player::addCard);
		player.addCard(new Card(Suit.CLOVER, Denomination.ACE));
		return player;
	}

	public static Player getBlackJackPlayer(int bet) {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerBlackJack", bet);
		cards.forEach(player::addCard);
		return player;
	}

	public static Player getBustPlayer(int bet) {
		List<Card> cards = List.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("playerBust", bet);
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

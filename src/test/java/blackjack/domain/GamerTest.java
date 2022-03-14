package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GamerTest {
	@Test
	void distribute_card() {
		Gamer gamer = new Player(new Name("pobi"));
		Deck deck = new Deck();
		gamer.addCard(deck.distributeCard());
		assertThat(gamer.getCards().size()).isEqualTo(1);
	}

	@Test
	void isBust() {
		Gamer gamer = new Dealer();
		gamer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		gamer.addCard(new Card(CardDenomination.TEN, CardSuit.HEART));
		gamer.addCard(new Card(CardDenomination.TWO, CardSuit.SPADE));
		assertThat(gamer.isBust()).isTrue();
	}

	@Test
	void check_optimal_ace_sum() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		gamer.addCard(new Card(CardDenomination.ACE, CardSuit.SPADE));
		gamer.addCard(new Card(CardDenomination.NINE, CardSuit.SPADE));
		assertThat(gamer.getScore()).isEqualTo(21);
	}

	@Test
	void check_blackjack() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		gamer.addCard(new Card(CardDenomination.TEN, CardSuit.SPADE));
		assertThat(gamer.isBlackJack()).isTrue();
	}

	@Test
	void check_not_blackjack() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.addCard(new Card(CardDenomination.TEN, CardSuit.SPADE));
		gamer.addCard(new Card(CardDenomination.TEN, CardSuit.CLOVER));
		gamer.addCard(new Card(CardDenomination.ACE, CardSuit.HEART));
		assertThat(gamer.isBlackJack()).isFalse();
	}
}

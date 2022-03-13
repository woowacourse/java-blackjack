package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GamerTest {
	@Test
	void distribute_card() {
		Gamer gamer = new Player(new Name("pobi"));
		Deck deck = new Deck();
		gamer.processCard(deck.distributeCard());
		assertThat(gamer.getCards().size()).isEqualTo(1);
	}

	@Test
	void isBust() {
		Gamer gamer = new Dealer();
		gamer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		gamer.processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		gamer.processCard(new Card(CardLetter.TWO, CardSuit.SPADE));
		assertThat(gamer.isBust()).isTrue();
	}

	@Test
	void check_optimal_ace_sum() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		gamer.processCard(new Card(CardLetter.ACE, CardSuit.SPADE));
		gamer.processCard(new Card(CardLetter.NINE, CardSuit.SPADE));
		assertThat(gamer.getScore()).isEqualTo(21);
	}

	@Test
	void check_blackjack() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		gamer.processCard(new Card(CardLetter.TEN, CardSuit.SPADE));
		assertThat(gamer.isBlackJack()).isTrue();
	}

	@Test
	void check_not_blackjack() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.processCard(new Card(CardLetter.TEN, CardSuit.SPADE));
		gamer.processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		gamer.processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		assertThat(gamer.isBlackJack()).isFalse();
	}
}

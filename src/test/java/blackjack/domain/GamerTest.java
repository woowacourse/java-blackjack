package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class GamerTest {
	@Test
	void distribute_card() {
		Gamer gamer = new Player(new Name("pobi"));
		Deck deck = new Deck();
		gamer.addCards(deck.distributeCards(1));
		assertThat(gamer.getCards().size()).isEqualTo(1);
	}

	@Test
	void isBust() {
		Gamer gamer = new Dealer();
		gamer.addCards(
			List.of(new Card(CardDenomination.TEN, CardSuit.CLOVER), new Card(CardDenomination.TEN, CardSuit.HEART),
				new Card(CardDenomination.TWO, CardSuit.SPADE)));
		assertThat(gamer.isBust()).isTrue();
	}

	@Test
	void check_optimal_ace_sum() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.addCards(
			List.of(new Card(CardDenomination.ACE, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.SPADE),
				new Card(CardDenomination.NINE, CardSuit.SPADE)));
		assertThat(gamer.getScore()).isEqualTo(21);
	}

	@Test
	void check_blackjack() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.addCards(
			List.of(new Card(CardDenomination.ACE, CardSuit.HEART), new Card(CardDenomination.TEN, CardSuit.SPADE)));
		assertThat(gamer.isBlackJack()).isTrue();
	}

	@Test
	void check_not_blackjack() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.addCards(
			List.of(new Card(CardDenomination.TEN, CardSuit.SPADE), new Card(CardDenomination.TEN, CardSuit.CLOVER),
				new Card(CardDenomination.ACE, CardSuit.HEART)));
		assertThat(gamer.isBlackJack()).isFalse();
	}
}

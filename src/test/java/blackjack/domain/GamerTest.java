package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GamerTest {
	@Test
	void distribute_card() {
		//given
		Gamer gamer = new Player(new Name("pobi"));
		Deck deck = new Deck();
		//when
		gamer.processCard(deck.distributeCard());
		//then
		assertThat(gamer.getCards().size()).isEqualTo(1);
	}

	@Test
	void isBurst() {
		//given
		Gamer gamer = new Dealer();
		Deck deck = new Deck();
		//when
		gamer.processCard(new Card(Number.TEN, Type.CLOVER));
		gamer.processCard(new Card(Number.TEN, Type.HEART));
		gamer.processCard(new Card(Number.TWO, Type.SPADE));
		//then
		assertThat(gamer.isBurst()).isTrue();
	}

	@Test
	void check_optimal_ace_sum() {
		// given
		Gamer gamer = new Player(new Name("pobi"));
		// when
		gamer.processCard(new Card(Number.ACE, Type.HEART));
		gamer.processCard(new Card(Number.ACE, Type.SPADE));
		gamer.processCard(new Card(Number.NINE, Type.SPADE));
		System.out.println(gamer.getScore());
		// then
		assertThat(gamer.getScore()).isEqualTo(21);
	}
}

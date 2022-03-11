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
	void isBurst() {
		Gamer gamer = new Dealer();
		gamer.processCard(new Card(Number.TEN, Type.CLOVER));
		gamer.processCard(new Card(Number.TEN, Type.HEART));
		gamer.processCard(new Card(Number.TWO, Type.SPADE));
		assertThat(gamer.isBurst()).isTrue();
	}

	@Test
	void check_optimal_ace_sum() {
		Gamer gamer = new Player(new Name("pobi"));
		gamer.processCard(new Card(Number.ACE, Type.HEART));
		gamer.processCard(new Card(Number.ACE, Type.SPADE));
		gamer.processCard(new Card(Number.NINE, Type.SPADE));
		System.out.println(gamer.getScore());
		assertThat(gamer.getScore()).isEqualTo(21);
	}
}

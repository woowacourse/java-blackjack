package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GamerTest {
	@Test
	void distribute_card() {
		//given
		Gamer gamer = new Player("pobi");
		Deck deck = new Deck();
		//when
		gamer.addCard(deck.distributeCard());
		//then
		assertThat(gamer.getCards().size()).isEqualTo(1);
	}

	@Test
	void isBurst() {
		//given
		Gamer gamer = new Dealer();
		Deck deck = new Deck();
		//when
		gamer.addCard(new Card(Number.TEN, Type.CLOVER));
		gamer.addCard(new Card(Number.TEN, Type.HEART));
		gamer.addCard(new Card(Number.TWO, Type.SPADE));
		//then
		assertThat(gamer.isBurst()).isTrue();
	}
}

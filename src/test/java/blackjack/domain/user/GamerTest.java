package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Gamer;
import blackjack.domain.user.Player;

class GamerTest {
	@Test
	void distribute_card() {
		//given
		Gamer gamer = new Player("pobi");
		Deck deck = new Deck();
		//when
		gamer.addCard(deck.distributeCard());
		//then
		System.out.println(gamer);
		assertThat(gamer.getCards().size()).isEqualTo(1);
	}

	@Test
	void isBurst() {
		//given
		Gamer gamer = new Dealer();
		//when
		gamer.addCard(new Card(Number.TEN, Type.CLOVER));
		gamer.addCard(new Card(Number.TEN, Type.HEART));
		gamer.addCard(new Card(Number.TWO, Type.SPADE));
		//then
		System.out.println(gamer.isBurst());
		assertThat(gamer.isBurst()).isTrue();
	}

	@Test
	void check_optimal_ace_sum() {
		// given
		Gamer gamer = new Player("pobi");
		gamer.addCard(new Card(Number.ACE, Type.HEART));
		gamer.addCard(new Card(Number.ACE, Type.SPADE));
		gamer.addCard(new Card(Number.NINE, Type.SPADE));
		// when
		gamer.calculateOptimalScoreWithAce();
		// then
		assertThat(gamer).extracting("score").isEqualTo(21);
	}
}

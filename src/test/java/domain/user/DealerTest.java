package domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class DealerTest {

	@Test
	void openCard_When_Dealer_Has_One_Card_Return_The_Card() {
		Dealer dealer = new Dealer();
		dealer.receive(new Card(Symbol.ACE, Type.CLOVER));
		assertEquals(new Card(Symbol.ACE, Type.CLOVER), dealer.openOneCard());
	}

	@Test
	void canDrawMore_When_Dealer_Has_16_Return_True() {
		Dealer dealer = new Dealer();
		dealer.receive(new Card(Symbol.ACE, Type.CLOVER));
		dealer.receive(new Card(Symbol.FIVE, Type.CLOVER));
		assertThat(dealer.canDrawMore()).isTrue();
	}

	@Test
	void canDrawMore_When_Dealer_Has_17_Return_False() {
		Dealer dealer = new Dealer();
		dealer.receive(new Card(Symbol.ACE, Type.CLOVER));
		dealer.receive(new Card(Symbol.SIX, Type.CLOVER));
		assertThat(dealer.canDrawMore()).isFalse();
	}
}
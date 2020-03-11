package blackjack.user;

import blackjack.card.Card;
import blackjack.card.Symbol;
import blackjack.card.Type;
import blackjack.user.Dealer;
import blackjack.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DealerTest {
	@Test
	void create() {
		User dealer = Dealer.create();

		Assertions.assertThat(dealer).isNotNull();
	}

	@Test
	void append() {
		User dealer = Dealer.create();
		dealer.append(new Card(Symbol.ACE, Type.CLUB));
		dealer.append(new Card(Symbol.EIGHT, Type.HEART));

		Assertions.assertThat(dealer.getCards())
				.isEqualTo(Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
						new Card(Symbol.EIGHT, Type.HEART)));
	}
}
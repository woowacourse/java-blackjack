package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class StayTest {

	@Test
	void stay_state() {
		//given
		Cards cards = new Cards();
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		State state = StateFactory.createState(cards);
		//when
		final State newState = state.stay();
		//given
		assertThatThrownBy(() -> newState.draw(Card.of(Denomination.TWO, Suit.DIAMOND)))
			.isInstanceOf(IllegalStateException.class);
	}
}

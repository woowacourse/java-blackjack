package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class BustTest {

	@Test
	void bust_state() {
		//given
		Cards cards = new Cards();
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		cards.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		State state = StateFactory.createState(cards);
		//when
		state = state.draw(Card.of(Denomination.FOUR, Suit.DIAMOND));
		//given
		assertThat(state).isInstanceOf(Bust.class);
	}
}

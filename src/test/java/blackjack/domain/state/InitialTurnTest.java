package blackjack.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class InitialTurnTest {

	@Test
	void blackjack_state() {
		//given
		Cards cards = new Cards();
		cards.addCard(Card.of(Denomination.ACE, Suit.DIAMOND));
		cards.addCard(Card.of(Denomination.JACK, Suit.DIAMOND));
		//when
		State state = InitialTurn.createState(cards);
		//then
		assertThat(state).isInstanceOf(BlackJack.class);
	}

	@Test
	void hit_state() {
		//given
		Cards cards = new Cards();
		cards.addCard(Card.of(Denomination.JACK, Suit.DIAMOND));
		cards.addCard(Card.of(Denomination.QUEEN, Suit.DIAMOND));
		//when
		State state = InitialTurn.createState(cards);
		//then
		assertThat(state).isInstanceOf(Hit.class);
	}
}

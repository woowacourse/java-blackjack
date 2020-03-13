package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
	private Players players;

	@BeforeEach
	void setUp() {
		players = Players.of("포비, 그니, 씨유");
	}

	@Test
	void of() {
		assertThat(players).isNotNull();
	}

	@Test
	void giveCards() {
		// given
		Card card = Card.of(Symbol.ACE, Type.CLUB);

		// when
		players.giveCards(0, card);
		players.giveCards(2, card, card);

		// then
		assertThat(players.getPlayers().get(0).getHand())
				.isEqualTo(Collections.singletonList(card));
		assertThat(players.getPlayers().get(1).getHand())
				.isNotEqualTo(Collections.singletonList(card));
		assertThat(players.getPlayers().get(2).getHand())
				.isEqualTo(Arrays.asList(card, card));
	}

	@Test
	void memberSize() {
		assertThat(players.memberSize()).isEqualTo(3);
	}
}
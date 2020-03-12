package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
	void createResult() {
		// given
		players.giveCards(0, Card.of(Symbol.ACE, Type.CLUB));
		players.giveCards(1, Card.of(Symbol.TEN, Type.CLUB));
		players.giveCards(2, Card.of(Symbol.TWO, Type.CLUB));

		Dealer dealer = DefaultDealer.create();
		dealer.giveCards(Card.of(Symbol.THREE, Type.HEART));

		// then
		Map<Player, Boolean> map = new HashMap<>();
		map.put(players.getPlayers().get(0), true);
		map.put(players.getPlayers().get(1), true);
		map.put(players.getPlayers().get(2), false);

		assertThat(players.createResult(dealer))
				.isEqualTo(map);
	}

	@Test
	void giveCards() {
		// given
		Card card = Card.of(Symbol.ACE, Type.CLUB);

		// when
		players.giveCards(0, card);

		// then
		assertThat(players.getPlayers().get(0).getCards())
				.isEqualTo(Collections.singletonList(card));
	}

	@Test
	void memberSize() {
		assertThat(players.memberSize()).isEqualTo(3);
	}
}
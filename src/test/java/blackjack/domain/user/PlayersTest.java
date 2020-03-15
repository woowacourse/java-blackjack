package blackjack.domain.user;

import blackjack.domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
	void giveCardsEachPlayer() {
		// given
		Card aceClub = Card.of(Symbol.ACE, Type.CLUB);
		Card jackHeart = Card.of(Symbol.JACK, Type.HEART);
		Card kingDiamond = Card.of(Symbol.KING, Type.DIAMOND);
		Drawable deck = Deck.of(Arrays.asList(aceClub, jackHeart, kingDiamond));

		// when
		players.giveCardEachPlayer(deck);

		// then
		assertThat(players.getPlayers().get(0).getHand().get(0))
				.isEqualTo(kingDiamond);
		assertThat(players.getPlayers().get(1).getHand().get(0))
				.isEqualTo(jackHeart);
		assertThat(players.getPlayers().get(2).getHand().get(0))
				.isEqualTo(aceClub);
	}

	@Test
	void memberSize() {
		assertThat(players.memberSize()).isEqualTo(3);
	}
}
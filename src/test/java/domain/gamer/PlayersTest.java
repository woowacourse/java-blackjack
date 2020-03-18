package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;

/**
 *
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PlayersTest {
	@ParameterizedTest
	@NullAndEmptySource
	void Should_ThrownException_When_NameIsNull_OrEmpty(String[] playersName) {
		assertThatThrownBy(() -> new Players(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

	@Test
	void Should_ThrownException_When_NameIsDuplicated() {
		String[] playersName = {"a", "a", "b"};
		assertThatThrownBy(() -> new Players(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@Test
	void Should_ThrownException_When_Overmanned() {
		String[] playersName = {"a", "b", "c", "d", "e", "gg"};
		assertThatThrownBy(() -> new Players(playersName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}

	@Test
	void testIsAllPlayerBust() {
		Players players = new Players(new String[] {"a", "b"});

		for (Player player : players.getPlayers()) {
			player.hit(new Card(Type.HEART, Symbol.KING));
			player.hit(new Card(Type.SPADE, Symbol.KING));
			player.hit(new Card(Type.CLUBS, Symbol.KING));
		}

		assertThat(players.isAllPlayersBust()).isTrue();
	}

	@Test
	void Should_hitAllPlayers_when_CardIsGiven() {
		Players players = new Players(new String[] {"a", "b"});

		players.hitAll(new Deck());

		players.getPlayers().forEach(player -> assertThat(player.getHands()
			.getCards()
			.size()).isEqualTo(1));
	}
}

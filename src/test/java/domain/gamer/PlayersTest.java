package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.money.Money;

/**
 *
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PlayersTest {
	@Test
	void Should_ThrownException_When_NameIsDuplicated() {
		List<Name> playersName = Arrays.asList(new Name("a"), new Name("a"), new Name("b"));
		List<Money> moneys = Arrays.asList(Money.of("10000"), Money.of("10000"), Money.of("10000"));
		assertThatThrownBy(() -> new Players(playersName, moneys))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@Test
	void Should_ThrownException_When_Overmanned() {
		List<Name> playersName = Arrays.asList(new Name("a"), new Name("b"), new Name("c"), new Name("d"),
			new Name("e"), new Name("f"));
		List<Money> moneys = Arrays.asList(Money.of("10000"), Money.of("10000"), Money.of("10000"));
		assertThatThrownBy(() -> new Players(playersName, moneys))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}

	@Test
	void testIsAllPlayerBust() {
		Players players = new Players(Arrays.asList(new Name("a"), new Name("c"), new Name("b")),
			Arrays.asList(Money.of("10000"), Money.of("10000"), Money.of("10000")));

		for (Player player : players.getPlayers()) {
			player.hit(new Card(Type.HEART, Symbol.KING));
			player.hit(new Card(Type.SPADE, Symbol.KING));
			player.hit(new Card(Type.CLUBS, Symbol.KING));
		}

		assertThat(players.isAllPlayersBust()).isTrue();
	}

	@Test
	void Should_hitAllPlayers_when_CardIsGiven() {
		Players players = new Players(Arrays.asList(new Name("a"), new Name("c"), new Name("b")),
			Arrays.asList(Money.of("10000"), Money.of("10000"), Money.of("10000")));

		players.hitAll(new Deck());

		players.getPlayers().forEach(player -> assertThat(player.getHands()
			.getCards()
			.size()).isEqualTo(1));
	}
}

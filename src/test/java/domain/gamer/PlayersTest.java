package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.money.Money;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
@SuppressWarnings("NonAsciiCharacters")
public class PlayersTest {
	@Test
	void 이름_중복() {
		List<Name> names = Arrays.asList(new Name("a"), new Name("a"), new Name("b"));
		List<Money> moneys = Arrays.asList(Money.of("10000"), Money.of("10000"), Money.of("10000"));

		assertThatThrownBy(() -> new Players(names, moneys))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복");
	}

	@Test
	void 인원수_초과() {
		List<Name> names = Arrays.asList(new Name("a"), new Name("b"), new Name("c"), new Name("d"), new Name("e"),
			new Name("f"));
		List<Money> moneys = Arrays.asList(Money.of("10000"), Money.of("10000"), Money.of("10000"), Money.of("10000"),
			Money.of("10000"), Money.of("10000"));

		assertThatThrownBy(() -> new Players(names, moneys))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("초과");
	}
}

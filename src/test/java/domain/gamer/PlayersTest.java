package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import domain.money.Money;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
@SuppressWarnings("NonAsciiCharacters")
public class PlayersTest {
	@ParameterizedTest
	@NullAndEmptySource
	void 이름_null_이나_빈_값(String playersName) {
		List<Name> names = Arrays.asList(new Name(playersName));
		List<Money> moneys = Arrays.asList(Money.of("10000"));

		assertThatThrownBy(() -> new Players(names, moneys))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("null이나 빈 값");
	}

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

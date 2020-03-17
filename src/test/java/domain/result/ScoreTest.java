package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Money;
import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
	@Test
	@DisplayName("카드 계산결과가 올바른지")
	void calculateScore() {
		Player player = new Player(new Name("pobi"), Money.ZERO);
		player.hit(new Card(Symbol.EIGHT, Type.CLUB));
		player.hit(new Card(Symbol.SEVEN, Type.CLUB));
		player.hit(new Card(Symbol.SIX, Type.CLUB));

		assertThat(player.getScore()).isEqualTo(Score.from(21));
	}

	@ParameterizedTest
	@MethodSource("generateWithAce")
	@DisplayName("카드 계산결과가 올바른지")
	void calculateWithAce(int expected, List<Card> cards) {
		Player player = new Player(new Name("pobi"), Money.ZERO);
		for (Card card : cards) {
			player.hit(card);
		}
		assertThat(player.getScore()).isEqualTo(Score.from(expected));
	}

	static Stream<Arguments> generateWithAce() {
		Card ace = new Card(Symbol.ACE, Type.DIAMOND);
		return Stream.of(
				Arguments.of(12, Arrays.asList(ace, ace)),
				Arguments.of(21, Arrays.asList(ace, new Card(Symbol.TEN, Type.DIAMOND))),
				Arguments.of(14, Arrays.asList(ace, new Card(Symbol.THREE, Type.DIAMOND))),
				Arguments.of(20, Arrays.asList(ace, new Card(Symbol.NINE, Type.DIAMOND), new Card(Symbol.TEN, Type.CLUB)))
		);
	}

	@Test
	@DisplayName("점수를 올바르게 비교하는지 테스트")
	void compareTest() {
		Score twentyOne = Score.from(21);
		Score ten = Score.from(10);

		assertThat(twentyOne.isBiggerThan(ten)).isTrue();
		assertThat(ten.isLowerThan(twentyOne)).isTrue();
		assertThat(twentyOne.isEqualTo(twentyOne)).isTrue();
	}

	@Test
	@DisplayName("Bust여부를 정상적으로 확인하는지 테스트")
	void bustTest() {
		assertThat(Score.from(22).isBust()).isTrue();
		assertThat(Score.from(21).isBust()).isFalse();
	}
}
package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class CardsTest {
	private static Stream<Arguments> generateInput() {
		return Stream.of(
			Arguments.of(Arrays.asList(new Card(Symbol.TWO, Type.HEART), new Card(Symbol.TWO, Type.DIAMOND)), 4),
			Arguments.of(Arrays.asList(new Card(Symbol.ACE, Type.HEART), new Card(Symbol.KING, Type.DIAMOND)), 21),
			Arguments.of(Arrays.asList(new Card(Symbol.ACE, Type.HEART), new Card(Symbol.NINE, Type.DIAMOND),
				new Card(Symbol.TWO, Type.DIAMOND)), 22));
	}

	@ParameterizedTest
	@MethodSource("generateInput")
	void 총_점수_계산(Cards cards, int expected) {
		assertThat(cards.calculateTotalScore()).isEqualTo(expected);
	}

	@Test
	void ACE_유무() {
		Cards cards1 = new Cards(Arrays.asList(new Card(Symbol.ACE, Type.HEART), new Card(Symbol.KING, Type.DIAMOND)));
		assertThat(cards1.hasAce()).isTrue();

		Cards cards2 = new Cards(Arrays.asList(new Card(Symbol.TWO, Type.HEART), new Card(Symbol.KING, Type.DIAMOND)));
		assertThat(cards2.hasAce()).isFalse();
	}
}

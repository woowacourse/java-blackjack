package domain.result;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class ScoreTest {

	@Test
	@DisplayName("카드 계산결과가 올바른지")
	void calculateScore() {
		List<Card> cards = Arrays.asList(
			new Card(Symbol.EIGHT, Type.CLUB),
			new Card(Symbol.SEVEN, Type.CLUB),
			new Card(Symbol.SIX, Type.CLUB)
		);
		assertThat(Score.calculate(cards)).isEqualTo(21);
	}

	@ParameterizedTest
	@MethodSource("generateWithAce")
	@DisplayName("카드 계산결과가 올바른지")
	void calculateWithAce(int expected, List<Card> cards) {
		assertThat(Score.calculate(cards)).isEqualTo(expected);
	}

	static Stream<Arguments> generateWithAce () {
		Card ace = new Card(Symbol.ACE, Type.DIAMOND);
		return Stream.of(
			Arguments.of(12, Arrays.asList(ace, ace)),
			Arguments.of(21, Arrays.asList(ace, new Card(Symbol.TEN, Type.DIAMOND))),
			Arguments.of(14, Arrays.asList(ace, new Card(Symbol.THREE, Type.DIAMOND))),
			Arguments.of(20, Arrays.asList(ace, new Card(Symbol.NINE, Type.DIAMOND), new Card(Symbol.TEN, Type.CLUB)))
			);
	}
}
package domain.result;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Symbol;
import domain.card.Type;
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
		Hand hand = Hand.createEmpty();
		hand.add(new Card(Symbol.EIGHT, Type.CLUB));
		hand.add(new Card(Symbol.SEVEN, Type.CLUB));
		hand.add(new Card(Symbol.SIX, Type.CLUB));

		assertThat(Score.from(hand)).isEqualTo(Score.from(21));
	}

	@ParameterizedTest
	@MethodSource("generateWithAce")
	@DisplayName("카드 계산결과가 올바른지")
	void calculateWithAce(int expected, List<Card> cards) {
		Hand hand = Hand.createEmpty();
		for (Card card : cards) {
			hand.add(card);
		}
		assertThat(Score.from(hand)).isEqualTo(Score.from(expected));
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
}
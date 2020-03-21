package domain.result;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
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
	private Score twentyOne, ten;

	@BeforeEach
	void setUp() {
		twentyOne = Score.from(21);
		ten = Score.from(10);
	}

	@ParameterizedTest
	@MethodSource("calculateInput")
	@DisplayName("스코어 계산결과가 올바른지")
	void calculateScore(List<Card> cards, int expected) {
		Hand hand = Hand.createEmpty();
		for (Card card : cards) {
			hand.add(card);
		}

		assertThat(Score.from(hand)).isEqualTo(Score.from(expected));
	}

	static Stream<Arguments> calculateInput() {
		Card ACE = new Card(Symbol.ACE, Type.CLUB);
		Card THREE = new Card(Symbol.THREE, Type.CLUB);
		Card SIX = new Card(Symbol.SIX, Type.CLUB);
		Card SEVEN = new Card(Symbol.SEVEN, Type.CLUB);
		Card EIGHT = new Card(Symbol.EIGHT, Type.CLUB);
		Card NINE = new Card(Symbol.NINE, Type.CLUB);
		Card TEN = new Card(Symbol.TEN, Type.CLUB);

		return Stream.of(Arguments.of(Arrays.asList(EIGHT, SEVEN, SIX), 21),
				Arguments.of(Arrays.asList(EIGHT, NINE), 17),
				Arguments.of(Arrays.asList(ACE, ACE, ACE), 13),
				Arguments.of(Arrays.asList(ACE, THREE), 14),
				Arguments.of(Arrays.asList(ACE, NINE, TEN), 20),
				Arguments.of(Arrays.asList(TEN, TEN, TEN), 30),
				Arguments.of(Arrays.asList(ACE, TEN, TEN, ACE), 22),
				Arguments.of(Arrays.asList(ACE, TEN, TEN), 21));
	}

	@Test
	@DisplayName("점수가 큰지 비교하는 테스")
	void compareBiggerTest() {
		assertThat(twentyOne.isBiggerThan(ten)).isTrue();
	}

	@Test
	@DisplayName("점수가 작은지 비교하는 테스")
	void compareLowerTest() {
		assertThat(ten.isLowerThan(twentyOne)).isTrue();
	}

	@Test
	@DisplayName("점수가 같은지 비교하는 테스")
	void compareEqualTest() {
		assertThat(twentyOne.isEqualTo(twentyOne)).isTrue();
	}
}
package domain.card;

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

class HandTest {
	private static Card ACE = new Card(Symbol.ACE, Type.DIAMOND);
	private static Card SIX = new Card(Symbol.SIX, Type.DIAMOND);
	private static Card SEVEN = new Card(Symbol.SEVEN, Type.DIAMOND);
	private static Card EIGHT = new Card(Symbol.EIGHT, Type.DIAMOND);

	private Hand hand;

	@BeforeEach
	void setUp() {
		hand = Hand.createEmpty();
	}

	@Test
	@DisplayName("빈 Hands 생성 테스트")
	void createTest() {
		assertThat(hand).isInstanceOf(Hand.class);
	}

	@ParameterizedTest
	@MethodSource("createCardInput")
	@DisplayName("카드의 합을 올바르게 계산하는지 테스트")
	void sumOfCardTest(List<Card> cards, int expected) {
		for (Card card : cards) {
			hand.add(card);
		}

		assertThat(hand.sumOfCards()).isEqualTo(expected);
	}

	static Stream<Arguments> createCardInput() {
		return Stream.of(Arguments.of(Arrays.asList(EIGHT, SEVEN, SIX), 21),
				Arguments.of(Arrays.asList(ACE, SIX), 7),
				Arguments.of(Arrays.asList(ACE, EIGHT, SIX, SEVEN), 22));
	}

	@ParameterizedTest
	@MethodSource("createAceInput")
	@DisplayName("에이스를 포함하는지 여부를 테스트")
	void hasAceTest(Card card, boolean expected) {
		hand.add(card);

		assertThat(hand.hasAce()).isEqualTo(expected);
	}

	static Stream<Arguments> createAceInput() {
		return Stream.of(Arguments.of(EIGHT, false),
				Arguments.of(ACE, true));
	}

	@ParameterizedTest
	@MethodSource("creatTwoCardsInput")
	@DisplayName("두개의 카드를 갖는지 여부를 테스트")
	void hasTwoCardsTest(List<Card> cards, boolean expected) {
		for (Card card : cards) {
			hand.add(card);
		}

		assertThat(hand.hasTwoCards()).isEqualTo(expected);
	}

	static Stream<Arguments> creatTwoCardsInput() {
		return Stream.of(Arguments.of(Arrays.asList(EIGHT), false),
				Arguments.of(Arrays.asList(EIGHT, ACE), true),
				Arguments.of(Arrays.asList(EIGHT, ACE, SEVEN), false));
	}
}
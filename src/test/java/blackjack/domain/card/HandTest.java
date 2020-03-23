package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.testAssistant.TestAssistant.*;
import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
	@DisplayName("Hand()가 인스턴스를 반환하는지 테스트")
	@Test
	void Hand_IsNotNull() {
		Hand hand = createHand();

		assertThat(hand).isNotNull();
	}

	@DisplayName("add()가 hand에 카드를 추가하는지 테스트")
	@ParameterizedTest
	@MethodSource("add_Card_AddCardToHand")
	void add_Card_AddCardToHand(Hand hand, Card card, Hand expect) {
		hand.add(card);

		assertThat(hand).isEqualTo(expect);
	}

	static Stream<Arguments> add_Card_AddCardToHand() {
		return Stream.of(
				Arguments.of(createHand(),
						createCard("ACE,SPADE"),
						createHand("ACE,SPADE")),
				Arguments.of(createHand("ACE,SPADE"),
						createCard("TWO,CLUB"),
						createHand("ACE,SPADE", "TWO,CLUB"))
		);
	}

	@DisplayName("addAll()가 카드를 추가하는지 테스트")
	@ParameterizedTest
	@MethodSource("addAll_Cards_AddAllToHand")
	void addAll_Cards_AddAllToHand(Hand hand, List<Card> cards, Hand expect) {
		hand.addAll(cards);

		assertThat(hand).isEqualTo(expect);
	}

	static Stream<Arguments> addAll_Cards_AddAllToHand() {
		return Stream.of(
				Arguments.of(createHand(), createCards(), createHand()),
				Arguments.of(createHand(),
						createCards("ACE,CLUB"),
						createHand("ACE,CLUB")),
				Arguments.of(createHand(),
						createCards("ACE,CLUB", "TWO,SPADE"),
						createHand("ACE,CLUB", "TWO,SPADE")),
				Arguments.of(createHand("ACE,CLUB"),
						createCards("TWO,CLUB", "THREE,SPADE"),
						createHand("ACE,CLUB", "TWO,CLUB", "THREE,SPADE"))
		);
	}

	@DisplayName("computeScore()가 점수를 계산하여 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("computeScore")
	void computeScore(Hand hand, Score score) {
		assertThat(hand.computeScore()).isEqualTo(score);
	}

	static Stream<Arguments> computeScore() {
		return Stream.of(Arguments.of(createHand(), createScore(0)),
				Arguments.of(createHand("ACE,SPADE"), createScore(11)),
				Arguments.of(createHand("ACE,SPADE", "TEN,CLUB"), createScore(21)),
				Arguments.of(createHand("JACK,SPADE", "KING,CLUB", "ACE,CLUB"), createScore(21)),
				Arguments.of(createHand("JACK,SPADE", "TWO,SPADE", "ACE,HEART"), createScore(13))
		);
	}
}
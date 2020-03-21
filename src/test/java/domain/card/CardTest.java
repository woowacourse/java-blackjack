package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
	@Test
	@DisplayName("equals 오버라이딩 테스트")
	void equalsTest() {
		assertThat(new Card(Symbol.EIGHT, Type.CLUB))
				.isEqualTo(new Card(Symbol.EIGHT, Type.CLUB));
	}

	@ParameterizedTest
	@MethodSource("createAceInput")
	@DisplayName("카드가 Ace인지 테스트")
	void isAceTest(Card card, boolean expected) {
		assertThat(card.isAce()).isEqualTo(expected);
	}

	static Stream<Arguments> createAceInput() {
		return Stream.of(Arguments.of(new Card(Symbol.ACE, Type.CLUB), true),
				Arguments.of(new Card(Symbol.SEVEN, Type.CLUB), false));
	}
}
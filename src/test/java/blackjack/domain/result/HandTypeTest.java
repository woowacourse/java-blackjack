package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjack.domain.exceptions.InvalidHandTypeException;

class HandTypeTest {
	@Test
	void of_ScoreAndIsInitialDealtSizeOfBlackjack_ReturnInstance() {
		assertThat(HandType.of(Score.valueOf(Score.BLACKJACK_JUDGEMENT_SCORE), true))
			.isEqualTo(HandType.BLACKJACK);
	}

	@Test
	void of_ScoreAndIsInitialDealtSizeOfBust_ReturnInstance() {
		assertThat(HandType.of(Score.valueOf(Score.BUST_SCORE), true))
			.isEqualTo(HandType.BUST);
	}

	@Test
	void of_ScoreAndIsInitialDealtSizeOfNormal_ReturnInstance() {
		assertThat(HandType.of(Score.valueOf(12), true))
			.isEqualTo(HandType.NORMAL);
	}

	@Test
	void of_ScoreAndIsInitialDealtSizeOfInvalid_InvalidHandTypeExceptionThrown() {
		assertThatThrownBy(() -> HandType.of(null, true))
			.isInstanceOf(InvalidHandTypeException.class)
			.hasMessage(InvalidHandTypeException.NULL);
	}

	@Test
	void isBlackjack_HandType_ReturnIsBlackjack() {
		HandType handType1 = HandType.BLACKJACK;
		assertThat(handType1.isBlackjack()).isTrue();

		HandType handType2 = HandType.BUST;
		assertThat(handType2.isBlackjack()).isFalse();
	}

	@Test
	void isBust_HandType_ReturnIsBust() {
		HandType handType2 = HandType.BUST;
		assertThat(handType2.isBust()).isTrue();

		HandType handType1 = HandType.NORMAL;
		assertThat(handType1.isBust()).isFalse();
	}
}

package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjack.domain.exceptions.InvalidScoreTypeException;

class ScoreTypeTest {
	@Test
	void of_ScoreAndIsInitialDealtSizeOfBlackjack_ReturnInstance() {
		assertThat(ScoreType.of(Score.valueOf(Score.BLACKJACK_JUDGEMENT_SCORE), true))
			.isEqualTo(ScoreType.BLACKJACK);
	}

	@Test
	void of_ScoreAndIsInitialDealtSizeOfBust_ReturnInstance() {
		assertThat(ScoreType.of(Score.valueOf(Score.BUST_SCORE), true))
			.isEqualTo(ScoreType.BUST);
	}

	@Test
	void of_ScoreAndIsInitialDealtSizeOfNormal_ReturnInstance() {
		assertThat(ScoreType.of(Score.valueOf(12), true))
			.isEqualTo(ScoreType.NORMAL);
	}

	@Test
	void of_ScoreAndIsInitialDealtSizeOfInvalid_InvalidScoreTypeExceptionThrown() {
		assertThatThrownBy(() -> ScoreType.of(null, true))
			.isInstanceOf(InvalidScoreTypeException.class)
			.hasMessage(InvalidScoreTypeException.NULL);
	}

	@Test
	void isBlackjack_ScoreType_ReturnIsBlackjack() {
		ScoreType scoreType1 = ScoreType.BLACKJACK;
		assertThat(scoreType1.isBlackjack()).isTrue();

		ScoreType scoreType2 = ScoreType.BUST;
		assertThat(scoreType2.isBlackjack()).isFalse();
	}

	@Test
	void isBust_ScoreType_ReturnIsBust() {
		ScoreType scoreType2 = ScoreType.BUST;
		assertThat(scoreType2.isBust()).isTrue();

		ScoreType scoreType1 = ScoreType.NORMAL;
		assertThat(scoreType1.isBust()).isFalse();
	}
}

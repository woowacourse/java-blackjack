package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {
	@Test
	void calculateScore_SumCards() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TWO, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));

		Score expected = Score.valueOf(10);
		assertThat(ScoreCalculator.calculateScore(cards)).isEqualTo(expected);
	}

	@Test
	void calculateScore_WithAceCard_SumCards() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.TWO, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));

		Score expected = Score.valueOf(10);
		assertThat(ScoreCalculator.calculateScore(cards)).isEqualTo(expected);
	}

	@Test
	void aceHandled_InputScore_ReturnAceHandledScore() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.ACE, Type.CLUB),
			Card.of(Symbol.EIGHT, Type.DIAMOND));

		Score expected = Score.valueOf(19);
		assertThat(ScoreCalculator.calculateScore(cards)).isEqualTo(expected);
	}

	@Test
	void calculateBustHandledScore_BustScore_ReturnZeroScore() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.JACK, Type.DIAMOND),
			Card.of(Symbol.QUEEN, Type.HEART),
			Card.of(Symbol.TWO, Type.SPADE));

		Score expected = Score.ZERO;
		assertThat(ScoreCalculator.calculateBustHandledScore(cards)).isEqualTo(expected);
	}

	@Test
	void calculateBustHandledScore_NotBustScore_ReturnScore() {
		List<Card> cards = Arrays.asList(
			Card.of(Symbol.JACK, Type.DIAMOND),
			Card.of(Symbol.QUEEN, Type.HEART));

		Score expected = Score.valueOf(20);
		assertThat(ScoreCalculator.calculateBustHandledScore(cards)).isEqualTo(expected);
	}
}

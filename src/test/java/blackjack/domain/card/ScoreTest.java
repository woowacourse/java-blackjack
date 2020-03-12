package blackjack.domain.card;

import blackjack.domain.card.exception.ScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {
	private Score score;

	@BeforeEach
	void setUp() {
		score = Score.of(5);
	}

	@Test
	void of() {
		assertThat(Score.of(0)).isNotNull();
		assertThat(score).isNotNull();
	}

	@Test
	void of_IfMinus_ShouldThrowException() {
		assertThatThrownBy(() ->
				Score.of(-1))
				.isInstanceOf(ScoreException.class);
	}

	@Test
	void add() {
		// then
		assertThat(score.add(Score.of(10)))
				.isEqualTo(Score.of(15));
	}

	@Test
	void isUnder() {
		assertThat(score.isUnder(6))
				.isTrue();
	}

	@Test
	void isOver() {
		assertThat(score.isOver(4))
				.isTrue();
	}

	@Test
	void getScore() {
		assertThat(score.getScore())
				.isEqualTo(5);
	}
}
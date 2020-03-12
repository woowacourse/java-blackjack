package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ScoreTest {
	@Test
	void create() {
		assertThat(new Score(0)).isEqualTo(Score.ZERO);
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void create_0미만() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Score(-1));
	}

	@Test
	void add() {
		assertThat(Score.ZERO.add(10)).isEqualTo(new Score(10));
		assertThat(new Score(2).add(3)).isEqualTo(new Score(5));
		assertThat(new Score(2).add(3).add(5)).isEqualTo(new Score(10));
	}

	@Test
	void addAceBonusIfNotBust() {
		assertThat(new Score(11).addAceBonusIfNotBust()).isEqualTo(new Score(21));
	}

	@Test
	void isBlackJack() {
		assertThat(new Score(21).isBlackjack(2)).isTrue();
	}

	@Test
	void isNotBlackJack() {
		assertThat(new Score(21).isBlackjack(3)).isFalse();
		assertThat(new Score(20).isBlackjack(2)).isFalse();
		assertThat(new Score(22).isBlackjack(2)).isFalse();
	}

	@Test
	void isBust() {
		assertThat(new Score(22).isBust()).isTrue();
	}

	@Test
	void isNotBust() {
		assertThat(new Score(21).isBust()).isFalse();
	}

	@Test
	void isGreaterThan() {
		Score score = new Score(21);
		assertThat(score.isGreaterThan(20)).isTrue();
		assertThat(score.isGreaterThan(21)).isTrue();
		assertThat(score.isGreaterThan(22)).isFalse();
	}
}

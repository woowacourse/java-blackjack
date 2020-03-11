package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ScoreTest {
	@Test
	void create() {
		assertThat(new Score(0)).isEqualTo(Score.ZERO);
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
	void isNotBust() {
		assertThat(new Score(21).isNotBust()).isTrue();
	}
}

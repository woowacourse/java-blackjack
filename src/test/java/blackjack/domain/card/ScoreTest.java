package blackjack.domain.card;

import blackjack.domain.card.exceptions.ScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {
	private Score zero;
	private Score five;
	private Score six;
	private Score eleven;

	@BeforeEach
	void setUp() {
		zero = Score.of(0);
		five = Score.of(5);
		six = Score.of(6);
		eleven = Score.of(11);
	}

	@Test
	void of() {
		assertThat(zero).isNotNull();
		assertThat(five).isNotNull();
		assertThat(six).isNotNull();
		assertThat(eleven).isNotNull();
	}

	@Test
	void of_IfMinus_ShouldThrowException() {
		assertThatThrownBy(() -> Score.of(-1))
				.isInstanceOf(ScoreException.class);
	}

	@Test
	void add() {
		// then
		assertThat(zero.add(five)).isEqualTo(five);
		assertThat(five.add(six)).isEqualTo(eleven);
	}

	@Test
	void isUnder() {
		assertThat(zero.isUnder(6)).isTrue();
		assertThat(five.isUnder(6)).isTrue();
		assertThat(six.isUnder(6)).isFalse();
		assertThat(eleven.isUnder(6)).isFalse();
	}

	@Test
	void isOver() {
		assertThat(zero.isOver(5)).isFalse();
		assertThat(five.isOver(5)).isFalse();
		assertThat(six.isOver(5)).isTrue();
		assertThat(eleven.isOver(5)).isTrue();
	}

	@Test
	void getScore() {
		assertThat(zero.getScore()).isEqualTo(0);
		assertThat(five.getScore()).isEqualTo(5);
		assertThat(six.getScore()).isEqualTo(6);
		assertThat(eleven.getScore()).isEqualTo(11);
	}
}
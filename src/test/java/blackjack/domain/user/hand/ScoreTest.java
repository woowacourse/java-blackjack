package blackjack.domain.user.hand;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import blackjack.domain.card.Symbol;

class ScoreTest {
	@Test
	void Score_GenerateInstance() {
		assertThat(new Score()).isInstanceOf(Score.class)
			.extracting("score").isEqualTo(0);
	}

	@Test
	void valueOf_InputInteger_ReturnInstance() {
		assertThat(Score.valueOf(10)).isInstanceOf(Score.class)
			.extracting("score").isEqualTo(10);
	}

	@ParameterizedTest
	@EnumSource(value = Symbol.class)
	void valueOf_InputSymbol_ReturnInstance(Symbol symbol) {
		assertThat(Score.valueOf(symbol)).isInstanceOf(Score.class)
			.extracting("score").isEqualTo(symbol.getSymbol());
	}

	@Test
	void add_InputScore_addScore() {
		Score score1 = Score.valueOf(Symbol.TWO);
		Score score2 = Score.valueOf(Symbol.EIGHT);

		assertThat(score1.add(score2)).extracting("score").isEqualTo(10);
	}
}

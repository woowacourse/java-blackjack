package blackjack.domain.user.hand;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;

class ScoreTest {
	@Test
	void valueOf_InputInteger_ReturnInstance() {
		assertThat(Score.valueOf(10)).isInstanceOf(Score.class)
			.extracting("score").isEqualTo(10);
	}

	@ParameterizedTest
	@EnumSource(value = Symbol.class)
	void valueOf_InputCard_ReturnInstance(Symbol symbol) {
		Card card = new Card(symbol, Type.CLUB);

		assertThat(Score.valueOf(card)).isInstanceOf(Score.class)
			.extracting("score").isEqualTo(card.getSymbolValue());
	}

	@Test
	void add_InputCard_addScore() {
		Card card = new Card(Symbol.TWO, Type.CLUB);
		assertThat(Score.ZERO.add(card)).extracting("score").isEqualTo(2);
	}

	@Test
	void add_ACECardAddBy11_addScore() {
		Card card = new Card(Symbol.ACE, Type.CLUB);

		assertThat(Score.ZERO.add(card)).extracting("score").isEqualTo(11);
	}

	@Test
	void add_ACECardAddBy1_addScore() {
		Card card = new Card(Symbol.ACE, Type.CLUB);
		Score score = Score.valueOf(11);

		assertThat(score.add(card)).extracting("score").isEqualTo(12);
	}

	@ParameterizedTest
	@CsvSource(value = {"8,false", "9,true"})
	void isLowerThan_InputIntegerScore_ReturnCompareResult(int value, boolean expected) {
		Score score = Score.valueOf(9);

		assertThat(score.isLowerThan(value)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"8,true", "9,false"})
	void isMoreThan_InputIntegerScore_ReturnCompareResult(int value, boolean expected) {
		Score score = Score.valueOf(9);

		assertThat(score.isMoreThan(value)).isEqualTo(expected);
	}
}

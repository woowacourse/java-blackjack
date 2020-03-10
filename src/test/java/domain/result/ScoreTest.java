package domain.result;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class ScoreTest {

	@Test
	@DisplayName("카드 계산결과가 올바른지")
	void calculateScore() {
		List<Card> cards = Arrays.asList(
			new Card(Symbol.EIGHT, Type.CLUB),
			new Card(Symbol.SEVEN, Type.CLUB),
			new Card(Symbol.SIX, Type.CLUB)
		);
		assertThat(Score.calculate(cards)).isEqualTo(21);
	}
}
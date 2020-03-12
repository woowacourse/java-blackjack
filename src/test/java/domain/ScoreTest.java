package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

	@Test
	void create_Score_By_Number() {
		assertThat(Score.of(1)).isInstanceOf(Score.class);
	}

	@Test
	void create_Score_By_Cards() {
		Cards cards = new Cards(new ArrayList<>());
		Card card = new Card(Symbol.ACE, Type.CLOVER);
		cards.add(card);

		assertThat(Score.of(cards)).isInstanceOf(Score.class);
	}

	@Test
	void isBlackjackScore_When_Score_Is_21() {
		assertThat(Score.of(21).isBlackjackScore()).isTrue();
	}

	@Test
	void isNotBurst_When_Score_Is_21_Return_True() {
		assertThat(Score.of(21).isNotBurst()).isTrue();
	}

	@Test
	void isNotBurst_When_Score_Is_22_Return_False() {
		assertThat(Score.of(22).isNotBurst()).isFalse();
	}
}

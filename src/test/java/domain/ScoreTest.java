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

	@Test
	void isBiggerThan_When_Has_Bigger_Return_True() {
		assertThat(Score.of(10).isBiggerThan(Score.of(9))).isTrue();
	}

	@Test
	void isBiggerThan_When_Has_Smaller_Return_False() {
		assertThat(Score.of(10).isBiggerThan(Score.of(11))).isFalse();
	}

	@Test
	void minus_When_Two_Minus_One_Return_One() {
		assertThat(Score.of(2).minus(Score.of(1))).isEqualTo(1);
	}
}

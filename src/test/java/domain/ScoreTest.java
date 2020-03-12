package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/12
 */
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
	void isDealerDraw_When_Score_Has_Under_17_Score_Then_Return_True() {
		assertThat(Score.of(16).isDealerDraw()).isTrue();
	}

	@Test
	void isDealerDraw_When_Score_Has_Over_16_Score_Then_Return_False() {
		assertThat(Score.of(17).isDealerDraw()).isFalse();
	}
}

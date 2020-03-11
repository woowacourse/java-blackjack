package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class ScoreTypeTest {

	private Cards cards;

	@BeforeEach
	void setUp() {
		cards = new Cards(new ArrayList<>());
	}

	@Test
	void of_When_Burst_Cards_Return_BURST() {
		cards.add(new Card(Symbol.KING, Type.CLOVER));
		cards.add(new Card(Symbol.JACK, Type.CLOVER));
		cards.add(new Card(Symbol.QUEEN, Type.CLOVER));

		assertThat(ScoreType.of(cards)).isEqualTo(ScoreType.BURST);
	}

	@Test
	void of_When_Blackjack_Cards_Return_BLACKJACK() {
		cards.add(new Card(Symbol.KING, Type.CLOVER));
		cards.add(new Card(Symbol.ACE, Type.CLOVER));

		assertThat(ScoreType.of(cards)).isEqualTo(ScoreType.BLACKJACK);
	}

	@Test
	void of_When_Normal_Cards_Return_NORMAL() {
		cards.add(new Card(Symbol.ACE, Type.CLOVER));
		cards.add(new Card(Symbol.ACE, Type.HEART));
		cards.add(new Card(Symbol.ACE, Type.SPADE));
		cards.add(new Card(Symbol.KING, Type.CLOVER));
		assertThat(ScoreType.of(cards)).isEqualTo(ScoreType.NORMAL);
	}
}

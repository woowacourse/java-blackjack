package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardsTest {

	private Cards cards;
	private Card card;

	@BeforeEach
	void setUp() {
		cards = new Cards(new ArrayList<>());
		card = new Card(Symbol.ACE, Type.CLOVER);
		cards.add(card);
	}

	@Test
	void create_Cards() {
		assertThat(new Cards(new ArrayList<>())).isInstanceOf(Cards.class);
	}

	@Test
	void add_Card() {
		Cards expected = new Cards(Collections.singletonList(card));

		assertThat(cards).isEqualTo(expected);
	}

	@Test
	void toList_Getter_For_View() {
		List<Card> expected = Collections.singletonList(card);

		assertEquals(expected, cards.toList());
	}

	@Test
	void getPoint_When_One_Ace_Card_Return_11() {
		assertThat(cards.calculateScore()).isEqualTo(11);
	}

	@Test
	void hasInitialSize_When_Two_Cards_Return_True() {
		cards.add(card);
		assertThat(cards.hasInitialSize()).isTrue();
	}

	@Test
	void hasInitialSize_When_Three_Cards_Return_False() {
		cards.add(card);
		cards.add(card);
		assertThat(cards.hasInitialSize()).isFalse();
	}
}

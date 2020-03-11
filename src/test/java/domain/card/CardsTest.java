package domain.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class CardsTest {

	@Test
	void create_Cards() {
		assertThat(new Cards(new ArrayList<>())).isInstanceOf(Cards.class);
	}

	@Test
	void add_Card() {
		Cards cards = new Cards(new ArrayList<>());
		Card card = new Card(Symbol.ACE, Type.CLOVER);

		cards.add(card);
		Cards expected = new Cards(Collections.singletonList(card));

		assertThat(cards).isEqualTo(expected);
	}
}

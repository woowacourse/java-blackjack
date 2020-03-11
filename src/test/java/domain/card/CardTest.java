package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class CardTest {
	@Test
	void create_Card() {
		assertThat(new Card(Symbol.ACE, Type.DIAMOND)).isInstanceOf(Card.class);
	}

	@Test
	void measure_Cache_Size() {
		assertThat(Card.CardCache.toList().size()).isEqualTo(52);
	}
}

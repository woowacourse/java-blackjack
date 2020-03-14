package domain.card.cardfactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardFactoryTest {
	@DisplayName("중복되지 않는 52장의 카드 생성 테스트")
	@Test
	void create() {
		List<Card> cards = CardFactory.create();

		assertThat(cards.size()).isEqualTo(52);
		assertThat(new HashSet<>(cards).size()).isEqualTo(52);
	}
}

package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.machine.Card;

public class CardTest {
	@DisplayName("인덱스에 맞는 카드 return 테스트")
	@Test
	void of() {
		assertThat(Card.of(2).getName()).isEqualTo("3다이아몬드");
	}
}

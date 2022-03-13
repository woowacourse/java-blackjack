package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

	@Test
	@DisplayName("하나의 덱에서 연속으로 뽑은 두 장의 카드는 서로 다른 카드이다")
	void pickDifferentTwoCards() {
		assertThat(CardDeck.pick()).isNotEqualTo(CardDeck.pick());
	}
}

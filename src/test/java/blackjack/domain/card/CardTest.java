package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	@DisplayName("카드의 모양과 숫자가 같으면 같은 카드로 판단한다.")
	void equalsTest() {
		Card card1 = new Card(CardShape.DIAMOND, CardNumber.ACE);
		Card card2 = new Card(CardShape.DIAMOND, CardNumber.ACE);

		assertThat(card1.equals(card2)).isTrue();
	}
}

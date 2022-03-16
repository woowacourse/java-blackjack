package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

	@DisplayName("카드가 ACE 라면 true를 반환한다")
	@Test
	void isAce() {
		Card card = new Card(CardSymbol.CLOVER, CardValue.ACE);
		assertThat(card.isAce()).isEqualTo(true);
	}

	@DisplayName("카드 symbol 이 SPADE, value 가 KING 이라면 카드 이름은 K스페이드이다")
	@Test
	void cardName_Spade_King() {
		Card card = new Card(CardSymbol.SPADE, CardValue.KING);
		assertThat(card.getName()).isEqualTo("K스페이드");
	}
}

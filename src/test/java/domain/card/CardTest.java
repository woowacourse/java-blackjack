package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
	@Test
	@DisplayName("equals 오버라이딩 테스트")
	void equalsTest() {
		assertThat(new Card(Symbol.EIGHT, Type.CLUB))
				.isEqualTo(new Card(Symbol.EIGHT, Type.CLUB));
	}

	@Test
	@DisplayName("카드가 Ace인지 테스트")
	void isAceTest() {
		assertThat(new Card(Symbol.ACE, Type.CLUB).isAce()).isTrue();
		assertThat(new Card(Symbol.TEN, Type.CLUB).isAce()).isFalse();
	}
}
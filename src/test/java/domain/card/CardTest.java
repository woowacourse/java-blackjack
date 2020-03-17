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
}
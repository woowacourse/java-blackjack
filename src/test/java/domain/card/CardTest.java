package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
	@Test
	@DisplayName("equals 오버라이딩 테스트")
	void equalsTest() {
		assertThat(new Card(Symbol.EIGHT, Type.CLUB))
			.isEqualTo(new Card(Symbol.EIGHT, Type.CLUB));
	}
}
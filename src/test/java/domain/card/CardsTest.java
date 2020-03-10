package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
	@Test
	@DisplayName("생성된 카드의 갯수를 확인합니다.")
	void createTest() {
		assertThat(Cards.create()).hasSize(52);
	}

	@Test
	@DisplayName("동일 타입,심볼을 가진 카드는 같다.")
	void equalsTest() {
		assertThat(Cards.create()).isEqualTo(Cards.create());
	}
}
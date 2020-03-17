package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardFactoryTest {
	@Test
	@DisplayName("생성된 카드의 갯수를 확인합니다.")
	void createTest() {
		assertThat(CardFactory.create()).hasSize(52);
	}
}
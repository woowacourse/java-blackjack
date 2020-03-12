package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardFactoryTest {
	@Test
	@DisplayName("생성된 카드의 갯수를 확인합니다.")
	void createTest() {
		assertThat(CardFactory.create()).hasSize(52);
	}

	@Test
	@DisplayName("생성된 카드의 갯수를 확인합니다.")
	void duplicateTest() {
		assertThat(new HashSet<>(CardFactory.create())).hasSize(52);
	}

	@Test
	@DisplayName("동일 타입,심볼을 가진 카드는 같다.")
	void equalsTest() {
		assertThat(CardFactory.create()).isEqualTo(CardFactory.create());
	}
}
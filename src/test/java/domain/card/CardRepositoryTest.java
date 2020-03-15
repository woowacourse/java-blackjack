package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardRepositoryTest {
	@DisplayName("CardRepository를 통해 생성된 카드뭉치가 52장인지 테스트")
	@Test
	void measure_Cards_Size() {
		assertThat(CardRepository.toList().size()).isEqualTo(52);
	}
}

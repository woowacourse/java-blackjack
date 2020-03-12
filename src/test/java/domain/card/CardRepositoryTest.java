package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardRepositoryTest {
	@Test
	void measure_Cards_Size() {
		assertThat(CardRepository.toList().size()).isEqualTo(52);
	}
}

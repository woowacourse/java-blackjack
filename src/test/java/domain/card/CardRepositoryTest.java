package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class CardRepositoryTest {
	@Test
	void measure_Cards_Size() {
		assertThat(CardRepository.toList().size()).isEqualTo(52);
	}
}

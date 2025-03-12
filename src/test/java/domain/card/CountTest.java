package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CountTest {

	@Nested
	@DisplayName("카운트에 대한 연산")
	class count {

		@DisplayName("1 카운트한다.")
		@Test
		void increment() {
			// given
			final Count count = new Count(0);
			final Count expected = new Count(1);

			// when
			final Count actual = count.increment();

			// then
			assertThat(actual).isEqualTo(expected);
		}
	}
}

package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CountTest {

	@Nested
	@DisplayName("카운트에 대한 연산")
	class count {

		@DisplayName("1만큼 카운트를 증가시킨다.")
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

		@DisplayName("1 만큼 카운트를 감소시킨다.")
		@Test
		void decrement() {
			// given
			final Count count = new Count(1);
			final Count expected = new Count(0);

			// when
			final Count actual = count.decrement();

			// then
			assertThat(actual).isEqualTo(expected);
		}
	}
}

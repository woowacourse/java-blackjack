package value;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

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
			final Count count = Count.from(0);
			final Count expected = Count.from(1);

			// when
			final Count actual = count.increment();

			// then
			assertThat(actual).isEqualTo(expected);
		}

		@DisplayName("1 만큼 카운트를 감소시킨다.")
		@Test
		void decrement() {
			// given
			final Count count = Count.from(1);
			final Count expected = Count.from(0);

			// when
			final Count actual = count.decrement();

			// then
			assertThat(actual).isEqualTo(expected);
		}
	}

	@Nested
	@DisplayName("검증연산")
	class is {

		@DisplayName("카운트가 0이라면 true를 아니라면 false를 반환한다.")
		@Test
		void isZero() {
			// given
			final Count notZero = Count.from(1);
			final Count zero = Count.from(0);

			// when
			final boolean actualNotZero = notZero.isZero();
			final boolean actualZero = zero.isZero();

			// then
			assertSoftly(s -> {
				s.assertThat(actualNotZero).isFalse();
				s.assertThat(actualZero).isTrue();
			});
		}
	}

	@Nested
	@DisplayName("비교 연산")
	class than {

		@DisplayName("x가 더 크다면 true를 반환한다.")
		@Test
		void isGreaterThan() {
			// given
			final Count greater = Count.from(1);
			final Count less = Count.from(0);

			// when
			final boolean actual = greater.isGreaterThan(less);

			// then
			assertThat(actual).isTrue();
		}
	}
}

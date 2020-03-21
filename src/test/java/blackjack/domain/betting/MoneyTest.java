package blackjack.domain.betting;

import blackjack.domain.betting.exceptions.MoneyException;
import blackjack.domain.user.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

	@DisplayName("zero()가 인스턴스를 반환하는지 테스트")
	@Test
	void zero_IsNotNull() {
		assertThat(Money.zero()).isNotNull();
	}

	@Test
	void zero_IsEqualToZeroInstance() {
		assertThat(Money.zero()).isEqualTo(Money.of("0"));
	}

	@ParameterizedTest
	@ValueSource(strings = {"1000", "1000.0", "1000d", "0"})
	void of_IsNotNull(String input) {
		Money money = Money.of(input);
		assertThat(money).isNotNull();
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " ", "abc"})
	void of_NotDouble_ThrowMoneyException(String input) {
		assertThatThrownBy(() -> Money.of(input))
				.isInstanceOf(MoneyException.class);
	}

	@Test
	void of_Null_ThrowMoneyException() {
		assertThatThrownBy(() -> Money.of(null))
				.isInstanceOf(MoneyException.class);
	}

	@ParameterizedTest
	@MethodSource("computeResultAmount")
	void computeResultAmount(ResultType resultType, String expect) {
		Money money = Money.of("1000");
		assertThat(money.computeResultingAmount(resultType)).isEqualTo(Money.of(expect));
	}

	static Stream<Arguments> computeResultAmount() {
		return Stream.of(Arguments.of(ResultType.BLACKJACK_WIN, "1500"),
				Arguments.of(ResultType.WIN, "1000"),
				Arguments.of(ResultType.DRAW, "0"),
				Arguments.of(ResultType.LOSE, "-1000"));
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,500,1500", "999.5,0.5,1000", "0,500.5,500.5"})
	void add(String input1, String input2, String expect) {
		Money a = Money.of(input1);
		Money b = Money.of(input2);
		assertThat(a.add(b)).isEqualTo(Money.of(expect));
	}
}
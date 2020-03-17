package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitTest {

	@ParameterizedTest
	@CsvSource(value = {"3000.0,5000.0,8000.0", "3000.0,2000.0, 5000.0"})
	@DisplayName("더하기 연산을 올바르게 수행하는지 테스트")
	void plusTest(double first, double second, double expected) {
		assertThat(new Profit(first).plus(new Profit(second))).isEqualTo(new Profit(expected));
	}
}
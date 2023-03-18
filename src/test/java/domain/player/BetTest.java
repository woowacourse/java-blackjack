package domain.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BetTest {

	@ParameterizedTest
	@DisplayName("0 이상 10,000이하의 베팅금은 정상적으로 생성되어야 한다.")
	@CsvSource({"0","10000"})
	void betConstructionSuccessTest(int betValue) {
		assertDoesNotThrow(() -> new Bet(betValue));
	}

	@ParameterizedTest
	@DisplayName("0 미만 10,000초과의 베팅금은 정상적으로 생성되어야 한다.")
	@CsvSource({"-1","10001"})
	void betConstructionFailTest(int betValue) {
		assertThrows(IllegalArgumentException.class, () -> new Bet(betValue));
	}
}

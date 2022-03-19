package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.result.BlackJackResult;

class BetTest {

	@Test
	@DisplayName("생성자에 0 이하가 들어가면 예외 발생")
	void createWithMinusException() {
		assertThatThrownBy(() -> new Bet(-1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("금액은 0보다 커야 합니다");
	}

	@Test
	@DisplayName("0보다 큰 금액으로는 생성된다.")
	void create() {
		assertThat(new Bet(1000))
			.isNotNull();
	}

	@Test
	@DisplayName("입력 받은 금액을 가지고 있다.")
	void moneyHaveValue() {
		Bet bet = new Bet(1000);
		assertThat(bet).isEqualTo(new Bet(1000));
	}

	@Test
	@DisplayName("금액은 10원 단위여야 한다..")
	void moneyDevidibleException() {
		assertThatThrownBy(() -> new Bet(1001))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("금액은 10원 단위여야 합니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACKJACK_WIN:1500", "WIN:1000", "LOSE:-1000", "DRAW:0"}, delimiter = ':')
	@DisplayName("수익률에 따른 수익 계산")
	void calculateEarning(String input, int bet) {
		BlackJackResult result = BlackJackResult.valueOf(input);
		int earning = new Bet(1000).multiply(result.getProfit());
		assertThat(earning).isEqualTo(bet);
	}
}

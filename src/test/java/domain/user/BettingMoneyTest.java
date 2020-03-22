package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BettingMoneyTest {

	@DisplayName("double형으로 BettingMoney객체 생성 테스트")
	@Test
	void create_When_Double_1000_Input_Then_Create_BettingMoney_Instance() {
		assertThat(new BettingMoney(1000)).isInstanceOf(BettingMoney.class);
	}

	@DisplayName("문자열로 BettingMoney객체 생성 테스트")
	@Test
	void create_When_String_1000_Input_Then_Create_BettingMoney_Instance() {
		assertThat(new BettingMoney("1000")).isInstanceOf(BettingMoney.class);
	}

	@DisplayName("문자열로 BettingMoney객체 생성시 숫자가 아닐시 예외처리")
	@Test
	void create_When_String_Not_Number_Input_Then_Exception() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new BettingMoney("money");
		});
	}

	@DisplayName("BettingMoney객체 생성시 유효한 숫자가 아닐시 예외처리")
	@Test
	void create_When_Invalid_Number_Input_Then_Exception() {
		assertThatThrownBy(() -> new BettingMoney(0))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("베팅 금액은 1원 이상이여야합니다.");
	}
}

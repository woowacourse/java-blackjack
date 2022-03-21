package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BettingAmount 테스트")
class BettingAmountTest {

	@Test
	@DisplayName("배팅 금액이 제대로 초기화 되는지 확인")
	void init_Betting_Amount() {
		BettingAmount bettingAmount = new BettingAmount(1000);
		assertThat(bettingAmount.getTotalValue()).isEqualTo(1000);
	}

	@Test
	@DisplayName("배팀 금액이 9원 이하면 에러 발생")
	void invalid_Betting_Amount_Error() {
		assertThatThrownBy(
			() -> new BettingAmount(9)
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("배팅 금액은 최소 10원이어야 합니다.");
	}

	@Test
	@DisplayName("배팅 금액의 1.5배를 받는지 확인")
	void receive_One_And_Half_Time() {
		BettingAmount bettingAmount = new BettingAmount(1000);
		bettingAmount.giveOneAndHalfTime();
		assertThat(bettingAmount.getTotalValue()).isEqualTo(2500);
	}

	@Test
	@DisplayName("배팅 금액을 모두 잃는지 확인")
	void lose_All_Betting_Amount() {
		BettingAmount bettingAmount = new BettingAmount(1000);
		bettingAmount.loseAll();
		assertThat(bettingAmount.getTotalValue()).isEqualTo(0);
	}

	@Test
	@DisplayName("최종 수익이 제대로 집계 되는지 확인")
	void calculate_Income() {
		BettingAmount bettingAmount = new BettingAmount(1000);
		assertThat(bettingAmount.calculateIncome()).isEqualTo(0);
	}

}
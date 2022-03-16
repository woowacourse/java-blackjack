package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BattingAmount 테스트")
class BattingAmountTest {

	@Test
	@DisplayName("배팅 금액이 제대로 초기화 되는지 확인")
	void init_Batting_Amount() {
		BattingAmount battingAmount = new BattingAmount(1000);
		assertThat(battingAmount.getValue()).isEqualTo(1000);
	}

	@Test
	@DisplayName("배팀 금액이 9원 이하면 에러 발생")
	void invalid_Batting_Amount_Error() {
		assertThatThrownBy(
			() -> new BattingAmount(9)
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("배팅 금액은 최소 10원이어야 합니다.");
	}

	@Test
	@DisplayName("배팅 금액의 1.5배를 받는지 확인")
	void receive_One_And_Half_Time() {
		BattingAmount battingAmount = new BattingAmount(1000);
		battingAmount.giveOneAndHalfTime();
		assertThat(battingAmount.getValue()).isEqualTo(1500);
	}

	@Test
	@DisplayName("배팅 금액을 모두 잃는지 확인")
	void lose_All_Batting_Amount() {
		BattingAmount battingAmount = new BattingAmount(1000);
		battingAmount.loseAll();
		assertThat(battingAmount.getValue()).isEqualTo(0);
	}

}
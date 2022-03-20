package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Betting 테스트")
class BettingTest {

	@DisplayName("플레이어 이름으로 배팅 금액을 가져오는 메서드 검증")
	@Test
	void check_Get_Money_By_Name() {
		Map<String, Money> bettingMoney = new HashMap<>();
		bettingMoney.put("player1", new Money(10000));
		bettingMoney.put("player2", new Money(15000));
		Betting betting = new Betting(bettingMoney);

		assertAll(
				() -> assertThat(betting.getBettingByName("player1")).isEqualTo(new Money(10000)),
				() -> assertThat(betting.getBettingByName("player2")).isEqualTo(new Money(15000))
		);
	}
}

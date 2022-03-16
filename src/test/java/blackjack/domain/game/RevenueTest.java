package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.role.Hand;
import blackjack.domain.role.Player;
import blackjack.domain.role.Role;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Revenue 테스트")
class RevenueTest {

	@Test
	@DisplayName("주어진 플레이어의 수익을 가져오는 기능 검증")
	void check_Player_Revenue_Results() {
		Role blackJackWinner = new Player("blackJackWinner", new Hand());
		Role winner = new Player("winner", new Hand());
		Role looser = new Player("looser", new Hand());
		Role tier = new Player("tier", new Hand());

		Map<Role, Money> result = new HashMap<>();
		result.put(blackJackWinner, new Money("1500"));
		result.put(winner, new Money("1000"));
		result.put(looser, new Money("-1000"));
		result.put(tier, new Money("0"));

		Revenue revenue = new Revenue(result);
		assertAll(
				() -> assertThat(revenue
						.getPlayerRevenueResult(blackJackWinner)
						.getValue()).isEqualTo(new BigDecimal("1500")),
				() -> assertThat(revenue
						.getPlayerRevenueResult(winner)
						.getValue()).isEqualTo(new BigDecimal("1000")),
				() -> assertThat(revenue
						.getPlayerRevenueResult(looser)
						.getValue()).isEqualTo(new BigDecimal("-1000")),
				() -> assertThat(revenue
						.getPlayerRevenueResult(tier)
						.getValue()).isEqualTo(new BigDecimal("0"))
		);
	}

	@Test
	@DisplayName("플레이어의 수익를 이용하여 딜러의 수익를 계산하는 테스트")
	void check_Dealer_Revenue_Results() {
		Role blackJackWinner = new Player("blackJackWinner", new Hand());
		Role winner = new Player("winner", new Hand());
		Role looser = new Player("looser", new Hand());
		Role tier = new Player("tier", new Hand());

		Map<Role, Money> result = new HashMap<>();
		result.put(blackJackWinner, new Money("1500"));
		result.put(winner, new Money("1000"));
		result.put(looser, new Money("-1000"));
		result.put(tier, new Money("0"));

		Revenue revenue = new Revenue(result);

		assertThat(revenue
				.getDealerRevenueResult()
				.getValue()
		).isEqualTo(new BigDecimal("-1500"));
	}
}

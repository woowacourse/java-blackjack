package money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import duel.DuelResult;

public class WagerResultStateTest {

	@Nested
	@DisplayName("베팅 결과 계산")
	class CalculateWagerResult {

		@DisplayName("패배라면, 모든 돈을 잃는다.")
		@Test
		void calculatePlayerBust() {
			// given
			final Money wagerMoney = new Money(1_000);
			final DuelResult duelResult = DuelResult.LOSE;
			final boolean isBlackjack = false;

			// when
			final Money wagerResult = WagerResultState.calculateWagerResultMoney(duelResult, isBlackjack, wagerMoney);

			// then
			assertThat(wagerResult.getValue()).isZero();
		}

		@DisplayName("무승부라면, 금액을 그대로 반환한다.")
		@Test
		void calculatePlayerBust1() {
			// given
			final Money wagerMoney = new Money(1_000);
			final DuelResult duelResult = DuelResult.DRAW;
			final boolean isBlackjack = false;

			// when
			final Money wagerResult = WagerResultState.calculateWagerResultMoney(duelResult, isBlackjack, wagerMoney);

			// then
			assertThat(wagerResult.getValue()).isEqualTo(1_000);
		}

		@DisplayName("플레이어가 우승이면서, 블랙잭이라면 2.5배를 반환한다. (베팅금액의 1.5배를 더해서 반환)")
		@Test
		void calculatePlayerBust2() {
			// given
			final Money wagerMoney = new Money(1_000);
			final DuelResult duelResult = DuelResult.WIN;
			final boolean isBlackjack = true;

			// when
			final Money wagerResult = WagerResultState.calculateWagerResultMoney(duelResult, isBlackjack, wagerMoney);

			// then
			assertThat(wagerResult.getValue()).isEqualTo(2_500);
		}

		@DisplayName("플레이어가 우승이면서, 블랙잭이 아니라면 2배를 반환한다. (베팅금액을 추가로 더해서 반환)")
		@Test
		void calculatePlayerBust3() {
			// given
			final Money wagerMoney = new Money(1_000);
			final DuelResult duelResult = DuelResult.WIN;
			final boolean isBlackjack = false;

			// when
			final Money wagerResult = WagerResultState.calculateWagerResultMoney(duelResult, isBlackjack, wagerMoney);

			// then
			assertThat(wagerResult.getValue()).isEqualTo(2_000);
		}
	}
}

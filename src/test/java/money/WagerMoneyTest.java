package money;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import duel.DuelResult;
import paticipant.Participant;
import paticipant.Player;

public class WagerMoneyTest {

	@Nested
	@DisplayName("생성")
	class WagerMoneyConstruct {

		@DisplayName("생성 테스트")
		@Test
		void wagerMoneyConstruct() {
			// given
			final Map<Player, Money> money = new HashMap<>();

			// when
			final WagerMoney wagerMoney = new WagerMoney(money);

			// then
			assertThat(wagerMoney.getMoney()).isEqualTo(money);
		}
	}

	@Nested
	@DisplayName("베팅 결과 반환")
	class CalculateWagerResult {

		@DisplayName("베팅 결과 수익률 Map을 반환한다.")
		@Test
		void calculateWagerResult() {
			// given
			final Participant participant = new Participant();
			participant.writeDuelResult(DuelResult.WIN);
			final Player player = new Player("", participant);
			final Map<Player, Money> money = Map.of(player, new Money(10_000));
			final WagerMoney wagerMoney = new WagerMoney(money);

			// when
			final Map<Player, Money> wagerResult = wagerMoney.calculateWagerResult();

			// then
			assertThat(wagerResult.get(player).getValue()).isEqualTo(10_000);
		}
	}
}

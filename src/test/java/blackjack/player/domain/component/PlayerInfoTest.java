package blackjack.player.domain.component;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PlayerInfoTest {

	@DisplayName("PlayerInfo 생성시 배팅금액이 1원 미만이면 Exception")
	@Test
	void name() {
		assertThatThrownBy(() -> {
			new PlayerInfo("allen", Money.createMoney(0));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("PlayerInfo 생성시 이름이 널 혹은 빈값을 입력시 Exception")
	@ParameterizedTest
	@NullAndEmptySource
	void name2(String arongName) {
		assertThatThrownBy(() -> {
			new PlayerInfo(arongName, Money.createMoney(1000));
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void calculateResultMoney() {
		PlayerInfo playerInfo = new PlayerInfo("allen", Money.createMoney(1000));
		double profit = 1.5;
		Money expect = Money.createMoney(1500);

		assertThat(playerInfo.calculateResultMoney(profit)).isEqualTo(expect);
	}
}
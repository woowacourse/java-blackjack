package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.user.Dealer;
import domain.user.Player;

class UserResultsTest {
	@DisplayName("결과 객체 생성시 인자에 null 이 들어가는 경우 Null 예외 발")
	@Test
	void construct_with_null_argument_exception_test() {
		assertThatThrownBy(() -> UserResults.fromPlayerResultsAndDealer(null, null))
			.isInstanceOf(NullPointerException.class);
	}

	@DisplayName("상금이 결정된 플레이어들 입력시, 딜러의 최종 상금 자동 계산 및 딜러, 플레이어 순으로 상금이 나열된다.")
	@Test
	void getPlayerResults() {
		List<UserResult> playerResults = Arrays.asList(
			new UserResult(Player.fromNameAndMoney("test1", 1_000), 0),
			new UserResult(Player.fromNameAndMoney("test2", 10_000), 10_000),
			new UserResult(Player.fromNameAndMoney("test3", 50_000), 75_000),
			new UserResult(Player.fromNameAndMoney("test4", 100_000), -100_000)
		);
		UserResults userResults = UserResults.fromPlayerResultsAndDealer(playerResults, new Dealer());

		assertThat(userResults.getPlayerResults()).extracting("prize")
			.containsExactly(15_000, 0, 10_000, 75_000, -100_000);
	}
}
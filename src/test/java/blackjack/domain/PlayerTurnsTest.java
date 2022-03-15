package blackjack.domain;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PlayerTurns 테스트")
class PlayerTurnsTest {

	@Test
	@DisplayName("순서에 맞게 플레이어의 턴을 가져오는지 확인")
	void check_Get_Current_Player() {
		Player player1 = new Player("player1", new Hand());
		Player player2 = new Player("player2", new Hand());

		PlayerTurns playerTurns = new PlayerTurns(List.of(player1, player2));

		assertAll(
				() -> assertThat(playerTurns.getCurrentPlayer()).isEqualTo(player1),
				() -> assertThat(playerTurns.getCurrentPlayer()).isEqualTo(player2)
		);
	}

	@Test
	@DisplayName("더이상 차례를 진행할 플레이어가 없을 때 예외 확인")
	void check_Do_Not_Have_Current_Player() {
		PlayerTurns playerTurns = new PlayerTurns(List.of());

		assertThatThrownBy(playerTurns::getCurrentPlayer)
				.isInstanceOf(NoSuchElementException.class)
				.hasMessageContaining("더이상 진행할 플레이어가 없습니다.");
	}
}


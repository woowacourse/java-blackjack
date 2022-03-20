package blackjack.domain.role;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.game.Money;
import blackjack.domain.state.Ready;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Players 테스트")
class PlayersTest {

	@Test
	@DisplayName("플레이어 이름을 통해서 플레이어를 가져오는 메서드 검증")
	void get_Player_By_Name() {
		Role player = new Player("player1", new Ready(), new Money(1000));
		Players players = new Players(List.of(player));
		assertThat(players.getPlayerByName("player1")).isEqualTo(player);
	}

	@Test
	@DisplayName("해당 플레이어의 이름이 없을 때 예외 발생")
	void not_find_Player_By_Name() {
		Role player = new Player("player1", new Ready(), new Money(1000));
		Players players = new Players(List.of(player));
		assertThatThrownBy(() -> players.getPlayerByName("player2"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 플레이어를 찾을 수 없습니다.");
	}
}


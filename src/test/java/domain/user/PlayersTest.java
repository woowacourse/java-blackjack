package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
	@DisplayName("매개 변수 플레이어 리스트가 null 인 경우 Illegal Exception 발생")
	@Test
	void throw_illegal_exception_when_construct_players_with_null_list_player_() {
		assertThatThrownBy(() -> new Players(null)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("플레이어가 1명 이하인 경우 Illegal Exception 발생")
	@Test
	void throw_illegal_exception_when_construct_players_with_zero_player() {
		List<Player> players = new ArrayList<>();
		assertThatThrownBy(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("플레이어가 1명인 경우 정상적으로 객체 생성")
	@Test
	void construct_players_with_single_player() {
		List<Player> players = Collections.singletonList(Player.fromNameAndMoney("test", 1_000));
		assertThatCode(() -> new Players(players)).doesNotThrowAnyException();
	}
}

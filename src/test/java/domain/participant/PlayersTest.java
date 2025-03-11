package domain.participant;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.paticipant.Players;

public class PlayersTest {

	@Nested
	@DisplayName("Players 생성")
	class ConstructPlayers {

		@DisplayName("이름들로 플레이어들을 생성한다.")
		@Test
		void constructPlayers() {
			// given
			final List<String> names = List.of("부기", "구구", "파랑");

			// when
			final var players = Players.from(names);

			// then
			assertThat(players.getPlayers()).hasSize(3);
		}
	}
}

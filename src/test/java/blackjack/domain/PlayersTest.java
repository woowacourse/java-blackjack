package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
	@Test
	@DisplayName("빈 리스트를 받았을 때 에러 발생")
	void check_empty_list() {
		String lines = ",,,,";
		List<String> strings = Arrays.asList(lines.split(","));
		assertThatThrownBy(() -> new Players(new Names(strings)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void check_busted_player_count() {
		String lines = "pobi,jason,alpha";
		List<String> strings = Arrays.asList(lines.split(","));
		Players players = new Players(new Names(strings));
		players.getPlayers().get(0).processCard(new Card(Number.TEN, Type.HEART));
		players.getPlayers().get(0).processCard(new Card(Number.TEN, Type.CLOVER));
		players.getPlayers().get(0).processCard(new Card(Number.TWO, Type.HEART));
		players.getPlayers().get(1).processCard(new Card(Number.TEN, Type.SPADE));
		players.getPlayers().get(1).processCard(new Card(Number.TEN, Type.DIAMOND));
		players.getPlayers().get(1).processCard(new Card(Number.ACE, Type.HEART));
		assertThat(players.getBustPlayers().size()).isEqualTo(1);
	}

	@Test
	void check_not_busted_player_count() {
		String lines = "pobi,jason,alpha";
		List<String> strings = Arrays.asList(lines.split(","));
		Players players = new Players(new Names(strings));
		players.getPlayers().get(0).processCard(new Card(Number.TEN, Type.HEART));
		players.getPlayers().get(0).processCard(new Card(Number.TEN, Type.CLOVER));
		players.getPlayers().get(0).processCard(new Card(Number.TWO, Type.HEART));
		players.getPlayers().get(1).processCard(new Card(Number.TEN, Type.SPADE));
		players.getPlayers().get(1).processCard(new Card(Number.TEN, Type.DIAMOND));
		players.getPlayers().get(1).processCard(new Card(Number.ACE, Type.HEART));
		assertThat(players.getNotBustPlayers().size()).isEqualTo(2);
	}

	@Test
	void check_all_player_blackjack_or_bust() {
		String lines = "pobi,jason";
		List<String> strings = Arrays.asList(lines.split(","));
		Players players = new Players(new Names(strings));
		players.getPlayers().get(0).processCard(new Card(Number.TEN, Type.HEART));
		players.getPlayers().get(0).processCard(new Card(Number.TEN, Type.CLOVER));
		players.getPlayers().get(0).processCard(new Card(Number.TWO, Type.HEART));
		players.getPlayers().get(1).processCard(new Card(Number.TEN, Type.SPADE));
		players.getPlayers().get(1).processCard(new Card(Number.ACE, Type.HEART));
		assertThat(players.isAllPlayersBlackJackOrBust()).isTrue();
	}
}

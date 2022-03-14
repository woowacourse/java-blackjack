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
	void check_all_player_blackjack_or_bust() {
		String lines = "pobi,jason";
		List<String> strings = Arrays.asList(lines.split(","));
		Players players = new Players(new Names(strings));
		players.getPlayers().get(0).processCard(new Card(CardLetter.TEN, CardSuit.HEART));
		players.getPlayers().get(0).processCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
		players.getPlayers().get(0).processCard(new Card(CardLetter.TWO, CardSuit.HEART));
		players.getPlayers().get(1).processCard(new Card(CardLetter.TEN, CardSuit.SPADE));
		players.getPlayers().get(1).processCard(new Card(CardLetter.ACE, CardSuit.HEART));
		assertThat(players.isAllPlayersBlackJackOrBust()).isTrue();
	}
}

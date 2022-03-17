package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDenomination;
import blackjack.domain.card.CardSuit;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class PlayersTest {
	@Test
	void check_empty_list() {
		String lines = ",,,,";
		List<String> strings = Arrays.asList(lines.split(","));
		assertThatThrownBy(() -> Players.from(strings.stream().map(Name::new).collect(Collectors.toList())))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void check_over_full() {
		String lines = "a,b,c,d,e,f,g,h";
		List<String> strings = Arrays.asList(lines.split(","));
		assertThatThrownBy(() -> Players.from(strings.stream().map(Name::new).collect(Collectors.toList())))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void check_all_player_blackjack_or_bust() {
		Player player1 = new Player(new Name("pobi"));
		Player player2 = new Player(new Name("jason"));
		player1.addCards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.TEN, CardSuit.CLOVER),
				new Card(CardDenomination.TWO, CardSuit.HEART)));
		player2.addCards(
			List.of(new Card(CardDenomination.TEN, CardSuit.SPADE), new Card(CardDenomination.ACE, CardSuit.HEART)));
		Players players = new Players(List.of(player1, player2));
		assertThat(players.isAllPlayersBlackJackOrBust()).isTrue();
	}
}

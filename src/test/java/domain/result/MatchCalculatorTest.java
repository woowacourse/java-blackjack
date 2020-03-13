package domain.result;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static domain.result.MatchResult.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;

class MatchCalculatorTest {
	@Test
	void getMatchResultsTest() {
		User player = new Player("test");
		User player2 = new Player("test");
		User player3 = new Player("test");

		player.addCards(Arrays.asList(new Card(CLOVER, ACE), new Card(CLOVER, EIGHT)));
		player2.addCards(Arrays.asList(new Card(CLOVER, ACE), new Card(CLOVER, NINE)));
		player3.addCards(Arrays.asList(new Card(CLOVER, ACE), new Card(CLOVER, TEN)));

		List<User> players = Arrays.asList(player, player2, player3);
		Dealer dealer = new Dealer();
		dealer.addCards(Arrays.asList(new Card(CLOVER, ACE), new Card(CLOVER, SEVEN)));

		MatchCalculator matchCalculator = new MatchCalculator(players, dealer);
		assertThat(matchCalculator.getMatchResults())
			.containsExactlyInAnyOrder(WIN, WIN, WIN);
	}
}
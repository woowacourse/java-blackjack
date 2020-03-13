package domain.result;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static domain.result.MatchResult.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

class MatchCalculatorTest {
	@DisplayName("플레이어들 각각 딜러와 점수를 비교한 뒤, 모든 플레이어의 게임 승패여부를 목록으로 반환한다.")
	@Test
	void getMatchResultsTest() {
		Player player = Player.fromNameAndCards("player", new Card(CLOVER, ACE), new Card(CLOVER, EIGHT));
		Player player2 = Player.fromNameAndCards("tester", new Card(CLOVER, ACE), new Card(CLOVER, NINE));
		Player player3 = Player.fromNameAndCards("black", new Card(CLOVER, ACE), new Card(CLOVER, TEN));

		List<Player> players = Arrays.asList(player, player2, player3);
		Dealer dealer = Dealer.fromCards(new Card(CLOVER, ACE), new Card(CLOVER, SEVEN));

		MatchCalculator matchCalculator = new MatchCalculator(players, dealer);
		assertThat(matchCalculator.getMatchResults())
			.containsExactlyInAnyOrder(WIN, WIN, WIN);
	}
}
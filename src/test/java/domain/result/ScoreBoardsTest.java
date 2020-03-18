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

class ScoreBoardsTest {
	@DisplayName("플레이어들 각각 딜러와 점수를 비교한 뒤, 딜러의 게임 전적 반환이 제대로 이뤄지는지 확인한다.")
	@Test
	void calculateDealerResultTest() {
		Player player = Player.fromNameAndCards("player", new Card(CLOVER, ACE), new Card(CLOVER, EIGHT));
		Player player2 = Player.fromNameAndCards("tester", new Card(CLOVER, ACE), new Card(CLOVER, NINE));
		Player player3 = Player.fromNameAndCards("black", new Card(CLOVER, ACE), new Card(CLOVER, NINE));

		List<Player> players = Arrays.asList(player, player2, player3);
		Dealer dealer = Dealer.fromCards(new Card(CLOVER, ACE), new Card(CLOVER, SEVEN));

		ScoreBoards resultCalculator = ScoreBoards.fromAllUsers(players, dealer);
		DealerResult dealerResult = resultCalculator.calculateDealerResults();
		assertThat(dealerResult.getResultCount(LOSE)).isEqualTo(3L);
	}

	@DisplayName("플레이어들 각각 딜러와 점수를 비교한 뒤, 모든 플레이어의 게임 승패결과가 제대로 반환되는지 확인한다.")
	@Test
	void calculatePlayerResultsTest() {
		Player player = Player.fromNameAndCards("player", new Card(CLOVER, ACE), new Card(CLOVER, EIGHT));
		Player player2 = Player.fromNameAndCards("tester", new Card(CLOVER, ACE), new Card(CLOVER, NINE));
		Player player3 = Player.fromNameAndCards("black", new Card(CLOVER, ACE), new Card(CLOVER, TEN));

		List<Player> players = Arrays.asList(player, player2, player3);
		Dealer dealer = Dealer.fromCards(new Card(CLOVER, ACE), new Card(CLOVER, SEVEN));

		ScoreBoards resultCalculator = ScoreBoards.fromAllUsers(players, dealer);
		PlayerResults playerResults = resultCalculator.calculatePlayersResult();
		assertThat(playerResults.getPlayerResults()).containsExactlyInAnyOrder(new PlayerResult(player, WIN),
			new PlayerResult(player2, WIN), new PlayerResult(player3, BLACKJACK_WIN));
	}
}
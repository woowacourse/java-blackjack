package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {
	Dealer dealer;
	Players players;
	Player player1;
	Player player2;
	Result result;
	
	@BeforeEach
	void gamer_init() {
		dealer = new Dealer();
		players = new Players(new Names(List.of("pobi", "jason")));
		result = new Result();
		player1 = players.getPlayers().get(0);
		player2 = players.getPlayers().get(1);
	}
	@Test
	void all_player_lose() {
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.KING, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.SPADE));
		player1.processCard(new Card(Number.NINE, Type.DIAMOND));
		player2.processCard(new Card(Number.TEN, Type.DIAMOND));
		player2.processCard(new Card(Number.NINE, Type.SPADE));
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.LOSE);
		}
	}

	@Test
	void all_player_win() {
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.NINE, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.DIAMOND));
		player2.processCard(new Card(Number.TEN, Type.HEART));
		player2.processCard(new Card(Number.TEN, Type.CLOVER));
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.WIN);
		}
	}

	@Test
	void all_player_draw() {
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.NINE, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.SPADE));
		player1.processCard(new Card(Number.NINE, Type.DIAMOND));
		player2.processCard(new Card(Number.TEN, Type.HEART));
		player2.processCard(new Card(Number.NINE, Type.CLOVER));
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.DRAW);
		}
	}
}

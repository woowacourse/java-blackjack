package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {
	Dealer dealer;
	Player player1;
	Player player2;
	@BeforeEach
	void gamer_init() {
		dealer = new Dealer();
		player1 = new Player(new Name("pobi"));
		player2 = new Player(new Name("jason"));
	}
	@Test
	void all_player_lose() {
		// given
		Players players = new Players(new Names(List.of("pobi", "jason")));
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.KING, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.SPADE));
		player1.processCard(new Card(Number.NINE, Type.DIAMOND));
		player2.processCard(new Card(Number.TEN, Type.DIAMOND));
		player2.processCard(new Card(Number.NINE, Type.SPADE));
		// when
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);

		// then
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.LOSE);
		}
	}

	@Test
	void all_player_win() {
		// given
		Players players = new Players(new Names(List.of("pobi", "jason")));
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.NINE, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.DIAMOND));
		player2.processCard(new Card(Number.TEN, Type.HEART));
		player2.processCard(new Card(Number.TEN, Type.CLOVER));
		// when
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		// then
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.WIN);
		}
	}

	@Test
	void all_player_draw() {
	    //given
		Players players = new Players(new Names(List.of("pobi", "jason")));
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.NINE, Type.SPADE));
		player1.processCard(new Card(Number.TEN, Type.SPADE));
		player1.processCard(new Card(Number.NINE, Type.DIAMOND));
		player2.processCard(new Card(Number.TEN, Type.HEART));
		player2.processCard(new Card(Number.NINE, Type.CLOVER));
	    //when
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
	    //then
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.DRAW);
		}
	}
}

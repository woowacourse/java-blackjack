package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Type;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public class ResultTest {
	Dealer dealer;
	Player player1;
	Player player2;
	@BeforeEach
	void gamer_init() {
		dealer = new Dealer();
		player1 = new Player("pobi");
		player2 = new Player("jason");
	}
	@Test
	void all_player_lose() {
		// given
		List<Player> players = List.of(player1, player2);
		dealer.addCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.addCard(new Card(Number.KING, Type.SPADE));
		player1.addCard(new Card(Number.TEN, Type.SPADE));
		player1.addCard(new Card(Number.NINE, Type.DIAMOND));
		player2.addCard(new Card(Number.TEN, Type.DIAMOND));
		player2.addCard(new Card(Number.NINE, Type.SPADE));
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
		List<Player> players = List.of(player1, player2);
		dealer.addCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.addCard(new Card(Number.NINE, Type.SPADE));
		player1.addCard(new Card(Number.TEN, Type.SPADE));
		player1.addCard(new Card(Number.TEN, Type.DIAMOND));
		player2.addCard(new Card(Number.TEN, Type.HEART));
		player2.addCard(new Card(Number.TEN, Type.CLOVER));
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
		List<Player> players = List.of(player1, player2);
		dealer.addCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.addCard(new Card(Number.NINE, Type.SPADE));
		player1.addCard(new Card(Number.TEN, Type.SPADE));
		player1.addCard(new Card(Number.NINE, Type.DIAMOND));
		player2.addCard(new Card(Number.TEN, Type.HEART));
		player2.addCard(new Card(Number.NINE, Type.CLOVER));
	    //when
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
	    //then
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.DRAW);
		}
	}
}

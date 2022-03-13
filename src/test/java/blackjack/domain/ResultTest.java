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
	Player player3;
	Result result;
	
	@BeforeEach
	void gamer_init() {
		dealer = new Dealer();
		players = new Players(new Names(List.of("pobi", "jason", "alpha")));
		result = new Result(players);
		player1 = players.getPlayers().get(0);
		player2 = players.getPlayers().get(1);
		player3 = players.getPlayers().get(2);
	}
	@Test
	void dealer_bust() {
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.KING, Type.SPADE));
		dealer.processCard(new Card(Number.TWO, Type.SPADE));
		setPlayerBlackJack(player1);
		setPlayerBust(player2);
		setPlayerBlackNormal(player3);
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		assertThat(gameResult.values()).containsSequence(ResultType.WIN, ResultType.LOSE, ResultType.WIN);
	}

	@Test
	void dealer_blackjack() {
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.ACE, Type.SPADE));
		setPlayerBlackJack(player1);
		setPlayerBust(player2);
		setPlayerBlackNormal(player3);
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		assertThat(gameResult.values()).containsSequence(ResultType.DRAW, ResultType.LOSE, ResultType.LOSE);
	}

	@Test
	void dealer_normal() {
		dealer.processCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.processCard(new Card(Number.NINE, Type.SPADE));
		setPlayerBlackJack(player1);
		setPlayerBust(player2);
		setPlayerBlackNormal(player3);
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		assertThat(gameResult.values()).containsSequence(ResultType.WIN, ResultType.LOSE, ResultType.LOSE);
	}

	private void setPlayerBust(Player player) {
		player.processCard(new Card(Number.TEN, Type.SPADE));
		player.processCard(new Card(Number.TEN, Type.DIAMOND));
		player.processCard(new Card(Number.TWO, Type.HEART));
	}

	private void setPlayerBlackJack(Player player) {
		player.processCard(new Card(Number.TEN, Type.HEART));
		player.processCard(new Card(Number.ACE, Type.DIAMOND));
	}

	private void setPlayerBlackNormal(Player player) {
		player.processCard(new Card(Number.SIX, Type.HEART));
		player.processCard(new Card(Number.FIVE, Type.DIAMOND));
	}
}

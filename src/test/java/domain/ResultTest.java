package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ResultTest {
	@Test
	void dealer_win_under_twenty_one() {
		// given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		Player player2 = new Player("jason");
		List<Player> players = List.of(player,player2);
		dealer.addCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.addCard(new Card(Number.KING, Type.SPADE));
		player.addCard(new Card(Number.TEN, Type.SPADE));
		player.addCard(new Card(Number.NINE, Type.DIAMOND));
		player2.addCard(new Card(Number.TEN, Type.DIAMOND));
		player2.addCard(new Card(Number.NINE, Type.SPADE));
		// when
		Result result = new Result(players, dealer);
		// then
		assertThat(result.getLosers().size()).isEqualTo(2);
	}

	@Test
	void one_player_win() {
		// given
		Dealer dealer = new Dealer();
		Player player = new Player("pobi");
		Player player2 = new Player("jason");
		List<Player> players = List.of(player,player2);
		dealer.addCard(new Card(Number.QUEEN, Type.CLOVER));
		dealer.addCard(new Card(Number.NINE, Type.SPADE));
		player.addCard(new Card(Number.TEN, Type.SPADE));
		player.addCard(new Card(Number.TEN, Type.DIAMOND));
		player2.addCard(new Card(Number.EIGHT, Type.HEART));
		player2.addCard(new Card(Number.TEN, Type.CLOVER));
		// when
		Result result = new Result(players, dealer);
		// then
		assertThat(result.getWinners()).contains(player);
	}
}

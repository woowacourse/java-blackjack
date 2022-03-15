package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
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
	@DisplayName("모든 플레이어가 패배하는 경우")
	void all_player_lose() {
		// given
		List<Player> players = List.of(player1, player2);
		dealer.addCard(Card.of(Denomination.QUEEN, Suit.CLOVER));
		dealer.addCard(Card.of(Denomination.KING, Suit.SPADE));
		player1.addCard(Card.of(Denomination.TEN, Suit.SPADE));
		player1.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		player2.addCard(Card.of(Denomination.TEN, Suit.DIAMOND));
		player2.addCard(Card.of(Denomination.NINE, Suit.SPADE));
		// when
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);

		// then
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.LOSE);
		}
	}

	@Test
	@DisplayName("모든 플레이어가 승리하는 경우")
	void all_player_win() {
		// given
		List<Player> players = List.of(player1, player2);
		dealer.addCard(Card.of(Denomination.QUEEN, Suit.CLOVER));
		dealer.addCard(Card.of(Denomination.NINE, Suit.SPADE));
		player1.addCard(Card.of(Denomination.TEN, Suit.SPADE));
		player1.addCard(Card.of(Denomination.TEN, Suit.DIAMOND));
		player2.addCard(Card.of(Denomination.TEN, Suit.HEART));
		player2.addCard(Card.of(Denomination.TEN, Suit.CLOVER));
		// when
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		// then
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.WIN);
		}
	}

	@Test
	@DisplayName("모든 플레이어와 딜러가 무승부인 경우")
	void all_player_draw() {
	    //given
		List<Player> players = List.of(player1, player2);
		dealer.addCard(Card.of(Denomination.QUEEN, Suit.CLOVER));
		dealer.addCard(Card.of(Denomination.NINE, Suit.SPADE));
		player1.addCard(Card.of(Denomination.TEN, Suit.SPADE));
		player1.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		player2.addCard(Card.of(Denomination.TEN, Suit.HEART));
		player2.addCard(Card.of(Denomination.NINE, Suit.CLOVER));
	    //when
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
	    //then
		for (Player player : gameResult.keySet()) {
			assertThat(gameResult.get(player)).isEqualTo(ResultType.DRAW);
		}
	}
}

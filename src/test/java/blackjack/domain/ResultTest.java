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
		dealer.addCards(
			List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER), new Card(CardDenomination.KING, CardSuit.SPADE),
				new Card(CardDenomination.TWO, CardSuit.SPADE)));
		setPlayerBlackJack(player1);
		setPlayerBust(player2);
		setPlayerBlackNormal(player3);
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		assertThat(gameResult.values()).containsSequence(ResultType.WIN, ResultType.LOSE, ResultType.WIN);
	}

	@Test
	void dealer_blackjack() {
		dealer.addCards(
			List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER), new Card(CardDenomination.ACE, CardSuit.SPADE)));
		setPlayerBlackJack(player1);
		setPlayerBust(player2);
		setPlayerBlackNormal(player3);
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		assertThat(gameResult.values()).containsSequence(ResultType.DRAW, ResultType.LOSE, ResultType.LOSE);
	}

	@Test
	void dealer_normal() {
		dealer.addCards(List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER),
			new Card(CardDenomination.NINE, CardSuit.SPADE)));
		setPlayerBlackJack(player1);
		setPlayerBust(player2);
		setPlayerBlackNormal(player3);
		Map<Player, ResultType> gameResult = result.getResult(players, dealer);
		assertThat(gameResult.values()).containsSequence(ResultType.WIN, ResultType.LOSE, ResultType.LOSE);
	}

	private void setPlayerBust(Player player) {
		player.addCards(
			List.of(new Card(CardDenomination.TEN, CardSuit.SPADE), new Card(CardDenomination.TEN, CardSuit.DIAMOND),
				new Card(CardDenomination.TWO, CardSuit.HEART)));
	}

	private void setPlayerBlackJack(Player player) {
		player.addCards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.DIAMOND)));
	}

	private void setPlayerBlackNormal(Player player) {
		player.addCards(
			List.of(new Card(CardDenomination.SIX, CardSuit.HEART), new Card(CardDenomination.FIVE, CardSuit.DIAMOND)));
	}
}

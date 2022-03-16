package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {
	Players players;
	Player player1;
	Player player2;
	Player player3;

	@BeforeEach
	void gamer_init() {
		player1 = getBustedPlayer();
		player2 = getBlackJackedPlayer();
		player3 = getNormalPlayer();
		players = new Players(List.of(player1, player2, player3));
	}

	@Test
	void dealer_bust() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER), new Card(CardDenomination.KING, CardSuit.SPADE),
				new Card(CardDenomination.TWO, CardSuit.SPADE))));
		Map<Player, ResultType> gameResult = Result.of(players, dealer).getGameResult();
		assertThat(gameResult.values()).containsSequence(ResultType.WIN, ResultType.LOSE, ResultType.WIN);
	}

	@Test
	void dealer_blackjack() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER),
			new Card(CardDenomination.ACE, CardSuit.SPADE))));
		Map<Player, ResultType> gameResult = Result.of(players, dealer).getGameResult();
		assertThat(gameResult.values()).containsSequence(ResultType.DRAW, ResultType.LOSE, ResultType.LOSE);
	}

	@Test
	void dealer_normal() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER),
			new Card(CardDenomination.NINE, CardSuit.SPADE))));
		Map<Player, ResultType> gameResult = Result.of(players, dealer).getGameResult();
		assertThat(gameResult.values()).containsSequence(ResultType.WIN, ResultType.LOSE, ResultType.LOSE);
	}

	private Player getBustedPlayer() {
		return new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.SPADE), new Card(CardDenomination.TEN, CardSuit.DIAMOND),
				new Card(CardDenomination.TWO, CardSuit.HEART))), new Name("pobi"));
	}

	private Player getBlackJackedPlayer() {
		return new Player(new Cards(
			List.of(new Card(CardDenomination.TEN, CardSuit.HEART), new Card(CardDenomination.ACE, CardSuit.DIAMOND))),
			new Name("jason"));
	}

	private Player getNormalPlayer() {
		return new Player(new Cards(
			List.of(new Card(CardDenomination.SIX, CardSuit.HEART), new Card(CardDenomination.FIVE, CardSuit.DIAMOND))),
			new Name("alpha"));
	}
}

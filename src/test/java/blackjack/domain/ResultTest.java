package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultTest {
	Players players;
	Player player1;
	Player player2;
	Player player3;
	BettingTokens bettingTokens;

	@BeforeEach
	void gamer_init() {
		player1 = getBustedPlayer();
		player2 = getBlackJackedPlayer();
		player3 = getNormalPlayer();
		players = new Players(List.of(player1, player2, player3));
		bettingTokens = new BettingTokens(
			List.of(new BettingToken(3000), new BettingToken(1000), new BettingToken(2000)));
	}

	@Test
	void dealer_bust() {
		Dealer dealer = new Dealer(new Cards(
			List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER), new Card(CardDenomination.KING, CardSuit.SPADE),
				new Card(CardDenomination.TWO, CardSuit.SPADE))));
		Result result = new Result(players, bettingTokens);
		assertAll(() -> assertThat(result.getPlayersMoney(dealer).values()).containsSequence(-3000, 1500, 2000),
			() -> assertThat(result.getDealerMoney(dealer)).isEqualTo(2500));
	}

	@Test
	void dealer_blackjack() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER),
			new Card(CardDenomination.ACE, CardSuit.SPADE))));
		Result result = new Result(players, bettingTokens);
		assertAll(() -> assertThat(result.getPlayersMoney(dealer).values()).containsSequence(-3000, 1000, -2000),
			() -> assertThat(result.getDealerMoney(dealer)).isEqualTo(5000));
	}

	@Test
	void dealer_normal() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER),
			new Card(CardDenomination.NINE, CardSuit.SPADE))));
		Result result = new Result(players, bettingTokens);
		assertAll(() -> assertThat(result.getPlayersMoney(dealer).values()).containsSequence(-3000, 1500, -2000),
			() -> assertThat(result.getDealerMoney(dealer)).isEqualTo(4500));
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

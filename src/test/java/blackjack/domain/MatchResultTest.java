package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.betting.BettingTokens;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDenomination;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.process.BettingResult;
import blackjack.domain.process.Match;
import blackjack.domain.process.MatchResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchResultTest {
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
		MatchResult matchResult = MatchResult.of(players, dealer);
		assertThat(matchResult.getPlayersMatchCache().values()).containsSequence(Match.LOSE, Match.BLACKJACK_WIN, Match.NOT_BLACKJACK_WIN);

	}

	@Test
	void dealer_blackjack() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER),
			new Card(CardDenomination.ACE, CardSuit.SPADE))));
		MatchResult matchResult = MatchResult.of(players, dealer);
		assertThat(matchResult.getPlayersMatchCache().values()).containsSequence(Match.LOSE, Match.DRAW, Match.LOSE);
	}

	@Test
	void dealer_normal() {
		Dealer dealer = new Dealer(new Cards(List.of(new Card(CardDenomination.QUEEN, CardSuit.CLOVER),
			new Card(CardDenomination.NINE, CardSuit.SPADE))));
		MatchResult matchResult = MatchResult.of(players, dealer);
		assertThat(matchResult.getPlayersMatchCache().values()).containsSequence(Match.LOSE, Match.BLACKJACK_WIN, Match.LOSE);
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

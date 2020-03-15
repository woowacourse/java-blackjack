package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DefaultDealer;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ResultsTest {
	private Results results;
	private Players players;

	@BeforeEach
	void setUp() {
		Dealer dealer = DefaultDealer.dealer();
		dealer.giveCard(Card.of(Symbol.ACE, Type.SPADE));

		players = Players.of("그니, 무늬, 포비");
		players.getPlayers().get(0).giveCard(Card.of(Symbol.TEN, Type.DIAMOND));
		players.getPlayers().get(1).giveCards(Arrays.asList(Card.of(Symbol.FIVE, Type.CLUB),
				Card.of(Symbol.SEVEN, Type.HEART)));
		players.getPlayers().get(2).giveCard(Card.of(Symbol.TWO, Type.DIAMOND));

		results = Results.of(dealer, players);
	}

	@Test
	void of() {
		assertThat(results).isNotNull();
	}

	@Test
	void getResultOf() {
		assertThat(results.getResult(players.getPlayers().get(0))).isFalse();
		assertThat(results.getResult(players.getPlayers().get(1))).isTrue();
		assertThat(results.getResult(players.getPlayers().get(2))).isFalse();
	}

	@Test
	void getDealerWin() {
		assertThat(results.getDealerWin()).isEqualTo(2);
	}

	@Test
	void getDealerLose() {
		assertThat(results.getDealerLose()).isEqualTo(1);
	}
}
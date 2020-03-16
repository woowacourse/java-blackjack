package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ResultsTest {
	private Results results;
	private Players players;

	@BeforeEach
	void setUp() {
		Playable dealer = Dealer.dealer();
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
	void getResult_IsReturnTrue() {
		assertThat(results.getResult(players.getPlayers().get(1))).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 2})
	void getResult_IsReturnFalse(int index) {
		assertThat(results.getResult(players.getPlayers().get(index))).isFalse();
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
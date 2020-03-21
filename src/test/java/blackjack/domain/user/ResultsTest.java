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
		dealer.receiveCard(Card.of(Symbol.ACE, Type.SPADE));

		players = Players.of(Arrays.asList("그니", "무늬", "포비"), Arrays.asList("1000", "1000", "1000"));
		players.getPlayers().get(0).receiveCard(Card.of(Symbol.TEN, Type.DIAMOND));
		players.getPlayers().get(1).receiveCards(Arrays.asList(Card.of(Symbol.FIVE, Type.CLUB),
				Card.of(Symbol.SEVEN, Type.HEART)));
		players.getPlayers().get(2).receiveCard(Card.of(Symbol.TWO, Type.DIAMOND));

		results = Results.of(dealer, players);
		System.out.println(results);
	}

	@Test
	void of_HasThreeMember_IsNotNull() {
		assertThat(results).isNotNull();
	}

	@Test
	void getDealerWin_DealerWonTwice_IsEqualToTwo() {
		assertThat(results.getDealerWin()).isEqualTo(2);
	}

	@Test
	void getDealerLose_DealerLoseOnce_IsEqualToOne() {
		assertThat(results.getDealerLose()).isEqualTo(1);
	}
}
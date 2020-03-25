package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Playable;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ResultsTest {
	private Results results;
	private Players players;

	@BeforeEach
	void setUp() {
		Playable dealer = Dealer.empty();
		dealer.receiveCard(Card.of(Symbol.ACE, Type.SPADE));

		players = Players.of(Arrays.asList("그니", "무늬", "포비"), Arrays.asList("1000", "1000", "1000"));
		players.getPlayers().get(0).receiveCard(Card.of(Symbol.TEN, Type.DIAMOND));
		players.getPlayers().get(1).receiveCards(Arrays.asList(Card.of(Symbol.FIVE, Type.CLUB),
				Card.of(Symbol.SEVEN, Type.HEART)));
		players.getPlayers().get(2).receiveCard(Card.of(Symbol.TWO, Type.DIAMOND));

		results = Results.of(dealer, players);
		System.out.println(results);
	}

	@DisplayName("of()가 인스턴스를 반환하는지 테스트")
	@Test
	void of_HasThreeMember_IsNotNull() {
		assertThat(results).isNotNull();
	}

	@DisplayName("getDealerWin()이 딜러의 승 수를 반환하는지 테스트")
	@Test
	void getDealerWin_DealerWonTwice_IsEqualToTwo() {
		assertThat(results.getDealerWin()).isEqualTo(2);
	}

	@DisplayName("getDealerLose()가 딜러의 패 수를 반환하는지 테스트")
	@Test
	void getDealerLose_DealerLoseOnce_IsEqualToOne() {
		assertThat(results.getDealerLose()).isEqualTo(1);
	}
}
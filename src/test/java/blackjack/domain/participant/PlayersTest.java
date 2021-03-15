package blackjack.domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.state.StateFactory;

public class PlayersTest {
	private Players players;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		List<String> input = Arrays.asList("pobi", "jason");
		dealer = new Dealer();
		players = new Players(input, dealer);
	}

	@Test
	@DisplayName("카드 처음 두장씩 분배 확인")
	void playerReceiveCards() {
		players.initialCards(new Deck());
		assertEquals(2, (int)players.toList().stream()
			.filter(player -> player.playerState.getCardStream().count() == 2)
			.count());
	}

	@Test
	@DisplayName("딜러 이윤 계산 확인")
	void calculateDealerProfit() {
		for (Player player : players.toList()) {
			player.makeProfit(100);
			player.playerState = StateFactory.drawTwoCards(new Card(CardPattern.DIAMOND, CardNumber.SEVEN),
				new Card(CardPattern.HEART, CardNumber.TWO));
			player.canReceiveCard(false);
		}
		assertEquals(-200, players.calculateProfits(dealer));
	}
}

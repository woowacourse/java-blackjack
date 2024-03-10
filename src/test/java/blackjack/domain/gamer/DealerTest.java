package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

class DealerTest {

	Dealer dealer;

	@BeforeEach
	void setUP() {
		dealer = new Dealer();
	}

	@Test
	@DisplayName("카드의 총합이 16 이하이면 카드를 받을 수 있다.")
	void receiveCardTest() {
		dealer.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
		dealer.addCard(new Card(CardShape.HEART, CardNumber.SIX));

		assertThat(dealer.canReceiveCard()).isTrue();
	}

	@Test
	@DisplayName("카드의 총합이 16을 초과하면 카드를 받을 수 없다.")
	void cantReceiveCardTest() {
		dealer.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
		dealer.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

		assertThat(dealer.canReceiveCard()).isFalse();
	}

	@Nested
	@DisplayName("딜러의 승,패 횟수를 계산한다.")
	class DealerResultTest {

		List<GameResult> playerResults;

		@BeforeEach
		void setUP() {
			playerResults = List.of(GameResult.WIN, GameResult.LOSE, GameResult.WIN, GameResult.LOSE, GameResult.WIN);
		}

		@Test
		@DisplayName("딜러의 승리 횟수를 올바르게 계산한다.")
		void calculateDealerWinCountTest() {
			assertThat(dealer.calculateWinCount(playerResults)).isEqualTo(2);
		}

		@Test
		@DisplayName("딜러의 패배 횟수를 올바르게 계산한다.")
		void calculateDealerLoseCountTest() {
			assertThat(dealer.calculateLoseCount(playerResults)).isEqualTo(3);
		}
	}
}

package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

class PlayerTest {

	Player player;
	Dealer dealerWithBust;
	Dealer dealerWithBlackjack;
	Dealer dealerWith21Score;
	Dealer dealerWith17Score;

	@BeforeEach
	void setUP() {
		player = new Player(new Name("hogi"));

		dealerWithBust = new Dealer();
		dealerWithBust.addCard(new Card(CardShape.DIAMOND, CardNumber.KING));
		dealerWithBust.addCard(new Card(CardShape.CLOVER, CardNumber.QUEEN));
		dealerWithBust.addCard(new Card(CardShape.HEART, CardNumber.TWO));

		dealerWithBlackjack = new Dealer();
		dealerWithBlackjack.addCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
		dealerWithBlackjack.addCard(new Card(CardShape.DIAMOND, CardNumber.JACK));

		dealerWith17Score = new Dealer();
		dealerWith17Score.addCard(new Card(CardShape.SPADE, CardNumber.QUEEN));
		dealerWith17Score.addCard(new Card(CardShape.SPADE, CardNumber.SEVEN));

		dealerWith21Score = new Dealer();
		dealerWith21Score.addCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
		dealerWith21Score.addCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));
		dealerWith21Score.addCard(new Card(CardShape.DIAMOND, CardNumber.SIX));
	}

	@Test
	@DisplayName("카드의 총합이 21 이하이면 카드를 받을 수 있다.")
	void receiveCardTest() {
		player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
		player.addCard(new Card(CardShape.HEART, CardNumber.FIVE));

		assertThat(player.canReceiveCard()).isTrue();
	}

	@Test
	@DisplayName("카드의 총합이 21을 초과하면 카드를 받을 수 없다.")
	void cantReceiveCardTest() {
		player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
		player.addCard(new Card(CardShape.HEART, CardNumber.FIVE));
		player.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

		assertThat(player.canReceiveCard()).isFalse();
	}

	@Nested
	@DisplayName("플레이어가 승리한다.")
	class IsWinTest {

		@Test
		@DisplayName("플레이어 점수가 21 이하이고, 딜러의 점수보다 큰 경우 승리한다.")
		void getResult_WithComparingScore() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.FOUR));
			player.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

			assertThat(player.getGameResult(dealerWith17Score)).isEqualTo(GameResult.WIN);
		}

		@Test
		@DisplayName("플레이어 점수가 21 이하이고, 딜러의 점수가 21을 초과하면 승리한다.")
		void getResult_WithDealerBust() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.FIVE));

			assertThat(player.getGameResult(dealerWithBust)).isEqualTo(GameResult.WIN);
		}

		@Test
		@DisplayName("플레이어와 딜러가 모두 21이고, 플레이어만 블랙잭인 경우 승리한다.")
		void getResult_WithPlayerOnlyBlackjack() {
			player.addCard(new Card(CardShape.SPADE, CardNumber.ACE));
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));

			assertThat(player.getGameResult(dealerWith21Score)).isEqualTo(GameResult.WIN);
		}
	}

	@Nested
	@DisplayName("플레이어가 패배한다.")
	class LoseTest {

		@Test
		@DisplayName("점수가 21을 초과하면 딜러의 점수와 무관하게 패배한다")
		void getResult_WithPlayerBust() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.FIVE));
			player.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

			assertThat(player.getGameResult(dealerWith17Score)).isEqualTo(GameResult.LOSE);
			assertThat(player.getGameResult(dealerWithBust)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("플레이어와 딜러의 점수가 모두 21 이하일 때, 딜러가 블랙잭이면 패배한다.")
		void getResult_WithDealerOnlyBlackjack() {
			player.addCard(new Card(CardShape.SPADE, CardNumber.ACE));
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));

			assertThat(player.getGameResult(dealerWithBlackjack)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("플레이어와 딜러의 점수가 모두 21 이하일 때, 딜러의 점수보다 낮으면 패배한다.")
		void getResult_WithComparingScore() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.SIX));

			assertThat(player.getGameResult(dealerWith17Score)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("플레이어와 딜러의 점수가 모두 21 이하이고 동점인 경우 플레이어가 패배한다.")
		void getResult_WithSameScore() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

			assertThat(player.getGameResult(dealerWith17Score)).isEqualTo(GameResult.LOSE);
		}
	}
}

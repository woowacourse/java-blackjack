package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.Dealer;
import domain.Player;
import domain.TestData;

class PlayerResultTest {

	@Nested
	@DisplayName("플레이어는 다음 상황에서 승리하고 양의 수익을 가져간다.")
	class PlayerWin {

		@Test
		@DisplayName("BlackJack으로 승리한 플레이어는 건 돈의 1.5배의 수익을 올려야 한다.")
		void blackJackProfitTest() {
			Player blackJackPlayer = TestData.getBlackJackPlayer();
			Dealer score20Dealer = TestData.getScore20Dealer();

			PlayerResult result = PlayerResult.decide(blackJackPlayer, score20Dealer);

			assertThat(result.getProfit())
				.isEqualTo(blackJackPlayer.getBet() * ResultState.BLACKJACK.getMultiplier());
		}

		@Test
		@DisplayName("높은 점수로 승리한 플레이어는 건 돈 만큼의 수익을 올려야 한다.")
		void playerWinProfitTest() {
			Player score20Player = TestData.getScore20Player();
			Dealer score19Dealer = TestData.getScore19Dealer();

			PlayerResult result = PlayerResult.decide(score20Player, score19Dealer);

			assertThat(result.getProfit())
				.isEqualTo(score20Player.getBet() * ResultState.WIN.getMultiplier());
		}

		@Test
		@DisplayName("플레이어가 blackjack이나 bust가 아니고 딜러가 bust라면 건 돈 만큼의 수익을 올려야 한다.")
		void playerWinByDealerBustProfitTest() {
			Player score20Player = TestData.getScore20Player();
			Dealer bustDealer = TestData.getBustDealer();

			PlayerResult result = PlayerResult.decide(score20Player, bustDealer);

			assertThat(result.getProfit())
				.isEqualTo(score20Player.getBet() * ResultState.WIN.getMultiplier());
		}
	}

	@Nested
	@DisplayName("플레이어는 다음 상황에서 무승부가 되어 0원의 수익을 가진다.")
	class PlayerDraw {

		@Test
		@DisplayName("bust나 blackJack이 아닌 같은 점수로 무승부인 결과에서는 0의 수익을 올려야 한다.")
		public void playerDealerDrawProfitTest() {
			Player score20Player = TestData.getScore20Player();
			Dealer score20Dealer = TestData.getScore20Dealer();

			PlayerResult result = PlayerResult.decide(score20Player, score20Dealer);

			assertThat(result.getProfit())
				.isEqualTo(score20Player.getBet() * ResultState.DRAW.getMultiplier());
		}

		@Test
		@DisplayName("둘 다 BlackJack으로 무승부인 결과에서는 0의 수익을 올려야 한다.")
		public void bothBlackJackProfitTest() {
			Player blackJackPlayer = TestData.getBlackJackPlayer();
			Dealer score20Dealer = TestData.getBlackJackDealer();

			PlayerResult result = PlayerResult.decide(blackJackPlayer, score20Dealer);

			assertThat(result.getProfit())
				.isEqualTo(blackJackPlayer.getBet() * ResultState.DRAW.getMultiplier());
		}

		@Test
		@DisplayName("플레이어와 딜러가 모두 bust인 결과에서는 무승부 처리되어 0의 수익을 올려야 한다.")
		public void playerBustDealerBustDrawProfitTest() {
			Player score20Player = TestData.getScore20Player();
			Dealer score20Dealer = TestData.getScore20Dealer();

			PlayerResult result = PlayerResult.decide(score20Player, score20Dealer);

			assertThat(result.getProfit())
				.isEqualTo(score20Player.getBet() * ResultState.DRAW.getMultiplier());
		}
	}

	@Nested
	@DisplayName("플레이어는 다음 상황에서 패배하여 건 돈 만큼의 손실이 발생한다.")
	class PlayerDefeat {

		@Test
		@DisplayName("플레이어만 bust라면 건 돈 만큼의 손실이 발생한다.")
		public void playerOnlyBustProfitTest() {
			Player bustPlayer = TestData.getBustPlayer();
			Dealer score20Dealer = TestData.getScore20Dealer();

			PlayerResult result = PlayerResult.decide(bustPlayer, score20Dealer);

			assertThat(result.getProfit())
				.isEqualTo(bustPlayer.getBet() * ResultState.DEFEAT.getMultiplier());
		}

		@Test
		@DisplayName("플레이어가 딜러보다 점수가 낮으며 둘 다 bust가 아니라면 건 돈 만큼의 손실이 발생한다.")
		public void playerDefeatProfitTest() {
			Player score19Player = TestData.getScore19Player();
			Dealer score20Dealer = TestData.getScore20Dealer();

			PlayerResult result = PlayerResult.decide(score19Player, score20Dealer);

			assertThat(result.getProfit())
				.isEqualTo(score19Player.getBet() * ResultState.DEFEAT.getMultiplier());
		}

		@Test
		@DisplayName("플레이어가 추가 히트로 21점이 되어도 딜러가 blackJack이라면 건 돈 만큼의 손실이 발생한다.")
		public void sameScoreButDealerBlackJackProfitTest() {
			Player score21Player = TestData.getScore21Player();
			Dealer blackJackDealer = TestData.getBlackJackDealer();

			PlayerResult result = PlayerResult.decide(score21Player, blackJackDealer);

			assertThat(result.getProfit())
				.isEqualTo(score21Player.getBet() * ResultState.DEFEAT.getMultiplier());
		}
	}
}

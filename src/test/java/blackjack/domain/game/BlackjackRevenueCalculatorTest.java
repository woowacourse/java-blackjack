package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

class BlackjackRevenueCalculatorTest {

	@Nested
	@DisplayName("딜러의 수익을 계산한다.")
	class DealerRevenueTest {

		BlackjackRevenueCalculator calculator;

		@BeforeEach
		void setUp() {
			Dealer dealer = new Dealer(List.of(
				new Card(CardShape.SPADE, CardNumber.NINE),
				new Card(CardShape.HEART, CardNumber.SIX),
				new Card(CardShape.DIAMOND, CardNumber.SIX)
			));
			calculator = BlackjackRevenueCalculator.fromDealer(dealer);
		}

		@Test
		@DisplayName("딜러는 패배한 플레이어의 베팅 금액만큼 수익을 얻는다.")
		void calculateDealerRevenue_WhenPlayerWin() {
			Player winPlayer = new Player("win", List.of(
				new Card(CardShape.DIAMOND, CardNumber.ACE),
				new Card(CardShape.SPADE, CardNumber.KING)
			));
			Player drawPlayer = new Player("draw", List.of(
				new Card(CardShape.SPADE, CardNumber.EIGHT),
				new Card(CardShape.DIAMOND, CardNumber.SEVEN),
				new Card(CardShape.HEART, CardNumber.SIX)
			));
			Player losePlayer = new Player("lose", List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.KING)
			));

			Players players = new Players(Map.of(
				winPlayer, new Money(10000),
				drawPlayer, new Money(20000),
				losePlayer, new Money(30000)
			));

			Assertions.assertThat(calculator.calculateDealerRevenue(players))
				.isEqualTo(new Money(30000));
		}
	}

	@Nested
	@DisplayName("플레이어의 결과를 바탕으로 수익을 계산한다.")
	class PlayerRevenueTest {

		BlackjackRevenueCalculator calculator;
		Player player;
		Money betAmount;

		@BeforeEach
		void setUp() {
			Dealer dealer = new Dealer(List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.QUEEN)
			));
			PlayerResultHandler handler = new PlayerResultHandler(dealer);
			calculator = new BlackjackRevenueCalculator(handler);
			betAmount = new Money(10000);
		}

		@Test
		@DisplayName("블랙잭 승리인 경우의 수익금은 베팅 금액의 1.5배이다.")
		void getReward_WhenBlackjackWin() {
			player = createTestPlayer(List.of(
				new Card(CardShape.SPADE, CardNumber.ACE),
				new Card(CardShape.DIAMOND, CardNumber.QUEEN)
			));

			assertThat(calculator.calculatePlayerRevenue(player, betAmount)).isEqualTo(new Money(15000));
		}

		@Test
		@DisplayName("블랙잭이 아닌 승리인 경우의 수익금은 베팅 금액만큼이다.")
		void getReward_WhenNotBlackjackWin() {
			player = createTestPlayer(List.of(
				new Card(CardShape.SPADE, CardNumber.EIGHT),
				new Card(CardShape.DIAMOND, CardNumber.SEVEN),
				new Card(CardShape.CLOVER, CardNumber.SIX)
			));

			assertThat(calculator.calculatePlayerRevenue(player, betAmount)).isEqualTo(new Money(10000));
		}

		@Test
		@DisplayName("무승부인 경우의 수익금은 0원이다.")
		void getReward_WhenDraw() {
			player = createTestPlayer(List.of(
				new Card(CardShape.SPADE, CardNumber.JACK),
				new Card(CardShape.DIAMOND, CardNumber.TEN)
			));

			assertThat(calculator.calculatePlayerRevenue(player, betAmount)).isEqualTo(new Money(0));
		}

		@Test
		@DisplayName("패배한 경우 베팅 금액만큼의 손실이 발생한다.")
		void getReward_WhenLose() {
			player = createTestPlayer(List.of(
				new Card(CardShape.SPADE, CardNumber.JACK),
				new Card(CardShape.DIAMOND, CardNumber.NINE)
			));

			assertThat(calculator.calculatePlayerRevenue(player, betAmount)).isEqualTo(new Money(-10000));
		}

		private Player createTestPlayer(List<Card> cards) {
			return new Player("test", cards);
		}
	}
}

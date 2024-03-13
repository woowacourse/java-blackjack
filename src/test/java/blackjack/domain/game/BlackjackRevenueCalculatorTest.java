package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;

class BlackjackRevenueCalculatorTest {

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
		return new Player(new Name("test"), cards);
	}
}

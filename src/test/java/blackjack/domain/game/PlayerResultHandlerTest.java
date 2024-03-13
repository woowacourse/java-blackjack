package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;

class PlayerResultHandlerTest {

	PlayerResultHandler handler;
	Player player;
	Dealer dealer;

	@Nested
	@DisplayName("플레이어가 블랙잭으로 승리한다.")
	class PlayerBlackjackWinTest {
		@BeforeEach
		void setUp() {
			player = createTestPlayer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.SPADE, CardNumber.ACE)
			));
		}

		@Test
		@DisplayName("딜러가 버스트된다.")
		void getResult_WithDealerBust() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.TWO)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.BLACKJACK_WIN);
		}

		@Test
		@DisplayName("딜러가 블랙잭이 아니다.")
		void getResult_WithDealerNotBlackjack() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.ACE)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.BLACKJACK_WIN);
		}
	}

	@Nested
	@DisplayName("플레이어가 블랙잭은 아니지만 승리한다.")
	class PlayerWinTest {
		@BeforeEach
		void setUp() {
			player = createTestPlayer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.SPADE, CardNumber.KING)
			));
		}

		@Test
		@DisplayName("딜러가 버스트된다.")
		void getResult_WithDealerBust() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.TWO)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.WIN);
		}

		@Test
		@DisplayName("딜러보다 점수가 높다.")
		void getResult_WithDealerNotBlackjack() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.NINE)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.WIN);
		}
	}

	@Nested
	@DisplayName("플레이어가 패배한다.")
	class DealerWinTest {
		@Test
		@DisplayName("딜러와 플레이어 모두 버스트된다.")
		void getResult_WhenAllBusted() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.DIAMOND, CardNumber.TWO)
			));

			player = createTestPlayer(List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.TWO)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("플레이어만 버스트된다.")
		void getResult_WhenPlayerOnlyBusted() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.KING)
			));

			player = createTestPlayer(List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.TWO)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("딜러만 블랙잭이다.")
		void getResult_WhenDealerOnlyBlackjack() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.ACE)
			));

			player = createTestPlayer(List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.ACE)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("딜러의 점수가 플레이어의 점수보다 높다.")
		void getResult_WhenDealerScoreLargerThanPlayers() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.JACK)
			));

			player = createTestPlayer(List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.NINE)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
		}
	}

	@Nested
	@DisplayName("딜러와 플레이어가 무승부이다.")
	class DrawTest {
		@Test
		@DisplayName("딜러와 플레이어 모두 블랙잭이다.")
		void getResult_WhenAllBlackjack() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.ACE)
			));

			player = createTestPlayer(List.of(
				new Card(CardShape.CLOVER, CardNumber.JACK),
				new Card(CardShape.HEART, CardNumber.ACE)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.DRAW);
		}

		@Test
		@DisplayName("딜러와 플레이어의 점수가 동일하다.")
		void getResult_WhenSameScore() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.KING)
			));

			player = createTestPlayer(List.of(
				new Card(CardShape.CLOVER, CardNumber.JACK),
				new Card(CardShape.HEART, CardNumber.TEN)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.notifyResultToPlayer(player)).isEqualTo(GameResult.DRAW);
		}
	}

	private Player createTestPlayer(List<Card> cards) {
		return new Player(new Name("test"), cards);
	}
}

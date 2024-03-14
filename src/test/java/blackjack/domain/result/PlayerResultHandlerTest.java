package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

class PlayerResultHandlerTest {

	PlayerResultHandler handler;
	Player player;
	Dealer dealer;

	@Test
	@DisplayName("패배한 플레이어 리스트를 얻는다.")
	void getLosePlayerTest() {
		dealer = new Dealer(List.of(
			new Card(Shape.SPADE, Number.QUEEN),
			new Card(Shape.CLOVER, Number.NINE),
			new Card(Shape.HEART, Number.TWO)
		));
		PlayerResultHandler resultHandler = new PlayerResultHandler(dealer);

		Player winPlayer = createTestPlayer(List.of(
			new Card(Shape.DIAMOND, Number.ACE),
			new Card(Shape.SPADE, Number.KING)
		));
		Player drawPlayer = createTestPlayer(List.of(
			new Card(Shape.SPADE, Number.EIGHT),
			new Card(Shape.DIAMOND, Number.SEVEN),
			new Card(Shape.HEART, Number.SIX)
		));
		Player losePlayer = createTestPlayer(List.of(
			new Card(Shape.HEART, Number.QUEEN),
			new Card(Shape.HEART, Number.KING)
		));

		List<Player> players = List.of(winPlayer, drawPlayer, losePlayer);

		assertThat(resultHandler.getLosePlayers(players))
			.containsExactly(losePlayer);
	}

	@Nested
	@DisplayName("플레이어가 블랙잭으로 승리한다.")
	class PlayerBlackjackWinTest {
		@BeforeEach
		void setUp() {
			player = createTestPlayer(List.of(
				new Card(Shape.DIAMOND, Number.KING),
				new Card(Shape.SPADE, Number.ACE)
			));
		}

		@Test
		@DisplayName("딜러가 버스트된다.")
		void getResult_WithDealerBust() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.KING),
				new Card(Shape.HEART, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.TWO)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.BLACKJACK_WIN);
		}

		@Test
		@DisplayName("딜러가 블랙잭이 아니다.")
		void getResult_WithDealerNotBlackjack() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.KING),
				new Card(Shape.HEART, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.ACE)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.BLACKJACK_WIN);
		}
	}

	@Nested
	@DisplayName("플레이어가 블랙잭은 아니지만 승리한다.")
	class PlayerWinTest {
		@BeforeEach
		void setUp() {
			player = createTestPlayer(List.of(
				new Card(Shape.DIAMOND, Number.KING),
				new Card(Shape.SPADE, Number.KING)
			));
		}

		@Test
		@DisplayName("딜러가 버스트된다.")
		void getResult_WithDealerBust() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.KING),
				new Card(Shape.HEART, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.TWO)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.WIN);
		}

		@Test
		@DisplayName("딜러보다 점수가 높다.")
		void getResult_WithDealerNotBlackjack() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.KING),
				new Card(Shape.HEART, Number.NINE)
			));
			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.WIN);
		}
	}

	@Nested
	@DisplayName("플레이어가 패배한다.")
	class DealerWinTest {
		@Test
		@DisplayName("딜러와 플레이어 모두 버스트된다.")
		void getResult_WhenAllBusted() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.KING),
				new Card(Shape.DIAMOND, Number.TWO)
			));

			player = createTestPlayer(List.of(
				new Card(Shape.HEART, Number.QUEEN),
				new Card(Shape.HEART, Number.KING),
				new Card(Shape.HEART, Number.TWO)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("플레이어만 버스트된다.")
		void getResult_WhenPlayerOnlyBusted() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.KING)
			));

			player = createTestPlayer(List.of(
				new Card(Shape.HEART, Number.QUEEN),
				new Card(Shape.HEART, Number.KING),
				new Card(Shape.HEART, Number.TWO)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("딜러만 블랙잭이다.")
		void getResult_WhenDealerOnlyBlackjack() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.ACE)
			));

			player = createTestPlayer(List.of(
				new Card(Shape.HEART, Number.QUEEN),
				new Card(Shape.HEART, Number.KING),
				new Card(Shape.HEART, Number.ACE)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("딜러의 점수가 플레이어의 점수보다 높다.")
		void getResult_WhenDealerScoreLargerThanPlayers() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.JACK)
			));

			player = createTestPlayer(List.of(
				new Card(Shape.HEART, Number.QUEEN),
				new Card(Shape.HEART, Number.NINE)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.LOSE);
		}
	}

	@Nested
	@DisplayName("딜러와 플레이어가 무승부이다.")
	class DrawTest {
		@Test
		@DisplayName("딜러와 플레이어 모두 블랙잭이다.")
		void getResult_WhenAllBlackjack() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.ACE)
			));

			player = createTestPlayer(List.of(
				new Card(Shape.CLOVER, Number.JACK),
				new Card(Shape.HEART, Number.ACE)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.DRAW);
		}

		@Test
		@DisplayName("딜러와 플레이어의 점수가 동일하다.")
		void getResult_WhenSameScore() {
			dealer = new Dealer(List.of(
				new Card(Shape.DIAMOND, Number.QUEEN),
				new Card(Shape.DIAMOND, Number.KING)
			));

			player = createTestPlayer(List.of(
				new Card(Shape.CLOVER, Number.JACK),
				new Card(Shape.HEART, Number.TEN)
			));

			handler = new PlayerResultHandler(dealer);

			assertThat(handler.getPlayerResult(player)).isEqualTo(GameResult.DRAW);
		}
	}

	private Player createTestPlayer(List<Card> cards) {
		return new Player("test", cards);
	}
}

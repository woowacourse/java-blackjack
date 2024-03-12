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

	Name playerName = new Name("test");
	Dealer dealer;
	Player player;

	@Test
	@DisplayName("카드의 총합이 16 이하이면 카드를 받을 수 있다.")
	void receiveCardTest() {
		dealer = new Dealer(List.of(
			new Card(CardShape.CLOVER, CardNumber.KING),
			new Card(CardShape.HEART, CardNumber.SIX)
		));

		assertThat(dealer.canReceiveCard()).isTrue();
	}

	@Test
	@DisplayName("카드의 총합이 16을 초과하면 카드를 받을 수 없다.")
	void cantReceiveCardTest() {
		dealer = new Dealer(List.of(
			new Card(CardShape.CLOVER, CardNumber.KING),
			new Card(CardShape.HEART, CardNumber.SEVEN)
		));

		assertThat(dealer.canReceiveCard()).isFalse();
	}

	@Nested
	@DisplayName("플레이어가 블랙잭으로 승리한다.")
	class PlayerBlackjackWinTest {
		@BeforeEach
		void setUp() {
			player = new Player(playerName, List.of(
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

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.BLACKJACK_WIN);
		}

		@Test
		@DisplayName("딜러가 블랙잭이 아니다.")
		void getResult_WithDealerNotBlackjack() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.ACE)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.BLACKJACK_WIN);
		}
	}

	@Nested
	@DisplayName("플레이어가 블랙잭은 아니지만 승리한다.")
	class PlayerWinTest {
		@BeforeEach
		void setUp() {
			player = new Player(playerName, List.of(
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

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.WIN);
		}

		@Test
		@DisplayName("딜러보다 점수가 높다.")
		void getResult_WithDealerNotBlackjack() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.NINE)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.WIN);
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

			player = new Player(playerName, List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.TWO)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("플레이어만 버스트된다.")
		void getResult_WhenPlayerOnlyBusted() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.KING)
			));

			player = new Player(playerName, List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.TWO)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("딜러만 블랙잭이다.")
		void getResult_WhenDealerOnlyBlackjack() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.ACE)
			));

			player = new Player(playerName, List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.KING),
				new Card(CardShape.HEART, CardNumber.ACE)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
		}

		@Test
		@DisplayName("딜러의 점수가 플레이어의 점수보다 높다.")
		void getResult_WhenDealerScoreLargerThanPlayers() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.JACK)
			));

			player = new Player(playerName, List.of(
				new Card(CardShape.HEART, CardNumber.QUEEN),
				new Card(CardShape.HEART, CardNumber.NINE)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.LOSE);
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

			player = new Player(playerName, List.of(
				new Card(CardShape.CLOVER, CardNumber.JACK),
				new Card(CardShape.HEART, CardNumber.ACE)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.DRAW);
		}

		@Test
		@DisplayName("딜러와 플레이어의 점수가 동일하다.")
		void getResult_WhenSameScore() {
			dealer = new Dealer(List.of(
				new Card(CardShape.DIAMOND, CardNumber.QUEEN),
				new Card(CardShape.DIAMOND, CardNumber.KING)
			));

			player = new Player(playerName, List.of(
				new Card(CardShape.CLOVER, CardNumber.JACK),
				new Card(CardShape.HEART, CardNumber.TEN)
			));

			assertThat(dealer.notifyResultToPlayer(player)).isEqualTo(GameResult.DRAW);
		}
	}
}

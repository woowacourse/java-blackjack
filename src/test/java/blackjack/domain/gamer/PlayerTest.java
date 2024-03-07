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

	@BeforeEach
	void setUP() {
		player = new Player(new Name("hogi"));
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
		void bustTest() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.FOUR));
			player.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

			assertThat(player.isWin(20)).isTrue();
		}

		@Test
		@DisplayName("플레이어 점수가 21 이하이고, 딜러의 점수가 21을 초과하면 승리한다.")
		void bustTest1() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.FIVE));

			assertThat(player.isWin(22)).isTrue();
		}
	}

	@Nested
	@DisplayName("플레이어가 패배한다.")
	class LoseTest {

		@Test
		@DisplayName("점수가 21을 초과하면 딜러의 점수와 무관하게 패배한다")
		void bustTest2() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.FIVE));
			player.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

			assertThat(player.isWin(21)).isFalse();
			assertThat(player.isWin(22)).isFalse();
		}

		@Test
		@DisplayName("플레이어와 딜러의 점수가 모두 21 이하일 때, 딜러의 점수보다 낮으면 패배한다.")
		void bustTest3() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.SIX));

			assertThat(player.isWin(17)).isFalse();
		}

		@Test
		@DisplayName("플레이어와 딜러의 점수가 모두 21 이하이고 동점인 경우 플레이어가 패배한다.")
		void bustTest4() {
			player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
			player.addCard(new Card(CardShape.HEART, CardNumber.SEVEN));

			assertThat(player.isWin(17)).isFalse();
		}
	}
}

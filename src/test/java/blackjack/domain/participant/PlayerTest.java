package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Hit;
import blackjack.domain.state.StateFactory;

public class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player("pobi", 0);
		Card first = new Card(CardPattern.CLOVER, CardNumber.FIVE);
		Card second = new Card(CardPattern.HEART, CardNumber.THREE);
		player.playerState = StateFactory.drawTwoCards(first, second);
	}

	@Test
	@DisplayName("이름에 공백 입력 경우 예외 처리")
	void playerNameSplitException() {
		String input = "pobi, jason";
		assertThatThrownBy(() -> {
			for (String name : input.split(",")) {
				new Player(name, 0);
			}
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("플레이어 점수 계산")
	void checkReceiveCard() {
		assertEquals(8, player.playerState.calculatePoint());
	}

	@Test
	@DisplayName("플레이어가 카드를 받을 수 있는지 확인")
	void playerPossibleReceiveCard() {
		assertTrue(player.canReceiveCard(true));
	}

	@Test
	@DisplayName("플레이어가 카드를 받을 수 없는 경우 확인")
	void playerImpossibleReceiveCard() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
		player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));

		assertFalse(player.canReceiveCard(false));
	}

	@Test
	@DisplayName("플레이어가 힛인 경우 매칭 확인")
	void playerIsHit() {
		assertThat(player.playerState).isInstanceOf(Hit.class);
	}

	@Test
	@DisplayName("플레이어가 버스트인 경우 매칭 확인")
	void playerIsBust() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
		player.receiveCard(new Card(CardPattern.HEART, CardNumber.KING));
		assertThat(player.playerState).isInstanceOf(Bust.class);
	}

	@Test
	@DisplayName("에이스가 11이어야할 때 계산 확인")
	void aceCardEleven() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
		player.canReceiveCard(false);
		assertEquals(19, player.calculatePoint());
	}

	@Test
	@DisplayName("에이스가 1이어야할 때 계산 확인")
	void aceCardOne() {
		player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TWO));
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
		player.canReceiveCard(false);
		assertEquals(21, player.calculatePoint());
	}

	@Test
	@DisplayName("딜러가 승리할 때 결과 매칭 테스트")
	void playerLoseResult() {
		Dealer dealer = new Dealer();
		dealer.playerState = StateFactory.drawTwoCards(
			new Card(CardPattern.DIAMOND, CardNumber.NINE),
			new Card(CardPattern.DIAMOND, CardNumber.TEN));
		player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
		player.canReceiveCard(false);
		player.makeMoney(1000);
		player.calculateProfit(dealer.playerState);
		assertEquals(-1000, player.getMoney());

	}

	@Test
	@DisplayName("딜러와 비길 때 결과 매칭 테스트")
	void playerDrawResult() {
		Dealer dealer = new Dealer();
		dealer.playerState = StateFactory.drawTwoCards(
			new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
			new Card(CardPattern.DIAMOND, CardNumber.TEN));
		player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
		player.canReceiveCard(false);
		player.makeMoney(1000);
		player.calculateProfit(dealer.playerState);
		assertEquals(0, player.getMoney());
	}

	@Test
	@DisplayName("딜러가 패할 때 결과 매칭 테스트")
	void playerWinResult() {
		Dealer dealer = new Dealer();
		dealer.playerState = StateFactory.drawTwoCards(
			new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
			new Card(CardPattern.DIAMOND, CardNumber.TEN));
		player.receiveCard(new Card(CardPattern.HEART, CardNumber.ACE));
		player.canReceiveCard(false);
		player.makeMoney(1000);
		player.calculateProfit(dealer.playerState);
		assertEquals(1000, player.getMoney());
	}
}

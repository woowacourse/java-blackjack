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
import blackjack.domain.state.StateFactory;

public class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player("pobi", 0);
		Card first = new Card(CardPattern.CLOVER, CardNumber.KING);
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
		assertEquals(13, player.playerState.calculatePoint());
	}

	@Test
	@DisplayName("플레이어가 카드를 받을 수 있는지 확인")
	void playerPossibleReceiveCard() {
		assertTrue(player.canReceiveCard(true));
	}

	@Test
	@DisplayName("플레이어가 카드를 받을 수 없는지 확인")
	void playerImpossibleReceiveCard() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
		assertFalse(player.canReceiveCard(false));
	}

	@Test
	@DisplayName("플레이어가 버스트지 확인")
	void playerIsBust() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
		assertThat(player.playerState).isInstanceOf(Bust.class);
	}

	@Test
	@DisplayName("에이스가 1이어야할 때")
	void aceCardBoundary() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
		assertEquals(14, player.calculatePoint());
	}

	@Test
	@DisplayName("버스트 나는 결과 매칭 테스트")
	void matchResult() {
		Dealer dealer = new Dealer();
		dealer.playerState = StateFactory.drawTwoCards(new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
			new Card(CardPattern.DIAMOND, CardNumber.TEN));
		player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
		player.makeMoney(1000);
		player.calculateProfit(dealer);
		assertEquals(-1000, player.getMoney());
	}
}

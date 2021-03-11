package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.state.Burst;
import blackjack.domain.state.StateFactory;

public class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player("pobi");
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
				new Player(name);
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
		assertTrue(player.canReceiveCard());
	}

	@Test
	@DisplayName("플레이어가 카드를 받을 수 없는지 확인")
	void playerImpossibleReceiveCard() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
		assertFalse(player.canReceiveCard());
	}

	@Test
	@DisplayName("플레이어가 버스트지 확인")
	void playerIsBurst() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
		assertThat(player.playerState).isInstanceOf(Burst.class);
	}

	@Test
	@DisplayName("에이스가 1이어야할 때")
	void aceCardBoundary() {
		player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
		assertEquals(14, player.calculatePoint());
	}

	@ParameterizedTest
	@DisplayName("다시 받을 때 올바른 입력 확인")
	@CsvSource(value = {"y:true", "n:false"}, delimiter = ':')
	void playerContinueDraw(String value, boolean expected) {
		assertEquals(expected, player.continueDraw(value, new Deck()));
	}

	@Test
	@DisplayName("다시 받을 때 예외 처리")
	void playerStopDraw() {
		assertThatThrownBy(() -> player.continueDraw("l", new Deck())).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("y");
	}
	//TODO: 결과 처리
	// @Test
	// @DisplayName("결과 매칭 테스트")
	// void matchResult() {
	// 	Dealer dealer = new Dealer();
	// 	Player player = new Player("pobi");
	// 	dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.NINE));
	// 	player.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));
	// 	player.calculateResult(dealer);
	// 	assertEquals(WinnerFlag.LOSE, player.getResult());
	// }
}

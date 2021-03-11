package blackjack.domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.state.StateFactory;
import blackjack.domain.state.Stay;

public class DealerTest {
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		dealer = new Dealer();
	}

	@Test
	@DisplayName("딜러 생성 확인")
	void create() {
		assertEquals("딜러", dealer.getName());
	}

	@Test
	@DisplayName("딜러가 받은 카드 계산")
	void dealerDealCard() {
		dealer.playerState = StateFactory.drawTwoCards((new Card(CardPattern.CLOVER, CardNumber.KING)),
			new Card(CardPattern.HEART, CardNumber.SEVEN));
		dealer.playerState = new Stay(dealer.playerState.cards());
		assertEquals(17, dealer.playerState.calculatePoint());
	}

	@Test
	@DisplayName("딜러가 카드를 받을 수 있는지 확인")
	void dealerPossibleReceiveCard() {
		dealer.playerState = StateFactory.drawTwoCards((new Card(CardPattern.CLOVER, CardNumber.KING)),
			new Card(CardPattern.HEART, CardNumber.TWO));
		assertTrue(dealer.canReceiveCard());    //TODO: ace 들어가면 뭔가 이상
	}

	@Test
	@DisplayName("딜러가 카드를 받을 수 없는지 확인")
	void dealerImpossibleReceiveCard() {
		dealer.playerState = StateFactory.drawTwoCards((new Card(CardPattern.CLOVER, CardNumber.KING)),
			new Card(CardPattern.HEART, CardNumber.SEVEN));
		dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));
		System.out.println(dealer.playerState.calculatePoint());
		assertFalse(dealer.canReceiveCard());
	}
}

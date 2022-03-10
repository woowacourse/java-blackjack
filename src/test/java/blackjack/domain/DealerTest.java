package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

	@Test
	@DisplayName("카드 한 장을 받는다.")
	void getCard() {
		Dealer dealer = initDealer();

		assertThat(dealer.showCards().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("카드를 한 장만 공개한다.")
	void openCard() {
		Dealer dealer = initDealer();
		assertThat(dealer.openCards()).contains(new Card(Suit.CLOVER, Denomination.FIVE));
	}

	@Test
	@DisplayName("카드를 모두 공개한다.")
	void showAllCards() {
		Dealer dealer = initDealer();
		dealer.receiveCard(new Card(Suit.CLOVER, Denomination.SIX));

		assertThat(dealer.showCards()).contains(new Card(Suit.CLOVER, Denomination.FIVE),
			new Card(Suit.CLOVER, Denomination.SIX));
	}

	@Test
	@DisplayName("딜러가 보유하는 카드의 점수를 계산한다.")
	void calculateDealerPoint() {
		Dealer dealer = initDealer();
		assertThat(dealer.calculateResult()).isEqualTo(5);
	}

	@Test
	@DisplayName("딜러가 추가 카드를 받을 수 있다.")
	void isReceivableStandardTrue() {
		Dealer dealer = initDealer();
		dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.ACE));

		assertTrue(dealer.isReceivable());
	}

	@Test
	@DisplayName("딜러가 추가 카드를 받을 수 없다.")
	void isReceivableStandardFalse() {
		Dealer dealer = initDealer();
		dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
		dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

		assertFalse(dealer.isReceivable());
	}

	private Dealer initDealer() {
		Dealer dealer = new Dealer();
		dealer.receiveCard(new Card(Suit.CLOVER, Denomination.FIVE));
		return dealer;
	}
}

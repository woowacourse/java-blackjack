package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class DealerTest {
	@Test
	@DisplayName("16이하의 경우 추가 카드를 받을 수 있는지")
	void canReceiveMore() {
		Dealer dealer = new Dealer();
		dealer.hit(new Card(Symbol.FIVE, Type.DIAMOND));
		dealer.hit(new Card(Symbol.SEVEN, Type.CLUB));
		dealer.hit(new Card(Symbol.FOUR, Type.DIAMOND));

		assertThat(dealer.canHit()).isTrue();
	}

	@Test
	@DisplayName("16초과의 경우 추가 카드를 받을 수 없는지")
	void canNotReceiveMore() {
		Dealer dealer = new Dealer();
		dealer.hit(new Card(Symbol.FIVE, Type.DIAMOND));
		dealer.hit(new Card(Symbol.EIGHT, Type.CLUB));
		dealer.hit(new Card(Symbol.FOUR, Type.DIAMOND));

		assertThat(dealer.canHit()).isFalse();
	}

	@Test
	@DisplayName("딜러가 처음에 1장의 패를 공개하는지 테스트")
	void firstOpenedCardsTest() {
		Dealer dealer = new Dealer();
		dealer.hit(new Card(Symbol.EIGHT, Type.DIAMOND));
		dealer.hit(new Card(Symbol.NINE, Type.DIAMOND));

		assertThat(dealer.firstOpenedCards()).hasSize(1);
	}
}
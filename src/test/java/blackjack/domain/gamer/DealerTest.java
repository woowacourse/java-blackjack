package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;

class DealerTest {
	Dealer dealer;

	@Test
	@DisplayName("카드의 총합이 16 이하이면 카드를 받을 수 있다.")
	void receiveCardTest() {
		dealer = new Dealer(List.of(
			new Card(Shape.CLOVER, Number.KING),
			new Card(Shape.HEART, Number.SIX)
		));

		assertThat(dealer.canReceiveCard()).isTrue();
	}

	@Test
	@DisplayName("카드의 총합이 16을 초과하면 카드를 받을 수 없다.")
	void cantReceiveCardTest() {
		dealer = new Dealer(List.of(
			new Card(Shape.CLOVER, Number.KING),
			new Card(Shape.HEART, Number.SEVEN)
		));

		assertThat(dealer.canReceiveCard()).isFalse();
	}
}

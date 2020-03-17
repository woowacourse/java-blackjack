package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerTest {

	@DisplayName("딜러가 두장의 카드를 가지고 있을 경우 openCard()를 하면 처음 카드 반환 확인")
	@Test
	void openCard_When_Dealer_Has_Ace_Heart_And_Two_Heart_Return_Former_card() {
		Dealer dealer = new Dealer();
		Card card1 = new Card(Symbol.ACE, Type.HEART);
		Card card2 = new Card(Symbol.TWO, Type.HEART);

		dealer.addCard(card1);
		dealer.addCard(card2);

		assertEquals(card1, dealer.openCard());
	}
}

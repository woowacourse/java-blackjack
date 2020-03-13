package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/13
 */
public class DealerTest {

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

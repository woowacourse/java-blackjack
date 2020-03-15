package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

	@Test
	void addCard() {
		Card card = new Card(Symbol.ACE, Type.CLOVER);
		User user = User.getInstance();
		user.receive(card);

		assertEquals(user, User.of(Collections.singletonList(card)));
	}

	@Test
	void isNotBlackjack_When_User_Has_Blackjack_Return_False() {
		User user = User.of(List.of(new Card(Symbol.KING, Type.CLOVER), new Card(Symbol.ACE, Type.CLOVER)));
		assertFalse(user.isNotBlackJack());
	}

	@Test
	void isNotBlackjack_When_User_Has_Blackjack_Score_But_Size_Is_Three_Return_True() {
		User user = User.of(List.of(
			new Card(Symbol.NINE, Type.CLOVER),
			new Card(Symbol.ACE, Type.CLOVER),
			new Card(Symbol.ACE, Type.DIAMOND))
		);
		assertTrue(user.isNotBlackJack());
	}
}

package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @DisplayName("블랙잭인 경우를 판별한다. - 참")
    @Test
    void isBlackJackTrueTest() {
        User user = new Player("youngE");
        user.draw(new Card(Suit.SPADE, Denomination.ACE));
        user.draw(new Card(Suit.SPADE, Denomination.JACK));

        assertTrue(user.isBlackJack());
    }

    @DisplayName("블랙잭인 경우를 판별한다. - 거짓")
    @Test
    void isBlackJackFalseTest() {
        User user = new Player("youngE");
        user.draw(new Card(Suit.SPADE, Denomination.ACE));
        user.draw(new Card(Suit.SPADE, Denomination.NINE));

        assertFalse(user.isBlackJack());
    }
}
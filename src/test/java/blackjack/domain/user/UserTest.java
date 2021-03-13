package blackjack.domain.user;

import blackjack.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static blackjack.domain.Fixture.*;

class UserTest {
    private final Money defaultMoney = Money.of(0);

    @DisplayName("블랙잭인 경우를 판별한다. - 참")
    @Test
    void isBlackJackTrueTest() {
        User user = new Player(Name.of("youngE"), defaultMoney);
        user.draw(ace);
        user.draw(jack);

        assertTrue(user.isBlackJack());
    }

    @DisplayName("블랙잭인 경우를 판별한다. - 거짓")
    @Test
    void isBlackJackFalseTest() {
        User user = new Player(Name.of("youngE"), defaultMoney);
        user.draw(ace);
        user.draw(nine);

        assertFalse(user.isBlackJack());
    }
}
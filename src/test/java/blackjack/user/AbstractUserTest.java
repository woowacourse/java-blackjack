package blackjack.user;

import blackjack.card.Card;
import blackjack.card.Score;
import blackjack.card.Symbol;
import blackjack.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractUserTest {
    User user;

    @BeforeEach
    void setUp() {
        user = Dealer.create();
    }
    @Test
    void create() {
        assertThat(user).isNotNull();
    }

    @Test
    void append() {
        user.append(new Card(Symbol.ACE, Type.CLUB));
        user.append(new Card(Symbol.EIGHT, Type.HEART));

        assertThat(user.getCards())
                .isEqualTo(Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
                        new Card(Symbol.EIGHT, Type.HEART)));
    }

    @Test
    void sumScore() {
        user.append(new Card(Symbol.TWO, Type.CLUB));
        user.append(new Card(Symbol.EIGHT, Type.HEART));

        assertThat(user.calculateScore()).isEqualTo(new Score(10));
    }

    @Test
    void sumScore_WhenAceShouldBeEleven() {
        user.append(new Card(Symbol.ACE, Type.CLUB));
        user.append(new Card(Symbol.EIGHT, Type.HEART));

        assertThat(user.calculateScore()).isEqualTo(new Score(19));
    }

    @Test
    void sumScore_WhenAceShouldBeOne() {
        user.append(new Card(Symbol.ACE, Type.CLUB));
        user.append(new Card(Symbol.EIGHT, Type.HEART));
        user.append(new Card(Symbol.THREE, Type.SPADE));

        assertThat(user.calculateScore()).isEqualTo(new Score(12));
    }

    @Test
    void isBust_ShouldReturnTrue() {
        user.append(new Card(Symbol.TEN, Type.CLUB));
        user.append(new Card(Symbol.EIGHT, Type.HEART));
        user.append(new Card(Symbol.FOUR, Type.SPADE));

        assertThat(user.isBust()).isTrue();
    }

    @Test
    void isBust_ShouldReturnFalse() {
        user.append(new Card(Symbol.TEN, Type.CLUB));
        user.append(new Card(Symbol.EIGHT, Type.HEART));
        user.append(new Card(Symbol.THREE, Type.SPADE));

        assertThat(user.isBust()).isFalse();
    }
}

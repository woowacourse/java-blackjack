package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
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
//
//    @Test
//    void giveCards() {
//        user.drawCards(new Card(Symbol.ACE, Type.CLUB),
//                new Card(Symbol.EIGHT, Type.HEART));
//
//        assertThat(user.getCards())
//                .isEqualTo(Arrays.asList(new Card(Symbol.ACE, Type.CLUB),
//                        new Card(Symbol.EIGHT, Type.HEART)));
//    }
//
//    @Test
//    void sumScore() {
//        user.drawCards(new Card(Symbol.TWO, Type.CLUB),
//                new Card(Symbol.EIGHT, Type.HEART));
//
//        assertThat(user.calculateScore()).isEqualTo(new Score(10));
//    }
//
//    @Test
//    void sumScore_WhenAceShouldBeEleven() {
//        user.drawCards(new Card(Symbol.ACE, Type.CLUB),
//                new Card(Symbol.EIGHT, Type.HEART));
//
//        assertThat(user.calculateScore()).isEqualTo(new Score(19));
//    }
//
//    @Test
//    void sumScore_WhenAceShouldBeOne() {
//        user.drawCards(new Card(Symbol.ACE, Type.CLUB),
//                new Card(Symbol.EIGHT, Type.HEART),
//                new Card(Symbol.THREE, Type.SPADE));
//
//        assertThat(user.calculateScore()).isEqualTo(new Score(12));
//    }
//
//    @Test
//    void isBust_ShouldReturnTrue() {
//        user.drawCards(new Card(Symbol.TEN, Type.CLUB),
//                new Card(Symbol.EIGHT, Type.HEART),
//                new Card(Symbol.FOUR, Type.SPADE));
//
//        assertThat(user.isBust()).isTrue();
//    }
//
//    @Test
//    void isBust_ShouldReturnFalse() {
//        user.drawCards(new Card(Symbol.TEN, Type.CLUB),
//                new Card(Symbol.EIGHT, Type.HEART),
//                new Card(Symbol.THREE, Type.SPADE));
//
//        assertThat(user.isBust()).isFalse();
//    }
}

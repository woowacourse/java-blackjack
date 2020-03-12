package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        card = Card.of(Symbol.ACE, Type.CLUB);
    }

    @Test
    void Card() {
        assertThat(card).isNotNull();
        assertThat(Card.of(Symbol.TWO, Type.HEART)).isNotNull();
        assertThat(Card.of(Symbol.ACE, Type.CLUB)).isNotNull();
        assertThat(Card.of(Symbol.ACE, Type.CLUB)).isNotNull();
    }

    @Test
    void isAce() {
        assertThat(card.isAce()).isTrue();
        assertThat(Card.of(Symbol.TWO, Type.CLUB).isAce())
                .isFalse();
    }

    @Test
    void getScore() {
        assertThat(card.getScore()).isEqualTo(Score.of(1));
    }

    @Test
    void testEquals() {
        assertThat(card).isEqualTo(Card.of(Symbol.ACE, Type.CLUB));
    }

    @Test
    void getName() {
        assertThat(card.getName()).isEqualTo("A 클로버");
    }
}

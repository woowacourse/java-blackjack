package model.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    public void 카드_저장_정상_작동() {
        Shape shape = Shape.CLOVER;
        CardNumber number = CardNumber.FIVE;

        Card card = new Card(shape, number);

        assertThat(card.shape()).isEqualTo(shape);
        assertThat(card.cardNumber()).isEqualTo(number);
    }

    @Test
    public void ACE_카드_구분_정상_작동() {
        Card card1 = new Card(Shape.DIAMOND, CardNumber.NINE);
        Card card2 = new Card(Shape.DIAMOND, CardNumber.ACE);

        assertThat(card1.isAce()).isFalse();
        assertThat(card2.isAce()).isTrue();
    }
}

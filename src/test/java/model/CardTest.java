package model;

import static org.assertj.core.api.Assertions.*;

import model.card.Card;
import model.card.CardNumber;
import model.card.Shape;
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

}

package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;
import util.CardsMaker;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("Card를 생성한다.")
    void createCardSuccess() {
        Shape diamond = Shape.DIAMOND;
        Letter two = Letter.TWO;

        Card card = Card.of(diamond, two);

        assertThat(card == Card.of(diamond, two)).isTrue();
        assertThat(card.getShapeName()).isEqualTo("다이아몬드");
        assertThat(card.getLetterExpression()).isEqualTo("2");
        assertThat(card.getLetterScore()).isEqualTo(2);
    }

}

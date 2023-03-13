package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("Card를 생성한다.")
    void createCardSuccess() {
        Shape diamond = Shape.DIAMOND;
        Letter two = Letter.TWO;

        Card card = Card.of(diamond, two);

        assertThat(card.getShape()).isEqualTo(Shape.DIAMOND);
        assertThat(card.getLetter().getExpression()).isEqualTo("2");
        assertThat(card.getScore()).isEqualTo(Score.from(2));
    }

    @Test
    @DisplayName("Card의 Shape 이 ACE 임을 확인한다.")
    void createAceCard() {
        Shape shape = Shape.DIAMOND;
        Letter letter = Letter.ACE;

        Card card = Card.of(shape, letter);

        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("같은 구조의 Card는 캐싱된 Card가 반환된다.")
    void createCardWithCache() {
        Shape shape = Shape.DIAMOND;
        Letter letter = Letter.KING;

        Card firstCard = Card.of(shape, letter);
        Card secondCard = Card.of(shape, letter);

        assertThat(firstCard).isSameAs(secondCard);
    }

}

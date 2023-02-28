package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardsTest {
    @Test
    @DisplayName("가지고 있는 카드의 점수를 합한다")
    void givenCards_thenSumScore() {
        //given
        final Cards cards = new Cards();
        cards.takeCard(Card.of(Shape.SPADE, Number.EIGHT));
        cards.takeCard(Card.of(Shape.HEARTS, Number.SIX));
        cards.takeCard(Card.of(Shape.DIAMOND, Number.SEVEN));

        //when
        int score = cards.sumScore();

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }
}

package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardsTest {

    @Test
    @DisplayName("Cards는 카드들의 리스트로 이루어져 있다.")
    void cardsSumTest() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.of(1)),
                new Card(Shape.DIAMOND, CardNumber.of(2)),
                new Card(Shape.HEART, CardNumber.of(3))
        );
        assertDoesNotThrow(()->{
            final Cards cards = new Cards(data);
        });
    }

}

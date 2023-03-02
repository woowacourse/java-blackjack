package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    @DisplayName("Cards에 Card를 추가할 수 있다.")
    void addCardTest() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.of(1)),
                new Card(Shape.DIAMOND, CardNumber.of(2)),
                new Card(Shape.HEART, CardNumber.of(3))
        );
        final Cards cards = new Cards(data);
        assertThat(cards).extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size().isEqualTo(3);

        final Card card = new Card(Shape.HEART, CardNumber.of(1));
        assertThat(cards.add(card)).extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size().isEqualTo(4);
    }

    @Test
    @DisplayName("Cards에 Card를 추가하면 Point도 업데이트 된다.")
    void addCardPointUpdateTest() {
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.of(1)),
                new Card(Shape.DIAMOND, CardNumber.of(2)),
                new Card(Shape.HEART, CardNumber.of(3))
        );
        final Cards cards = new Cards(data);
        assertThat(cards)
                .extracting("point")
                .extracting("gamePoint")
                .isEqualTo(16);

        final Card card = new Card(Shape.HEART, CardNumber.of(5));

        assertThat(cards.add(card))
                .extracting("point")
                .extracting("gamePoint")
                .isEqualTo(21);
    }

    @Test
    @DisplayName("cards의 bust 상태 테스트")
    void bustTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(10))
        );
        final Cards cards = new Cards(data);
        assertThat(cards.isBust()).isTrue();
    }
}

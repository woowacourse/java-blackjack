package blackjack.domain;

import blackjack.domain.card.*;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardsTest {

    @Test
    @DisplayName("Cards에 Card를 추가할 수 있다.")
    void addCardTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.TWO),
                new Card(Shape.HEART, CardNumber.THREE)
        );
        final Cards cards = new Cards(data);
        final List<Card> originCardData = cards.getCards();
        assertThat(originCardData.size()).isEqualTo(3);

        final Card additionalCard = new Card(Shape.HEART, CardNumber.ACE);

        //when
        final Cards addedCard = cards.add(additionalCard);
        final List<Card> addedCardData = addedCard.getCards();

        //then
        assertThat(addedCardData.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Cards에 Card를 추가하면 Point도 업데이트 된다.")
    void addCardPointUpdateTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.CLOVER, CardNumber.ACE),
                new Card(Shape.DIAMOND, CardNumber.TWO),
                new Card(Shape.HEART, CardNumber.THREE)
        );
        final Cards cards = new Cards(data);
        final GamePoint point = cards.getPoint();
        final int value = point.getValue();
        assertThat(value).isEqualTo(16);

        final Card card = new Card(Shape.HEART, CardNumber.FIVE);

        //when
        final Cards addedCards = cards.add(card);
        final GamePoint addedCardsPoint = addedCards.getPoint();
        final int addedValue = addedCardsPoint.getValue();

        //then
        assertThat(addedValue).isEqualTo(21);
    }

    @Test
    @DisplayName("cards의 bust 상태 테스트")
    void bustTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.TEN)
        );
        final Cards cards = new Cards(data);

        //when
        final boolean isBust = cards.isBust();

        assertTrue(isBust);
    }
}

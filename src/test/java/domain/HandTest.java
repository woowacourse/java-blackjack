package domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    Hand testHandThatValueFive = Hand.of(
            new Card(CardShape.다이아몬드, CardContents.TWO),
            new Card(CardShape.다이아몬드, CardContents.THREE)
    );

    @Nested
    class isBustTest {
        @Test
        @DisplayName("카드 합이 21 이하이면 isBust는 False다")
        void isBust_False() {
            List<Card> cardsThatValue16 = List.of(
                    new Card(CardShape.다이아몬드, CardContents.SIX),
                    new Card(CardShape.다이아몬드, CardContents.TEN)
            );
            cardsThatValue16.forEach(
                    card -> testHandThatValueFive.addCard(card)
            );

            //when
            boolean result = testHandThatValueFive.isBust();

            //then
            Assertions.assertFalse(result);
        }

        @Test
        @DisplayName("카드 합이 21 초과이면 isBust는 True다")
        void isBust_True() {
            List<Card> cardsThatValue17 = List.of(
                    new Card(CardShape.다이아몬드, CardContents.SEVEN),
                    new Card(CardShape.다이아몬드, CardContents.TEN)
            );
            cardsThatValue17.forEach(
                    card -> testHandThatValueFive.addCard(card)
            );

            //when
            boolean result = testHandThatValueFive.isBust();

            //then
            Assertions.assertTrue(result);
        }
    }

    @Test
    @DisplayName("받은 카드 1장을 잘 추가한다")
    void addCard_success() {
        //given
        Card testCard = new Card(CardShape.하트, CardContents.SEVEN);
        int beforeAddCardSize = testHandThatValueFive.showCards().size();

        //when
        testHandThatValueFive.addCard(testCard);

        //then
        int afterAddCardSize = testHandThatValueFive.showCards().size();
        Assertions.assertTrue(beforeAddCardSize + 1 == afterAddCardSize);
    }
}
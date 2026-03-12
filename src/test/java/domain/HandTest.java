package domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    Hand testHandThatValueFive;

    @BeforeEach
    void setUpHand() {
        testHandThatValueFive = Hand.of(
                new Card(CardShape.다이아몬드, CardContents.TWO),
                new Card(CardShape.다이아몬드, CardContents.THREE)
        );
    }

    @Nested
    class isBustTest {
        @Test
        @DisplayName("카드 합이 21이하면 isBust는 False다")
        void isBust_False() {
            //when
            boolean result = testHandThatValueFive.isBust();

            //then
            Assertions.assertFalse(result);
        }

        @Test
        @DisplayName("카드 합이 21 이어도 isBust는 False다")
        void isBust_False_TwentyOne() {
            List<Card> cardsThatValue16 = List.of(
                    new Card(CardShape.다이아몬드, CardContents.SIX),
                    new Card(CardShape.다이아몬드, CardContents.TEN)
            );

            for (Card card : cardsThatValue16) {
                testHandThatValueFive = testHandThatValueFive.addCard(card);
            }

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

            for (Card card : cardsThatValue17) {
                testHandThatValueFive = testHandThatValueFive.addCard(card);
            }

            //when
            boolean result = testHandThatValueFive.isBust();

            //then
            Assertions.assertTrue(result);
        }
    }

    @Nested
    class isFullTest {
        @Test
        @DisplayName("21이면 isFull 은 True다")
        void isTrue_true() {
            List<Card> cardsThatValue16 = List.of(
                    new Card(CardShape.다이아몬드, CardContents.SIX),
                    new Card(CardShape.다이아몬드, CardContents.TEN)
            );

            for (Card card : cardsThatValue16) {
                testHandThatValueFive = testHandThatValueFive.addCard(card);
            }

            //when
            boolean result = testHandThatValueFive.isFull();

            //then
            Assertions.assertTrue(result);
        }

        @Test
        @DisplayName("21이 아니면 isFull 은 False다")
        void isTrue_false() {
            //when
            boolean result = testHandThatValueFive.isFull();

            //then
            Assertions.assertFalse(result);
        }
    }

    @Nested
    class isBlackJackTest {
        @Test
        @DisplayName("카드 합이 21이 아니면 BlackJack 절대 아니다")
        void isBlackJack_false() {
            List<Card> cardsThatValue16 = List.of(
                    new Card(CardShape.다이아몬드, CardContents.SIX),
                    new Card(CardShape.다이아몬드, CardContents.TEN)
            );

            for (Card card : cardsThatValue16) {
                testHandThatValueFive = testHandThatValueFive.addCard(card);
            }

            //when
            boolean result = testHandThatValueFive.isBlackJack();

            //then
            Assertions.assertFalse(result);
        }

        @Test
        @DisplayName("카드 합이 21이어도 카드 개수가 2장이 아니면 BlackJack 절대 아니다")
        void isBlackJack_false_cardSize_not_two() {
            //when
            boolean result = testHandThatValueFive.isBlackJack();

            //then
            Assertions.assertFalse(result);
        }

        @Test
        @DisplayName("카드 합이 21이고 카드 개수가 2장이면 BlackJack 이다")
        void isBlackJack_true() {
            //given
            Hand blackJackHand = Hand.of(
                    new Card(CardShape.다이아몬드, CardContents.TEN),
                    new Card(CardShape.다이아몬드, CardContents.A)
            );
            //when
            boolean result = blackJackHand.isBlackJack();

            //then
            Assertions.assertTrue(result);
        }
    }

    @Test
    @DisplayName("카드를 잘 반환한다")
    void showCards_test() {
        //given
        int expectSize = testHandThatValueFive.showCards().size();
        Card diamondTwo = new Card(CardShape.다이아몬드, CardContents.TWO);
        Card diamondThree = new Card(CardShape.다이아몬드, CardContents.THREE);

        //when
        List<Card> result = testHandThatValueFive.showCards();

        //then
        Assertions.assertEquals(expectSize, result.size());
        Assertions.assertTrue(result.contains(diamondTwo));
        Assertions.assertTrue(result.contains(diamondThree));
    }

    @Test
    @DisplayName("받은 카드 1장을 잘 추가한다")
    void addCard_success() {
        //given
        Card testCard = new Card(CardShape.하트, CardContents.SEVEN);
        int beforeAddCardSize = testHandThatValueFive.showCards().size();

        //when
        testHandThatValueFive = testHandThatValueFive.addCard(testCard);

        //then
        int afterAddCardSize = testHandThatValueFive.showCards().size();
        Assertions.assertEquals(beforeAddCardSize + 1, afterAddCardSize);
    }

    @Test
    @DisplayName("카드의 합을 잘 구한다")
    void calculateCardScoreSum_success() {
        //when
        int result = testHandThatValueFive.calculateCardScoreSum();
        int expect = 5;

        //then
        Assertions.assertEquals(expect, result);
    }
}
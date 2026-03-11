package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    @Nested
    class isBustTest {
        @Test
        @DisplayName("카드 합이 21 이하이면 isBust는 False다")
        void isBust_False() {
            //given
            Hand testHand = Hand.of(
                    new Card(CardShape.다이아몬드, CardContents.K),
                    new Card(CardShape.다이아몬드, CardContents.J)
            );

            //when
            boolean result = testHand.isBust();

            //then
            Assertions.assertFalse(result);
        }
    }
}
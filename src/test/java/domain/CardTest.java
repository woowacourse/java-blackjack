package domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    @DisplayName("카드 콘텐츠 잘 제공")
    void getCardContents_success() {
        //given
        CardContents testCardContents = CardContents.J;
        Card testCard = new Card(CardShape.스페이드, testCardContents);

        //when
        CardContents result = testCard.getCardContents();

        //then
        Assertions.assertThat(result).isEqualTo(testCardContents);
    }

    @Nested
    class isAceTest {
        @Test
        void isAce_true() {
            //given
            Card card = new Card(CardShape.하트, CardContents.A);

            //when
            boolean result = card.isAce();

            //then
            assertTrue(result);
        }

        @Test
        void isAce_false() {
            //given
            Card card = new Card(CardShape.하트, CardContents.J);

            //when
            boolean result = card.isAce();

            //then
            assertFalse(result);
        }
    }
}

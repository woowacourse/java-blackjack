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

    @Test
    @DisplayName("카드 정보 출력 양식")
    void card_toString() {
        //given
        CardContents testCardContents = CardContents.J;
        Card testCard = new Card(CardShape.스페이드, testCardContents);
        String expect = "J스페이드";

        //when & then
        Assertions.assertThat(testCard.toString()).isEqualTo(expect);
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

    @Test
    @DisplayName("컨텐츠가 같으면 같은 카드로 인식한다")
    void same_content_same_card() {
        //given
        CardContents aceCardContents = CardContents.A;
        CardShape cardShapeHeart = CardShape.하트;
        Card card1 = new Card(cardShapeHeart, aceCardContents);
        Card card2 = new Card(cardShapeHeart, aceCardContents);

        //when
        boolean result = card1.equals(card2);

        //then
        assertTrue(result);
    }
}

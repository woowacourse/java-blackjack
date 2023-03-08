package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardsTest {

    private static final int BUST_SCORE = -1;
    private static final int MAX_CARD_SCORE = 21;

    @Test
    void 카드를_받아_점수를_계산한다() {
        //given
        Cards hand = new Cards();
        CardNumber cardNumber = CardNumber.TWO;
        List<Card> cards = List.of(new Card(cardNumber, CardShape.CLOVER));

        // when
        cards.forEach(hand::add);

        // then
        Assertions.assertThat(hand.calculateScore())
                .isEqualTo(cardNumber.getScore());
    }

    @Nested
    class 에이스_카드는 {

        @Test
        void _카드_총합이_21이_넘어가면_1로_취급한다() {
            // given
            Cards hand = new Cards();
            Card cardTen = new Card(CardNumber.TEN, CardShape.CLOVER);
            Card cardNine = new Card(CardNumber.NINE, CardShape.CLOVER);
            Card cardAce = new Card(CardNumber.ACE, CardShape.CLOVER);
            List<Card> cards = List.of(cardTen, cardNine, cardAce);

            // when
            cards.forEach(hand::add);

            // then
            Assertions.assertThat(hand.calculateScore())
                    .isEqualTo(20);
        }

        @Test
        void _카드_총합이_21이_넘어가지_않으면_11로_취급한다() {
            // given
            Cards hand = new Cards();
            Card cardTen = new Card(CardNumber.TEN, CardShape.CLOVER);
            Card cardAce = new Card(CardNumber.ACE, CardShape.CLOVER);
            List<Card> cards = List.of(cardTen, cardAce);

            // when
            cards.forEach(hand::add);

            // then
            Assertions.assertThat(hand.calculateScore())
                    .isEqualTo(MAX_CARD_SCORE);
        }
    }

    @Test
    void _카드_총합이_21을_넘어가면_무조건_패배이다() {
        // given
        Cards hand = new Cards();
        Card cardTen1 = new Card(CardNumber.TEN, CardShape.CLOVER);
        Card cardTen2 = new Card(CardNumber.TEN, CardShape.CLOVER);
        Card cardTen3 = new Card(CardNumber.TEN, CardShape.CLOVER);
        List<Card> cards = List.of(cardTen1, cardTen2, cardTen3);

        //when
        cards.forEach(hand::add);

        //then
        Assertions.assertThat(hand.calculateScore())
                .isEqualTo(BUST_SCORE);
    }
}
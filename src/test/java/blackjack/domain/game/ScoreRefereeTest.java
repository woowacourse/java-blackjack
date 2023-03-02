package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreRefereeTest {

    @Test
    void 카드를_받아_점수를_계산한다() {
        //given
        List<Card> cards = List.of(new Card(CardNumber.TWO, CardShape.CLOVER));

        // when
        final int score = ScoreReferee.calculateScore(cards);

        // then
        Assertions.assertThat(score).isEqualTo(2);
    }

    @Nested
    class 에이스_카드는 {

        @Test
        void _카드_총합이_21이_넘어가면_1로_취급한다() {
            // given
            Card cardTen = new Card(CardNumber.TEN, CardShape.CLOVER);
            Card cardNine = new Card(CardNumber.NINE, CardShape.CLOVER);
            Card cardAce = new Card(CardNumber.ACE, CardShape.CLOVER);
            List<Card> cards = List.of(cardTen, cardNine, cardAce);

            // when
            final int score = ScoreReferee.calculateScore(cards);

            // then
            Assertions.assertThat(score).isEqualTo(20);
        }

        @Test
        void _카드_총합이_21이_넘어가지_않으면_11로_취급한다() {
            // given
            Card cardTen = new Card(CardNumber.TEN, CardShape.CLOVER);
            Card cardAce = new Card(CardNumber.ACE, CardShape.CLOVER);
            List<Card> cards = List.of(cardTen, cardAce);

            // when
            final int score = ScoreReferee.calculateScore(cards);

            // then
            Assertions.assertThat(score).isEqualTo(21);
        }
    }

    @Test
    void _카드_총합이_21을_넘어가면_무조건_패배이다() {
        // given
        Card cardTen1 = new Card(CardNumber.TEN, CardShape.CLOVER);
        Card cardTen2 = new Card(CardNumber.TEN, CardShape.CLOVER);
        Card cardTen3 = new Card(CardNumber.TEN, CardShape.CLOVER);
        List<Card> cards = List.of(cardTen1, cardTen2, cardTen3);

        //when
        final int score = ScoreReferee.calculateScore(cards);

        //then
        Assertions.assertThat(score).isEqualTo(-1);

    }
}

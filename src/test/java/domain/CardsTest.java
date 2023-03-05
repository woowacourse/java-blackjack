package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardsTest {
    @Test
    @DisplayName("가지고 있는 카드의 점수를 합한다")
    void givenCards_thenSumScore() {
        //given
        final Cards cards = new Cards();
        cards.takeCard(Card.of(Suit.SPADE, Rank.EIGHT));
        cards.takeCard(Card.of(Suit.HEARTS, Rank.SIX));
        cards.takeCard(Card.of(Suit.DIAMOND, Rank.SEVEN));

        //when
        int score = cards.getScore();

        //then
        assertThat(score).isEqualTo(21);
    }

    @Nested
    class SumScoreTest {
        @Test
        @DisplayName("점수 총합이 11이하면 ACE의 값을 11로 계산한다")
        void givenAceAndUnderElevenScore_thenAceScoreEleven() {
            //given
            final Cards cards = new Cards();
            cards.takeCard(Card.of(Suit.SPADE, Rank.ACE));
            cards.takeCard(Card.of(Suit.HEARTS, Rank.TWO));
            cards.takeCard(Card.of(Suit.DIAMOND, Rank.SEVEN));

            //when
            final int score = cards.getScore();

            //then
            assertThat(score).isEqualTo(20);
        }

        @Test
        @DisplayName("점수 총합이 12이상이면 Ace에 값을 1로 계산한다")
        void givenAceAndOverTwelveScore_thenAceScoreOne() {
            //given
            final Cards cards = new Cards();
            cards.takeCard(Card.of(Suit.SPADE, Rank.ACE));
            cards.takeCard(Card.of(Suit.HEARTS, Rank.FIVE));
            cards.takeCard(Card.of(Suit.DIAMOND, Rank.SEVEN));

            //when
            final int score = cards.getScore();

            //then
            assertThat(score).isEqualTo(13);
        }
    }
}

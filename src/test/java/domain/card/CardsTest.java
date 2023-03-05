package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardsTest {
    @Test
    @DisplayName("가지고 있는 카드의 점수를 합한다")
    void givenCards_thenSumScore() {
        //given
        final Cards cards = new Cards();
        final List<Rank> ranks = List.of(Rank.EIGHT, Rank.SIX, Rank.SEVEN);
        final Deck deck = Deck.from(TestCardGenerator.from(ranks));

        for (int i = 0; i < ranks.size(); i++) {
            cards.takeCard(deck.dealCard());
        }

        //when
        int score = cards.getScore();

        //then
        assertThat(score).isEqualTo(21);
    }

    @Nested
    class IsSoftTest {
        @Test
        @DisplayName("점수 총합이 11이하면 ACE의 값을 11로 계산한다")
        void givenAceAndUnderElevenScore_thenAceScoreEleven() {
            //given
            final Cards cards = new Cards();
            final List<Rank> ranks = List.of(Rank.ACE, Rank.TWO, Rank.SEVEN);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            for (int i = 0; i < ranks.size(); i++) {
                cards.takeCard(deck.dealCard());
            }

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

            final List<Rank> ranks = List.of(Rank.ACE, Rank.FIVE, Rank.SEVEN);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            for (int i = 0; i < ranks.size(); i++) {
                cards.takeCard(deck.dealCard());
            }

            //when
            final int score = cards.getScore();

            //then
            assertThat(score).isEqualTo(13);
        }
    }
}

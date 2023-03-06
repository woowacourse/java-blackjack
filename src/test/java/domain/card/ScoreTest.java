package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
    @Test
    @DisplayName("한장의 카드가 주어지면 카드의 랭크만큼 숫자를 더한다.")
    void givenCard_thenSumScore() {
        //given
        final Score score = Score.from(0);

        //when
        final Card spadeEight = Card.of(Suit.SPADE, Rank.EIGHT);
        score.sumScore(spadeEight);

        //then
        assertThat(score.getScore())
                .isEqualTo(8);
    }

    @Test
    @DisplayName("여러장의 카드가 주어지면 각 카드들의 랭크만큼 숫자를 더한다.")
    void givenCards_thenSumScore() {
        //given
        final Score score = Score.from(0);

        //when
        final Card eight = Card.of(Suit.SPADE, Rank.EIGHT);
        final Card six = Card.of(Suit.HEARTS, Rank.SIX);
        final Card seven = Card.of(Suit.CLUBS, Rank.SEVEN);
        score.sumScore(eight, six, seven);

        //then
        assertThat(score.getScore())
                .isEqualTo(21);
    }

    @Nested
    @DisplayName("ace를 뽑았을 때,")
    class IsSoftTest {
        @Test
        @DisplayName("점수 총합이 11이하면 ace의 값을 11로 계산한다")
        void givenAceAndUnderElevenScore_thenAceScoreEleven() {
            //given
            final Score score = Score.from(0);

            //when
            final Card ace = Card.of(Suit.SPADE, Rank.ACE);
            final Card two = Card.of(Suit.HEARTS, Rank.TWO);
            final Card three = Card.of(Suit.CLUBS, Rank.THREE);
            score.sumScore(ace, two, three);


            //then
            assertThat(score.getScore())
                    .isEqualTo(16);
        }

        @Test
        @DisplayName("점수 총합이 12이상이면 ace의 값을 1로 계산한다")
        void givenAceAndOverTwelveScore_thenAceScoreOne() {
            //given
            final Score score = Score.from(0);

            //when
            final Card ace = Card.of(Suit.SPADE, Rank.ACE);
            final Card six = Card.of(Suit.HEARTS, Rank.SIX);
            final Card seven = Card.of(Suit.CLUBS, Rank.SEVEN);
            score.sumScore(ace, six, seven);


            //then
            assertThat(score.getScore()).isEqualTo(14);
        }
    }
}

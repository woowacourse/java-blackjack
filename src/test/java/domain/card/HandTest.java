package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TestFixture;
import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.card.vo.Suit;
import domain.score.Score;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {
    @Nested
    @DisplayName("getScore(): ")
    class GetScore {
        public static Stream<Arguments> ifTotalScoreBelow11() {
            return Stream.of(
                    //A를 우선 1로 처리하고 총합이 11 이하라면 +11해서 반환한다.
                    Arguments.of(new Hand(TestFixture.getCardsByRanks(List.of(Rank.ACE, Rank.KING))),
                            new Score(11 + 10)),
                    Arguments.of(new Hand(TestFixture.getCardsByRanks(List.of(Rank.ACE, Rank.ACE))),
                            new Score(2 + 10))
            );
        }

        public static Stream<Arguments> ifTotalScoreOver11() {
            return Stream.of(
                    //A를 우선 1로 처리하고 총합이 11 이하라면 +11해서 반환한다.
                    Arguments.of(new Hand(TestFixture.getCardsByRanks(List.of(Rank.ACE, Rank.KING))),
                            new Card(Rank.ACE, Suit.SPADE),
                            new Score(12)),
                    Arguments.of(new Hand(TestFixture.getCardsByRanks(List.of(Rank.ACE, Rank.KING))),
                            new Card(Rank.TWO, Suit.SPADE),
                            new Score(13)),
                    Arguments.of(new Hand(TestFixture.getCardsByRanks(List.of(Rank.ACE, Rank.KING))),
                            new Card(Rank.TEN, Suit.SPADE),
                            new Score(21))
            );
        }

        @ParameterizedTest
        @MethodSource
        @DisplayName("A를 포함하면서 11이하라면 +10 해서 전달한다. ")
        void ifTotalScoreBelow11(Hand hand, Score score) {
            assertThat(hand.getScore()).isEqualTo(score);
        }

        @ParameterizedTest
        @MethodSource
        @DisplayName("A를 포함하면서 11초과한다면 그냥 전달한다.")
        void ifTotalScoreOver11(Hand hand, Card card, Score score) {
            hand.add(card);
            assertThat(hand.getScore()).isEqualTo(score);
        }
    }
}

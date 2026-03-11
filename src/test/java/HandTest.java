import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Rank;
import domain.player.Hand;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.ErrorMessage;

class HandTest {

    @Nested
    @DisplayName("getTotalScore(): ")
    class GetTotalScore {
        public static Stream<Arguments> getTotalScore() {
            return Stream.of(
                    // ACE 미포함 총합을 반환한다.
                    Arguments.of(TestDefaults.getCardByRank(Rank.TWO), TestDefaults.getCardByRank(Rank.JACK),
                            Rank.TWO.getScore() + Rank.JACK.getScore()),
                    // ACE가 포함되고, 총합이 11 이하일 경우 - 총합에 10을 더하여 반환해야한다.
                    Arguments.of(TestDefaults.getCardByRank(Rank.TWO), TestDefaults.getCardByRank(Rank.ACE),
                            Rank.TWO.getScore() + Rank.ACE.getScore() + Hand.ACE_PROFIT_VALUE),
                    // ACE가 여러개 있을 경우 우선 1로 간주하고 총합이 11을 넘지 않는다면 + 10을 한다.
                    Arguments.of(TestDefaults.getCardByRank(Rank.ACE), TestDefaults.getCardByRank(Rank.ACE),
                            Rank.ACE.getScore() + Rank.ACE.getScore() + Hand.ACE_PROFIT_VALUE)
            );
        }

        @ParameterizedTest
        @MethodSource
        void getTotalScore(Card firstCard, Card secondCard, int cardsSum) {

            List<Card> cards = List.of(firstCard, secondCard);
            Hand hand = new Hand(cards);

            assertThat(hand.getTotalScore()).isEqualTo(cardsSum);
        }

        @Test
        @DisplayName("ACE가 포함되어 있고, 총합이 11을 넘으면 A는 1로 간주된다.")
        void includeAceOverScore() {
            List<Card> cards = TestDefaults.getCardsByRanks(List.of(Rank.ACE, Rank.JACK));
            Hand hand = new Hand(cards);

            hand.add(TestDefaults.getCardByRank(Rank.KING));

            assertThat(hand.getTotalScore()).isEqualTo(21);
        }
    }

    @Nested
    @DisplayName("isBlackjack(): ")
    class IsBlackjack {

        @Test
        @DisplayName("첫 패에서 합이 21이면 True를 반환한다.")
        void isBlackjack() {
            Hand hand = new Hand(TestDefaults.getCardsByRanks(List.of(Rank.ACE, Rank.QUEEN)));

            assertThat(hand.isBlackjack()).isEqualTo(true);
        }

        @Test
        @DisplayName("카드를 더 받아서 21일 경우 false를 반환한다.(Blackjack 아님)")
        void notBlackjack() {
            Hand hand = new Hand(TestDefaults.getCardsByRanks(List.of(Rank.KING, Rank.QUEEN)));

            hand.add(TestDefaults.getCardByRank(Rank.ACE));

            assertThat(hand.isBlackjack()).isEqualTo(false);
        }
    }


    @Nested
    @DisplayName("isBust(): ")
    class IsBurst {

        @Test
        @DisplayName("패의 총합이 21을 초과하면 true를 반환한다")
        void returnsTrueWhenTotalScoreIsOver21() {
            Hand hand = new Hand(TestDefaults.getCardsByRanks(List.of(Rank.KING, Rank.QUEEN)));

            hand.add(TestDefaults.getCardByRank(Rank.TWO));

            assertThat(hand.isBust()).isTrue();
        }

        @Test
        @DisplayName("패의 총합이 21 이하이면 false를 반환한다")
        void returnsFalseWhenTotalScoreIs21OrLess() {
            Hand hand = new Hand(TestDefaults.getCardsByRanks(List.of(Rank.ACE, Rank.QUEEN)));

            assertThat(hand.isBust()).isFalse();
        }
    }


    @Nested
    @DisplayName("constructor(): ")
    class Constructor {
        @Test
        @DisplayName("카드사이즈가 2인 핸드가 생성된다.")
        void constructor() {
            Hand hand = new Hand(TestDefaults.getCards(2));

            assertThat(hand.getHandsSize()).isEqualTo(2);
        }

        @Test
        @DisplayName("초기 카드 사이즈가 2가 아니라면 예외를 반환한다.")
        void validateSize() {
            assertThatThrownBy(() ->
                    new Hand(List.of(TestDefaults.getCard(0))))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.HANDS_CARDS_SIZE.getMessage());
        }
    }


}

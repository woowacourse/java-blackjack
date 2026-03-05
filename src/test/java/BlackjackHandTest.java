import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.BlackjackHand;
import domain.Card;
import domain.Rank;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import util.ErrorMessage;

class BlackjackHandTest {

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
                            Rank.TWO.getScore() + Rank.ACE.getScore() + BlackjackHand.ACE_PROFIT_VALUE),
                    // ACE가 여러개 있을 경우 우선 1로 간주하고 총합이 11을 넘지 않는다면 + 10을 한다.
                    Arguments.of(TestDefaults.getCardByRank(Rank.ACE), TestDefaults.getCardByRank(Rank.ACE),
                            Rank.ACE.getScore() + Rank.ACE.getScore() + BlackjackHand.ACE_PROFIT_VALUE)
            );
        }

        @ParameterizedTest
        @MethodSource
        void getTotalScore(Card firstCard, Card secondCard, int cardsSum) {

            List<Card> cards = List.of(firstCard, secondCard);
            BlackjackHand blackjackHand = new BlackjackHand(cards);

            assertThat(blackjackHand.getTotalScore()).isEqualTo(cardsSum);
        }

        @Test
        @DisplayName("ACE가 포함되어 있고, 총합이 11을 넘으면 A는 1로 간주된다.")
        void includeAceOverScore() {
            List<Card> cards = TestDefaults.getCardsByRanks(List.of(Rank.ACE, Rank.JACK));
            BlackjackHand blackjackHand = new BlackjackHand(cards);

            blackjackHand.add(TestDefaults.getCardByRank(Rank.KING));

            assertThat(blackjackHand.getTotalScore()).isEqualTo(21);
        }
    }


    @Nested
    @DisplayName("isBurst(): ")
    class IsBurst {
        @ParameterizedTest
        @DisplayName("2 이하의 값이 들어오면 예외를 발생시킨다.")
        @CsvSource({
                "-1",
                "0",
                "1"
        })
        void underTwoValue(int totalScore) {
            assertThatThrownBy(() -> BlackjackHand.isBurst(totalScore))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.BURST_TOTAL_SCORE.getMessage());
        }

        @ParameterizedTest
        @DisplayName("총합이 21을 초과한다면 true 반환, 아니라면 false 반환한다")
        @CsvSource({
                "2,false",
                "21,false",
                "22,true"
        })
        void isBurst(int totalScore, boolean expected) {
            assertThat(BlackjackHand.isBurst(totalScore)).isEqualTo(expected);
        }
    }


    @Nested
    @DisplayName("constructor(): ")
    class Constructor {
        @Test
        @DisplayName("카드사이즈가 2인 핸드가 생성된다.")
        void constructor() {
            BlackjackHand blackjackHand = new BlackjackHand(TestDefaults.getCards(2));

            assertThat(blackjackHand.getHandsSize()).isEqualTo(2);
        }

        @Test
        @DisplayName("초기 카드 사이즈가 2가 아니라면 예외를 반환한다.")
        void validateSize() {
            assertThatThrownBy(() ->
                    new BlackjackHand(List.of(TestDefaults.getCard(0))))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.HANDS_CARDS_SIZE.getMessage());
        }
    }


}

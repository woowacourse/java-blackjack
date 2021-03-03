package blackjack.domain.rule;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackScoreRuleTest {

    @DisplayName("카드 총점을 합산한다.")
    @ParameterizedTest
    @MethodSource
    void test_sum_total_score_normal(List<Card> cards, int expected) {
        //given
        BlackJackScoreRule blackJackScoreRule = new BlackJackScoreRule();

        //when
        int totalScore = blackJackScoreRule.sumTotalScore(cards);

        //then
        assertThat(totalScore).isEqualTo(expected);
    }

    private static Stream<Arguments> test_sum_total_score_normal() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CardType.CLOVER, CardValue.EIGHT),
                        new Card(CardType.CLOVER, CardValue.SEVEN)), 15),
                Arguments.of(Arrays.asList(
                        new Card(CardType.CLOVER, CardValue.ACE),
                        new Card(CardType.CLOVER, CardValue.SEVEN)), 18),
                Arguments.of(Arrays.asList(
                        new Card(CardType.CLOVER, CardValue.EIGHT),
                        new Card(CardType.CLOVER, CardValue.SEVEN),
                        new Card(CardType.CLOVER, CardValue.ACE)), 16)
        );
    }
}

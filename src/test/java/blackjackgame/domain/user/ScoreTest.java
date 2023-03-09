package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CloverCard;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("블랙잭 게임 점수는 ")
class ScoreTest {
    @DisplayName("카드 숫자 값들이 주어지면 단순 합산으로 계산할 수 있다.")
    @Test
    void calculateScoreTest() {
        Score score = new Score();
        List<Card> cards = List.of(CloverCard.CLOVER_TWO, CloverCard.CLOVER_THREE, CloverCard.CLOVER_FOUR,
                CloverCard.CLOVER_FIVE);

        score.setScore(cards);

        Assertions.assertThat(score.getScore()).isEqualTo(14);
    }

    @DisplayName("에이스를 포함하며 21 초과 시 에이스를 1점으로 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCase_greaterThan21")
    void calculateScoreWithAceTest_greaterThan21(List<Card> cards, int expected) {
        Score score = new Score();
        score.setScore(cards);

        Assertions.assertThat(score.getScore()).isEqualTo(expected);
    }

    static Stream<Arguments> calculateScoreWithAceCase_greaterThan21() {
        return Stream.of(
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_SEVEN,
                        CloverCard.CLOVER_EIGHT), 16),
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_ACE,
                        CloverCard.CLOVER_EIGHT), 20),
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_ACE, CloverCard.CLOVER_TEN,
                        CloverCard.CLOVER_NINE), 21)
        );
    }

    @DisplayName("에이스를 포함하며 22 미만 시 에이스를 11점으로 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCase_lessThan22")
    void calculateScoreWithAceTest_lessThan22(List<Card> cards, int expected) {
        Score score = new Score();
        score.setScore(cards);

        Assertions.assertThat(score.getScore()).isEqualTo(expected);
    }

    static Stream<Arguments> calculateScoreWithAceCase_lessThan22() {
        return Stream.of(
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_SEVEN), 18),
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_TEN), 21),
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_THREE), 14)
        );
    }
}

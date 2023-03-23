package blackjackgame.domain.user;

import static blackjackgame.domain.Fixtures.ACE_ACE_ACE_ACE_CARDS;
import static blackjackgame.domain.Fixtures.ACE_KING_CARDS;
import static blackjackgame.domain.Fixtures.ACE_THREE_FOUR_CARDS;
import static blackjackgame.domain.Fixtures.EIGHT_NINE_CARDS;
import static blackjackgame.domain.Fixtures.JACK_KING_ACE_CARDS;

import blackjackgame.domain.card.Card;
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
        Hands hands = new Hands();
        hands.add(EIGHT_NINE_CARDS);

        score.setScore(hands);

        Assertions.assertThat(score.getScore()).isEqualTo(17);
    }

    @DisplayName("에이스를 포함하며 21 초과 시 에이스를 1점으로 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCase_greaterThan21")
    void calculateScoreWithAceTest_greaterThan21(List<Card> cards, int expected) {
        Hands hands = new Hands();
        hands.add(cards);
        Score score = new Score();
        score.setScore(hands);

        Assertions.assertThat(score.getScore()).isEqualTo(expected);
    }

    static Stream<Arguments> calculateScoreWithAceCase_greaterThan21() {
        return Stream.of(
                Arguments.of(ACE_ACE_ACE_ACE_CARDS, 14),
                Arguments.of(JACK_KING_ACE_CARDS, 21)
        );
    }

    @DisplayName("에이스를 포함하며 22 미만 시 에이스를 11점으로 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCase_lessThan22")
    void calculateScoreWithAceTest_lessThan22(List<Card> cards, int expected) {
        Hands hands = new Hands();
        hands.add(cards);
        Score score = new Score();
        score.setScore(hands);

        Assertions.assertThat(score.getScore()).isEqualTo(expected);
    }

    static Stream<Arguments> calculateScoreWithAceCase_lessThan22() {
        return Stream.of(
                Arguments.of(ACE_KING_CARDS, 21),
                Arguments.of(ACE_THREE_FOUR_CARDS, 18)
        );
    }
}

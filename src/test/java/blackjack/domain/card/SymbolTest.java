package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    private static Stream<Arguments> getSymbolAndScore() {
        return Stream.of(Arguments.of(Symbol.KING, 10),
                Arguments.of(Symbol.JACK, 10),
                Arguments.of(Symbol.QUEEN, 10),
                Arguments.of(Symbol.SEVEN, 7));
    }

    @DisplayName("각각의 카드 심볼은 고유한 점수를 가지고 있다.")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("getSymbolAndScore")
    void compareScore(Symbol symbol, int score) {
        int symbolScore = symbol.getScore();

        assertThat(symbolScore).isEqualTo(score);
    }

    @DisplayName("심볼이 에이스면 true를 반환한다.")
    @Test
    void isAce_True() {
        boolean isAce = Symbol.ACE.isAce();

        assertThat(isAce).isTrue();
    }

    @DisplayName("심볼이 에이스가 아니면 false를 반환한다.")
    @Test
    void isAce_False() {
        boolean isAce = Symbol.KING.isAce();

        assertThat(isAce).isFalse();
    }

    @DisplayName("점수가 11점일 때 에이스가 1개 있으면 보너스 +10점 추가")
    @Test
    void calculateAceBonusScore_10() {
        int bonusScore = Symbol.ACE.calculateAceBonusScore(11, 1);

        assertThat(bonusScore).isEqualTo(10);
    }

    @DisplayName("점수가 12점일 때 에이스가 1개 있으면 보너스 점수는 0점")
    @Test
    void calculateAceBonusScore_0() {
        int bonusScore = Symbol.ACE.calculateAceBonusScore(12, 1);

        assertThat(bonusScore).isZero();
    }

    @DisplayName("점수가 2점일 때 에이스가 2개 있으면 보너스 +10점 추가")
    @Test
    void calculateAceBonusScoreWhenAceIsTwo() {
        int bonusScore = Symbol.ACE.calculateAceBonusScore(2, 2);

        assertThat(bonusScore).isEqualTo(10);
    }
}

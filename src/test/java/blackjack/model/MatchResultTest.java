package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("승부 결과 테스트")
class MatchResultTest {

    @DisplayName("승부의 반대를 알 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "WIN, LOSE",
            "LOSE, WIN",
            "DRAW, DRAW"
    })
    void reverseTest(MatchResult matchResult, MatchResult expected) {
        // when
        MatchResult reverseMatchResult = matchResult.getReversed();

        // then
        assertThat(reverseMatchResult)
                .isSameAs(expected);
    }
}

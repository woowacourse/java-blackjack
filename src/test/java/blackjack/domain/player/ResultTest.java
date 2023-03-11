package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ResultTest {

    @ParameterizedTest(name = "결과에 따른 베팅금액의 배율을 반환한다. 입력: {0}, 배율: {1}")
    @CsvSource({"BLACKJACK_WIN,1.5", "WIN,1.0", "PUSH,0", "LOSE,-1.0"})
    void 결과에_따른_베팅금액의_배율을_반환한다(final Result result, final double ratio) {
        assertThat(result.getRatio()).isEqualTo(ratio);
    }
}

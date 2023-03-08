package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("GamblerCompeteResult 는")
class GamblerCompeteResultTest {

    @ParameterizedTest(name = "reverse() 시 {0} 는 {1} 로 변경된다.")
    @CsvSource(value = {
            "WIN -> LOSE",
            "LOSE -> WIN",
            "DRAW -> DRAW"
    }, delimiterString = " -> ")
    void reverse_테스트(final GamblerCompeteResult before, final GamblerCompeteResult reversed) {
        // then
        assertThat(before.reverse()).isEqualTo(reversed);
    }
}
package blackjack.view;

import blackjack.domain.game.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("게임 결과 이름")
class GameResultNameTest {
    @Test
    @DisplayName("게임 결과 이름이 모두 변환 된다.")
    void convertAllTest() {
        // given & when & then
        assertThatCode(() ->
                Arrays.stream(GameResult.values())
                        .forEach(GameResultName::convert)
        )
                .doesNotThrowAnyException();
    }
}

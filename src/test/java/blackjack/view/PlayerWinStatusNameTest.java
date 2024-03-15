package blackjack.view;

import blackjack.domain.game.PlayerWinStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("게임 결과 이름")
class PlayerWinStatusNameTest {
    @Test
    @DisplayName("모두 변환이 된다.")
    void convertAllTest() {
        // given & when & then
        assertThatCode(() ->
                Arrays.stream(PlayerWinStatus.values())
                        .forEach(GameResultName::convert)
        )
                .doesNotThrowAnyException();
    }
}

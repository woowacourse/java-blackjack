package domain;

import static domain.BlackJackWinningResult.DRAW;
import static domain.BlackJackWinningResult.LOSE;
import static domain.BlackJackWinningResult.WIN;
import static domain.BlackJackWinningResult.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/11
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackJackWinningResultTest {

    @Test
    @DisplayName("결과값이 1, 0, -1이 아닌경우 예외가 발생한다")
    void resultTest() {
        assertThatThrownBy(() -> from(-2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결과값을 확인해주세요.");
    }

    @Test
    void 승무패결과를_확인할_수_있다() {
        Assertions.assertAll(
                () -> assertThat(from(1)).isEqualTo(WIN),
                () -> assertThat(from(0)).isEqualTo(DRAW),
                () -> assertThat(from(-1)).isEqualTo(LOSE)
        );
    }
}

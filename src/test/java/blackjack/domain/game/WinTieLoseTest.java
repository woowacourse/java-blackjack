package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WinTieLoseTest {
    @Test
    @DisplayName("승무패 값을 제대로 가지고 있는지 테스트")
    void getValueTest() {
        assertThat(WinTieLose.WIN.getValue()).isEqualTo("승");
        assertThat(WinTieLose.TIE.getValue()).isEqualTo("무");
        assertThat(WinTieLose.LOSE.getValue()).isEqualTo("패");
    }

    @Test
    @DisplayName("승무패의 반대값을 제대로 반환하는지 테스트")
    void reverseValueTest() {
        assertThat(WinTieLose.WIN.reverseValue()).isEqualTo(WinTieLose.LOSE);
        assertThat(WinTieLose.TIE.reverseValue()).isEqualTo(WinTieLose.TIE);
        assertThat(WinTieLose.LOSE.reverseValue()).isEqualTo(WinTieLose.WIN);
    }

}

package blackjack.domain.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WinTieLoseTest {
    @Test
    void getValueTest(){
        assertThat(WinTieLose.WIN.getValue()).isEqualTo("승");
        assertThat(WinTieLose.TIE.getValue()).isEqualTo("무");
        assertThat(WinTieLose.LOSE.getValue()).isEqualTo("패");
    }
}

package model.user;

import static model.user.Result.LOSE;
import static model.user.Result.TIE;
import static model.user.Result.WIN;
import static model.user.Result.judge;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("딜러와 플레이어의 카드 총 합에 따라 Score를 반환한다.")
    @Test
    void judgeTest() {
        // given, when, then
        assertAll(
                () -> assertThat(judge(21, 21)).isEqualTo(TIE),
                () -> assertThat(judge(20, 20)).isEqualTo(TIE),

                () -> assertThat(judge(19, 20)).isEqualTo(WIN),
                () -> assertThat(judge(22, 21)).isEqualTo(WIN),

                () -> assertThat(judge(21, 23)).isEqualTo(LOSE),
                () -> assertThat(judge(21, 20)).isEqualTo(LOSE)
        );
    }
}

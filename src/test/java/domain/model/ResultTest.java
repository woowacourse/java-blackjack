package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.vo.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("결과 생성 테스트")
    public void testCreate() {
        //given
        final int victory = 1;
        final int draw = 2;
        final int defeat = 3;

        //when
        final Result result = new Result(victory, draw, defeat);

        //then
        assertThat(result.getVictory()).isEqualTo(victory);
        assertThat(result.getDraw()).isEqualTo(draw);
        assertThat(result.getDefeat()).isEqualTo(defeat);
    }
}

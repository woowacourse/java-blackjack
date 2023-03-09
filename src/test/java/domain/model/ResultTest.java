package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.vo.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("빈 결과 생성 테스트")
    public void testCreate() {
        //given
        //when
        Result result = Result.empty();

        //then
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDraw()).isEqualTo(0);
        assertThat(result.getDefeat()).isEqualTo(0);
    }

    @Test
    @DisplayName("결과 승리 생성 테스트")
    public void testVictoryCreate() {
        //given
        //when
        Result result = Result.victory();

        //then
        assertThat(result.getVictory()).isEqualTo(1);
        assertThat(result.getDraw()).isEqualTo(0);
        assertThat(result.getDefeat()).isEqualTo(0);
    }

    @Test
    @DisplayName("결과 무승부 생성 테스트")
    public void testDrawCreate() {
        //given
        //when
        Result result = Result.draw();

        //then
        assertThat(result.getDraw()).isEqualTo(1);
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDefeat()).isEqualTo(0);
    }

    @Test
    @DisplayName("결과 패배 생성 테스트")
    public void testDefeatCreate() {
        //given
        //when
        Result result = Result.defeat();

        //then
        assertThat(result.getDefeat()).isEqualTo(1);
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDraw()).isEqualTo(0);
    }

    @Test
    @DisplayName("승리 추가 테스트")
    public void testAddVictory() {
        //given
        Result result = Result.empty();

        //when
        result = result.addVictory();

        //then
        assertThat(result.getVictory()).isEqualTo(1);
        assertThat(result.getDraw()).isEqualTo(0);
        assertThat(result.getDefeat()).isEqualTo(0);
    }

    @Test
    @DisplayName("무승부 추가 테스트")
    public void testAddDraw() {
        //given
        Result result = Result.empty();

        //when
        result = result.addDraw();

        //then
        assertThat(result.getDraw()).isEqualTo(1);
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDefeat()).isEqualTo(0);
    }

    @Test
    @DisplayName("패배 추가 테스트")
    public void testAddDefeat() {
        //given
        Result result = Result.empty();

        //when
        result = result.addDefeat();

        //then
        assertThat(result.getDefeat()).isEqualTo(1);
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDraw()).isEqualTo(0);
    }
}

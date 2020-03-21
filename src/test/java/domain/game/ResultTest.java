package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @Test
    @DisplayName("Result 생성")
    void create() {
        assertThat(new Result()).isInstanceOf(Result.class);
    }

    @Test
    @DisplayName("winCount 증가 확인")
    void addWinCount() {
        Result result = new Result();

        assertThat(result.getWinCount()).isEqualTo(0);

        result.addWinCount();
        assertThat(result.getWinCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("loseCount 증가 확인")
    void addLoseCount() {
        Result result = new Result();

        assertThat(result.getLoseCount()).isEqualTo(0);

        result.addLoseCount();
        assertThat(result.getLoseCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이 횟수 1초과 확인")
    void isPlayCountMoreThanOne_IfPlayedMoreThanOnce_IsTrue() {
        Result result = new Result();

        result.addLoseCount();
        result.addLoseCount();
        assertThat(result.isPlayCountMoreThanOne()).isTrue();
    }

    @Test
    @DisplayName("1회 이상 승리 확인")
    void hasWin_IfWonMoreThanOnce_IsTrue() {
        Result result = new Result();

        result.addLoseCount();
        result.addWinCount();
        assertThat(result.hasWin()).isTrue();
    }
}

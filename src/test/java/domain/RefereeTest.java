package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RefereeTest {

    private Referee referee;

    @BeforeEach
    void beforeEach() {
        referee = new Referee();
    }

    @DisplayName("플레이어 점수가 딜러보다 높으면 승리한다")
    @Test
    void 플레이어_점수가_딜러보다_높으면_승리한다() {
        assertThat(referee.judge(21, 10)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 점수가 딜러보다 낮으면 패배한다")
    @Test
    void 플레이어_점수가_딜러보다_낮으면_패배한다() {
        assertThat(referee.judge(7, 19)).isEqualTo(Result.LOSE);
    }

    @DisplayName("동점이면 무승부가 된다")
    @Test
    void 동점이면_딜러가_이긴다() {
        assertThat(referee.judge(10, 10)).isEqualTo(Result.TIE);
    }

    @DisplayName("플레이어가 버스트면 패배한다")
    @Test
    void 플레이어가_버스트면_패배한다() {
        assertThat(referee.judge(22, 5)).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러가 버스트면 플레이어가 승리한다")
    @Test
    void 딜러가_버스트면_플레이어가_승리한다() {
        assertThat(referee.judge(7, 22)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러 모두 버스트면 플레이어가 패배한다")
    @Test
    void 양쪽_모두_버스트면_플레이어가_패배한다() {
        assertThat(referee.judge(22, 22)).isEqualTo(Result.LOSE);
    }
}

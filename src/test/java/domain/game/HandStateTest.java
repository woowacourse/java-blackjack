package domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandStateTest {

    @DisplayName("블랙잭 vs 블랙잭이면 무승부다")
    @Test
    void 블랙잭_vs_블랙잭이면_무승부다() {
        assertThat(new Blackjack().versus(new Blackjack())).isSameAs(Outcome.TIE);
    }

    @DisplayName("블랙잭 vs Stay면 블랙잭 승리다")
    @Test
    void 블랙잭_vs_Stay면_블랙잭_승리다() {
        assertThat(new Blackjack().versus(new Stay(21))).isSameAs(Outcome.BLACKJACK_WIN);
    }

    @DisplayName("블랙잭 vs 버스트면 블랙잭 승리다")
    @Test
    void 블랙잭_vs_버스트면_블랙잭_승리다() {
        assertThat(new Blackjack().versus(new Bust())).isSameAs(Outcome.BLACKJACK_WIN);
    }

    @DisplayName("버스트는 상대가 무엇이든 패배한다")
    @Test
    void 버스트는_무조건_패배한다() {
        assertThat(new Bust().versus(new Blackjack())).isSameAs(Outcome.LOSE);
        assertThat(new Bust().versus(new Stay(15))).isSameAs(Outcome.LOSE);
        assertThat(new Bust().versus(new Bust())).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Stay vs 블랙잭이면 패배한다")
    @Test
    void Stay_vs_블랙잭이면_패배한다() {
        assertThat(new Stay(21).versus(new Blackjack())).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Stay vs 버스트면 승리한다")
    @Test
    void Stay_vs_버스트면_승리한다() {
        assertThat(new Stay(15).versus(new Bust())).isSameAs(Outcome.WIN);
    }

    @DisplayName("Stay vs Stay에서 점수가 높으면 승리한다")
    @Test
    void Stay_점수가_높으면_승리한다() {
        assertThat(new Stay(20).versus(new Stay(18))).isSameAs(Outcome.WIN);
    }

    @DisplayName("Stay vs Stay에서 점수가 같으면 무승부다")
    @Test
    void Stay_점수가_같으면_무승부다() {
        assertThat(new Stay(18).versus(new Stay(18))).isSameAs(Outcome.TIE);
    }

    @DisplayName("Stay vs Stay에서 점수가 낮으면 패배한다")
    @Test
    void Stay_점수가_낮으면_패배한다() {
        assertThat(new Stay(15).versus(new Stay(18))).isSameAs(Outcome.LOSE);
    }
}
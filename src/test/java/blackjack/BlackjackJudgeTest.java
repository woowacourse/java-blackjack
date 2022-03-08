package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class BlackjackJudgeTest {
    @Test
    void 딜러가_패배하는_경우_테스트() {
        boolean dealerWin = BlackjackJudge.judge(new Cards("1다이아몬드", "2하트"), new Cards("3클로버", "4하트"));
        assertThat(dealerWin).isFalse();
    }

    @Test
    void 딜러가_이기는_경우_테스트() {
        boolean dealerWin = BlackjackJudge.judge(new Cards("3클로버", "4하트"), new Cards("1다이아몬드", "2하트"));
        assertThat(dealerWin).isTrue();
    }

    @Test
    void J_Q_K_카드_점수_계산() {
        boolean dealerWin = BlackjackJudge.judge(new Cards("Q클로버", "J하트"), new Cards("K다이아몬드", "2하트"));
        assertThat(dealerWin).isTrue();
    }

    @Test
    void ACE_카드_점수_계산() {
        boolean dealerWin = BlackjackJudge.judge(new Cards("Q클로버", "J하트"), new Cards("A다이아몬드", "2하트"));
        assertThat(dealerWin).isTrue();
    }

    @Test
    void 버스트가_발생하는_경우_테스트() {
        boolean dealerWin = BlackjackJudge
            .judge(new Cards("Q클로버", "J하트", "K클로버"), new Cards("A다이아몬드", "2하트"));
        assertThat(dealerWin).isFalse();
    }

    @Test
    void ACE를_1로_처리해야_이기는_경우_테스트() {
        boolean dealerWin = BlackjackJudge
            .judge(new Cards("A다이아몬드", "J하트", "Q클로버"), new Cards("Q클로버", "J하트"));
        assertThat(dealerWin).isTrue();
    }

    @Test
    void 서로_점수가_같아_무승부인_경우() {
        Result result = BlackjackJudge.judge(new Cards("8다이아몬드", "4클로버"), new Cards("6다이아몬드", "6하트"));
        assertThat(result).isEqualTo(Result.DRAW);
    }
}

package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class BlackJackTest {
    @Test
    void 딜러가_패배하는_경우_테스트() {
        boolean dealerWin = BlackJack.play(List.of("1다이아몬드", "2하트"), List.of("3클로버", "4하트"));
        assertThat(dealerWin).isFalse();
    }

    @Test
    void 딜러가_이기는_경우_테스트() {
        boolean dealerWin = BlackJack.play(List.of("3클로버", "4하트"), List.of("1다이아몬드", "2하트"));
        assertThat(dealerWin).isTrue();
    }

    @Test
    void J_Q_K_카드_점수_계산() {
        boolean dealerWin = BlackJack.play(List.of("Q클로버", "J하트"), List.of("K다이아몬드", "2하트"));
        assertThat(dealerWin).isTrue();
    }

    @Test
    void ACE_카드_점수_계산() {
        boolean dealerWin = BlackJack.play(List.of("Q클로버", "J하트"), List.of("A다이아몬드", "2하트"));
        assertThat(dealerWin).isTrue();
    }

    @Test
    void 버스트가_발생하는_경우_테스트() {
        boolean dealerWin = BlackJack.play(List.of("Q클로버", "J하트", "K클로버"), List.of("A다이아몬드", "2하트"));
        assertThat(dealerWin).isFalse();
    }

    @Test
    void ACE를_1로_처리해야_이기는_경우_테스트() {
        boolean dealerWin = BlackJack.play(List.of("A다이아몬드", "J하트", "Q클로버"), List.of("Q클로버", "J하트"));
        assertThat(dealerWin).isTrue();
    }
}

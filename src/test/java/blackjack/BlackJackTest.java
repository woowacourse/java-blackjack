package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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

}

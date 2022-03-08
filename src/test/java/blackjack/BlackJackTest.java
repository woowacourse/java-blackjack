package blackjack;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlackJackTest {
    @Test
    void 딜러가_패배하는_경우_테스트() {
        boolean dealerWin = BlackJack.play(List.of("8다이아몬드", "9하트"), List.of("J클로버", "Q하트"));
        Assertions.assertThat(dealerWin).isFalse();
    }

    @Test
    void 딜러가_이기는_경우_테스트() {
        boolean dealerWin = BlackJack.play(List.of("J클로버", "Q하트"), List.of("8다이아몬드", "9하트"));
        Assertions.assertThat(dealerWin).isTrue();
    }

}

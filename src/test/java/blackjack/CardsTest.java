package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    void 카드_합_계산() {
        Cards cards = new Cards("1다이아몬드", "2다이아몬드");
        assertThat(cards.score()).isEqualTo(3);
    }

}

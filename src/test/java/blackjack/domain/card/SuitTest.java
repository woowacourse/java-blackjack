package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuitTest {

    @Test
    void 카드_모양은_하트_클로버_스페이드_다이아몬드로_4가지이다() {
        final Suit[] suits = Suit.values();
        assertThat(suits).containsExactly(Suit.HEART, Suit.CLOVER, Suit.SPADE, Suit.DIAMOND);
    }
}

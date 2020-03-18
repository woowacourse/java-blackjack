package second.domain.card;

import org.junit.jupiter.api.Test;
import second.domain.player.Dealer;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    void 딜러가_카드를_더뽑을수있는지_확인() {
        Dealer dealer = new Dealer();

        dealer.draw(Card.of(Rank.SIX, Suit.CLOVER));
        dealer.draw(Card.of(Rank.Q, Suit.CLOVER));
        assertThat(dealer.canDrawMore()).isTrue();

        dealer.draw(Card.of(Rank.FOUR, Suit.CLOVER));
        assertThat(dealer.canDrawMore()).isFalse();
    }
}

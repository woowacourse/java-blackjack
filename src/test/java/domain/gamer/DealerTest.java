package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    void 딜러가_카드를_더뽑을수있는지_확인() {
        Dealer dealer = new Dealer();

        dealer.drawCard(() -> Card.of(Rank.SIX, Suit.CLOVER));
        dealer.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        assertThat(dealer.canDrawMore()).isTrue();

        dealer.drawCard(() -> Card.of(Rank.FOUR, Suit.CLOVER));
        assertThat(dealer.canDrawMore()).isFalse();
    }
}

package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Hand emptyHand = new Hand();

    @Test
    void 처음_딜링된_카드는_1장만_반환한다() {
        // given
        Dealer dealer = new Dealer("딜러", emptyHand);
        dealer.hit(new Card(Rank.ACE, Suit.CLOVER));
        dealer.hit(new Card(Rank.TWO, Suit.CLOVER));
        // when
        List<Card> visibleCards = dealer.getInitialVisibleCards();
        // then
        assertThat(visibleCards.size()).isEqualTo(1);
    }

    @Test
    void 딜링된_카드가_없는_경우_빈_리스트를_반환한다() {
        // given
        Dealer dealer = new Dealer("딜러", emptyHand);
        // when
        List<Card> visibleCards = dealer.getInitialVisibleCards();
        // then
        assertThat(visibleCards.size()).isEqualTo(0);
    }
}

package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        dealer = new Dealer();
    }

    @DisplayName("점수가 16 이하면 히트할 수 있다")
    @Test
    void 점수가_16이하면_히트할_수_있다() {
        dealer.addCard(new Card(Rank.KING, Suit.SPADE));  // 10
        dealer.addCard(new Card(Rank.SIX, Suit.HEART));   // 6
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("점수가 17 이상이면 히트할 수 없다")
    @Test
    void 점수가_17이상이면_히트할_수_없다() {
        dealer.addCard(new Card(Rank.KING, Suit.SPADE));  // 10
        dealer.addCard(new Card(Rank.SEVEN, Suit.HEART)); // 7
        assertThat(dealer.canHit()).isFalse();
    }
}

package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    void 점수가_임계점을_초과하면_스탠드한다() {
        // given
        Dealer dealer = Dealer.create();
        dealer.hit(new Card(Rank.SEVEN, Suit.CLOVER));
        dealer.hit(new Card(Rank.TEN, Suit.CLOVER));
        // when
        boolean canHit = dealer.canHit();
        // then
        assertThat(canHit).isFalse();
    }

    @Test
    void 점수가_임계점_이하면_힛한다() {
        // given
        Dealer dealer = Dealer.create();
        dealer.hit(new Card(Rank.FIVE, Suit.CLOVER));
        dealer.hit(new Card(Rank.TEN, Suit.CLOVER));
        // when
        boolean shouldHit = dealer.canHit();
        // then
        assertThat(shouldHit).isTrue();
    }
}

package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {
    private final Hand emptyHand = new Hand();

    @Test
    void 점수가_임계점을_초과하면_스탠드한다() {
        // given
        Dealer dealer = new Dealer("딜러", emptyHand);
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
        Dealer dealer = new Dealer("딜러", emptyHand);
        dealer.hit(new Card(Rank.FIVE, Suit.CLOVER));
        dealer.hit(new Card(Rank.TEN, Suit.CLOVER));
        // when
        boolean shouldHit = dealer.canHit();
        // then
        assertThat(shouldHit).isTrue();
    }
}

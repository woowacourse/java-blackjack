package blackjack.domain.participants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    void 점수가_임계점을_초과하면_스탠드한다() {
        // given
        Hand hand = new Hand(List.of(
            new Card(Rank.SEVEN, Suit.CLOVER),
            new Card(Rank.TEN, Suit.CLOVER)));
        Dealer dealer = new Dealer(hand);
        // when
        boolean canHit = dealer.canHit();
        // then
        assertThat(canHit).isFalse();
    }

    @Test
    void 점수가_임계점_이하면_힛한다() {
        // given
        Hand hand = new Hand(List.of(
            new Card(Rank.SIX, Suit.CLOVER),
            new Card(Rank.TEN, Suit.CLOVER)));
        Dealer dealer = new Dealer(hand);
        // when
        boolean shouldHit = dealer.canHit();
        // then
        assertThat(shouldHit).isTrue();
    }
}

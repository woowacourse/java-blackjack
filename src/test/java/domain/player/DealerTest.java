package domain.player;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 카드의 합이 17 미만이라면 히트할 수 있다")
    void canHit() {
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(Rank.TEN, Suit.CLUBS));
        dealer.hit(new Card(Rank.SIX, Suit.CLUBS));

        Assertions.assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 합이 17 이상이면 히트할 수 없다")
    void canNotHit() {
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(Rank.TEN, Suit.CLUBS));
        dealer.hit(new Card(Rank.SEVEN, Suit.CLUBS));

        Assertions.assertThat(dealer.canHit()).isFalse();
    }
}

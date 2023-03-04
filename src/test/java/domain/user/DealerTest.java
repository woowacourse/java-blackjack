package domain.user;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @Test
    @DisplayName("17이면 히트가 아니다")
    void notHit() {
        Dealer dealer = new Dealer("deal", new Hand(List.of(
                new Card(Suit.HEART, Denomination.SIX),
                new Card(Suit.HEART, Denomination.ACE)
        )));

        assertThat(dealer.isHit()).isFalse();
    }

    @Test
    @DisplayName("16이면 히트다")
    void isHit() {
        Dealer dealer = new Dealer("deal", new Hand(List.of(
                new Card(Suit.HEART, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.ACE)
        )));

        assertThat(dealer.isHit()).isTrue();
    }
}

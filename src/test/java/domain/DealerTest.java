package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;
import domain.user.CardPool;
import domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @Test
    @DisplayName("17이면 히트가 아니다")
    void notHit() {
        Dealer dealer = new Dealer("deal", new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.SIX),
                new Card(CardType.HEART, CardNumber.ACE)
        )));

        assertThat(dealer.isHit()).isFalse();
    }

    @Test
    @DisplayName("16이면 히트다")
    void isHit() {
        Dealer dealer = new Dealer("deal", new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.FIVE),
                new Card(CardType.HEART, CardNumber.ACE)
        )));

        assertThat(dealer.isHit()).isTrue();
    }
}

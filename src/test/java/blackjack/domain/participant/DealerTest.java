package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class DealerTest {

    @Test
    @DisplayName("딜러가 카드를 더 받아야할지 체크한다.")
    void checkDealerHit() {
        Dealer dealer = new Dealer();

        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.SIX)
        ));

        assertThat(dealer.checkMustHit()).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받지 않아도 될지 체크한다.")
    void checkDealerNotHit() {
        Dealer dealer = new Dealer();

        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.KING),
            new Card(Suit.HEART, Denomination.SEVEN)
        ));

        assertThat(dealer.checkMustHit()).isFalse();
    }
}

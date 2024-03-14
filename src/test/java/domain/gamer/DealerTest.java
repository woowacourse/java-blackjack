package domain.gamer;

import domain.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DealerTest {

    List<Card> unShuffledDeck;

    @BeforeEach
    void setUp() {
        unShuffledDeck = Card.makeCardDeck();
    }

    @DisplayName("카드를 더 받을 수 있는지(Hit) 알려준다.")
    @Test
    void checkCanDealerHit() {
        Card twoCard = unShuffledDeck.get(1);
        Card threeCard = unShuffledDeck.get(2);

        Dealer dealer = new Dealer();
        dealer.hit(twoCard);
        dealer.hit(threeCard);
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("카드를 더 받을 수 없는지(Hit) 알려준다.")
    @Test
    void lowerThanCannotHitThresholdTest() {
        Card tenCard = unShuffledDeck.get(9);
        Card sevenCard = unShuffledDeck.get(6);

        Dealer dealer = new Dealer();
        dealer.hit(tenCard);
        dealer.hit(sevenCard);

        assertThat(dealer.canHit()).isFalse();
    }
}

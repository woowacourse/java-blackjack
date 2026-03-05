package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        Dealer dealer = new Dealer();

        dealer.hit(new Card(Rank.ACE, Suit.DIAMOND));

        Assertions.assertEquals(dealer.getHand().getHand().size(), 1);
    }
}

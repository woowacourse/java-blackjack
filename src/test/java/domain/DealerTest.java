package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.Rank;
import vo.Suit;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void 딜러_16이하_히트_필요() {
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.SIX));

        assertThat(dealer.determineDealerDealMore()).isTrue();
    }

    @Test
    void 딜러_17이상_히트_불필요() {
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.HEART, Rank.SEVEN));

        assertThat(dealer.determineDealerDealMore()).isFalse();
    }
}
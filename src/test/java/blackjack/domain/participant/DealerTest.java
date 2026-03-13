package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("점수가 17미만일 때 Hit 가능")
    void test_can_hit_before_17() {

        dealer.addCard(new Card(Suit.HEART, Rank.JACK));
        dealer.addCard(new Card(Suit.CLOVER, Rank.SIX));

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("점수가 17이상일 때 Hit 불가")
    void test_cannot_hit_at_17() {

        dealer.addCard(new Card(Suit.HEART, Rank.JACK));
        dealer.addCard(new Card(Suit.CLOVER, Rank.SEVEN));

        assertThat(dealer.canHit()).isFalse();
    }
}

package domain.people;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

class DealerTest {

    @Test
    @DisplayName("딜러의 handValue가 16이하인 경우 true를 반환한다.")
    void shouldHit() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(Card.from(Suit.SPADE, Rank.FOUR));
        assertThat(dealer.shouldHit()).isTrue();
    }
}

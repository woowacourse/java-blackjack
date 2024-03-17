package domain.user;

import static domain.card.Number.JACK;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("손패의 합이 16 이하이면 receivable이다.")
    void isReceivableTest() {
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, JACK), new Card(SPADE, SIX)));

        assertThat(dealer.isReceivable()).isTrue();
    }

    @Test
    @DisplayName("손패의 합이 16 초과이면 receivable이지 않다.")
    void isNotReceivableTest() {
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, JACK), new Card(SPADE, SEVEN)));

        assertThat(dealer.isReceivable()).isFalse();
    }
}

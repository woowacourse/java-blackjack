package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러는 참가자를 상속한다.")
    @Test
    void extendsTest() {
        Dealer dealer = new Dealer(CardDeck.createShuffledDeck());

        assertThat(dealer).isInstanceOf(Participant.class);
    }

    @DisplayName("손패가 16이하이면 히트한다.")
    @Test
    void canHit() {
        Dealer dealer = new Dealer(CardDeck.createShuffledDeck());
        dealer.initHand(List.of(TEN_HEART, SIX_HEART));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isTrue();
    }

    @DisplayName("손패가 17이상이면 스테이해야 한다.")
    @Test
    void cantHit() {
        Dealer dealer = new Dealer(CardDeck.createShuffledDeck());
        dealer.initHand(List.of(TEN_HEART, SEVEN_HEART));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isFalse();
    }
}

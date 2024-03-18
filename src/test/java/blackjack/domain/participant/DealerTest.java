package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.fixture.FixtureCard.*;
import static blackjack.domain.fixture.FixtureCardDeck.NOT_SHUFFLED_CARD_DECK;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러는 참가자를 상속한다.")
    @Test
    void extendsTest() {
        Dealer dealer = new Dealer(NOT_SHUFFLED_CARD_DECK);

        assertThat(dealer).isInstanceOf(Participant.class);
    }

    @DisplayName("손패가 16이하이면 히트한다.")
    @Test
    void canHit() {
        Dealer dealer = new Dealer(NOT_SHUFFLED_CARD_DECK);
        dealer.initHand(List.of(SIX_HEART, TEN_HEART));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isTrue();
    }

    @DisplayName("손패가 17이상이면 스테이해야 한다.")
    @Test
    void cantHit() {
        Dealer dealer = new Dealer(NOT_SHUFFLED_CARD_DECK);
        dealer.initHand(List.of(SEVEN_HEART, TEN_HEART));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isFalse();
    }
}

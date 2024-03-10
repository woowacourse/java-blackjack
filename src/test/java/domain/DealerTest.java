package domain;

import static domain.FixtureCard.SEVEN_HEARTS;
import static domain.FixtureCard.SIX_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 참가자를 상속한다.")
    @Test
    void extendsTest() {
        Dealer dealer = new Dealer(new CardDeck());

        assertThat(dealer).isInstanceOf(Player.class);
    }

    @DisplayName("손패가 16이하이면 히트한다.")
    @Test
    void canHit() {
        Dealer dealer = new Dealer(List.of(TEN_HEARTS, SIX_HEARTS));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isTrue();
    }

    @DisplayName("손패가 17이상이면 스테이해야 한다.")
    @Test
    void cantHit() {
        Dealer dealer = new Dealer(List.of(TEN_HEARTS, SEVEN_HEARTS));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isFalse();
    }
}

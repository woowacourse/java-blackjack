package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Card card1 = new Card(Letter.TEN, Mark.HEART);
        Card card2 = new Card(Letter.SIX, Mark.HEART);
        Dealer dealer = new Dealer(List.of(card1, card2));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isTrue();
    }

    @DisplayName("손패가 17이상이면 스테이해야 한다.")
    @Test
    void cantHit() {
        Card card1 = new Card(Letter.TEN, Mark.HEART);
        Card card2 = new Card(Letter.SEVEN, Mark.HEART);
        Dealer dealer = new Dealer(List.of(card1, card2));

        boolean canHit = dealer.isHittable();

        assertThat(canHit).isFalse();
    }
}

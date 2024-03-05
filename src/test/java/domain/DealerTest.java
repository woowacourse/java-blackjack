package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @DisplayName("딜러는 참가자를 상속한다.")
    @Test
    void extendsTest() {
        Card card1 = new Card(Letter.FIVE, Mark.HEART);
        Card card2 = new Card(Letter.FOUR, Mark.HEART);
        Hand hand = new Hand(List.of(card1, card2));
        Dealer dealer = new Dealer(hand);

        assertThat(dealer).isInstanceOf(Player.class);
    }

    @DisplayName("손패가 16이하이면 히트한다.")
    @Test
    void canHit() {
        Card card1 = new Card(Letter.TEN, Mark.HEART);
        Card card2 = new Card(Letter.SIX, Mark.HEART);
        Hand hand = new Hand(List.of(card1, card2));
        Dealer dealer = new Dealer(hand);

        boolean canHit = dealer.canHit();

        assertThat(canHit).isTrue();
    }

    @DisplayName("손패가 17이상이면 스테이해야 한다.")
    @Test
    void cantHit() {
        Card card1 = new Card(Letter.TEN, Mark.HEART);
        Card card2 = new Card(Letter.SEVEN, Mark.HEART);
        Hand hand = new Hand(List.of(card1, card2));
        Dealer dealer = new Dealer(hand);

        boolean canHit = dealer.canHit();

        assertThat(canHit).isFalse();
    }
}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DealerTest {

    @DisplayName("딜러는 참가자를 상속한다.")
    @Test
    void extendsTest() {
        Card card1 = new Card(Letter.FIVE, Mark.HEART);
        Card card2 = new Card(Letter.FOUR, Mark.HEART);
        Hand hand = new Hand(List.of(card1, card2));
        Dealer dealer = new Dealer(hand);

        Assertions.assertThat(dealer).isInstanceOf(Player.class);
    }
}

package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    @DisplayName("딜러는 카드의 합이 17점 이상이 될 때까지 카드를 받는다")
    void canHitTest() {
        // given
        Dealer dealer = new Dealer(new SequentialNumberGenerator(List.of(0, 0, 0, 0)));

        // when
        dealer.doAction(new SequentialNumberGenerator(List.of(0, 8, 0, 2)));

        // then
        int cardsTotal = dealer.getHand().calculateCardsTotal();
        assertThat(cardsTotal).isGreaterThan(17);
    }
}

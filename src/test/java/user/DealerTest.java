package user;

import card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("생성했을 때, 두 장의 카드를 보유하고 있는지 확인")
    @Test
    void constructor_InitializeDealer_HandsSizeIsTwo() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);
        assertThat(dealer.handSize()).isEqualTo(2);
    }
}

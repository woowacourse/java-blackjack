package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DealerTest {

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        Participant dealer = new Dealer("pobi", new ArrayList<>(), cards -> 0);
        Card card = new Card(CardType.DIAMOND, CardValue.TEN);
        dealer.receiveCard(card);
        Assertions.assertThat(dealer.showCards().contains(card)).isTrue();
    }
}

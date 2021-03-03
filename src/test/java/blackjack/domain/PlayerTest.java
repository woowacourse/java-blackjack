package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PlayerTest {

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        Participant player = new Player("pobi", new ArrayList<>(), cards -> 0);
        Card card = new Card(CardType.DIAMOND, CardValue.TEN);
        player.receiveCard(card);
        Assertions.assertThat(player.showCards().contains(card)).isTrue();
    }
}

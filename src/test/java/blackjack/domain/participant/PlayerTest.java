package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 정상적으로 생성되는지 확인")
    void create() {
        Player player = new Player("승팡");

        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        Player player = new Player("필즈");
        Card card = new Card(Denomination.ACE, Suit.SPADE);

        player.receiveCard(card);

        assertThat(player.getCards()).containsExactly(card);
    }
}

package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("카드 받기 테스트")
    @Test
    void draw() {
        Player player = new Player();
        Deck deck = new Deck();
        player.draw(deck, 0);

        assertThat(player.cards().getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
    }
}

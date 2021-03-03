package blackjack.domain;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("히트 - 플레이어는 카드를 받는다.")
    @Test
    void hitCard() {
        Player player = new Player();
        CardDeck cardDeck = CardDeck.createDeck();

        player.hit(cardDeck.getDeck().pop());

        assertThat(player.getCards()).hasSize(1);
    }

}
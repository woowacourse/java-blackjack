package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 초기 카드 2장을 받는다") 
    void get_two_cards() {
        Player player = new Player();
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);
        player.getInitialCards(List.of(card1, card2));

        assertThat(player.getHoldingCards())
                .containsExactly(card1, card2);
    }
}

package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("플레이어 카드 추가")
    void addCard() {
        Player player = new Player("wannte");
        Card card = Deck.draw();
        player.addCard(card);
        assertThat(player.getCards()).containsExactly(card);
    }
}

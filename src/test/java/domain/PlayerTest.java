package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("카드를 받아 수중에 카드를 추가한다")
    void should_add_Card_card() {
        // given
        Card card = new Card(Shape.HEART, Rank.A);
        Player player = new Player("a");

        // then
        player.addCard(card);

        // when
        assertThat(player.getCards()).hasSize(1);
    }
}

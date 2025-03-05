package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @DisplayName("카드를 추가할 수 있다.")
    @Test
    void 카드_추가_가능() {
        // given
        Player player = new Player();
        Card card = new Card(CardNumber.A, CardShape.CLOVER);
        player.addCard(card);

        // when
        Cards cards = player.getCards();

        // then
        assertThat(cards.getCards()).contains(card);
    }
}

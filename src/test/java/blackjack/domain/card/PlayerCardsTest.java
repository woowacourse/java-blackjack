package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerCardsTest {
    @DisplayName("추가 테스트")
    @Test
    void create() {
        PlayerCards cards = new PlayerCards();
        assertThat(cards.getCards()).hasSize(0);
        cards.add(Card.valueOf(CardShape.CLUB, CardNumber.ACE));
        assertThat(cards.getCards()).hasSize(1);
    }
}

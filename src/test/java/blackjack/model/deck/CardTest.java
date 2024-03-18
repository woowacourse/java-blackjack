package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    private List<Card> cards;

    @BeforeEach
    void init() {
        cards = Card.getCards();
    }

    @Test
    @DisplayName("카드 목록을 생성한다.")
    void createDeck() {
        assertThat(cards).hasSize(52);
    }
}

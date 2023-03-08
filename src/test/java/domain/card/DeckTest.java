package domain.card;

import domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @DisplayName("초기 카드 더미의 크기는 52장이다.")
    @Test
    void createDeckSizeSuccessTest() {
        Deck deck = new Deck();

        assertThat(deck).extracting("shuffledDeck")
                .asList()
                .hasSize(52);
    }

    @DisplayName("카드가 남아있지 않으면 뽑을 수 없다.")
    @Test
    void drawCardFailTestWhenDeckIsEmpty() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class);
    }
}

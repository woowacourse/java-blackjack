package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @DisplayName("초기 카드 더미의 크기는 52장이다.")
    @Test
    void createDeckSizeSuccessTest(){
        Deck deck = DeckFactory.getShuffledDeck();

        assertDeckSize(deck, 52);
    }

    @DisplayName("카드가 남아있지 않으면 뽑을 수 없다.")
    @Test
    void drawCardFailTestWhenDeckIsEmpty() {
        Deck deck = DeckFactory.getShuffledDeck();
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertDeckSize(deck, 0);
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class);
    }

    private void assertDeckSize(Deck deck, int expectedSize) {
        assertThat(deck.getSize()).isEqualTo(expectedSize);
    }
}

package domain.deck;

import static domain.card.Number.NINE;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerDeckTest {
    @Test
    @DisplayName("보여지는 카드를 반환할 때 덱의 첫 카드만 반환한다.")
    void getVisibleCardTest() {
        DealerDeck dealerDeck = new DealerDeck();
        Card card = new Card(CLOVER, TEN);

        dealerDeck.addCard(card);
        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.getVisibleCards()).containsExactly(card);
    }

    @Test
    @DisplayName("카드의 합이 16 이하면 true를 반환한다.")
    void isAddableTest() {
        DealerDeck dealerDeck = new DealerDeck();

        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.isAddable()).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 16 초과면 false를 반환한다.")
    void isNotAddableTest() {
        DealerDeck dealerDeck = new DealerDeck();

        dealerDeck.addCard(new Card(CLOVER, NINE));
        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.isAddable()).isFalse();
    }
}

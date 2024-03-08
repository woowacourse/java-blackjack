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
    @DisplayName("딜러덱의 첫 카드를 반환한다.")
    void getVisibleCardTest() {
        DealerDeck dealerDeck = new DealerDeck();
        Card card = new Card(CLOVER, TEN);

        dealerDeck.addCard(card);
        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.getVisibleCard()).isEqualTo(card);
    }

    @Test
    @DisplayName("카드의 합이 16 이하이면 true를 반환한다.")
    void isAddConditionTest() {
        DealerDeck dealerDeck = new DealerDeck();

        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.isAddCondition()).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 17 이상이면 false를 반환한다.")
    void isNotAddConditionTest() {
        DealerDeck dealerDeck = new DealerDeck();

        dealerDeck.addCard(new Card(CLOVER, NINE));
        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.isAddCondition()).isFalse();
    }
}

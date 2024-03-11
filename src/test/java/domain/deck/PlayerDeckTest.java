package domain.deck;

import static domain.card.Number.NINE;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerDeckTest {
    @Test
    @DisplayName("보여지는 카드를 반환할 때 덱의 모든 카드를 반환한다.")
    void getVisibleCardTest() {
        PlayerDeck dealerDeck = new PlayerDeck();
        Card card = new Card(CLOVER, TEN);

        dealerDeck.addCard(card);
        dealerDeck.addCard(card);

        assertThat(dealerDeck.getVisibleCards()).containsExactly(card, card);
    }

    @Test
    @DisplayName("busted가 false이면 true를 반환한다.")
    void isAddableTest() {
        PlayerDeck dealerDeck = new PlayerDeck();

        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.isAddable()).isTrue();
    }

    @Test
    @DisplayName("busted가 true이면 false를 반환한다.")
    void isNotAddableTest() {
        PlayerDeck dealerDeck = new PlayerDeck();

        dealerDeck.addCard(new Card(CLOVER, NINE));
        dealerDeck.addCard(new Card(CLOVER, NINE));
        dealerDeck.addCard(new Card(CLOVER, NINE));

        assertThat(dealerDeck.isAddable()).isFalse();
    }
}

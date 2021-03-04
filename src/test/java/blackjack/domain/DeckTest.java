package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {
    @Test
    @DisplayName("카드 분배 확인")
    void checkDealCards() {
        Card card = Deck.choiceCard(0);
        assertEquals(Deck.dealCard(), card);
    }

    @Test
    @DisplayName("제일 앞 카드 주는지 확인")
    void checkRemoveCard() {
        Card card = Deck.choiceCard(0);
        assertEquals(Deck.dealCard(), card);
    }

    @Test
    @DisplayName("카드 주고 나면 두번째 카드가 앞으로 오는지 확인")
    void checkIndex() {
        Card card = Deck.choiceCard(1);
        Deck.dealCard();
        assertEquals(card, Deck.choiceCard(0));
    }
}

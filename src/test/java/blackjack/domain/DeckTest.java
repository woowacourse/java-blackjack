package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {
    @Test
    @DisplayName("덱에서 카드 분배 확인")
    void checkDealCards() {
        Card firstCard = new Card(CardPattern.HEART, CardNumber.ACE);
        assertEquals(Deck.dealCard(), firstCard);
    }

    @Test
    @DisplayName("카드 주고 나면 두번째 카드가 앞으로 오는지 확인")
    void checkIndex() {
        Card secondCard = new Card(CardPattern.HEART, CardNumber.TWO);
        Deck.dealCard();
        assertEquals(secondCard, secondCard);
    }

    @Test
    @DisplayName("52장 주고 나면 빈 리스트이므로 에러나는지 확인")
    void checkEmptyDeck() {
        for (int i = 0; i < 52; i++) {
            Deck.dealCard();
        }
        assertThatThrownBy(Deck::dealCard).isInstanceOf(IndexOutOfBoundsException.class);
    }
}

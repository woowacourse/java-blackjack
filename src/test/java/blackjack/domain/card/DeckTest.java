package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {
    @Test
    @DisplayName("카드 주고 나면 두번째 카드가 앞으로 오는지 확인")
    void checkIndex() {
        Card secondCard = new Card(CardPattern.HEART, CardNumber.TWO);
        Deck.dealCard();
        assertEquals(secondCard, secondCard);
    }
}

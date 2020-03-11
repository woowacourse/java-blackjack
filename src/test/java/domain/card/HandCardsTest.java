package domain.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandCardsTest {
    @Test
    void 카드뽑기테스트() {
        List<Card> cards = new ArrayList<>();
        HandCards handCards = new HandCards(cards);
        handCards.drawCard(() -> Card.of(10, Symbol.HEART));
        assertThat(handCards.getCards()).contains(Card.of(10, Symbol.HEART));
    }
}
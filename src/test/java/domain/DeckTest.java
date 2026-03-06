package domain;

import domain.card.Card;
import domain.card.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class DeckTest {

    @Test
    @DisplayName("52장의 카드를 생성했는지 확인한다.")
    void 카드_52장_생성_성공() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        Assertions.assertThrows(IllegalArgumentException.class, deck::drawCard);
    }

    @Test
    @DisplayName("52장의 카드가 중복이 없는지 확인한다.")
    void 카드_52장_중복_없음_성공() {
        Deck deck = new Deck();
        Set<Card> set = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = deck.drawCard();
            set.add(card);
        }

        Assertions.assertEquals(set.size(), 52);
    }
}

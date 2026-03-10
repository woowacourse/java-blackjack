package domain.card;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    @Test
    @DisplayName("초기에 생성할 때, 완전한 카드 덱을 구성한다.")
    void createCardsTest() {
        Deck deck = new Deck();
        long cardSize = deck.getCards().stream().distinct().count();
        assertThat(cardSize).isEqualTo(52);
    }

    @Test
    @DisplayName("초기 카드는 두 장씩 나누어준다.")
    void dealFirstHandCardsTest() {
        Deck deck = new Deck();
        List<Card> cards = deck.dealFirstHandCards();
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 한 장 뽑는다.")
    void drawOneCardTest() {
        Deck deck = new Deck();
        deck.drawCard();
        assertThat(deck.getCards().size()).isEqualTo(51);
    }

    @Test
    @DisplayName("덱에 카드가 없을 때 예외를 반환한다.")
    void emptyDeckTest() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        Assertions.assertThrows(IllegalStateException.class, deck::drawCard);
    }
}

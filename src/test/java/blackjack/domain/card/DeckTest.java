package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    @DisplayName("덱이 정상적으로 생성된다.")
    void createDeck() {
        int size = 52;
        List<Card> cards = createCard(size);

        Deck deck = Deck.create(cards);

        assertThat(deck).isNotNull();
    }

    @Test
    @DisplayName("덱 가장 위에 있는 카드 한 장을 가져온다.")
    void drawCardFromTopOfDeck() {
        List<Card> cards = createCard(52);
        Deck deck = Deck.create(cards);

        Card card = deck.draw();

        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("덱에 카드가 없는데 카드를 뽑는 경우 예외가 발생한다.")
    void drawCardFromEmptyDeck() {
        List<Card> cards = createCard(52);
        Deck deck = Deck.create(cards);
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }

    private List<Card> createCard(int size) {
        List<Suit> suits = Suit.getAll();
        List<Denomination> denominations = Denomination.getAll();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cards.add(Card.of(suits.get(i / 13), denominations.get(i % 13)));
        }
        return cards;
    }
}

package blackjack.domain.carddeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {

    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = CardDeck.newShuffledDeck();
    }

    @Test
    @DisplayName("카드를 한장 뽑는다.")
    void testCardDeckDraw() {
        assertThat(cardDeck.draw()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드는 52장이다.")
    void testCardDeckSize() {
        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }
        assertThatThrownBy(cardDeck::draw)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("카드 52장은 중복이 없다.")
    void testCardDuplicate() {
        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(cardDeck.draw());
        }
        assertThat(cards).hasSize(52);
    }

    @Test
    @DisplayName("문양별 카드는 각각 13장씩 존재한다.")
    void testCountOfCardPatterns() {
        Set<Card> cloverCards = new HashSet<>();
        Set<Card> spadeCards = new HashSet<>();
        Set<Card> heartCards = new HashSet<>();
        Set<Card> diamondCards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = cardDeck.draw();
            compareCardPattern(cloverCards, card, Pattern.CLOVER);
            compareCardPattern(spadeCards, card, Pattern.SPADE);
            compareCardPattern(heartCards, card, Pattern.HEART);
            compareCardPattern(diamondCards, card, Pattern.DIAMOND);
        }

        assertThat(cloverCards).hasSize(13);
        assertThat(spadeCards).hasSize(13);
        assertThat(heartCards).hasSize(13);
        assertThat(diamondCards).hasSize(13);
    }

    private void compareCardPattern(final Set<Card> cards, final Card card, final Pattern pattern) {
        if (card.getPatternName().equals(pattern.getPattern())) {
            cards.add(card);
        }
    }
}

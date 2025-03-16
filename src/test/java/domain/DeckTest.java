package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.create();
    }

    @Test
    @DisplayName("덱이 정상적으로 생성되는지 확인")
    void testDeckInitialization() {
        assertNotNull(deck, "덱이 null이어서는 안 됩니다.");
    }

    @Test
    @DisplayName("덱의 초기 크기가 52장인지 확인")
    void testDeckSize() {
        assertEquals(52, deck.getCards().size(), "덱의 카드 수는 52장이어야 합니다.");
    }

    @Test
    @DisplayName("덱에 중복된 카드가 없는지 확인")
    void testUniqueCardsInDeck() {
        List<TrumpCard> cards = deck.getCards();
        assertEquals(cards.size(), new HashSet<>(cards).size(), "덱에는 중복된 카드가 없어야 합니다.");
    }

    @Test
    @DisplayName("카드를 뽑을 때 덱 크기가 줄어드는지 확인")
    void testDrawReducesDeckSize() {
        int initialSize = deck.getCards().size();
        TrumpCard drawnCard = deck.draw();
        assertNotNull(drawnCard, "draw는 null이 아닌 카드를 반환해야 합니다.");
        assertEquals(initialSize - 1, deck.getCards().size(), "draw 호출 후 덱 크기가 줄어들어야 합니다.");
    }

    @Test
    @DisplayName("빈 덱에서 카드를 뽑을 때 예외가 발생하는지 확인")
    void testDrawFromEmptyDeckThrowsException() {
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThrows(IllegalStateException.class, deck::draw, "빈 덱에서 draw 호출 시 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("덱을 셔플한 후 카드 순서가 랜덤화되는지 확인")
    void testShuffleRandomizesDeck() {
        List<TrumpCard> originalOrder = List.copyOf(deck.getCards());
        deck.shuffle();
        List<TrumpCard> shuffledOrder = deck.getCards();
        assertNotEquals(originalOrder, shuffledOrder, "shuffle은 덱의 순서를 랜덤화해야 합니다.");
    }

    @Test
    @DisplayName("같은 카드로 생성된 두 덱이 동일한지 확인")
    void testDeckEquality() {
        Deck anotherDeck = new Deck(deck.getCards());
        assertEquals(deck, anotherDeck, "같은 카드로 생성된 덱은 동일해야 합니다.");
    }

    @Test
    @DisplayName("서로 다른 카드로 생성된 두 덱이 다른지 확인")
    void testDeckInequality() {
        Deck differentDeck = Deck.create();
        assertNotEquals(deck, differentDeck, "서로 다른 카드로 생성된 덱은 달라야 합니다.");
    }
}

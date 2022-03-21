package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.initDeck(Card.values());
    }

    @Test
    @DisplayName("Deck을 초기화했을 때, 카드가 모두 덱에 저장되는지 테스트")
    public void initDeckTest() {
        Card card1 = Card.valueOf(Symbol.DIAMOND, Denomination.TEN);
        Card card2 = Card.valueOf(Symbol.DIAMOND, Denomination.NINE);
        Card card3 = Card.valueOf(Symbol.DIAMOND, Denomination.SEVEN);

        Deck testDeck = Deck.initDeck(Arrays.asList(card1, card2, card3));
        List<Card> deckCards = testDeck.getCards();

        assertThat(deckCards).containsAll(Arrays.asList(card1, card2, card3));
    }

    @Test
    @DisplayName("Deck에서 카드를 뽑을 때, 알맞은 카드가 뽑히는지 확인한다.")
    public void handOutTest() {
        Card card1 = deck.getCards().get(0);
        Card card2 = deck.handOut();

        assertThat(card1).isEqualTo(card2);
    }

    @Test
    @DisplayName("Deck에서 처음에 카드 2장을 뽑을 때, 인덱스 0,1의 카드가 뽑히는지 확인한다.")
    public void handOutInitialTurnTest() {
        Card card1 = deck.getCards().get(0);
        Card card2 = deck.getCards().get(1);

        List<Card> cards = deck.handOutInitialTwoCards();
        assertThat(cards).contains(card1);
        assertThat(cards).contains(card2);
    }

}

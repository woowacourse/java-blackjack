package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;
    @BeforeEach
    public void setUp() {
        deck = new Deck(new AlwaysDescNumberMachine());
    }
    @Test
    @DisplayName("Cards 객체 생성 확인")
    public void createCards() {
        assertThat(deck).isInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("Cards 반환 확인")
    public void checkCardReturn() {
        Card card = deck.assignCard();
        Card compareCard = new Card(Suit.CLUB, Rank.KING);
        assertThat(card).isEqualTo(compareCard);
    }

    @Test
    @DisplayName("차례대로 받아오는 카드 두 개가 다른 카드인지 확인")
    public void checkTwoDifferentCards() {
        Card firstCard = deck.assignCard();
        Card secondCard = deck.assignCard();
        assertThat(firstCard).isNotEqualTo(secondCard);
    }
}

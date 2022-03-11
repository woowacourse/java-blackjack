package blackjack.model.trumpcard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    void initializeDeck() {
        this.deck = new Deck();
    }

    @DisplayName("7하트, 10다이아몬드 점수의 합은 17이다")
    @Test
    void sumCardScore_7heart_10diamond() {
        deck.add(new TrumpCard(TrumpNumber.SEVEN, TrumpSymbol.HEART));
        deck.add(new TrumpCard(TrumpNumber.TEN, TrumpSymbol.DIAMOND));

        assertThat(deck.sumScore()).isEqualTo(17);
    }

    @DisplayName("9클로버, J하트 점수의 합은 19이다")
    @Test
    void sumCardScore_9clover_Jheart() {
        deck.add(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        deck.add(new TrumpCard(TrumpNumber.JACK, TrumpSymbol.HEART));

        assertThat(deck.sumScore()).isEqualTo(19);
    }

    @DisplayName("9클로버, J하트, A클로버 점수에는 Ace Advantage가 반영되지 않는다")
    @Test
    void sumCardScore_9clover_Jheart_Aclover() {
        deck.add(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        deck.add(new TrumpCard(TrumpNumber.JACK, TrumpSymbol.HEART));
        deck.add(new TrumpCard(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(deck.sumScore()).isEqualTo(20);
    }

    @DisplayName("9클로버, A클로버 점수에 Ace Advantage가 반영된다")
    @Test
    void sumCardScore_9clover_Aclover() {
        deck.add(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        deck.add(new TrumpCard(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(deck.sumScore()).isEqualTo(20);
    }

    @DisplayName("5하트, 6클로버, A클로버 점수에 Ace Advantage가 반영되지 않는다")
    @Test
    void sumCardScore_5heart_6clover_Aclover() {
        deck.add(new TrumpCard(TrumpNumber.FIVE, TrumpSymbol.HEART));
        deck.add(new TrumpCard(TrumpNumber.SIX, TrumpSymbol.CLOVER));
        deck.add(new TrumpCard(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(deck.sumScore()).isEqualTo(12);
    }
}

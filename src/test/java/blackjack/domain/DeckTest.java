package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("덱 객체 생성 확인")
    public void createDeck() {
        Deck deck = new Deck();
        assertThat(deck).isInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("deck cards에 card 더하는 로직 확인")
    public void checkAddCard() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        Deck comparedDeck = new Deck();
        comparedDeck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        assertThat(deck).isEqualTo(comparedDeck);

    }

    @Test
    @DisplayName("deck card 2개일 때 점수 합산")
    public void checkSumDeckCardsPoint() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        deck.addCard(new Card(Suit.SPADE, Rank.TWO));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(10);
    }

    @Test
    @DisplayName("deck card 3개일 때 점수 합산")
    public void checkSumDeckThreeCardsPoint() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        deck.addCard(new Card(Suit.SPADE, Rank.TWO));
        deck.addCard(new Card(Suit.SPADE, Rank.JACK));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(20);
    }
}

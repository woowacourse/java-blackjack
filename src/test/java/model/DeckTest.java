package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.Denomination;
import model.card.Suit;
import model.casino.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("Card 종류별로 모아 Deck 을 만든다.")
    void test1() {
        Deck deck = new Deck();

        assertThat(deck.getDeck().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("Deck 에는 Ace 가 4장 있다.")
    void test2() {
        Deck deck = new Deck();
        assertThat(deck.getDeck().stream().filter(Card::isAce).count()).isEqualTo(4);
    }

    @Test
    @DisplayName("Deck 은 랜덤 셔플이 가능하다.")
    void test3() {
        Deck deck = new Deck();
        List<Card> deckCards = new ArrayList<>(deck.getDeck());
        deck.shuffle();
        assertThat(deck.getDeck().equals(deckCards)).isFalse();
    }

    @Test
    @DisplayName("Deck 은 맨 위에서 카드 한장을 뽑을 수 있다.")
    void test4() {
        Deck deck = new Deck();

        Card card = deck.draw();
        assertThat(card.getSuit()).isEqualTo(Suit.CLUB);
        assertThat(card.getDenomination()).isEqualTo(Denomination.ACE);
    }
}

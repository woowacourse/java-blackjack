package blackjack.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.fixedCaradsGenerator.FixedCardsGenerator;
import card.Card;
import card.Rank;
import card.Suit;
import deck.CardsGenerator;
import deck.Deck;

class DeckTest {
    Deck deck;

    @BeforeEach
    void setUp() {
        List<Card> cards = List.of(
                new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.ACE, Suit.CLOVER),
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.ACE, Suit.HEART)
        );
        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator(cards);
        deck = new Deck(fixedCardsGenerator);
    }

    @DisplayName("Deck을 생성할 수 있다.")
    @Test
    void create() {
        List<Card> cards = List.of(
                new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.ACE, Suit.CLOVER),
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.ACE, Suit.HEART)
        );
        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator(cards);
        assertThatCode(() -> new Deck(fixedCardsGenerator))
                .doesNotThrowAnyException();
    }

    @DisplayName("맨 위의 카드를 뽑아줄 수 있다.")
    @Test
    void drawCard() {
        Card card = deck.drawCard();
        assertThat(card).isEqualTo(new Card(Rank.ACE, Suit.SPADE));
    }
}

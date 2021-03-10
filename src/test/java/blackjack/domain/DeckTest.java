package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @DisplayName("카드 덱 2개 뽑기")
    @Test
    void pickInitialCards() {
        Deque<Card> cards = CardGenerator.makeShuffledNewDeck();
        Deck deck = new Deck(cards);

        List<Card> initialCards = Stream.generate(cards::pop)
            .limit(2)
            .collect(Collectors.toList());

        assertThat(deck.pickInitialCards()).isEqualTo(initialCards);

    }

    @DisplayName("카드 덱 1개 뽑기")
    @Test
    void pickSingleCard() {
        Deque<Card> cards = CardGenerator.makeShuffledNewDeck();
        Deck deck = new Deck(cards);

        assertThat(deck.pickSingleCard()).isEqualTo(cards.pop());
        assertThat(deck.pickSingleCard()).isEqualTo(cards.pop());
    }
}
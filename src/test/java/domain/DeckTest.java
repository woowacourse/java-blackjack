package domain;

import domain.card.Card;
import domain.deck.CardShuffleStrategy;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @Test
    void 중복되지_않은_52장의_카드_덱을_생성한다() {
        CardShuffleStrategy cardShuffleStrategy = new RandomShuffleStrategy();
        Deck deck = Deck.createDeck(cardShuffleStrategy);

        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.draw());
        }


        assertThat(cards).hasSize(52);
    }

    @Test
    void 덱이_빈_상태에서_카드를_뽑을_경우_예외를_발생시킨다() {
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
                .isInstanceOf(NoSuchElementException.class);
    }
}

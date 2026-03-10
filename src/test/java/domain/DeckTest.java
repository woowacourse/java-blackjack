package domain;

import domain.card.Card;
import domain.deck.CardShuffleStrategy;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @Test
    void 중복되지_않은_52장의_카드_덱을_생성한다() {
        CardShuffleStrategy cardShuffleStrategy = new RandomShuffleStrategy();
        Deck deck = Deck.createDeck(cardShuffleStrategy);

        assertThat(deck.size()).isEqualTo(52);

        Set<Card> cards = new HashSet<>();
        while (deck.size() > 0) {
            cards.add(deck.draw());
        }

        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    void 덱이_빈_상태에서_카드를_뽑을_경우_예외를_발생시킨다() {
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        int deckSize = deck.size();

        for (int i = 0; i < deckSize; i++) {
            deck.draw();
        }

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 덱에서_카드를_한_장_뽑으면_덱의_사이즈가_감소한다() {
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());
        int size = deck.size();

        deck.draw();
        int changedSize = deck.size();

        assertThat(changedSize).isEqualTo(size - 1);
    }
}

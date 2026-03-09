package domain;

import domain.card.Card;
import domain.strategy.NoShuffleStrategy;
import domain.strategy.ShuffleStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    ShuffleStrategy strategy = new NoShuffleStrategy();

    @Test
    void 중복되지_않은_52장의_카드_묶음을_만든다() {

        Deck deck = Deck.createDeck(strategy);
        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    void 덱이_비었을_때_예외를_발생한다() {
        List<Card> cards = new ArrayList<>();
        Deck deck = new Deck(cards);

        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void 덱에서_카드를_한_장_뽑으면_덱의_사이즈가_감소한다() {
        Deck deck = Deck.createDeck(strategy);
        int size = deck.size();

        deck.draw();
        int changedSize = deck.size();

        assertThat(changedSize).isEqualTo(size - 1);
    }
}

package domain.strategy;

import domain.Deck;
import domain.card.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShuffleStrategyTest {
    ShuffleStrategy reversedShuffleStrategy = new ShuffleStrategy() {
        @Override
        public List<Card> shuffle(List<Card> cards) {
            List<Card> reversed = new ArrayList<>(cards);
            Collections.reverse(reversed);
            return reversed;
        }
    };

    @Test
    void 셔플전략이_없으면_정렬된_순서대로_카드가_뽑힌다() {
        Deck deck = Deck.createDeck(new NoShuffleStrategy());

        assertThat(deck.draw().name()).isEqualTo("A다이아몬드");
        assertThat(deck.draw().name()).isEqualTo("A하트");
        assertThat(deck.size()).isEqualTo(50);
    }

    @Test
    void 역순전략을_사용하면_첫_카드는_마지막카드가_된다() {
        Deck deck = Deck.createDeck(reversedShuffleStrategy);

        assertThat(deck.draw().name()).isEqualTo("K클로버");
    }
}
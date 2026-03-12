package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Deck;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

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

        Card first = deck.draw();
        Card second = deck.draw();

        assertThat(first.rank()).isEqualTo(Rank.ACE);
        assertThat(first.suit()).isEqualTo(Suit.DIAMOND);
        assertThat(second.rank()).isEqualTo(Rank.ACE);
        assertThat(second.suit()).isEqualTo(Suit.HEART);
        assertThat(deck.size()).isEqualTo(50);
    }

    @Test
    void 역순전략을_사용하면_첫_카드는_마지막카드가_된다() {
        Deck deck = Deck.createDeck(reversedShuffleStrategy);

        Card first = deck.draw();

        assertThat(first.rank()).isEqualTo(Rank.KING);
        assertThat(first.suit()).isEqualTo(Suit.CLOVER);
    }
}

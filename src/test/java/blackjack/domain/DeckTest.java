package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DeckTest {
    
    @Test
    void 덱에서_카드를_한장씩_뽑을_수_있다() {
        //given
        final Deck deck = new Deck();
        
        // expected
        assertThat(deck.draw()).isExactlyInstanceOf(Card.class);
    }
    
    @Test
    void 덱에서_52장_초과의_카드를_뽑으면_예외가_발생한다() {
        //given
        final Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        // expected
        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    void 덱에서_뽑은_카드들_중에는_중복된_카드가_없어야_한다() {
        //given
        final Deck deck = new Deck();
        final Map<Integer, Integer> numberCount = getNumberMap();
        EnumMap<CardShape, Integer> shapeCount = getShapeMap();
        
        for (int i = 0; i < 52; i++) {
            final Card card = deck.draw();
            numberCount.put(card.getNumber(), numberCount.get(card.getNumber()) + 1);
            shapeCount.put(card.getShape(), shapeCount.get(card.getShape()) + 1);
        }
        
        // expected
        for (int i = 1; i <= 13; i++) {
            Assertions.assertThat(numberCount.get(i)).isEqualTo(4);
        }
        for (CardShape shape : CardShape.values()) {
            Assertions.assertThat(shapeCount.get(shape)).isEqualTo(13);
        }
    }
    
    private static Map<Integer, Integer> getNumberMap() {
        final Map<Integer, Integer> numberCount = new HashMap<>();
        for (int i = 1; i <= 13; i++) {
            numberCount.put(i, 0);
        }
        return numberCount;
    }
    
    private static EnumMap<CardShape, Integer> getShapeMap() {
        EnumMap<CardShape, Integer> shapeCount = new EnumMap<>(CardShape.class);
        for (CardShape shape : CardShape.values()) {
            shapeCount.put(shape, 0);
        }
        return shapeCount;
    }
}

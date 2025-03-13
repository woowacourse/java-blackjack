package blackjack.domain.deck;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlackjackDeckTest {
    
    @Test
    void 덱에서_카드를_한장씩_뽑을_수_있다() {
        // given
        final BlackjackDeck deck = new BlackjackDeck();
        
        // expected
        assertThat(deck.draw()).isExactlyInstanceOf(Card.class);
    }
    
    @Test
    void 덱에서_52장_초과의_카드를_뽑으면_예외가_발생한다() {
        // given
        final BlackjackDeck deck = new BlackjackDeck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        // expected
        assertThatThrownBy(() -> deck.draw())
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    void 덱에서_뽑은_카드들_중에는_중복된_카드가_없어야_한다() {
        // given
        final BlackjackDeck deck = new BlackjackDeck();
        final EnumMap<CardNumber, Integer> numberCount = getNumberMap();
        final EnumMap<CardShape, Integer> shapeCount = getShapeMap();
        
        for (int i = 0; i < 52; i++) {
            final Card card = deck.draw();
            numberCount.put(card.getNumber(), numberCount.get(card.getNumber()) + 1);
            shapeCount.put(card.getShape(), shapeCount.get(card.getShape()) + 1);
        }
        
        // expected
        for (CardNumber number : CardNumber.values()) {
            Assertions.assertThat(numberCount.get(number)).isEqualTo(4);
        }
        for (CardShape shape : CardShape.values()) {
            Assertions.assertThat(shapeCount.get(shape)).isEqualTo(13);
        }
    }
    
    private static EnumMap<CardNumber, Integer> getNumberMap() {
        final EnumMap<CardNumber, Integer> numberCount = new EnumMap<>(CardNumber.class);
        for (CardNumber cardNumber : CardNumber.values()) {
            numberCount.put(cardNumber, 0);
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

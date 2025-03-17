package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CardsTest {
    
    
    @Test
    void 모든_종류의_카드를_받을_수_있다() {
        // given
        Cards trumpCards = Cards.createTrumpCards();
        
        // expected
        assertThat(trumpCards.getCards().size()).isEqualTo(52);
    }
    
    @Test
    void 트럼프_카드에_중복이_없어야_한다() {
        // given
        List<Card> trumpCards = Cards.createTrumpCards().getCards();
        
        final EnumMap<CardNumber, Integer> numberCount = getNumberMap();
        final EnumMap<CardShape, Integer> shapeCount = getShapeMap();
        
        for (int i = 0; i < 52; i++) {
            final Card card = trumpCards.get(i);
            numberCount.put(card.getNumber(), numberCount.get(card.getNumber()) + 1);
            shapeCount.put(card.getShape(), shapeCount.get(card.getShape()) + 1);
        }
        
        // expected
        for (CardNumber cardNumber : CardNumber.values()) {
            assertThat(numberCount.get(cardNumber)).isEqualTo(4);
        }
        for (CardShape shape : CardShape.values()) {
            assertThat(shapeCount.get(shape)).isEqualTo(13);
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

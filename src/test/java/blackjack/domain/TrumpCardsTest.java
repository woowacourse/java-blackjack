package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.TrumpCards;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrumpCardsTest {
    
    @Test
    void 모든_종류의_카드를_받을_수_있다() {
        // given
        List<Card> trumpCards = new ArrayList<>(TrumpCards.createTrumpCards());
        
        // expected
        assertThat(trumpCards.size()).isEqualTo(52);
    }
    
    @Test
    void 트럼프_카드에_중복이_없어야_한다() {
        // given
        List<Card> trumpCards = new ArrayList<>(TrumpCards.createTrumpCards());
        
        final EnumMap<CardNumber, Integer> numberCount = getNumberMap();
        final EnumMap<CardShape, Integer> shapeCount = getShapeMap();
        
        for (int i = 0; i < 52; i++) {
            final Card card = trumpCards.get(i);
            numberCount.put(card.getNumber(), numberCount.get(card.getNumber()) + 1);
            shapeCount.put(card.getShape(), shapeCount.get(card.getShape()) + 1);
        }
        
        // expected
        for (CardNumber cardNumber : CardNumber.values()) {
            org.assertj.core.api.Assertions.assertThat(numberCount.get(cardNumber)).isEqualTo(4);
        }
        for (CardShape shape : CardShape.values()) {
            org.assertj.core.api.Assertions.assertThat(shapeCount.get(shape)).isEqualTo(13);
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

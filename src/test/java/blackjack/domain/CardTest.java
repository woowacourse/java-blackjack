package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CardTest {
    
    @Test
    void 카드는_가능한_블랙잭_숫자를_반환할_수_있다() {
        // given
        CardNumber number = CardNumber.NUMBER_7;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = Card.createTrumpCards().get(number, shape);
        
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(7);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"NUMBER_J", "NUMBER_Q", "NUMBER_K"})
    void JQK는_블랙잭_숫자에서_10으로_반환된다(CardNumber number) {
        // given
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = Card.createTrumpCards().get(number, shape);
        
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(10);
    }
    
    @Test
    void A는_블랙잭_숫자에서_1과_11로_반환된다() {
        // given
        CardNumber number = CardNumber.NUMBER_A;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = Card.createTrumpCards().get(number, shape);
        
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(1, 11);
    }
    
    @Test
    void 트럼프_카드를_여러번_생성해도_동일한_객체가_나온다() {
        // given
        CardNumber number = CardNumber.NUMBER_A;
        CardShape shape = CardShape.다이아몬드;
        
        // expected
        assertThat(Card.createTrumpCards().get(number, shape)).isEqualTo(Card.createTrumpCards().get(number, shape));
    }
    
    @Test
    void 카드를_생성하면_모양과_숫자를_알_수_있다() {
        // given
        CardNumber number = CardNumber.NUMBER_A;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = Card.createTrumpCards().get(number, shape);
        
        // then
        assertAll(
                () -> assertThat(card.getNumber()).isEqualTo(CardNumber.NUMBER_A),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.다이아몬드)
        );
    }
    
    @Test
    void 모든_종류의_카드를_받을_수_있다() {
        // given
        List<Card> trumpCards = new ArrayList<>(Card.createTrumpCards().values());
        
        // expected
        assertThat(trumpCards.size()).isEqualTo(52);
    }
    
    @Test
    void 트럼프_카드에_중복이_없어야_한다() {
        // given
        List<Card> trumpCards = new ArrayList<>(Card.createTrumpCards().values());
        
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

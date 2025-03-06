package blackjack.domain;

import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CardTest {
    
    @Test
    void 카드는_숫자와_모양을_기반으로_생성할_수_있다() {
        // given
        int number = 1;
        CardShape shape = CardShape.다이아몬드;
        
        // expected
        assertDoesNotThrow(() -> new Card(number, shape));
    }
    
    
    @ParameterizedTest
    @ValueSource(ints = {0, 14})
    void 카드_숫자의_범위가_1에서_13이_아닌_경우_예외가_발생한다(int number) {
        // given
        CardShape shape = CardShape.다이아몬드;
        
        // expected
        assertThatThrownBy(() -> new Card(number, shape))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1부터 13 사이여야 합니다.");
    }
    
    @Test
    void 카드는_가능한_블랙잭_숫자를_반환할_수_있다() {
        // given
        int number = 7;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = new Card(number, shape);
        
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(7);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {11, 12, 13})
    void JQK는_블랙잭_숫자에서_10으로_반환된다(int number) {
        // given
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = new Card(number, shape);
        
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(10);
    }
    
    @Test
    void A는_블랙잭_숫자에서_1과_11로_반환된다() {
        // given
        int number = 1;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = new Card(number, shape);
        
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(1, 11);
    }
    
    @Test
    void 카드_문양이_null인_경우_예외가_발생한다() {
        // given
        CardShape shape = null;
        
        // expected
        assertThatThrownBy(() -> new Card(1, shape))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("문양은 하트, 다이아몬드, 스페이드, 클로버 중 하나여야 합니다.");
    }
    
    @Test
    void 카드는_문양과_숫자가_같아도_동일하다고_판단되지_않는다() {
        // given
        int number = 1;
        CardShape shape = CardShape.다이아몬드;
        
        // expected
        assertThat(new Card(number, shape)).isNotEqualTo(new Card(number, shape));
    }
    
    @Test
    void 카드를_생성하면_모양과_숫자를_알_수_있다() {
        // given
        int number = 1;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = new Card(number, shape);
        
        // then
        assertAll(
                () -> assertThat(card.getNumber()).isEqualTo(CardNumber.NUMBER_A),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.다이아몬드)
        );
    }
    
    @Test
    void 모든_종류의_카드를_받을_수_있다() {
        // given
        List<Card> trumpCards = Card.createTrumpCards();
        
        // expected
        assertThat(trumpCards.size()).isEqualTo(52);
    }
    
    @Test
    void 트럼프_카드에_중복이_없어야_한다() {
        // given
        List<Card> trumpCards = Card.createTrumpCards();
        
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

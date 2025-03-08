package blackjack.domain;

import blackjack.test_util.TestConstants;

import java.util.EnumMap;
import java.util.List;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CardTest {

    @Test
    void 카드는_가능한_블랙잭_숫자를_반환할_수_있다() {
        // expected
        assertThat(HEART_7.getBlackjackValue())
                .containsExactlyInAnyOrder(7);
    }
    
    @ParameterizedTest
    @MethodSource("provideJQKCards")
    void JQK는_블랙잭_숫자에서_10으로_반환된다(Card card) {
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(10);
    }
    
    private static Stream<Arguments> provideJQKCards() {
        return Stream.of(
                Arguments.of(HEART_11),
                Arguments.of(HEART_12),
                Arguments.of(HEART_13)
        );
    }
    
    @Test
    void A는_블랙잭_숫자에서_1과_11로_반환된다() {
        // expected
        assertThat(HEART_1.getBlackjackValue())
                .containsExactlyInAnyOrder(1, 11);
    }
    
    @Test
    void 카드를_생성하면_모양과_숫자를_알_수_있다() {
        // given, when
        final Card card = DIAMOND_1;
        
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

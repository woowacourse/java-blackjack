package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThat(card.getBlackjackNumber())
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
        assertThat(card.getBlackjackNumber())
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
        assertThat(card.getBlackjackNumber())
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
                () -> assertThat(card.getNumber()).isEqualTo(1),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.다이아몬드)
        );
    }
}

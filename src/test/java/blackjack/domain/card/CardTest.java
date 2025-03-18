package blackjack.domain.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CardTest {
    
    @Test
    void 카드는_가능한_블랙잭_숫자를_반환할_수_있다() {
        // given
        CardNumber number = CardNumber.NUMBER_7;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = new Card(number, shape);
        
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
        final Card card = new Card(number, shape);
        
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
        final Card card = new Card(number, shape);
        
        // expected
        assertThat(card.getBlackjackValue())
                .containsExactlyInAnyOrder(1, 11);
    }
    
    @Test
    void 카드를_생성하면_모양과_숫자를_알_수_있다() {
        // given
        CardNumber number = CardNumber.NUMBER_A;
        CardShape shape = CardShape.다이아몬드;
        
        // when
        final Card card = new Card(number, shape);
        
        // then
        assertAll(
                () -> assertThat(card.getNumber()).isEqualTo(CardNumber.NUMBER_A),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.다이아몬드)
        );
    }
}

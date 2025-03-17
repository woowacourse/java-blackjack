package blackjack.domain.card;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
                () -> assertThat(card.getNumber()).isEqualTo(CardNumber.ACE),
                () -> assertThat(card.getShape()).isEqualTo(CardShape.DIAMOND)
        );
    }
}

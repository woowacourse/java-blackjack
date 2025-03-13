package blackjack.domain.card_hand;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;

import java.util.List;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackCardHandTest {
    
    @Test
    void 블랙잭_손패를_생성할_수_있다() {
        // expected
        assertDoesNotThrow(() -> new BlackjackCardHand(List::of));
    }
    
    @Test
    void 블랙잭_손패를_생성_시_카드를_초기화할_수_있다() {
        // given
        BlackjackCardHand cardHand = new BlackjackCardHand(() -> List.of(HEART_1, HEART_2));
        
        // expected
        assertThat(cardHand.getCards()).containsExactly(HEART_1, HEART_2);
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsAndBlackjackSum")
    void 현재_갖고있는_손패에_대한_블랙잭_합을_구할_수_있다(List<Card> cards, int expected) {
        // given
        BlackjackCardHand cardHand = new BlackjackCardHand(() -> cards);
        
        // expected
        assertThat(cardHand.getBlackjackSum()).isEqualTo(expected);
    }
    
    private static Stream<Arguments> provideCardsAndBlackjackSum() {
        return Stream.of(
                Arguments.of(List.of(HEART_1, HEART_2, HEART_13), 13),
                Arguments.of(List.of(HEART_1, HEART_10), 21),
                Arguments.of(List.of(HEART_1, HEART_10, DIAMOND_1), 12)
        );
    }
    
    @Test
    void 손패에_카드를_한_장_추가할_수_있다() {
        // given
        BlackjackCardHand cardHand = new BlackjackCardHand(List::of);
        
        // when
        cardHand.addCard(HEART_1);
        cardHand.addCard(HEART_2);
        
        // expected
        assertThat(cardHand.getCards()).containsExactly(HEART_1, HEART_2);
    }
}

package blackjack.domain.card_hand;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class CardHandTest {
    
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
    
    @Test
    void 손패에_카드를_여러_장_추가할_수_있다() {
        // given
        BlackjackCardHand cardHand = new BlackjackCardHand(() -> List.of(HEART_1, HEART_2));
        
        // expected
        assertThat(cardHand.getCards()).containsExactly(HEART_1, HEART_2);
    }
}

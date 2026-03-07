package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void A클로버라는_카드가_생성된다() {

        Card card = new Card(CardPoint.ACE, CardPattern.CLUB);
        assertNotNull(card);

        assertThat(card.getCardPointName()).isEqualTo("A");
        assertThat(card.getCardPoint()).isEqualTo(11);
    }

    @Test
    void 에이스인지_아닌지_확인한다() {
        Card aceCard = new Card(CardPoint.ACE, CardPattern.CLUB);
        Card normalCard = new Card(CardPoint.TWO, CardPattern.CLUB);

        assertTrue(aceCard.isAce());
        assertFalse(normalCard.isAce());
    }
}

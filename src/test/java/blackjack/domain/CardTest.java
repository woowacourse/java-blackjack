package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void A클로버라는_카드가_생성된다() {
        Card card = new Card(CardPoint.ACE, CardPattern.CLUB);
        assertThat(card.getCardPointName()).isEqualTo("A");
        assertThat(card.getCardPoint()).isEqualTo(11);
    }

    @Test
    void 에이스인지_확인한다() {
        Card aceCard = new Card(CardPoint.ACE, CardPattern.CLUB);
        assertTrue(aceCard.isAce());
    }

    @Test
    void 에이스가_아닌_일반_카드를_확인한다() {
        Card normalCard = new Card(CardPoint.TWO, CardPattern.CLUB);
        assertFalse(normalCard.isAce());
    }

}

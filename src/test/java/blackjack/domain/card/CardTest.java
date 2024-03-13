package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    void 에이스_카드인지_확인한다() {
        Card aceCard = new Card(ACE, HEART);

        assertThat(aceCard.isAceCard()).isTrue();
    }

    @Test
    void 에이스_카드가_아닌지_확인한다() {
        Card notAceCard = new Card(TWO, HEART);

        assertThat(notAceCard.isAceCard()).isFalse();
    }
}

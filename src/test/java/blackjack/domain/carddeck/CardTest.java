package blackjack.domain.carddeck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardNumber;
import blackjack.domain.carddeck.CardPattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드생성 테스트")
    void testCreateCard() {
        Card card = new Card(CardPattern.CLOVER, CardNumber.ACE);

        assertThat(card).isEqualTo(new Card(CardPattern.CLOVER, CardNumber.ACE));
        assertThat(card).isNotEqualTo(new Card(CardPattern.HEART, CardNumber.ACE));
        assertThat(card).isNotEqualTo(new Card(CardPattern.CLOVER, CardNumber.KING));
    }
}

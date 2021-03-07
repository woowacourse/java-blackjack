package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CardTest {
    @Test
    @DisplayName("카드가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Card(CardNumber.TWO, CardSuit.CLOVER))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("해당 카드가 ACE 넘버인지 확인")
    void isAce() {
        final Card aceCard = new Card(CardNumber.ACE, CardSuit.CLOVER);
        assertThat(aceCard.isAce()).isTrue();

        final Card notAceCard = new Card(CardNumber.TWO, CardSuit.CLOVER);
        assertThat(notAceCard.isAce()).isFalse();
    }
}

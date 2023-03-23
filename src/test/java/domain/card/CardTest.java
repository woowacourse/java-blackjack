package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("카드는 모양과 숫자를 가진다.")
    @Test
    void createCardSuccess() {
        Card card = new Card(TrumpCardType.HEART, TrumpCardNumber.ACE);

        assertThat(card.getTypeName())
                .isEqualTo(TrumpCardType.HEART.getName());

        assertThat(card.getNumberSignature())
                .isEqualTo(TrumpCardNumber.ACE.getSignature());
    }
}


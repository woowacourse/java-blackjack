package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드를 정상 생성한다.")
    void createCard() {
        final CardPattern expectedPattern = CardPattern.HEART;
        final CardNumber expectedNumber = CardNumber.TWO;

        final Card card = new Card(CardPattern.HEART, CardNumber.TWO);

        assertThat(card.getCardPattern()).isEqualTo(expectedPattern);
        assertThat(card.getCardNumber()).isEqualTo(expectedNumber);
    }
}

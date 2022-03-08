package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("문양과 번호를 가지고 카드를 생성할 수 있다.")
    void createCard() {
        final CardPattern pattern = CardPattern.SPADE;
        final CardNumber number = CardNumber.A;
        final Card card = new Card(pattern, number);
        assertThat(card).isInstanceOf(Card.class);
    }
}

package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardGroupTest {

    @Test
    @DisplayName("카드 숫자의 총합을 반환하는 기능 추가")
    void getTotalValueTest() {
        final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
        final Card secondCard = new Card(CardShape.SPADE, CardNumber.EIGHT);

        final CardGroup cards = new CardGroup(firstCard, secondCard);

        int value = cards.getTotalValue();

        Assertions.assertThat(value).isEqualTo(firstCard.getNumber().getValue() + secondCard.getNumber().getValue());
    }

}

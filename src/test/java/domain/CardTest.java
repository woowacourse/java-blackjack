package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("객체 생성 테스트")
    @Test
    void cardConstructorTest() {
        Assertions.assertThat(new Card(CardNumber.ACE, CardSuitSymbol.CLUB)
                .equals(new Card(CardNumber.ACE, CardSuitSymbol.CLUB))).isTrue();
    }
}

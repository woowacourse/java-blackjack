package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("객체 생성 테스트")
    @Test
    void cardConstructorTest() {
        Assertions.assertThat(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB)
                == Card.of(CardNumber.ACE, CardSuitSymbol.CLUB)).isTrue();
    }
}

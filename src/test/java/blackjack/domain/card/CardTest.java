package blackjack.domain.card;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("하나의 Denomination 과 하나의 Suit 를 활용하여 생성한다.")
    @Test
    void 카드_생성_정상() {
        Assertions.assertDoesNotThrow(() -> Card.of(ACE, SPADE));
    }
}

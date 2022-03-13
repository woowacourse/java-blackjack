package blackjack.domain.card;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("하나의 Denomination 과 하나의 Suit 를 활용하여 생성한다.")
    @Test
    void 카드_생성_정상() {
        Assertions.assertDoesNotThrow(() -> Card.of(ACE, SPADE));
    }

    @DisplayName("끗수와 무늬를 조합하여 52장의 카드를 만든다.")
    @Test
    void 카드_52장_생성() {
        Set<Card> cards = Card.VALUES;

        assertThat(cards.size()).isEqualTo(52);
    }
}

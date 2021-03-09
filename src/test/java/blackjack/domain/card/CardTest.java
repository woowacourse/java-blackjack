package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class CardTest {

    @DisplayName("카드 객체 생성")
    @Test
    void create() {
        assertThatCode(() -> Card.of(Suit.CLUB, Denomination.KING))
                .doesNotThrowAnyException();
    }
}

package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드를 생성한다.")
    void createCard() {
        // when
        Card card = Card.of(Pattern.DIAMOND, Denomination.ACE);

        // then
        assertThat(card).isNotNull();
    }
}

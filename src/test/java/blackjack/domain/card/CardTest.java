package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createCard() {
        Card card = new Card(Suit.SPADE, Denomination.ACE);

        assertThat(card).isNotNull();
    }
}

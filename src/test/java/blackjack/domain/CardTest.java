package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드를 생성한다.")
    void createCard() {
        Card card = new Card(Denomination.THREE, Suit.DIAMOND);
        assertThat(card.toString()).isEqualTo("3다이아몬드");
    }
}

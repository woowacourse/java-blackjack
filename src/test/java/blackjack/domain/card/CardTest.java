package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    void cardTest() {
        Card card = new Card(CardSymbol.ACE, CardType.SPADE);
        assertThat(card.toString()).isEqualTo("A스페이드");
    }
}

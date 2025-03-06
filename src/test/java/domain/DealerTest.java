package domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러는_초기에_카드를_1장_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(deck);

        // when & then
        assertThat(dealer.openInitialCards().size())
                .isEqualTo(1);
    }
}
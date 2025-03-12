package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러는_초기에_카드를_1장_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(deck);
        dealer.openInitialCards();

        // when & then
        assertThat(dealer.getOpenedCards().size())
                .isEqualTo(1);
    }

    @Test
    void 딜러는_이름이_딜러로_고정되어있다() {
        // given
        Dealer dealer = new Dealer();

        // when & then
        Assertions.assertThat(dealer.getName())
                .isEqualTo("딜러");
    }
}
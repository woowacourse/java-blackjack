package domain.player;

import domain.card.Deck;
import domain.card.DeckGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 초기_카드_분배_시_딜러는_2장을_받는다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = Dealer.createDealer();

        // when
        dealer.drawInitialCards(deck);

        // when & then
        Assertions.assertThat(dealer.cards().size())
                .isEqualTo(2);
    }

    @Test
    void 초기_카드_공개_시_딜러는_카드_한장만_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        Dealer dealer = Dealer.createDealer();
        dealer.drawInitialCards(deck);

        // when
        dealer.openInitialCards();

        // when & then
        Assertions.assertThat(dealer.cards().openedCards().size())
                .isEqualTo(1);
    }
}
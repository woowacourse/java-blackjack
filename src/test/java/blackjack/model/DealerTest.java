package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 카드_뭉치에서_N_개의_카드를_뽑아_반환한다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);

        Cards cards = dealer.draw(2);

        assertThat(cards.getValues()).hasSize(2);
    }

    @Test
    void 자신의_카드를_N_개_뽑는다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);

        dealer.drawSelf(2);

        assertThat(dealer.getCards().hasSize(2)).isTrue();
    }

}

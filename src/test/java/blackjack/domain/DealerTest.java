package blackjack.domain;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("히트 - 딜러는 카드를 받는다.")
    @Test
    void hitCard() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.createDeck();

        dealer.hit(cardDeck.getDeck().pop());

        assertThat(dealer.getCards()).hasSize(1);
    }

}
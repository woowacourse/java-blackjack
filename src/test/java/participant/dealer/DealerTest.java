package participant.dealer;

import card.CardDeck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private static final int MIN_DEALER_SCORE = 16;

    @DisplayName("최소 점수를 넘을때까지 카드를 받는다.")
    @Test
    void getExtraCardIfNotOverMinScore() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.firstCards());

        dealer.playGame(cardDeck);

        Assertions.assertTrue(dealer.getCardScore() > MIN_DEALER_SCORE);
    }
}

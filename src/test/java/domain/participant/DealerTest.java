package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.CardDeck;

class DealerTest {
    private static final int CRITERIA_SCORE = 16;

    @Test
    @DisplayName("딜러의 점수 합이 16 이하면 계속 카드를 받게 하는지")
    void performHit() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        dealer.performHit(cardDeck);
        assertThat(dealer.calculateScore() > CRITERIA_SCORE).isTrue();
    }
}

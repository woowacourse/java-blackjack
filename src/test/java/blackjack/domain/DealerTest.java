package blackjack.domain;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealerTest {

    @DisplayName("히트 - 딜러는 카드를 받는다.")
    @Test
    void hitCard() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.createDeck();

        dealer.hit(cardDeck.drawCard(), cardDeck.drawCard());

        assertThat(dealer.getCards()).hasSize(2);
    }

    @DisplayName("isStay - 딜러는 16이하일 때 스테이 할 수 없다.")
    @Test
    void check16() {
        Dealer dealer = new Dealer();

        dealer.hit(Fixture.CLUBS_TEN, Fixture.CLUBS_TWO);

        assertTrue(dealer.isDealerDrawScore());
    }

    @DisplayName("isStay - 딜러는 17 이상일 때 스테이한다.")
    @Test
    void check17() {
        Dealer dealer = new Dealer();

        dealer.hit(Fixture.CLUBS_KING, Fixture.CLUBS_TEN);

        assertFalse(dealer.isDealerDrawScore());
    }
}

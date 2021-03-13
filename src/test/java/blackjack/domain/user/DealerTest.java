package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static blackjack.domain.Fixture.*;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
    }

    @DisplayName("딜러는 카드를 받을 수 있다")
    @Test
    void hitCard() {
        CardDeck cardDeck = CardDeck.createDeck();

        dealer.draw(cardDeck.drawCard());

        assertThat(dealer.getCards().cards()).hasSize(1);
    }

    @DisplayName("딜러의 첫 카드를 리턴받는다.")
    @Test
    void getFirstCardOfDealerTest() {
        dealer.draw(jack);

        assertThat(dealer.getFirstCard()).isEqualTo(jack);
    }

    @DisplayName("isStay - 딜러는 점수가 17 미만일 때 카드를 뽑아야 한다.")
    @Test
    void dealerHasToDrawACardWhenUnder17Score() {
        dealer.draw(jack);
        dealer.draw(six);

        assertTrue(dealer.canContinue());
    }

    @DisplayName("isStay - 딜러는 점수가 16 초과일 때 카드를 뽑지 않는다.")
    @Test
    void dealerCannotDrawACardWhenOver16Score() {
        dealer.draw(jack);
        dealer.draw(seven);

        assertFalse(dealer.canContinue());
    }

}
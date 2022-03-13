package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.trumpcard.TrumpCard;
import blackjack.model.trumpcard.TrumpNumber;
import blackjack.model.trumpcard.TrumpSymbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void initializeDealer() {
        dealer = new Dealer();
        dealer.addCard(new TrumpCard(TrumpNumber.NINE, TrumpSymbol.CLOVER));
    }

    @DisplayName("딜러의 점수가 16점이면 딜러는 hit할 수 있다")
    @Test
    void canHit_true() {
        dealer.addCard(new TrumpCard(TrumpNumber.SEVEN, TrumpSymbol.HEART));

        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 점수가 17점이면 딜러는 hit할 수 없다")
    @Test
    void canHit_false() {
        dealer.addCard(new TrumpCard(TrumpNumber.EIGHT, TrumpSymbol.CLOVER));

        assertThat(dealer.canHit()).isFalse();
    }
}

package domain.user;

import domain.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러 드로우 테스트")
    void isAbleDrawCard() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        dealer.drawCard(cardDeck);
        Assertions.assertThat(dealer.canDrawCard()).isTrue();
        for (int i = 0; i < 10; i++) {
            dealer.drawCard(cardDeck);
        }
        Assertions.assertThat(dealer.canDrawCard()).isFalse();
    }
}

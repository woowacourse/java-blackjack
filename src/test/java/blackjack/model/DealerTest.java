package blackjack.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("카드 덱에서 카드를 뽑아서 핸즈에 추가한다.")
    void pickACard() {
        // given
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.init();

        // when & then
        assertThatCode(() -> dealer.pickACard(cardDeck))
                .doesNotThrowAnyException();
    }
}
package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("처음에 카드를 지급받지 않은 경우 카드 조회시 오류를 던진다.")
    void getReadyCardsTestFailed() {
        Dealer dealer = new Dealer();
        Assertions.assertThatThrownBy(dealer::faceUpInitialHand)
            .isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("처음에 지급받은 카드를 반환한다.")
    void getReadyCardsTestSuccess() {
        Dealer dealer = new Dealer();
        dealer.dealt(new Card(Denomination.ACE, Suit.SPADE));
        dealer.dealt(new Card(Denomination.THREE, Suit.HEART));
        Assertions.assertThat(dealer.faceUpInitialHand())
            .containsExactly(new Card(Denomination.ACE, Suit.SPADE));
    }
}

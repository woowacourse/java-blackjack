package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 게임을 시작할 카드 중 1개만 반환한다.")
    @Test
    void getInitialHand() {
        Dealer dealer = new Dealer();
        Card firstCard = new Card(Denomination.JACK, Suit.DIAMOND);
        Card secondCard = new Card(Denomination.ACE, Suit.DIAMOND);
        dealer.dealt(firstCard);
        dealer.dealt(secondCard);
        assertThat(dealer.getInitialHand()).containsExactly(firstCard);
    }

    @DisplayName("카드를 지급받지 않고 게임을 시작할 카드를 조회하면 오류를 던진다")
    @Test
    void getInitialHandWithoutCard() {
        Dealer dealer = new Dealer();
        assertThatThrownBy(dealer::getInitialHand)
            .isExactlyInstanceOf(IllegalStateException.class)
            .hasMessage("딜러는 카드 두장을 받고 시작해야 합니다.");
    }
}

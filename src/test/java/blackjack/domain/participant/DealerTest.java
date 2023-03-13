package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    private static Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.SIX));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.JACK));
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 성공")
    void isAbleToReceiveTest() {
        assertThat(dealer.getScore()).isEqualTo(16);
        assertThat(dealer.isAbleToReceive()).isTrue();
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 실패")
    void isAbleToReceiveFailTest() {
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));

        assertThat(dealer.getScore()).isEqualTo(17);
        assertThat(dealer.isAbleToReceive()).isFalse();
    }
}

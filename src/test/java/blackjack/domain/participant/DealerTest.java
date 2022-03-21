package blackjack.domain.participant;

import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 정상적으로 생성되는지 확인")
    void create() {
        assertThat(dealer).isNotNull();
    }

    @Test
    @DisplayName("딜러가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        dealer.hit(jackCard);
        Participant participant = dealer;
        Card openCard = participant.getOpenCard();

        assertThat(openCard.getDenomination()).isEqualTo("J");
    }

    @Test
    @DisplayName("딜러는 카드의 수가 16이하 일 때 한 장의 카드를 더 받는다")
    void dealerReceiveCard() {
        dealer.hit(tenCard);
        dealer.hit(sixCard);

        Assertions.assertThat(dealer.shouldReceive()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 수가 17이상일 떄 카드를 받을 수 없다")
    void dealerCannotReceiveCard() {
        dealer.hit(tenCard);
        dealer.hit(sevenCard);

        Assertions.assertThat(dealer.shouldReceive()).isFalse();
    }

    @Test
    @DisplayName("딜러는 자신의 카드 한장을 정상적으로 오픈 하는지 확인")
    void openDealerCard() {
        dealer.hit(jackCard);
        dealer.hit(fiveCard);
        Participant participant = dealer;

        assertThat(participant.getOpenCard()).isEqualTo(jackCard);
    }

    @Test
    @DisplayName("카드패의 총합이 정확한지 확인")
    void getCardTotalScore() {
        dealer.hit(aceCard);
        dealer.hit(jackCard);
        Participant participant = dealer;

        assertThat(participant.getCardTotalScore()).isEqualTo(21);
    }
}

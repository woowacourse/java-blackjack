package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("딜러가 카드를 뽑을 상황 테스트_블랙잭일 때")
    @Test
    void canDrawBlackjack() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.A_HEART);
        dealer.addCard(Card.J_CLOVER);

        assertThat(dealer.canDraw()).isFalse();
    }
}

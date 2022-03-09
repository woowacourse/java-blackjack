package blackjack.domain;

import blackjack.domain.CardMachine;
import blackjack.domain.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private CardMachine cardMachine = new CardMachine();
    private Dealer dealer;

    @BeforeEach
    void before() {
        dealer = new Dealer(cardMachine.giveInitCard());
    }

    @DisplayName("딜러의 카드 총 합이 16이하일 경우 True 를 반환하는지 확인한다.")
    @Test
    void isGiven_true() {
        final boolean given = dealer.isReceived();
        assertThat(given).isTrue();
    }
}

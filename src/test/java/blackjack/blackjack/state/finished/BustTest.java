package blackjack.blackjack.state.finished;

import static blackjack.fixture.TestFixture.provide16Cards;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.Denomination;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.card.Suit;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.running.DealerRunning;
import blackjack.blackjack.state.running.PlayerRunning;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BustTest {

    private State playerState;

    @BeforeEach
    void setUp() {
        playerState = PlayerRunning.initialState(provide16Cards());
        playerState = playerState.receiveCards(new Hand(List.of(new Card(Suit.DIAMOND, Denomination.J))));
    }

    @Test
    void 수익을_계산한다() {
        // Given
        BigDecimal bettingAmount = BigDecimal.valueOf(3_000);
        State dealerState = DealerRunning.initialState(provideUnder16Cards());
        dealerState = dealerState.stay();

        // When & Then
        assertThat(playerState.calculateProfit(bettingAmount, dealerState)).isEqualTo(new BigDecimal(-3000));
    }
}

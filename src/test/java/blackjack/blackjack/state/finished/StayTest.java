package blackjack.blackjack.state.finished;

import static blackjack.fixture.TestFixture.provide16Cards;
import static blackjack.fixture.TestFixture.provideOver16Cards;
import static blackjack.fixture.TestFixture.provideUnder16Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.Denomination;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.card.Suit;
import blackjack.blackjack.participant.Dealer;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.running.PlayerRunning;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StayTest {

    private State playerState;

    @BeforeEach
    void setUp() {
        playerState = PlayerRunning.initialState(provide16Cards());
        playerState = playerState.stay();
    }

    @Test
    void 딜러가_버스트이면_베팅금을_얻는다() {
        // Given
        BigDecimal bettingAmount = BigDecimal.valueOf(3_000);
        Dealer dealer = new Dealer(provide16Cards());
        dealer.receiveCards(new Hand(List.of(new Card(Suit.DIAMOND, Denomination.J))));

        // When & Then
        assertThat(playerState.calculateProfit(bettingAmount, dealer)).isEqualTo(new BigDecimal(3000));
    }


    @Test
    void 딜러_점수가_더_높으면_베팅금을_잃는다() {
        // Given
        BigDecimal bettingAmount = BigDecimal.valueOf(3_000);
        Dealer dealer = new Dealer(provideOver16Cards());

        // When & Then
        assertThat(playerState.calculateProfit(bettingAmount, dealer)).isEqualTo(new BigDecimal(-3000));
    }

    @Test
    void 딜러_점수가_더_낮으면_베팅금을_얻는다() {
        // Given
        BigDecimal bettingAmount = BigDecimal.valueOf(3_000);
        Dealer dealer = new Dealer(provideUnder16Cards());

        // When & Then
        assertThat(playerState.calculateProfit(bettingAmount, dealer)).isEqualTo(new BigDecimal(3000));
    }
}

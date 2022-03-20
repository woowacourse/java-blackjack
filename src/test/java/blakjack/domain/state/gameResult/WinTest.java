package blakjack.domain.state.gameResult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.running.Init;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.Fixture.CLUB_TEN;
import static org.assertj.core.api.Assertions.assertThat;

public class WinTest {
    private State playerState;
    private State dealerState;

    @BeforeEach
    void setUp() {
        final PrivateArea playerArea = new PrivateArea("칙촉");
        final Chip playerChip = new Chip(1000);
        playerState = new Init(playerArea, playerChip);
        final PrivateArea dealerArea = new PrivateArea("딜러");
        final Chip dealerChip = new Chip(0);
        dealerState = new Init(dealerArea, dealerChip);
    }

    @Test
    @DisplayName("플레이어 우승시 수익 반환 기능")
    void getProfit() {
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.stay();

        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(CLUB_TEN);

        final State win = playerState.compare(dealerState);

        assertThat(win.getProfit()).isEqualTo(1000);
    }

    @Test
    @DisplayName("딜러 우승시 수익 반환 기능")
    void getProfit2() {
        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.stay();

        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(CLUB_TEN);


        final State win = dealerState.compare(playerState);

        assertThat(win.getProfit()).isEqualTo(1000);
    }
}

package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.gameresult.BlackjackWin;
import blakjack.domain.state.gameresult.Draw;
import blakjack.domain.state.gameresult.Win;
import blakjack.domain.state.running.Init;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.Fixture.CLUB_TEN;
import static blakjack.domain.Fixture.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackTest {
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
    @DisplayName("둘 다 블랙잭인 경우 무승부 반환")
    void draw() {
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(HEART_ACE);

        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(HEART_ACE);

        assertThat(playerState.compare(dealerState)).isExactlyInstanceOf(Draw.class);
    }

    @Test
    @DisplayName("딜러만 블랙잭인 경우 우승 반환")
    void win() {
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(CLUB_TEN);

        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(HEART_ACE);

        assertThat(dealerState.compare(playerState)).isExactlyInstanceOf(Win.class);
    }

    @Test
    @DisplayName("플레이어만 블랙잭인 경우 블랙잭우승 반환")
    void blackjackWin() {
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(HEART_ACE);

        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(CLUB_TEN);

        assertThat(playerState.compare(dealerState)).isExactlyInstanceOf(BlackjackWin.class);
    }
}

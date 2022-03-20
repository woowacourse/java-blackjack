package blakjack.domain.state.gameResult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.running.Init;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.Fixture.CLUB_TEN;
import static blakjack.domain.Fixture.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;

public class DrawTest {
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
    @DisplayName("비긴 경우 수익 반환 기능")
    void getProfit() {
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(HEART_ACE);

        dealerState = dealerState.draw(CLUB_TEN);
        dealerState = dealerState.draw(HEART_ACE);

        final State draw = playerState.compare(dealerState);

        assertThat(draw.getProfit()).isEqualTo(0);
    }
}

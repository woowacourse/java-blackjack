package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.gameresult.Lose;
import blakjack.domain.state.gameresult.Win;
import blakjack.domain.state.running.Init;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.Fixture.CLUB_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BustTest {

    private State myBust;
    private State dealerBust;

    @BeforeEach
    void setUp() {
        final PrivateArea myArea = new PrivateArea("칙촉");
        final Chip myChip = new Chip(1000);

        final PrivateArea dealerArea = new PrivateArea("딜러");
        final Chip dealerChip = new Chip(0);

        myBust = new Init(myArea, myChip);
        myBust = myBust.draw(CLUB_TEN);
        myBust = myBust.draw(CLUB_TEN);
        myBust = myBust.draw(CLUB_TEN);

        dealerBust = new Init(dealerArea, dealerChip);
        dealerBust = dealerBust.draw(CLUB_TEN);
        dealerBust = dealerBust.draw(CLUB_TEN);
        dealerBust = dealerBust.draw(CLUB_TEN);
    }

    @Test
    @DisplayName("카드를 더 받으려고 하면 예외발생")
    void exception() {
        assertThatThrownBy(() -> myBust.draw(CLUB_TEN))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("두 상태가 모두 버스트일 때 딜러가 승리한다.")
    void dealerWin() {
        assertThat(dealerBust.compare(myBust)).isExactlyInstanceOf(Win.class);
    }

    @Test
    @DisplayName("두 상태가 모두 버스트일 때 딜러가 승리한다.")
    void playerLose() {
        assertThat(myBust.compare(dealerBust)).isExactlyInstanceOf(Lose.class);
    }

    @Test
    @DisplayName("딜러가 버스트고 플레이어가 버스트가 아닌 경우")
    void lose() {
        final PrivateArea playerArea = new PrivateArea("촉촉");
        final Chip playerChip = new Chip(1000);

        State playerState = new Init(playerArea, playerChip);
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.draw(CLUB_TEN);
        playerState = playerState.stay();

        assertThat(dealerBust.compare(playerState)).isExactlyInstanceOf(Lose.class);
    }
}

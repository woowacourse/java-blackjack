package blackjack.model.betting;

import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static blackjack.model.PlayerFixture.NOT_BLACKJACK_21_PLAYER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingBoardTest {

    @Test
    @DisplayName("배팅 목록에 해당 플레이어의 베팅 정보가 존재하지 않을 경우 예외를 던진다.")
    void findBettingAccountBy() {
        BettingBoard bettingBoard = new BettingBoard(List.of(
                new BettingAccount(BLACKJACK_PLAYER.getPlayer(), new Money(0))));

        assertThatThrownBy(() -> bettingBoard.findBettingAccountBy(NOT_BLACKJACK_21_PLAYER.getPlayer()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 플레이어에 해당하는 베팅 정보가 존재하지 않습니다.");
    }
}

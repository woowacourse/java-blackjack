package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingBoxTest {
    @Test
    @DisplayName("게스트가 블랙잭으로 이겼을 때 상금을 잘 받는지 확인한다.")
    void checkPrizeMoneyBlackjack() {
        BettingBox bettingBox = new BettingBox();
        Player guest = new Guest("test", (p) -> HitFlag.Y);
        bettingBox.betGuest(guest, new BettingMoney(10000));
        assertThat(bettingBox.getPrizeMoney(guest, WinDrawLose.WIN, true))
                .isEqualTo(15000);
    }
    
    @Test
    @DisplayName("게스트가 블랙잭이 아니고 승리했을 때 상금을 잘 받는지 확인한다.")
    void checkPrizeMoneyNotBlackjack() {
        BettingBox bettingBox = new BettingBox();
        Player guest = new Guest("test", (p) -> HitFlag.Y);
        bettingBox.betGuest(guest, new BettingMoney(10000));
        assertThat(bettingBox.getPrizeMoney(guest, WinDrawLose.WIN, false))
                .isEqualTo(10000);
    }

    @Test
    @DisplayName("게스트가 무승부일 때 수익이 0인지 확인한다.")
    void checkPrizeMoneyDraw() {
        BettingBox bettingBox = new BettingBox();
        Player guest = new Guest("test", (p) -> HitFlag.Y);
        bettingBox.betGuest(guest, new BettingMoney(10000));
        assertThat(bettingBox.getPrizeMoney(guest, WinDrawLose.DRAW, true))
                .isEqualTo(0);
    }

    @Test
    @DisplayName("게스트가 졌을 때 베팅한 금액을 잃는지 확인한다.")
    void checkPrizeMoneyLose() {
        BettingBox bettingBox = new BettingBox();
        Player guest = new Guest("test", (p) -> HitFlag.Y);
        bettingBox.betGuest(guest, new BettingMoney(10000));
        assertThat(bettingBox.getPrizeMoney(guest, WinDrawLose.LOSE, true))
                .isEqualTo(-10000);
    }
}
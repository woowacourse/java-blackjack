package model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @DisplayName("0 원 이하의 배팅을 하면 예외 발생")
    @Test
    void testInvalidBettingMoney() {
        assertThatThrownBy(() ->
                new Betting(new Player("lily"), 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("승리 시 배팅 금액을 수익으로 배당")
    @Test
    void testProfitWhenWin() {
        Betting betting = new Betting(new Player("lily"), 10000);
        assertEquals(betting.getMoney(), betting.profit("승"));
    }

    @DisplayName("패배 or 버스트 시 배팅 금액은 소멸")
    @Test
    void testProfitWhenFail() {
        Betting betting = new Betting(new Player("lily"), 10000);
        assertEquals(-10000, betting.profit("패"));
    }
}

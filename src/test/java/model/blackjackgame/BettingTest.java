package model.blackjackgame;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.card.Card;
import model.card.Cards;
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

    @DisplayName("블랙잭인 경우 배팅 금액의 1.5배 배당")
    @Test
    void testProfitWhenBlackjack() {
        Cards cards = new Cards(
                List.of(new Card(JACK, DIAMOND), new Card(ACE, CLOVER))
        );
        Player player = new Player("lily", cards);
        Betting betting = new Betting(player, 10000);
        assertEquals(10000 * 1.5, betting.profit("블랙잭"));
    }
}

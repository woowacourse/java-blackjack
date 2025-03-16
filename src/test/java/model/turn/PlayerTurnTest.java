package model.turn;

import static org.junit.jupiter.api.Assertions.*;

import model.betting.Betting;
import model.card.Deck;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTurnTest {

    @Test
    void processHit() {
    }

    @Test
    @DisplayName("플레이어가 더블 다운을 했을 경우 패는 1장이 늘었는지 테스트")
    void 플레이어가_더블다운_했을_때_패를_한_장_받는지_테스트() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        Deck deck = Deck.of();
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        int addBetting = 5000;
        playerTurn.processDoubleDown(deck, addBetting);
        int expect = 1;
        int result = player.getHandCards().size();
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("플레이어가 더블 다운을 하고 이겼을 경우 수익 확인 테스트")
    void 플레이어가_더블_다운_후_승리했을_경우_수익_확인_테스트() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        Deck deck = Deck.of();
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        int addBetting = 5000;
        playerTurn.processDoubleDown(deck, addBetting);
        int expect = 15000;
        int result = betting.calculateWin();
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("플레이어가 걸 수 있는 최대 보험금액 계산 테스트")
    void getMaxInsuranceAmount() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        int expect = 5000;
        int result = playerTurn.getMaxInsuranceAmount();
        assertEquals(expect, result);
    }
}
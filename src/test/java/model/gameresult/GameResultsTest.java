package model.gameresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.bettingamount.BettingAmount;
import model.deck.Deck;
import model.deck.DeckFactory;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultsTest {

    private final Deck deck = new Deck(DeckFactory.initializeDeck());
    GameResults gameResults = new GameResults(
            Map.of("win", GameResult.WIN, "draw", GameResult.DRAW, "lose", GameResult.LOSE));
    Player win = new Player("win", deck, new BettingAmount(1000));
    Player draw = new Player("draw", deck, new BettingAmount(1000));
    Player lose = new Player("lose", deck, new BettingAmount(1000));
    Players players = new Players(List.of(win, lose, draw));

    @DisplayName("플레이어의 게임 결과와 베팅 금액에 따라 플레이어의 이익 금액을 반환한다.")
    @Test
    void calculatePlayerProfit_ByPlayer() {
        assertThat(gameResults.calculatePlayerProfit(win)).isEqualTo(1000);
        assertThat(gameResults.calculatePlayerProfit(draw)).isEqualTo(0);
        assertThat(gameResults.calculatePlayerProfit(lose)).isEqualTo(-1000);
    }

    @DisplayName("플레이어의 게임 결과와 베팅 금액에 따라 딜러의 이익 금액을 반환한다.")
    @Test
    void calculateDealerProfit() {
        assertThat(gameResults.calculateDealerProfit(players)).isEqualTo(0);
    }
}
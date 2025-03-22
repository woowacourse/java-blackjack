package model.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultsTest {
    private Dealer dealer;
    private Players players;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    void setUp() {
        //given
        dealer = new Dealer();
        players = Players.from(List.of("moda", "pobi", "nana", "mimi"));
        player1 = players.getPlayers().getFirst();
        player2 = players.getPlayers().get(1);
        player3 = players.getPlayers().get(2);
        player4 = players.getPlayers().getLast();
        dealer.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));
        player1.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player2.receiveCard(new Card(CardRank.THREE, CardSuit.CLOVER));
        player3.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player4.receiveCard(new Card(CardRank.FIVE, CardSuit.CLOVER));
    }

    @Test
    @DisplayName("한명의 딜러와 여러명의 플레이어가 있을 때 각각의 플레이어의 승패를 저장한다.")
    void 모든_플레이어의_승패를_저장한다() {
        //when
        WinningResults winningResults = WinningResults.of(players, dealer);

        //then
        assertThat(winningResults.getResults().get(player1)).isEqualTo(GameResult.LOSE);
        assertThat(winningResults.getResults().get(player2)).isEqualTo(GameResult.DRAW);
        assertThat(winningResults.getResults().get(player3)).isEqualTo(GameResult.WIN);
        assertThat(winningResults.getResults().get(player4)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러의 최종 승패를 결정한다.")
    void 딜러의_최종_승패를_결정한다() {
        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<GameResult, Integer> dealerResults = winningResults.decideDealerWinning();

        int loseCount = dealerResults.getOrDefault(GameResult.LOSE, 0);
        int drawCount = dealerResults.getOrDefault(GameResult.DRAW, 0);
        int winCount = dealerResults.getOrDefault(GameResult.WIN, 0);

        //then
        assertThat(loseCount).isEqualTo(2);
        assertThat(drawCount).isEqualTo(1);
        assertThat(winCount).isEqualTo(1);
    }
}

package domain;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.Judge;
import domain.result.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JudgeTest {

    private Judge judge;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        judge = new Judge();
        dealer = new Dealer();
        dealer.hit(new Card(CardNumber.FIVE, CardShape.HEART));
    }

    @DisplayName("플레이어가 딜러를 상대로 승리를 판단한다.")
    @Test
    void decidePlayerWinByDealer() {
        Player player = new Player("player");
        player.hit(new Card(CardNumber.KING, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players, dealer);
        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.WIN);
    }

    @DisplayName("플레이어가 딜러를 상대로 패배를 판단한다.")
    @Test
    void decidePlayerLoseByDealer() {
        Player player = new Player("player");
        player.hit(new Card(CardNumber.TWO, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players, dealer);
        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.LOSE);
    }

    @DisplayName("플레이어가 딜러를 상대로 무승부를 판단한다.")
    @Test
    void decidePlayerDrawByDealer() {
        Player player = new Player("player");
        player.hit(new Card(CardNumber.FIVE, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players, dealer);
        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.DRAW);
    }

    @DisplayName("플레이어가 딜러를 상대로 승리하면서 블랙잭인지 판단한다.")
    @Test
    void decidePlayerBlackJackByDealer() {
        Player player = new Player("player");
        player.hit(new Card(CardNumber.ACE, CardShape.HEART));
        player.hit(new Card(CardNumber.KING, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players, dealer);
        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.BLACK_JACK);
    }

    @DisplayName("딜러의 모든 승리 패배 무승부 상태를 반환한다.")
    @Test
    void decideDealerResult() {
        Player player1 = new Player("player1");
        player1.hit(new Card(CardNumber.KING, CardShape.HEART));
        Player player2 = new Player("player2");
        player2.hit(new Card(CardNumber.THREE, CardShape.CLOVER));
        Player player3 = new Player("player3");
        player3.hit(new Card(CardNumber.FIVE, CardShape.HEART));
        Players players = new Players(List.of(player1, player2, player3));

        judge.decideResult(players, dealer);
        Map<WinState, Integer> dealerResult = judge.getDealerResult();

        assertThat(dealerResult.get(WinState.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(WinState.LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(WinState.DRAW)).isEqualTo(1);
    }
}

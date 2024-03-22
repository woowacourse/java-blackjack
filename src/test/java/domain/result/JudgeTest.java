package domain;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.result.Judge;
import domain.result.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JudgeTest {

    private Judge judge;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        judge = new Judge();
        dealer = new Dealer();
        dealer.hit(Card.valueOf(CardNumber.FIVE, CardShape.HEART));
    }

    @DisplayName("플레이어가 딜러를 상대로 승리를 판단한다.")
    @Test
    void decidePlayerWinByDealer() {
        Player player = new Player("player");
        player.hit(Card.valueOf(CardNumber.SIX, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players.getPlayers(), dealer);
        assertThat(judge.getPlayersResult().get(player)).isEqualTo(WinState.WIN);
    }

    @DisplayName("플레이어가 딜러를 상대로 패배를 판단한다.")
    @Test
    void decidePlayerLoseByDealer() {
        Player player = new Player("player");
        player.hit(Card.valueOf(CardNumber.FOUR, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players.getPlayers(), dealer);
        assertThat(judge.getPlayersResult().get(player)).isEqualTo(WinState.LOSE);
    }

    @DisplayName("플레이어가 딜러를 상대로 무승부를 판단한다.")
    @Test
    void decidePlayerDrawByDealer() {
        Player player = new Player("player");
        player.hit(Card.valueOf(CardNumber.FIVE, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players.getPlayers(), dealer);
        assertThat(judge.getPlayersResult().get(player)).isEqualTo(WinState.DRAW);
    }

    @DisplayName("플레이어가 딜러를 상대로 승리하면서 블랙잭인지 판단한다.")
    @Test
    void decidePlayerBlackJackByDealer() {
        Player player = new Player("player");
        player.hit(Card.valueOf(CardNumber.ACE, CardShape.HEART));
        player.hit(Card.valueOf(CardNumber.KING, CardShape.HEART));
        Players players = new Players(List.of(player));
        judge.decideResult(players.getPlayers(), dealer);
        assertThat(judge.getPlayersResult().get(player)).isEqualTo(WinState.BLACK_JACK);
    }
}

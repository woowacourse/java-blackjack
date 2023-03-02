package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameResultTest {

    @Test
    @DisplayName("딜러와 플레이어의 게임 결과 확인: 플레이어가 지는 경우")
    void gameResult() {
        Players players = new Players(List.of(new Player("pobi", List.of(new Card(TrumpShape.CLOVER, TrumpNumber.TWO), new Card(TrumpShape.DIAMOND, TrumpNumber.TWO)))));
        Dealer dealer = new Dealer(List.of(new Card(TrumpShape.HEART, TrumpNumber.JACK), new Card(TrumpShape.SPADE, TrumpNumber.EIGHT)));
        players.getPlayers().get(0).isAbleToReceive();
        dealer.isAbleToReceive();
        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerResults()).isEqualTo(List.of(Result.WIN));
        assertThat(gameResult.getPlayerResult(players.getPlayers().get(0))).isEqualTo(Result.LOSE);
    }


}
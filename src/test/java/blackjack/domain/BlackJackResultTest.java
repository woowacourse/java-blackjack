package blackjack.domain;

import blackjack.domain.BlackJackResult;
import blackjack.domain.MatchResult;
import blackjack.domain.card.*;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.state.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackResultTest {

    @Test
    @DisplayName("게임 결과 생성")
    void createBlackJackResult() {
        Players players = new Players(Arrays.asList(new Player("pika", new Hit(new Cards())), new Player("air", new Hit(new Cards()))));
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(new Dealer(new Hit(new Cards()))));
        assertThat(blackJackResult).isNotNull();
    }

    @Test
    @DisplayName("딜러의 승패 결과 확인")
    void getResultSucceed() {
        Players players = new Players(Collections.singletonList(new Player("pika")));
        Dealer dealer = new Dealer();


        for (Player player : players.getPlayers()) {
            player.firstDraw(new Cards(Arrays.asList(new Card(Shape.SPADE, Denomination.JACK), new Card(Shape.SPADE, Denomination.ACE))));
        }
        dealer.firstDraw(new Cards(Arrays.asList(new Card(Shape.SPADE, Denomination.FIVE), new Card(Shape.SPADE, Denomination.ACE))));
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(dealer));
        Map<MatchResult, Integer> dealerResult = blackJackResult.getDealerResult();
        assertThat(dealerResult.get(MatchResult.WIN)).isEqualTo(0);
        assertThat(dealerResult.get(MatchResult.LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(MatchResult.DRAW)).isEqualTo(0);
    }
}

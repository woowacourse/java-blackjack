package blackjack.domain;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player(new Name("pobi"), Money.of(1000));
        dealer.receiveFirstHand(Arrays.asList(
                new Card(Pattern.HEART, Number.TEN),
                new Card(Pattern.HEART, Number.JACK)
        ));
        player.receiveFirstHand(Arrays.asList(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.CLOVER, Number.JACK)
        ));
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 스테이라면 플레이어 승리")
    void playerBlackjackAndDealerStay() {
        Player blackjackPlayer = new Player(new Name("brown"), Money.of(1000));
        blackjackPlayer.receiveFirstHand(Arrays.asList(
                new Card(Pattern.HEART, Number.TEN),
                new Card(Pattern.HEART, Number.ACE)
        ));
        dealer.stay();
        assertThat(dealer.judgePlayer(blackjackPlayer)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 버스트라면 플레이어 승리")
    void playerBlackjackAndDealerBust() {
        Player blackjackPlayer = new Player(new Name("brown"), Money.of(1000));
        blackjackPlayer.receiveFirstHand(Arrays.asList(
                new Card(Pattern.HEART, Number.TEN),
                new Card(Pattern.HEART, Number.ACE)
        ));
        dealer.receiveCard(new Card(Pattern.CLOVER, Number.TEN));
        assertThat(dealer.judgePlayer(blackjackPlayer)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘다 블랙잭 이라면 무승부")
    void playerBlackjackAndDealerBlackjack() {
        Player blackjackPlayer = new Player(new Name("brown"), Money.of(1000));
        blackjackPlayer.receiveFirstHand(Arrays.asList(
                new Card(Pattern.HEART, Number.TEN),
                new Card(Pattern.HEART, Number.ACE)
        ));
        Dealer blackjackDealer = new Dealer();
        blackjackDealer.receiveFirstHand(Arrays.asList(
                new Card(Pattern.HEART, Number.ACE),
                new Card(Pattern.HEART, Number.JACK)
        ));
        assertThat(blackjackDealer.judgePlayer(blackjackPlayer)).isEqualTo(GameResult.TIE);
    }

    @Test
    @DisplayName("플레이어가 스테이고 딜러가 블랙잭 이라면 플레이어 패배")
    void playerStayAndDealerBlackjack() {
        Dealer blackjackDealer = new Dealer();
        blackjackDealer.receiveFirstHand(Arrays.asList(
                new Card(Pattern.HEART, Number.ACE),
                new Card(Pattern.HEART, Number.JACK)
        ));
        assertThat(blackjackDealer.judgePlayer(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘다 버스트가 아닐 때 플레이어의 점수가 더 높으면 플레이어의 승리")
    void playerScoreBiggerThanDealerScoreTest() {
        player.receiveCard(new Card(Pattern.CLOVER, Number.ACE));
        player.stay();
        dealer.stay();
        assertThat(dealer.judgePlayer(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트, 플레이어는 스테이라면 플레이어 승리")
    void ifDealerBustedAndPlayerStayTest() {
        dealer.receiveCard(new Card(Pattern.CLOVER, Number.TWO));
        player.receiveCard(new Card(Pattern.CLOVER, Number.ACE));
        player.stay();
        assertThat(dealer.judgePlayer(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘다 버스트가 아닐 때 딜러의 점수가 더 높으면 플레이어의 패배")
    void dealerScoreBiggerThanPlayerScoreTest() {
        dealer.receiveCard(new Card(Pattern.CLOVER, Number.ACE));
        player.stay();
        assertThat(dealer.judgePlayer(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트고 딜러는 스테이라면 플레이어의 패배")
    void ifPlayerBustedAndDealerStayTest() {
        player.receiveCard(new Card(Pattern.HEART, Number.TWO));
        assertThat(dealer.judgePlayer(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘다 버스트라면 플레이어의 패배")
    void ifPlayerAndDealerBothBustedTest() {
        dealer.receiveCard(new Card(Pattern.CLOVER, Number.TWO));
        player.receiveCard(new Card(Pattern.HEART, Number.TWO));
        assertThat(dealer.judgePlayer(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 버스트가 아니고 점수가 같으면 무승부")
    void tieTest() {
        assertThat(dealer.judgePlayer(player)).isEqualTo(GameResult.TIE);
    }
}

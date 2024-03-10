package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.cards.gamercards.DealerCards;
import domain.cards.gamercards.PlayerCards;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    private Judge judge;

    @BeforeEach
    void setUp() {
        judge = new Judge();
    }

    @DisplayName("플레이어가 bust 일 경우 플레이어의 패배로 판단한다.")
    @Test
    void judgePlayerLoseWhenPlayerBust() {
        Player player = new Player("p",
                new PlayerCards(List.of(new Card(CardNumber.K, CardShape.SPADE),
                        new Card(CardNumber.K, CardShape.HEART),
                        new Card(CardNumber.K, CardShape.CLOVER))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.FIVE, CardShape.HEART))));
        Gamers gamers = new Gamers(List.of(player), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.LOSE);
    }

    @DisplayName("딜러가 bust 일 경우 플레이어의 승리로 판단한다.")
    @Test
    void judgePlayerWinWhenDealerBust() {
        Player player = new Player("p",
                new PlayerCards(List.of(new Card(CardNumber.FIVE, CardShape.HEART))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.K, CardShape.SPADE),
                new Card(CardNumber.K, CardShape.HEART),
                new Card(CardNumber.K, CardShape.CLOVER))));
        Gamers gamers = new Gamers(List.of(player), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.WIN);
    }

    @DisplayName("플레이어와 딜러 모두 bust 가 아닌 경우 플레이어가 높은 점수를 가지면 플레이어의 승리로 판단한다.")
    @Test
    void judgePlayerWinWhenNotBustHigherScore() {
        Player player = new Player("p", new PlayerCards(List.of(new Card(CardNumber.K, CardShape.SPADE))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.TWO, CardShape.SPADE))));
        Gamers gamers = new Gamers(List.of(player), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.WIN);
    }

    @DisplayName("플레이어와 딜러 모두 bust 가 아닌 경우 점수가 같다면 무승부로 판단한다.")
    @Test
    void judgePlayerDrawWhenNotBustSameScore() {
        Player player = new Player("p", new PlayerCards(List.of(new Card(CardNumber.FIVE, CardShape.SPADE))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.FIVE, CardShape.CLOVER))));
        Gamers gamers = new Gamers(List.of(player), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.DRAW);
    }

    @DisplayName("플레이어와 딜러 모두 bust 가 아닌 경우 딜러가 높은 점수를 가지면 플레이어의 패배로 판단한다.")
    @Test
    void judgePlayerLoseWhenNotBustLowerScore() {
        Player player = new Player("p", new PlayerCards(List.of(new Card(CardNumber.FIVE, CardShape.SPADE))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.K, CardShape.CLOVER))));
        Gamers gamers = new Gamers(List.of(player), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getPlayerResult().get(player)).isEqualTo(WinState.LOSE);
    }

    @DisplayName("N명의 플레이어가 패배한 경우 딜러는 N번의 승리를 얻는다.")
    @Test
    void judgeDealerWinWhenPlayerLose() {
        Player player1 = new Player("p1", new PlayerCards(List.of(new Card(CardNumber.TWO, CardShape.SPADE))));
        Player player2 = new Player("p2", new PlayerCards(List.of(new Card(CardNumber.THREE, CardShape.SPADE))));
        Player player3 = new Player("p3", new PlayerCards(List.of(new Card(CardNumber.FOUR, CardShape.SPADE))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.K, CardShape.CLOVER))));
        Gamers gamers = new Gamers(List.of(player1, player2, player3), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getDealerResult().get(WinState.WIN)).isEqualTo(3);
    }

    @DisplayName("N명의 플레이어가 승리한 경우 딜러는 N번의 패배를 얻는다.")
    @Test
    void judgeDealerLoseWhenPlayerWin() {
        Player player1 = new Player("p1", new PlayerCards(List.of(new Card(CardNumber.J, CardShape.SPADE))));
        Player player2 = new Player("p2", new PlayerCards(List.of(new Card(CardNumber.Q, CardShape.SPADE))));
        Player player3 = new Player("p3", new PlayerCards(List.of(new Card(CardNumber.K, CardShape.SPADE))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.FIVE, CardShape.CLOVER))));
        Gamers gamers = new Gamers(List.of(player1, player2, player3), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getDealerResult().get(WinState.LOSE)).isEqualTo(3);
    }

    @DisplayName("N명의 플레이어가 무승부인 경우 딜러는 N번의 무승부를 얻는다.")
    @Test
    void judgeDealerDrawWhenPlayerDraw() {
        Player player1 = new Player("p1", new PlayerCards(List.of(new Card(CardNumber.K, CardShape.HEART))));
        Player player2 = new Player("p2", new PlayerCards(List.of(new Card(CardNumber.K, CardShape.DIAMOND))));
        Player player3 = new Player("p3", new PlayerCards(List.of(new Card(CardNumber.K, CardShape.SPADE))));
        Dealer dealer = new Dealer(new DealerCards(List.of(new Card(CardNumber.K, CardShape.CLOVER))));
        Gamers gamers = new Gamers(List.of(player1, player2, player3), dealer);

        judge.decideResult(gamers);

        assertThat(judge.getDealerResult().get(WinState.DRAW)).isEqualTo(3);
    }
}

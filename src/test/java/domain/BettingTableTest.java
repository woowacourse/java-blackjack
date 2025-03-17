package domain;

import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTableTest {
    @DisplayName("유저가 승리했을 때 배팅한만큼 돈을 얻는다.")
    @Test
    void test() {
        // given
        Player player = new Player("수양");
        player.addTrumpCard(new Card(CardShape.CLOVER, CardRank.FIVE));
        player.addTrumpCard(new Card(CardShape.HEART, CardRank.FIVE));

        Dealer dealer = new Dealer();
        dealer.addTrumpCard(new Card(CardShape.SPADE, CardRank.TWO));
        dealer.addTrumpCard(new Card(CardShape.CLOVER, CardRank.TWO));

        Participants participants = new Participants(List.of(player, dealer));
        BettingTable bettingTable = new BettingTable();
        Score score = new Score(participants);

        bettingTable.betMoney(player, 1000);
        Map<User, GameResult> userGameResultMap = score.calculatePlayerScore();

        // when
        Map<User, Long> rewards = bettingTable.calculateRewards(userGameResultMap, dealer);

        // then
        Assertions.assertThat(rewards.get(player)).isEqualTo(1000);
    }

    @DisplayName("유저가 패배했을 때 배팅한만큼 돈을 잃는다.")
    @Test
    void test2() {
        // given
        Player player = new Player("수양");
        player.addTrumpCard(new Card(CardShape.SPADE, CardRank.TWO));
        player.addTrumpCard(new Card(CardShape.CLOVER, CardRank.TWO));

        Dealer dealer = new Dealer();
        dealer.addTrumpCard(new Card(CardShape.CLOVER, CardRank.FIVE));
        dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.FIVE));

        Participants participants = new Participants(List.of(player, dealer));
        BettingTable bettingTable = new BettingTable();
        Score score = new Score(participants);

        bettingTable.betMoney(player, 1000);
        Map<User, GameResult> userGameResultMap = score.calculatePlayerScore();

        // when
        Map<User, Long> rewards = bettingTable.calculateRewards(userGameResultMap, dealer);

        // then
        Assertions.assertThat(rewards.get(player)).isEqualTo(-1000);
    }

    @DisplayName("유저가 블랙잭으로 승리했을 때 배팅한 돈의 1.5배를 얻는다.")
    @Test
    void test3() {
        // given
        Player player = new Player("수양");
        player.addTrumpCard(new Card(CardShape.SPADE, CardRank.Q));
        player.addTrumpCard(new Card(CardShape.CLOVER, CardRank.ACE));

        Dealer dealer = new Dealer();
        dealer.addTrumpCard(new Card(CardShape.CLOVER, CardRank.FIVE));
        dealer.addTrumpCard(new Card(CardShape.HEART, CardRank.FIVE));

        Participants participants = new Participants(List.of(player, dealer));
        BettingTable bettingTable = new BettingTable();
        Score score = new Score(participants);

        bettingTable.betMoney(player, 1000);
        Map<User, GameResult> userGameResultMap = score.calculatePlayerScore();

        // when
        Map<User, Long> rewards = bettingTable.calculateRewards(userGameResultMap, dealer);

        // then
        Assertions.assertThat(rewards.get(player)).isEqualTo(1500);
    }
}
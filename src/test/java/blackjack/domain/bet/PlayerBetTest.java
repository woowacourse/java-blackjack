package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerBetTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        PlayersBet playerBet = new PlayersBet();
        assertThat(playerBet).isNotNull();
    }

    @Test
    @DisplayName("플레이어와 배팅금 추가 테스트")
    void testAdd() {
        PlayersBet playerBet = new PlayersBet();
        Player player = Player.of("pobi");
        Money money = new Money(10000);

        playerBet.add(player, money);

        assertThat(playerBet.get(player)).isEqualTo(money);
    }

    @Test
    @DisplayName("플레이어 승리 수익 테스트")
    void calculate_player_win_profit() {
        Map<Player, Result> judgeResult = new HashMap<>();
        Participant dealer = Dealer.createDefaultNameDealer();
        Player player1 = Player.of("player1");
        judgeResult.put(player1, Result.WIN);

        PlayersBet playersBet = new PlayersBet();
        playersBet.add(player1, new Money(10000));
        Map<Participant, Money> participantMoneyTable = playersBet.calculateProfit(judgeResult, dealer);

        assertThat(participantMoneyTable.get(player1).getValue()).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어 무승부 수익 테스트")
    void calculate_player_tie_profit() {
        Map<Player, Result> judgeResult = new HashMap<>();
        Participant dealer = Dealer.createDefaultNameDealer();
        Player player1 = Player.of("player1");
        judgeResult.put(player1, Result.TIE);

        PlayersBet playersBet = new PlayersBet();
        playersBet.add(player1, new Money(10000));
        Map<Participant, Money> participantMoneyTable = playersBet.calculateProfit(judgeResult, dealer);

        assertThat(participantMoneyTable.get(player1).getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어 패배 수익 테스트")
    void calculate_player_lose_profit() {
        Map<Player, Result> judgeResult = new HashMap<>();
        Participant dealer = Dealer.createDefaultNameDealer();
        Player player1 = Player.of("player1");
        judgeResult.put(player1, Result.LOSE);

        PlayersBet playersBet = new PlayersBet();
        playersBet.add(player1, new Money(10000));
        Map<Participant, Money> participantMoneyTable = playersBet.calculateProfit(judgeResult, dealer);

        assertThat(participantMoneyTable.get(player1).getValue()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어 블랙잭 수익 테스트")
    void calculate_player_blackjack_profit() {
        Map<Player, Result> judgeResult = new HashMap<>();
        Participant dealer = Dealer.createDefaultNameDealer();
        Player player1 = Player.of("player1");
        judgeResult.put(player1, Result.Blackjack);

        PlayersBet playersBet = new PlayersBet();
        playersBet.add(player1, new Money(10000));
        Map<Participant, Money> participantMoneyTable = playersBet.calculateProfit(judgeResult, dealer);

        assertThat(participantMoneyTable.get(player1).getValue()).isEqualTo(5000);
    }
}

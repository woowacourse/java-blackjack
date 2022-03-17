package blackjack.domain;

import blackjack.domain.card.*;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerOutcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    Player player = new Player("플레이어", 10000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.EIGHT), Card.valueOf(Suit.CLUB, Denomination.EIGHT))));

    @Test
    @DisplayName("딜러 숫자보다 플레이어 숫자가 큰 경우 베팅 금액을 갖는다.")
    void winTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        result.put(PlayerOutcome.WIN, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getBettingMoney(player, PlayerOutcome.WIN)).isEqualTo(10000);
    }

    @Test
    @DisplayName("딜러 숫자보다 플레이어 숫자가 작은 경우 베팅 금액을 잃는다.")
    void loseTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        result.put(PlayerOutcome.LOSE, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getBettingMoney(player, PlayerOutcome.LOSE)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("딜러 숫자와 플레이어 숫자가 같은 경우 베팅 금액을 잃는다.")
    void drawTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        result.put(PlayerOutcome.DRAW, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getBettingMoney(player, PlayerOutcome.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우 베팅 금액의 1.5배를 받는다.")
    void blackjackTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        result.put(PlayerOutcome.BLACKJACK_WIN, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getBettingMoney(player, PlayerOutcome.BLACKJACK_WIN)).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어가 승리한 경우 딜러는 돈을 잃는다.")
    void getDealerMoneyWhenPlayerWinTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        result.put(PlayerOutcome.WIN, List.of(player));

        GameResult gameResult = new GameResult(result);
        Map<Participant, Integer> playerMoney = new HashMap<>();
        playerMoney.put(player, 10000);

        assertThat(gameResult.getDealerMoney(player, PlayerOutcome.WIN)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 패배한 경우 딜러는 돈을 얻는다.")
    void getDealerMoneyWhenPlayerLoseTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        result.put(PlayerOutcome.LOSE, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getDealerMoney(player, PlayerOutcome.LOSE)).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어와 무승부인 경우 딜러는 0원이다.")
    void getDealerMoneyWhenPlayerDrawTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();

        result.put(PlayerOutcome.DRAW, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getDealerMoney(player, PlayerOutcome.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 딜러는 1.5배만큼 돈을 잃는다.")
    void getDealerMoneyWhenPlayerBlackjackTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
       result.put(PlayerOutcome.BLACKJACK_WIN, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getDealerMoney(player, PlayerOutcome.BLACKJACK_WIN)).isEqualTo(-15000);
    }

    @Test
    @DisplayName("딜러의 최종 수익을 구한다.")
    void getDealerTotalMoney() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        Player player1 = new Player("플레이어1", 10000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.EIGHT), Card.valueOf(Suit.CLUB, Denomination.EIGHT))));

        Player player2 = new Player("플레이어2", 20000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.THREE), Card.valueOf(Suit.CLUB, Denomination.TWO))));

        Player player3 = new Player("플레이어3",15000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.ACE), Card.valueOf(Suit.CLUB, Denomination.FOUR))));

        result.put(PlayerOutcome.WIN, List.of(player1));
        result.put(PlayerOutcome.LOSE, List.of(player2));
        result.put(PlayerOutcome.DRAW, List.of(player3));

        GameResult gameResult = new GameResult(result);
        assertThat(gameResult.getDealerTotalMoney()).isEqualTo(10000);
    }

    @Test
    @DisplayName("딜러의 최종 수익을 구한다.(총 수익이 마이너스인 경우)")
    void getDealerNegativeTotalMoney() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        Player player1 = new Player("플레이어1", 10000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.EIGHT), Card.valueOf(Suit.CLUB, Denomination.EIGHT))));

        Player player2 = new Player("플레이어2", 20000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.THREE), Card.valueOf(Suit.CLUB, Denomination.TWO))));

        Player player3 = new Player("플레이어3", 15000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.ACE), Card.valueOf(Suit.CLUB, Denomination.FOUR))));

        Player player4 = new Player("플레이어4", 30000, HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.NINE), Card.valueOf(Suit.CLUB, Denomination.FIVE))));


        result.put(PlayerOutcome.WIN, List.of(player1, player4));
        result.put(PlayerOutcome.LOSE, List.of(player2));
        result.put(PlayerOutcome.DRAW, List.of(player3));

        GameResult gameResult = new GameResult(result);
        assertThat(gameResult.getDealerTotalMoney()).isEqualTo(-20000);
    }

}

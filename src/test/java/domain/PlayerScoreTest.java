package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerScoreTest {

    @Test
    @DisplayName("플레이어 이름과 수익을 입력하면 playerScore 객체가 정상적으로 생성된다")
    void generatePlayerScore() {
        Name name = Name.generatePlayerName("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.KING)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);
        Profit profit = Profit.winnerProfit(player);

        assertDoesNotThrow(() -> new PlayerScore(player.getName(), profit));
    }

    //
//    @Test
//    @DisplayName("플레이어 이름과 게임 결과를 입력하면 PlayerScore가 정상적으로 생성된다.")
//    void createPlayerScore() {
//        String name = "roy";
//        GameResult gameResult = GameResult.WIN;
//
//        PlayerScore playerScore = new PlayerScore(name, gameResult);
//
//        assertDoesNotThrow(() -> playerScore);
//    }
}

package domain;

import domain.constant.GamerResult;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BettingTableTest {
    @Test
    @DisplayName("생성자의 Map과 totalProfit Map의 크기가 다르면 예외를 발생한다")
    void mapSizeIsDifferent() {
        BettingTable bettingTable = new BettingTable(Map.of(
                "test1", new BettingAmount(1000),
                "test2", new BettingAmount(2000)));
        Assertions.assertThatThrownBy(() -> bettingTable.getPlayersProfit(Map.of("test1", GamerResult.WIN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("생성자로 입력받은 Map과 크기가 다릅니다");
    }

    @Nested
    @DisplayName("플레이어 이익 검증 테스트")
    class getPlayerProfit {
        @Test
        @DisplayName("총 플레이어가 본 손해 만큼 딜러는 이익을 본다")
        void variousPlayersCase() {
            BettingTable bettingTable = new BettingTable(Map.of(
                    "test1", new BettingAmount(1000),
                    "test2", new BettingAmount(2000),
                    "test3", new BettingAmount(3000)));
            Assertions.assertThat(bettingTable.getPlayersProfit(Map.of(
                            "test1", GamerResult.LOSE,
                            "test2", GamerResult.WIN,
                            "test3", GamerResult.LOSE)))
                    .isEqualTo(Map.of(
                            "test1", -1000D,
                            "test2", 2000D,
                            "test3", -3000D
                    ));
        }

        @Test
        @DisplayName("이긴 플레이어가 한 명인 경우")
        void onePlayerWinCase() {
            BettingTable bettingTable = new BettingTable(Map.of("test", new BettingAmount(1000)));
            Assertions.assertThat(bettingTable.getPlayersProfit(Map.of("test", GamerResult.WIN)))
                    .isEqualTo(Map.of(
                            "test", 1000D
                    ));
        }

        @Test
        @DisplayName("이긴 플레이어가 여러 명인 경우")
        void variousPlayersWinCase() {
            BettingTable bettingTable = new BettingTable(Map.of(
                    "test1", new BettingAmount(1000),
                    "test2", new BettingAmount(2000),
                    "test3", new BettingAmount(3000)));
            Assertions.assertThat(bettingTable.getPlayersProfit(Map.of(
                            "test1", GamerResult.WIN,
                            "test2", GamerResult.WIN,
                            "test3", GamerResult.WIN)))
                    .isEqualTo(Map.of(
                            "test1", 1000D,
                            "test2", 2000D,
                            "test3", 3000D
                    ));
        }

        @Test
        @DisplayName("진 플레이어가 한 명인 경우")
        void onePlayerLoseCase() {
            BettingTable bettingTable = new BettingTable(Map.of("test", new BettingAmount(1000)));
            Assertions.assertThat(bettingTable.getPlayersProfit(Map.of("test", GamerResult.LOSE)))
                    .isEqualTo(Map.of(
                            "test", -1000D
                    ));
        }

        @Test
        @DisplayName("진 플레이어가 여러 명인 경우")
        void variousPlayersLoseCase() {
            BettingTable bettingTable = new BettingTable(Map.of(
                    "test1", new BettingAmount(1000),
                    "test2", new BettingAmount(2000),
                    "test3", new BettingAmount(3000)));
            Assertions.assertThat(bettingTable.getPlayersProfit(Map.of(
                            "test1", GamerResult.LOSE,
                            "test2", GamerResult.LOSE,
                            "test3", GamerResult.LOSE)))
                    .isEqualTo(Map.of(
                            "test1", -1000D,
                            "test2", -2000D,
                            "test3", -3000D
                    ));
        }
    }

    @Nested
    @DisplayName("딜러 이익 검증 테스트")
    class getDealerProfit {
        @Test
        @DisplayName("총 플레이어가 본 손해 만큼 딜러는 이익을 본다")
        void variousPlayersCase() {
            BettingTable bettingTable = new BettingTable(Map.of(
                    "test1", new BettingAmount(1000),
                    "test2", new BettingAmount(2000),
                    "test3", new BettingAmount(3000)));
            Assertions.assertThat(bettingTable.getDealerProfit(Map.of(
                            "test1", GamerResult.LOSE,
                            "test2", GamerResult.WIN,
                            "test3", GamerResult.LOSE)))
                    .isEqualTo(2000D);
        }

        @Test
        @DisplayName("이긴 플레이어가 한 명인 경우")
        void onePlayerWinCase() {
            BettingTable bettingTable = new BettingTable(Map.of("test", new BettingAmount(1000)));
            Assertions.assertThat(bettingTable.getDealerProfit(Map.of("test", GamerResult.WIN)))
                    .isEqualTo(-1000D);
        }

        @Test
        @DisplayName("플레이어가 여러 명인 경우")
        void variousPlayersWinCase() {
            BettingTable bettingTable = new BettingTable(Map.of(
                    "test1", new BettingAmount(1000),
                    "test2", new BettingAmount(2000),
                    "test3", new BettingAmount(3000)));
            Assertions.assertThat(bettingTable.getDealerProfit(Map.of(
                            "test1", GamerResult.WIN,
                            "test2", GamerResult.WIN,
                            "test3", GamerResult.WIN)))
                    .isEqualTo(-6000D);
        }

        @Test
        @DisplayName("플레이어가 한 명인 경우")
        void onePlayerLoseCase() {
            BettingTable bettingTable = new BettingTable(Map.of("test", new BettingAmount(1000)));
            Assertions.assertThat(bettingTable.getDealerProfit(Map.of("test", GamerResult.LOSE)))
                    .isEqualTo(1000D);
        }

        @Test
        @DisplayName("플레이어가 여러 명인 경우")
        void variousPlayersLoseCase() {
            BettingTable bettingTable = new BettingTable(Map.of(
                    "test1", new BettingAmount(1000),
                    "test2", new BettingAmount(2000),
                    "test3", new BettingAmount(3000)));
            Assertions.assertThat(bettingTable.getDealerProfit(Map.of(
                            "test1", GamerResult.LOSE,
                            "test2", GamerResult.LOSE,
                            "test3", GamerResult.LOSE)))
                    .isEqualTo(6000D);
        }
    }
}
